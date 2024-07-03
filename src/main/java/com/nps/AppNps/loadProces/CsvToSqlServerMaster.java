package com.nps.AppNps.loadProces;

import com.nps.AppNps.Data.ConsultaResultado;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
            if (!Files.exists(Paths.get(this.inputFilePath))) {
                System.err.println("El archivo no existe: " + this.inputFilePath);
                return;
            }

            Connection connection = DriverManager.getConnection(this.jdbcUrl);
            try (CSVReader csvReader = new CSVReader(new FileReader(this.inputFilePath))) {
                String[] headers = csvReader.readNext();
                String insertionSql = buildInsertionSql(headers);
                PreparedStatement preparedStatement = connection.prepareStatement(insertionSql);
                String[] row;

                while ((row = csvReader.readNext()) != null) {
                    try {
                        setParameters(preparedStatement, headers, row);
                        System.out.println("Executing: " + preparedStatement.toString());
                        preparedStatement.executeUpdate();

                    } catch (SQLException e) {
                        e.printStackTrace();
                        System.out.println("e1 = -------" + e);
                        logErrorRecord(row);

                    }
                }
                System.out.println("Data successfully loaded into SQL Server.");
                preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("e2 = -------" + e);
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("e3 = -------" + e);
        } catch (Exception e) {

            e.printStackTrace();
            System.out.println("e4 = --------- " + e);
        }
    }
    private String buildInsertionSql(String[] headers) {
        if (headers == null || headers.length == 0) {
            System.err.println("Error: Los encabezados están vacíos o son nulos.");
            return null;
        }

        StringBuilder sql = new StringBuilder("INSERT INTO " + this.tableName + " VALUES (");
        for (int i = 0; i < headers.length; i++) {
            sql.append((i == 0) ? "?" : ", ?");
        }
        sql.append(")");
        return sql.toString();
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
        System.out.println("sql = " + sql);
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
