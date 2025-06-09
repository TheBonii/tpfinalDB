import dao.DBConnection;
import dao.DonanteDAO;
import dao.PadrinoDAO;
import java.sql.Connection;
import java.sql.Date;
import java.util.Scanner;
import models.Padrino;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static PadrinoDAO padrinoDAO = new PadrinoDAO();
    private static DonanteDAO donanteDAO = new DonanteDAO();

    public static void main(String[] args) {
        //Test de conexion...
        if (!testearConexion()) {
            System.out.println("Error al conectar con la base de datos. Programa Cerrado");
            return;
        }
        
        System.out.println("Conectado a la base de datos!");
        mostrarMenu();
    }

    private static boolean testearConexion() {
        try {
            Connection connection = DBConnection.getConnection();
            if (connection != null) {
                connection.close();
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
        return false;
    }

    private static void mostrarMenu() {
        int opcion = 0;
        while(opcion != 5) {
            
            System.out.println("\n" + "===========================================================");
            System.out.println("             SISTEMA DE PADRINOS  ");
            System.out.println("\n" + "===========================================================");
            System.out.println("1) Cargar nuevo padrino");
            System.out.println("2) Eliminar donante por DNI");
            System.out.println("3) Listar padrinos con programas");
            System.out.println("4) Listar todos los padrinos");
            System.out.println("5) Salir del sistema");
            System.out.println("\n" + "===========================================================");
            System.out.print("Seleccione una opción (1-4): ");
            
            opcion = scanner.nextInt();
            scanner.nextLine();

            if(opcion == 1){
                cargarPadrino();
            }else if(opcion == 2){
                eliminarDonante();
            }else if(opcion == 3){
                listarPadrinosConProgramas();
            }else if(opcion == 4){
                listarTodosPadrinos();
            }else if(opcion == 5){
                System.out.println("Saliendo del sistema!");
            }  
            else{
                System.out.println("Opcion incorrecta!");
            }
        }
        scanner.close();
    }

    private static void cargarPadrino() {
            System.out.println("\n" + "===========================================================");
        System.out.println("       CARGAR NUEVO PADRINO");
            System.out.println("\n" + "===========================================================");
        
        try {
            System.out.print("DNI: ");
            int dni = scanner.nextInt();
            scanner.nextLine();
            
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            
            System.out.print("Apellido: ");
            String apellido = scanner.nextLine();
            
            System.out.print("Dirección: ");
            String direccion = scanner.nextLine();
            
            System.out.print("Celular: ");
            String celular = scanner.nextLine();
            
            System.out.print("Teléfono fijo (opcional): ");
            String telefonoFijo = scanner.nextLine();
            
            System.out.print("Email: ");
            String email = scanner.nextLine();
            
            System.out.print("Facebook (opcional): ");
            String facebook = scanner.nextLine();
            
            System.out.print("Código postal: ");
            int codigoPostal = scanner.nextInt();
            scanner.nextLine();
            
            System.out.print("Fecha de nacimiento (AAAA-MM-DD): ");
            String fechaStr = scanner.nextLine();
            Date fechaNacimiento = Date.valueOf(fechaStr);
            
            
            Padrino padrino = new Padrino(dni, nombre, apellido, direccion, celular, 
                                        telefonoFijo, email, facebook, codigoPostal, fechaNacimiento);
            
            System.out.print("\n Guardando padrino en base de datos...");
            boolean resultado = padrinoDAO.insertar(padrino);
            
            if (resultado) {
                System.out.println("Padrino guardado con éxito");
                System.out.println("Resumen de padrino: " + nombre + " " + apellido + " (DNI: " + dni + ")");
            } else {
                System.out.println("Fallo al guardar padrino.");
            }
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("💡 Tip: Verificá el formato de la fecha (YYYY-MM-DD) y que el DNI sea un número.");
        }
    }

    private static void eliminarDonante() {
        System.out.println("\n" + "===========================================================");
        System.out.println("       ELIMINAR DONANTE");
        System.out.println("\n" + "===========================================================");
        
        try {
            System.out.print(" Ingrese DNI del donante a eliminar: ");
            int dni = scanner.nextInt();
            scanner.nextLine();
            
            System.out.print(" Confirme si desea eliminar al donante con DNI: " + dni + "? (s/n): ");
            String confirmacion = scanner.nextLine().toLowerCase();
            
            if (confirmacion.equals("s") || confirmacion.equals("si")) {
                System.out.print(" Eliminando donante...");
                boolean resultado = donanteDAO.remove(dni);
                
                if (resultado) {
                    System.out.println(" El donante fue eliminado con éxito");
                    
                } else {
                    System.out.println(" No se pudo eliminar el donante (puede que no exista).");
                }
            } else {
                System.out.println(" Operación cancelada.");
            }
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void listarPadrinosConProgramas() {
        System.out.println("\n" + "===========================================================");
        System.out.println("           PADRINOS CON PROGRAMAS");
        System.out.println("\n" + "===========================================================");
        
        try {
            padrinoDAO.listarConProgramas();
        System.out.println("\n" + "===========================================================");
            System.out.println(" Listado finalizado.");
            
        } catch (Exception e) {
            System.out.println(" Error al obtener el listado: " + e.getMessage());
        }
    }

    private static void listarTodosPadrinos(){
         System.out.println("\n" + "===========================================================");
        System.out.println("           LISTA DE PADRINOS");
        System.out.println("\n" + "===========================================================");
        
        try {
            padrinoDAO.listarPadrinos();
        System.out.println("\n" + "===========================================================");
            System.out.println(" Listado finalizado.");
            
        } catch (Exception e) {
            System.out.println(" Error al obtener el listado: " + e.getMessage());
        }
        

    }
}
