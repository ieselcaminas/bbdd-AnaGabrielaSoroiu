import java.sql.Connection;
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

            opcion = sc.nextInt();
            if (opcion == 1) {
                nuevoComentario();
            }

        }
    }

    public static void nuevoComentario() throws SQLException {
        int opcion = 0;

        Scanner sc = new Scanner(System.in);

        if (Main.id_usuario == -1) {
            System.out.println("Debes de logearte antes.");
            GestionUsuarios.existeUsuario();
            return;
        }

        Connection con = Main.connection;

        System.out.println("Elige el post que desees comentar.");
        GestionPosts.listarPosts();


    }
}
