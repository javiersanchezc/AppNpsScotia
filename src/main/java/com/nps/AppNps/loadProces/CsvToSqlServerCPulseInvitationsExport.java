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
public class CsvToSqlServerCPulseInvitationsExport {
    private String jdbcUrl;

    private String inputFilePathwm_cPulse_Invitation_Export;

    private String tableNamecPulse_Invitation_Export;

    private String errorFilePath;

    public CsvToSqlServerCPulseInvitationsExport() {
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
                this.inputFilePathwm_cPulse_Invitation_Export = properties.getProperty("inputFilePathwm_cPulse_Invitation_Export");
                this.tableNamecPulse_Invitation_Export = properties.getProperty("tableNamecPulse_Invitation_Export");
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
                CSVReader csvReader = new CSVReader(new FileReader(this.inputFilePathwm_cPulse_Invitation_Export));
                try {
                    String[] headers = csvReader.readNext();
                    String insertionSql = buildInsertionSql(headers);
                    PreparedStatement preparedStatement = connection.prepareStatement(insertionSql);
                    try {
                        String[] row;
                        while ((row = csvReader.readNext()) != null) {
                            try {
                                setParameters(preparedStatement, row);
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
        } catch (FileNotFoundException error) {
            error.printStackTrace();
            System.out.println("------------" + error);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String buildInsertionSql(String[] headers) {
        String sql = "INSERT INTO " + this.tableNamecPulse_Invitation_Export + " VALUES (";
        for (int i = 0; i < headers.length; i++)
            sql = sql + ((i == 0) ? "?" : ", ?");
        sql = sql + ")";
        return sql;
    }

    private void setParameters(PreparedStatement preparedStatement, String[] values) throws SQLException {
        for (int i = 0; i < values.length; i++)
            preparedStatement.setString(i + 1, values[i]);
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
}
