package com.nps.AppNps.loadProces;

import com.nps.AppNps.Data.ConsultaResultado;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;

import java.io.InputStream;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
@Component
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

    public List<ConsultaResultado> realizarConsultas(LocalDate fechaConsulta) {
        List<ConsultaResultado> resultados = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
            resultados.add(consultarTabla(connection, tableNamebPulse_digital_inline_export, fechaConsulta));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultados;
    }

    private ConsultaResultado consultarTabla(Connection connection, String tableName, LocalDate fechaConsulta) throws SQLException {
        String sql = "SELECT COUNT(*) FROM " + tableName + " WHERE Interaction_Date = ?";
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

