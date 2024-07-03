package com.nps.AppNps.service;


import com.nps.AppNps.Data.ConsultaResultado;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class loadAllServiceData {

    private String jdbcUrl;

    public loadAllServiceData() {
        loadProperties();
    }

    private void loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.err.println("No se pudo encontrar el archivo de propiedades.");
                return;
            }
            properties.load(input);
            this.jdbcUrl = properties.getProperty("jdbcUrl");
        } catch (Exception e) {
            System.err.println("Error al leer el archivo de propiedades: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<ConsultaResultado> realizarConsultas(LocalDate fechaConsulta) {
        System.out.println("fechaConsulta = " + fechaConsulta);
        List<ConsultaResultado> resultados = new ArrayList<>();
        String sql = "SELECT Tabla AS nombreTabla, SUM(CantidadRegistros) AS cantidadDatos " +
                "FROM RegistroNPSInserciones " +
                "WHERE CAST(Fecha AS DATE) = ? " +
                "GROUP BY Tabla";
        System.out.println("sql = " + sql);
        try (Connection connection = DriverManager.getConnection(jdbcUrl);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Convertir LocalDate a java.sql.Date
            preparedStatement.setDate(1, Date.valueOf(fechaConsulta));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String nombreTabla = resultSet.getString("nombreTabla");
                    long cantidadDatos = resultSet.getLong("cantidadDatos");
                    LocalDateTime fechaConsultaDateTime = fechaConsulta.atStartOfDay(); // Convertir LocalDate a LocalDateTime
                    ConsultaResultado resultado = new ConsultaResultado(fechaConsultaDateTime, nombreTabla, cantidadDatos);
                    resultados.add(resultado);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultados;
    }

}