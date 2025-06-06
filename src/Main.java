import dao.DBConnection;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = DBConnection.getConnection();
            System.out.println("Funca");
            connection.close();
        } catch (Exception e) {
            System.out.println("No Funca: " + e.getMessage());
        }    }
}