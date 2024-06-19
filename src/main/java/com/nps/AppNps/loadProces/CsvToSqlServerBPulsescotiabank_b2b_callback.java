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

@Component
public class CsvToSqlServerBPulsescotiabank_b2b_callback {
    private String jdbcUrl;

    private String inputFilePathwm_scotiabank_b2b_callback;

    private String tableNamescotiabank_b2b_callback;

    private String logFilename = "BPulsescotiabank_b2b_callback.log";

    public CsvToSqlServerBPulsescotiabank_b2b_callback() {
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
            this.inputFilePathwm_scotiabank_b2b_callback = properties.getProperty("inputFilePathwm_scotiabank_b2b_callback");
            this.tableNamescotiabank_b2b_callback = properties.getProperty("tableNamescotiabank_b2b_callback");
            this.jdbcUrl = properties.getProperty("jdbcUrl");
            input.close();
        } catch (Exception e) {
            System.err.println("Error al leer el archivo de propiedades: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void convertCsvToSqlServer() {
        try {
            Connection connection = DriverManager.getConnection(this.jdbcUrl);
            CSVReader csvReader = new CSVReader(new FileReader(this.inputFilePathwm_scotiabank_b2b_callback));
            String[] headers = csvReader.readNext();
            String insertionSql = buildInsertionSql(headers);
            PreparedStatement preparedStatement = connection.prepareStatement(insertionSql);
            String[] row;
            while ((row = csvReader.readNext()) != null) {
                if (!isNumeric(row[0])) {
                    System.out.println("Skipping line with non-numeric first column: " + Arrays.toString(row));
                    logErrorRecord(row);
                    continue;
                }
                setParameters(preparedStatement, row, headers.length);
                try {
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    logErrorRecord(row);
                }
            }
            System.out.println("Data successfully loaded into SQL Server.");
            preparedStatement.close();
            csvReader.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
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

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String buildInsertionSql(String[] headers) {
        StringBuilder sql = new StringBuilder("INSERT INTO " + this.tableNamescotiabank_b2b_callback + " VALUES (");
        for (int i = 0; i < headers.length; i++) {
            sql.append((i == 0) ? "?" : ", ?");
        }
        sql.append(")");
        return sql.toString();
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
            resultados.add(consultarTabla(connection, tableNamescotiabank_b2b_callback, fechaConsulta));
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
