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
import java.util.List;
import java.util.Properties;
@Component
public class CsvToSqlServerCPulseCallBacksExport {
    private String jdbcUrl;

    private String inputFilePathwm_cPulse_callback_export;

    private String tableNamecPulse_Callbacks_Export;

    private String errorFilePath;
    private String logFilename ="C:/data/CPulseCallBacksExport.log";

    public CsvToSqlServerCPulseCallBacksExport() {
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
                this.inputFilePathwm_cPulse_callback_export = properties.getProperty("inputFilePathwm_cPulse_callback_export");
                this.tableNamecPulse_Callbacks_Export = properties.getProperty("tableNamecPulse_Callbacks_Export");
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
                CSVReader csvReader = new CSVReader(new FileReader(this.inputFilePathwm_cPulse_callback_export));
                try {
                    String[] headers = csvReader.readNext();
                    String insertionSql = buildInsertionSql(headers);
                    PreparedStatement preparedStatement = connection.prepareStatement(insertionSql);
                    try {
                        String[] row;
                        while ((row = csvReader.readNext()) != null) {
                            try {
                                setParameters(preparedStatement, headers, row);
                                preparedStatement.executeUpdate();
                            } catch (SQLException e) {
                                e.printStackTrace();
                                logErrorRecord(row);
                            }
                        }
                        System.out.println("Data successfully loaded into SQL Server.");
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private String buildInsertionSql(String[] headers) {
        String sql = "INSERT INTO " + this.tableNamecPulse_Callbacks_Export + " VALUES (";
        for (int i = 0; i < headers.length; i++) {
            sql = sql + ((i == 0) ? "?" : ", ?");

        }
        sql = sql + ")";

        System.out.println("sql = " + sql);
        return sql;
    }

    private void setParameters(PreparedStatement preparedStatement, String[] headers, String[] values) throws SQLException {
        // Si los valores son menores que los headers, completamos con nulls
        String[] extendedValues = new String[headers.length];
        for (int i = 0; i < headers.length; i++) {
            if (i < values.length) {
                extendedValues[i] = values[i];
            } else {
                extendedValues[i] = null;
            }
        }

        for (int i = 0; i < extendedValues.length; i++) {
            System.out.println("extendedValues[i] = " + extendedValues[i]);
            System.out.println("i = " + i);
            if (extendedValues[i] == null) {
                preparedStatement.setNull(i + 1, java.sql.Types.VARCHAR);
            } else {
                preparedStatement.setString(i + 1, extendedValues[i]);
            }
        }
    }



    private void logErrorRecord(String[] values) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.logFilename, true));
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
            resultados.add(consultarTabla(connection, tableNamecPulse_Callbacks_Export, fechaConsulta));
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
