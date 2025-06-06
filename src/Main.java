import dao.DBConnection;
import dao.PadrinoDAO;
import models.Padrino;

import java.sql.Connection;
import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        Padrino padrino = new Padrino();


        padrino.setDni(123456);
        padrino.setNombre("Juanito");
        padrino.setApellido("Juanote");
        padrino.setDireccion("Av. Italia 123");
        padrino.setCelular("358411123");
        padrino.setTelefonoFijo("123456890");
        padrino.setEmail("Juanito.juanote@testeando.com");
        padrino.setFacebook("juanito.juanote");
        padrino.setCodigoPostal(5800);
        padrino.setFechaNacimiento(Date.valueOf("1990-05-15")); // Formato: YYYY-MM-DD
        PadrinoDAO dao = new PadrinoDAO();
        boolean resultado = dao.insertar(padrino);

        // 3. VER SI FUNCIONÓ
        if (resultado) {
            System.out.println("Padrino Insertado");
        } else {
            System.out.println("Error padrino NO Insertado");
        }

        try {
            Connection connection = DBConnection.getConnection();
            System.out.println("Funca");
            connection.close();
        } catch (Exception e) {
            System.out.println("No Funca: " + e.getMessage());
        }



    }
}