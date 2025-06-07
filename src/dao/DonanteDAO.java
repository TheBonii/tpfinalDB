package dao;

import java.sql.*;


public class DonanteDAO {

    public boolean remove(int dni){
        
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "DELETE FROM Donante WHERE dni = ?";

        try{
            connection = DBConnection.getConnection();
            connection.setAutoCommit(false);
            
            statement = connection.prepareStatement(query);
            statement.setInt(1,dni);
            
            int filasAfectadas = statement.executeUpdate();

            if(filasAfectadas == 1){
                connection.commit();
                return true;
            }else if(filasAfectadas > 1){
                connection.rollback();
                return false;
            }else{
                return false;
            }

        } catch (SQLException sqle){
            try {
                System.err.println("Error en BD: " + sqle);
                connection.rollback();
            } catch (Exception e){
                e.printStackTrace();
            }
            return false;
        } finally{
            try {
                if(statement != null) statement.close();
                if(connection != null) connection.close();
            } catch (SQLException e ) {
                e.printStackTrace();
            }
        }
    }
}