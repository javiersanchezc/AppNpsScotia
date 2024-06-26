package com.nps.AppNps.loadProces;

import com.nps.AppNps.Data.ConsultaResultado;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
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
public class CsvToSqlServerCsvToSqlServercPulseCardifCallbackExport {
    private String jdbcUrl;

    private String inputFilePathwm_cPulseInsuranceCardifCallbackExport;

    private String tableNamecPulseInsuranceCardifCallbackExport;

    private String errorFilePath;
    private String logFilename = "C:/data/cPulseCardifCallbackExport.log";

    public CsvToSqlServerCsvToSqlServercPulseCardifCallbackExport() {
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
            this.inputFilePathwm_cPulseInsuranceCardifCallbackExport = properties.getProperty("inputFilePathwm_cPulseInsuranceCardifCallbackExport");
            this.tableNamecPulseInsuranceCardifCallbackExport = properties.getProperty("tableNamecPulseInsuranceCardifCallbackExport");
            this.jdbcUrl = properties.getProperty("jdbcUrl");
            this.errorFilePath = properties.getProperty("errorFilePath");
            input.close();
        } catch (Exception e) {
            System.err.println("Error al leer el archivo de propiedades: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void convertCsvToSqlServer() {
        try (Connection connection = DriverManager.getConnection(this.jdbcUrl)) {
            try (CSVReader csvReader = new CSVReader(new FileReader(this.inputFilePathwm_cPulseInsuranceCardifCallbackExport))) {
                String[] headers = csvReader.readNext();
                String insertionSql = buildInsertionSql(headers);
                System.out.println("insertionSql = " + insertionSql);
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertionSql)) {
                    String[] row;
                    while ((row = csvReader.readNext()) != null) {
                        try {
                            setParameters(preparedStatement, row);
                            System.out.println(buildFullSql(insertionSql, row)); // Aquí imprimimos la sentencia SQL con los valores
                            preparedStatement.executeUpdate();
                        } catch (SQLException e) {
                            e.printStackTrace();
                            logErrorRecord(row);
                        }
                    }
                    System.out.println("Data successfully loaded into SQL Server.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (CsvValidationException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String buildInsertionSql(String[] headers) {
        String sql = "INSERT INTO " + this.tableNamecPulseInsuranceCardifCallbackExport + " VALUES (";
        for (int i = 0; i < headers.length; i++)
            sql = sql + ((i == 0) ? "?" : ", ?");
        sql = sql + ")";
        System.out.println(sql);
        return sql;
    }

    private void setParameters(PreparedStatement preparedStatement, String[] values) throws SQLException {
        
        for (int i = 0; i < values.length; i++) {
            System.out.println("values[i] = " + values[i]);
            if (values[i] == null || values[i].isEmpty()) {
                preparedStatement.setNull(i + 1, Types.NULL);
            } else {
                preparedStatement.setString(i + 1, values[i]);
            }
        }
    }

    private String buildFullSql(String insertionSql, String[] values) {
        StringBuilder sql = new StringBuilder(insertionSql);
        for (String value : values) {
            int index = sql.indexOf("?");
            if (index != -1) {
                if (value == null || value.isEmpty()) {
                    sql.replace(index, index + 1, "NULL");
                } else {
                    sql.replace(index, index + 1, "'" + value + "'");
                }
            }
        }
        return sql.toString();
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

    public List<ConsultaResultado> realizarConsultas(LocalDate fechaConsulta) {
        List<ConsultaResultado> resultados = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
            resultados.add(consultarTabla(connection, tableNamecPulseInsuranceCardifCallbackExport, fechaConsulta));
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
