package dao;

import models.Padrino;
import models.PadrinoProgram;


import java.util.List;
import java.sql.*;
public class PadrinoDAO {

    public boolean insertar(Padrino padrino) {
        Connection connection = null;
        try{
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/Proyecto";
            String username = "root";
            String password = "root";

            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);

            connection.setAutoCommit(false);
            String query = "INSERT INTO Padrino (dni, nombre, apellido, direccion, celular, telefono_fijo, email, facebook, codigo_postal, fecha_nacimiento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(query);


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


        } catch (ClassNotFoundException cnfe) {
            System.err.println("Error loading driver: " + cnfe);
            return false;
        } catch (SQLException sqle) {
            try {
                System.err.println("Error en BD: " + sqle);
                connection.rollback();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        } finally {
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    }

    public List<PadrinoProgram> listarConProgramas() {
        return null;
    }

    public Padrino buscarPorDni(int dni) {
        return null;
    }

    public List<Padrino> listarTodos() {
        return null;
    }
}
