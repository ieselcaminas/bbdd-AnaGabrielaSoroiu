import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
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
            System.out.print(" -1 - Salir | ");

            System.out.println();

            opcion = sc.nextInt();
            if (opcion == 1) {
                nuevoComentario();
            }

        }
    }

    public static void nuevoComentario() throws SQLException {
        int id_post;
        String texto;

        Scanner sc = new Scanner(System.in);

        if (Main.id_usuario == -1) {
            System.out.println("Debes de logearte antes.");
            GestionUsuarios.existeUsuario();
            return;
        }

        Connection con = Main.connection;

        System.out.println("Elige el id del post que desees comentar.");
        System.out.println("-----------------------------------------------");
        GestionPosts.listarPosts();

        System.out.println("Id: ");
        id_post = sc.nextInt();
        //Sirve para que me pueda recoger luego mi comentario
        sc.nextLine();

        PreparedStatement pst = con.prepareStatement("INSERT INTO comentarios (id_post, id_usuario, texto, fecha) VALUES (?, ?, ?,?)");

        System.out.println("Escriba su comentario.");
        texto = sc.nextLine();
        java.sql.Date fecha = new java.sql.Date(new Date().getTime());

        pst.setInt(1, id_post);
        pst.setInt(2, Main.id_usuario);
        pst.setString(3, texto);
        pst.setDate(4, fecha);
        pst.executeUpdate();
    }

    public static void resultadoComentarios(int id_post) throws SQLException {
        Connection con = Main.connection;

        PreparedStatement pst = con.prepareStatement("SELECT c.id, u.nombre, c.texto, c.fecha FROM comentarios as c " +
                "INNER JOIN posts as p ON c.id_post = p.id " +
                "INNER JOIN usuarios as u ON c.id_usuario = u.id " +
                "WHERE p.id = ?");

        pst.setInt(1, id_post);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            System.out.println("\t" + rs.getString(2) + "| " + rs.getString(3) + " | " + rs.getDate(4));
        }
    }
}
