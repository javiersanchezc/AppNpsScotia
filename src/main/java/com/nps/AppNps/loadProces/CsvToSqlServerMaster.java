package com.nps.AppNps.loadProces;

import com.nps.AppNps.Data.ConsultaResultado;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CsvToSqlServerMaster {

    private String jdbcUrl;
    private String inputFilePath;
    private String tableName;
    private String errorFilePath;
    private String logFilename;

    public CsvToSqlServerMaster(String jdbcUrl, String inputFilePath, String tableName, String errorFilePath, String logFilename) {
        this.jdbcUrl = jdbcUrl;
        this.inputFilePath = inputFilePath;
        this.tableName = tableName;
        this.errorFilePath = errorFilePath;
        this.logFilename = logFilename;
    }
    public void convertCsvToSqlServer() {
        try {
            Connection connection = DriverManager.getConnection(this.jdbcUrl);
            try {
                CSVReader csvReader = new CSVReader(new FileReader(this.inputFilePath));
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
        String sql = "INSERT INTO " + this.tableName + " VALUES (";
        for (int i = 0; i < headers.length; i++) {
            sql = sql + ((i == 0) ? "?" : ", ?");
        }
        sql = sql + ")";
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
    public List<com.nps.AppNps.Data.ConsultaResultado> realizarConsultas(LocalDate fechaConsulta) {
        List<com.nps.AppNps.Data.ConsultaResultado> resultados = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
            resultados.add(consultarTabla(connection, tableName, fechaConsulta));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultados;
    }
    private com.nps.AppNps.Data.ConsultaResultado consultarTabla(Connection connection, String tableName, LocalDate fechaConsulta) throws SQLException {
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
