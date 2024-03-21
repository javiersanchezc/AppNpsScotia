package com.nps.AppNps.loadProces;

import com.opencsv.CSVReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
public class CsvToSqlServerBPulse_digital_inline_export {
    private String jdbcUrl;

    private String inputFilePathwm_bPulse_digital_inline_export;

    private String tableNamebPulse_digital_inline_export;

    public CsvToSqlServerBPulse_digital_inline_export() {
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
                this.inputFilePathwm_bPulse_digital_inline_export = properties.getProperty("inputFilePathwm_bPulse_digital_inline_export");
                this.tableNamebPulse_digital_inline_export = properties.getProperty("tableNamebPulse_digital_inline_export");
                this.jdbcUrl = properties.getProperty("jdbcUrl");
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
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(this.jdbcUrl);
            CSVReader csvReader = new CSVReader(new FileReader(this.inputFilePathwm_bPulse_digital_inline_export));
            String[] headers = csvReader.readNext();
            String insertionSql = buildInsertionSql(headers);
            PreparedStatement preparedStatement = connection.prepareStatement(insertionSql);
            try {
                String[] row;
                while ((row = csvReader.readNext()) != null) {
                    if (row.length == headers.length && !isLastFieldNull(row)) {
                        setParameters(preparedStatement, row, headers.length);
                        preparedStatement.executeUpdate();
                        System.out.println("Row inserted successfully.");
                        continue;
                    }
                    System.out.println("Skipped row due to null last field.");
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
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException error) {
            error.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null)
                try {
                    connection.close();
                    System.out.println("connection =  cerrada");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }

    private String buildInsertionSql(String[] headers) {
        String sql = "INSERT INTO " + this.tableNamebPulse_digital_inline_export + " VALUES (";
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

    private boolean isLastFieldNull(String[] row) {
        return (row[row.length - 1] == null);
    }
}

