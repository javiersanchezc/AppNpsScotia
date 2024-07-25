package com.nps.AppNps.loadProces;

import com.nps.AppNps.Data.ConsultaResultado;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger logger = LoggerFactory.getLogger(CsvToSqlServerMaster.class);

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
        if (!Files.exists(Paths.get(this.inputFilePath))) {
            logger.error("El archivo no existe: {}", this.inputFilePath);
            return;
        }

        try (Connection connection = DriverManager.getConnection(this.jdbcUrl);
             CSVReader csvReader = new CSVReaderBuilder(new FileReader(this.inputFilePath))
                     .withCSVParser(new com.opencsv.CSVParserBuilder().withSeparator('|').build())
                     .build()) {

            String[] headers = csvReader.readNext();
            if (headers == null) {
                logger.error("Error: Los encabezados están vacíos o son nulos.");
                return;
            }

            String insertionSql = buildInsertionSql(headers);
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertionSql)) {
                String[] row;
                int lineNumber = 0;

                while ((row = csvReader.readNext()) != null) {
                    lineNumber++;
                    try {
                        setParameters(preparedStatement, headers, row);
                        logger.debug("Executing: {}", preparedStatement.toString());
                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        logger.error("Error en la línea {}: {}", lineNumber, e.getMessage());
                        logErrorRecord(row, lineNumber);
                    }
                }
                logger.info("Data successfully loaded into SQL Server.");
            }
        } catch (IOException | SQLException | CsvValidationException e) {
            logger.error("Error al convertir CSV a SQL Server: ", e);
        }
    }

    private String buildInsertionSql(String[] headers) {
        StringBuilder sql = new StringBuilder("INSERT INTO " + this.tableName + " VALUES (");
        for (int i = 0; i < headers.length; i++) {
            sql.append((i == 0) ? "?" : ", ?");
        }
        sql.append(")");
        return sql.toString();
    }

    private void setParameters(PreparedStatement preparedStatement, String[] headers, String[] values) throws SQLException {
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

    private void logErrorRecord(String[] values, int lineNumber) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.logFilename, true))) {
            writer.write("Line " + lineNumber + ": ");
            for (String value : values) {
                writer.write(value + "|");
            }
            writer.newLine();
        } catch (IOException e) {
            logger.error("Error al escribir en el archivo de log de errores: ", e);
        }
    }

    public List<ConsultaResultado> realizarConsultas(LocalDate fechaConsulta) {
        List<ConsultaResultado> resultados = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
            resultados.add(consultarTabla(connection, tableName, fechaConsulta));
        } catch (SQLException e) {
            logger.error("Error realizando consultas: ", e);
        }
        return resultados;
    }

    private ConsultaResultado consultarTabla(Connection connection, String tableName, LocalDate fechaConsulta) throws SQLException {
        String sql = "SELECT COUNT(*) FROM " + tableName + " WHERE Response_Date_EST = ?";
        logger.debug("sql = {}", sql);
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
