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
        List<ConsultaResultado> resultados = new ArrayList<>();
        String sql = "SELECT Tabla AS nombreTabla, SUM(CantidadRegistros) AS cantidadDatos " +
                "FROM RegistroNPSInserciones " +
                "WHERE CAST(Fecha AS DATE) = ? " +
                "GROUP BY Tabla";

        try (Connection connection = DriverManager.getConnection(jdbcUrl);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setDate(1, java.sql.Date.valueOf(fechaConsulta));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String nombreTabla = resultSet.getString("nombreTabla");
                    long cantidadDatos = resultSet.getLong("cantidadDatos");
                    ConsultaResultado resultado = new ConsultaResultado(LocalDateTime.now(), nombreTabla, cantidadDatos);
                    resultados.add(resultado);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultados;
    }
}