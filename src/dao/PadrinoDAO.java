package dao;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import models.Padrino;
public class PadrinoDAO {

    public boolean insertar(Padrino padrino) {
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = DBConnection.getConnection();
            connection.setAutoCommit(false);
            String query = "INSERT INTO Padrino (dni, nombre, apellido, direccion, celular, telefono_fijo, email, facebook, codigo_postal, fecha_nacimiento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            statement = connection.prepareStatement(query);


            statement.setInt(1, padrino.getDni());
            statement.setString(2, padrino.getNombre());
            statement.setString(3, padrino.getApellido());
            statement.setString(4, padrino.getDireccion());
            statement.setString(5, padrino.getCelular());
            statement.setString(6, padrino.getTelefonoFijo());
            statement.setString(7, padrino.getEmail());
            statement.setString(8, padrino.getFacebook());
            statement.setInt(9, padrino.getCodigoPostal());
            statement.setDate(10, padrino.getFechaNacimiento());

            statement.executeUpdate();

            connection.commit();
            return true;


        } catch (SQLException sqle) {
            try {
                System.err.println("Error en BD: " + sqle);
                if(connection != null) connection.rollback();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        } finally {
        try {
            if (statement != null) statement.close();   
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    }

    public void listarConProgramas() {
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnection.getConnection();
            String query = "SELECT p.Dni, p.Apellido, p.Nombre, " +
               "pr.nombre, a.Monto, a.Frecuencia_Aporte " +
               "FROM Padrino p " +
               "JOIN Aportan a ON p.Dni = a.Dni " +                   
               "JOIN Programa pr ON a.Id_Programa = pr.Id_Programa";  


            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while(resultSet.next()){
                System.out.println("DNI: " + resultSet.getInt(1));
                System.out.println("APELLIDO: " + resultSet.getString(2));
                System.out.println("NOMBRE: " + resultSet.getString(3));
                System.out.println("PROGRAMA: " + resultSet.getString(4));
                System.out.println("MONTO: " + resultSet.getInt(5));
                System.out.println("FRECUENCIA: " + resultSet.getString(6));
                System.out.println("--------------------\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if (connection != null) connection.close();
                if (statement != null) statement.close();
                if(resultSet != null) resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void listarPadrinos() {
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnection.getConnection();
            String query = "SELECT p.dni, p.apellido, p.nombre, p.direccion, " +
                      "p.celular, p.email, p.fecha_nacimiento " +
                      "FROM Padrino p";

        statement = connection.prepareStatement(query);
        resultSet = statement.executeQuery();

        while(resultSet.next()){
            System.out.println("DNI: " + resultSet.getInt("dni"));
            System.out.println("APELLIDO: " + resultSet.getString("apellido"));
            System.out.println("NOMBRE: " + resultSet.getString("nombre"));
            System.out.println("DIRECCION: " + resultSet.getString("direccion"));
            System.out.println("CELULAR: " + resultSet.getString("celular"));
            System.out.println("EMAIL: " + resultSet.getString("email"));
            //Calcular Edad}
            Date fechaNacimiento = resultSet.getDate("fecha_nacimiento");
            if (fechaNacimiento != null){
                LocalDate nac = fechaNacimiento.toLocalDate();
                LocalDate now = LocalDate.now();
                int edad = Period.between(nac, now).getYears();
                System.out.println("EDAD: " + edad + " años.");
            }
            System.out.println("--------------------\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if (connection != null) connection.close();
                if (statement != null) statement.close();
                if(resultSet != null) resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void cantidadPagosPorPrograma() {
        String query = "SELECT Id_Programa, COUNT(Id_Pago) AS CantPagos FROM Aportan GROUP BY Id_Programa";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int idPrograma = rs.getInt("Id_Programa");
                int cantPagos = rs.getInt("CantPagos");
                System.out.println("Programa: " + idPrograma + " - Pagos: " + cantPagos);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void donantesConMasDeDosAportes() {
        String query = "SELECT Dni, Nombre, Apellido, COUNT(Id_Pago) AS CantAportes " +
                       "FROM Donante NATURAL JOIN Aportan NATURAL JOIN Padrino " +
                       "GROUP BY Dni HAVING CantAportes > 2";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int dni = rs.getInt("Dni");
                String nombre = rs.getString("Nombre");
                String apellido = rs.getString("Apellido");
                int cantAportes = rs.getInt("CantAportes");
                System.out.println("DNI: " + dni + " - Nombre: " + nombre + " " + apellido + " - Aportes: " + cantAportes);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
    public void pagosPorDonante() {
        String query = "SELECT Dni, Nombre, Apellido, Id_Pago " +
                       "FROM Aportan NATURAL JOIN Donante NATURAL JOIN Padrino";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int dni = rs.getInt("Dni");
                String nombre = rs.getString("Nombre");
                String apellido = rs.getString("Apellido");
                int idPago = rs.getInt("Id_Pago");
                System.out.println("DNI: " + dni + " - Nombre: " + nombre + " " + apellido + " - Pago: " + idPago);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
