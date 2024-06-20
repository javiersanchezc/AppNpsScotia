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
public class CsvToSqlServerBPulse_digital_inline_export {
    private String jdbcUrl;

    private String inputFilePathwm_bPulse_digital_inline_export;

    private String tableNamebPulse_digital_inline_export;

    private String logFilename = "C:/data/BPulse_digital_inline_export.log";

    public CsvToSqlServerBPulse_digital_inline_export() {
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
            this.inputFilePathwm_bPulse_digital_inline_export = properties.getProperty("inputFilePathwm_bPulse_digital_inline_export");
            this.tableNamebPulse_digital_inline_export = properties.getProperty("tableNamebPulse_digital_inline_export");
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
            connection = DriverManager.getConnection(this.jdbcUrl);
            CSVReader csvReader = new CSVReader(new FileReader(this.inputFilePathwm_bPulse_digital_inline_export));
            String[] headers = csvReader.readNext();
            String insertionSql = buildInsertionSql(headers);
            PreparedStatement preparedStatement = connection.prepareStatement(insertionSql);
            String[] row;
            while ((row = csvReader.readNext()) != null) {
                if (row.length == headers.length && !isLastFieldNull(row)) {
                    setParameters(preparedStatement, row, headers.length);
                    try {
                        preparedStatement.executeUpdate();
                        System.out.println("Row inserted successfully.");
                    } catch (SQLException e) {
                        logErrorRecord(row);
                    }
                } else {
                    logErrorRecord(row);
                    System.out.println("Skipped row due to null last field.");
                }
            }
            System.out.println("Data successfully loaded into SQL Server.");
            preparedStatement.close();
            csvReader.close();
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
                    System.out.println("connection = cerrada");
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
        String sql = "INSERT INTO " + this.tableNamebPulse_digital_inline_export + " VALUES (";
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
