package com.nps.AppNps.loadProces;

import com.nps.AppNps.Data.ConsultaResultado;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Component;

import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Component
public class CsvToSqlServerBPulse_Invitation_Export {
    private String jdbcUrl;

    private String inputFilePathwm_bPulse_Invitation_Export;

    private String tableNamebPulse_bPulse_Invitation_Export;

    private String logFilename = "BPulse_Invitation_Export.log";

    public CsvToSqlServerBPulse_Invitation_Export() {
        loadProperties();
    }

    private void loadProperties() {
        Properties properties = new Properties();
        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");
            if (input == null) {
                System.err.println("No se pudo encontrar el archivo de propiedades.");
                return;
            }
            properties.load(input);
            this.inputFilePathwm_bPulse_Invitation_Export = properties.getProperty("inputFilePathwm_bPulse_Invitation_Export");
            this.tableNamebPulse_bPulse_Invitation_Export = properties.getProperty("tableNamebPulse_bPulse_Invitation_Export");
            this.jdbcUrl = properties.getProperty("jdbcUrl");
            input.close();
        } catch (Exception e) {
            System.err.println("Error al leer el archivo de propiedades: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void convertCsvToSqlServer() {
        Connection connection = null;
        try {
            CSVReader csvReader = new CSVReader(new FileReader(this.inputFilePathwm_bPulse_Invitation_Export));
            try {
                connection = DriverManager.getConnection(this.jdbcUrl);
                String[] headers = csvReader.readNext();
                String insertionSql = buildInsertionSql(headers);
                PreparedStatement preparedStatement = connection.prepareStatement(insertionSql);
                try {
                    String[] row;
                    while ((row = csvReader.readNext()) != null) {
                        setParameters(preparedStatement, row, headers.length);
                        try {
                            preparedStatement.executeUpdate();
                            System.out.println("Row inserted successfully.");
                        } catch (SQLException e) {
                            logErrorRecord(row);
                        }
                    }
                    System.out.println("Data successfully loaded into SQL Server.");
                    preparedStatement.close();
                } catch (Throwable throwable) {
                    if (preparedStatement != null) {
                        try {
                            preparedStatement.close();
                        } catch (Throwable throwable1) {
                            throwable.addSuppressed(throwable1);
                        }
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
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void logErrorRecord(String[] values) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.logFilename, true))) {
            for (String value : values) {
                writer.write(value + ",");
            }
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String buildInsertionSql(String[] headers) {
        String sql = "INSERT INTO " + this.tableNamebPulse_bPulse_Invitation_Export + " VALUES (";
        for (int i = 0; i < headers.length; i++) {
            sql += (i == 0) ? "?" : ", ?";
        }
        sql += ")";
        return sql;
    }

    private void setParameters(PreparedStatement preparedStatement, String[] values, int expectedLength) throws SQLException {
        for (int i = 0; i < expectedLength; i++) {
            if (i < values.length) {
                preparedStatement.setString(i + 1, values[i]);
            } else {
                preparedStatement.setNull(i + 1, Types.VARCHAR);
            }
        }
    }

    public List<ConsultaResultado> realizarConsultas(LocalDate fechaConsulta) {
        List<ConsultaResultado> resultados = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
            resultados.add(consultarTabla(connection, tableNamebPulse_bPulse_Invitation_Export, fechaConsulta));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultados;
    }

    private ConsultaResultado consultarTabla(Connection connection, String tableName, LocalDate fechaConsulta) throws SQLException {
        String sql = "SELECT COUNT(*) FROM " + tableName + " WHERE Interaction_Date_Timestamp = ?";
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
