import java.sql.SQLException;
import java.util.Scanner;

public class GestionComentarios {
    public static void gestionMenu() throws SQLException {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        while (opcion != -1) {
            if (!Main.usuario.isEmpty()) {
                System.out.println(Main.usuario + " | ");
            }
            System.out.print(" 1 - Nuevo comentario | ");
            System.out.print(" 2 - Listar comentario | ");
            System.out.print(" -1 - Salir | ");

            System.out.println();
        }

    }
}
