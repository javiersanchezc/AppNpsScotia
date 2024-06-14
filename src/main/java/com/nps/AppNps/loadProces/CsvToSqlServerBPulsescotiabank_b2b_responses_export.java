package com.nps.AppNps.loadProces;

import com.nps.AppNps.Data.ConsultaResultado;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;
@Component
public class CsvToSqlServerBPulsescotiabank_b2b_responses_export {
    private String jdbcUrl;

    private String inputFilePathwm_bPulse_Response_Export;

    private String tableNamebPulse_bPulse_Response_Export;

    private String errorFilePath;

    public CsvToSqlServerBPulsescotiabank_b2b_responses_export() {
        loadProperties();
    }

    private void loadProperties() {
        Properties properties = new Properties();
        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");
            try {
                if (input == null) {
                    System.err.println("No se pudo encontrar el archivo de propiedades.");
                    if (input != null)
                        input.close();
                    return;
                }
                properties.load(input);
                this.inputFilePathwm_bPulse_Response_Export = properties.getProperty("inputFilePathwm_bPulse_Response_Export");
                this.tableNamebPulse_bPulse_Response_Export = properties.getProperty("tableNamebPulse_bPulse_Response_Export");
                this.jdbcUrl = properties.getProperty("jdbcUrl");
                this.errorFilePath = properties.getProperty("errorFilePath");
                if (input != null)
                    input.close();
            } catch (Throwable throwable) {
                if (input != null)
                    try {
                        input.close();
                    } catch (Throwable throwable1) {
                        throwable.addSuppressed(throwable1);
                    }
                throw throwable;
            }
        } catch (Exception e) {
            System.err.println("Error al leer el archivo de propiedades: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void convertCsvToSqlServer() {
        try {
            Connection connection = DriverManager.getConnection(this.jdbcUrl);
            try {
                CSVReader csvReader = new CSVReader(new FileReader(this.inputFilePathwm_bPulse_Response_Export));
                try {
                    String[] headers = csvReader.readNext();
                    String insertionSql = buildInsertionSql(headers);
                    PreparedStatement preparedStatement = connection.prepareStatement(insertionSql);
                    try {
                        String[] row;
                        while ((row = csvReader.readNext()) != null) {
                            try {
                                row[0] = row[0].trim();
                                row[0] = Pattern.compile("[^\\w\\s]").matcher(row[0]).replaceAll("");
                                if (!isNumeric(row[0])) {
                                    System.err.println("El primer campo de la l" + Arrays.toString((Object[])row) + " no es numSaltando ");
                                    continue;
                                }
                                setParameters(preparedStatement, row, headers.length);
                                preparedStatement.executeUpdate();
                                System.out.println("Fila insertada exitosamente.");
                            } catch (Exception e) {
                                e.printStackTrace();
                                logErrorRecord(row);
                            }
                        }
                        System.out.println("Datos cargados exitosamente en SQL Server.");
                        if (preparedStatement != null)
                            preparedStatement.close();
                    } catch (Throwable throwable) {
                        if (preparedStatement != null)
                            try {
                                preparedStatement.close();
                            } catch (Throwable throwable1) {
                                throwable.addSuppressed(throwable1);
                            }
                        throw throwable;
                    }
                    csvReader.close();
                } catch (Throwable throwable) {
                    try {
                        csvReader.close();
                    } catch (Throwable throwable1) {
                        throwable.addSuppressed(throwable1);
                    }
                    throw throwable;
                }
                if (connection != null)
                    connection.close();
            } catch (Throwable throwable) {
                if (connection != null)
                    try {
                        connection.close();
                    } catch (Throwable throwable1) {
                        throwable.addSuppressed(throwable1);
                    }
                throw throwable;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException error) {
            error.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isDecimal(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String buildInsertionSql(String[] headers) {
        String sql = "INSERT INTO " + this.tableNamebPulse_bPulse_Response_Export + " VALUES (";
        for (int i = 0; i < headers.length; i++)
            sql = sql + ((i == 0) ? "?" : ", ?");
        sql = sql + ")";
        return sql;
    }

    private void setParameters(PreparedStatement preparedStatement, String[] values, int expectedLength) throws SQLException {
        for (int i = 0; i < expectedLength; i++) {
            if (i < values.length) {
                preparedStatement.setString(i + 1, values[i]);
            } else {
                preparedStatement.setNull(i + 1, 12);
            }
        }
    }

    private void logErrorRecord(String[] values) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.errorFilePath, true));
            try {
                for (String value : values)
                    writer.write(value + ",");
                writer.newLine();
                writer.close();
            } catch (Throwable throwable) {
                try {
                    writer.close();
                } catch (Throwable throwable1) {
                    throwable.addSuppressed(throwable1);
                }
                throw throwable;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<ConsultaResultado> realizarConsultas(LocalDate fechaConsulta) {
        List<ConsultaResultado> resultados = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
            resultados.add(consultarTabla(connection, tableNamebPulse_bPulse_Response_Export, fechaConsulta));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultados;
    }

    private ConsultaResultado consultarTabla(Connection connection, String tableName, LocalDate fechaConsulta) throws SQLException {
        String sql = "SELECT COUNT(*) FROM " + tableName + " WHERE Response_Date_EST = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDate(1, java.sql.Date.valueOf(fechaConsulta));
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                long cantidadDatos = resultSet.getLong(1);
                return new ConsultaResultado(LocalDateTime.now(), tableName, cantidadDatos);
            }
        }
    }
}
