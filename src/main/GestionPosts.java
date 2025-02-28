import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class GestionPosts {
    public static void gestionMenu() throws SQLException {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        while (opcion != -1) {
            if (!Main.usuario.isEmpty()) {
                System.out.print(Main.usuario + " | ");
            }
            System.out.print(" 1 - Nuevo post | ");
            System.out.print(" 2 - Listar posts | ");
            System.out.print(" 3 - Listar mis posts | ");
            System.out.print("-1 - Salir");

            System.out.println();

            opcion = sc.nextInt();
            if (opcion == 1) {
                newPost();
            } else if (opcion == 2) {
                listarPosts();
            }
        }
    }

    public static void newPost() throws SQLException {
        if (Main.id_usuario == -1) {
            System.out.println("Debes logearte antes");
            GestionUsuarios.existeUsuario();
            return;
        }

        Connection con = Main.connection;

        Scanner sc = new Scanner(System.in);

        System.out.println("Introduzca el texto de su post: ");
        String texto = sc.nextLine();
        java.sql.Date fecha = new java.sql.Date(new Date().getTime());

        PreparedStatement pst = con.prepareStatement("INSERT INTO posts (texto,likes,fecha,id_usuario) VALUES (?,?,?,?)");
        pst.setString(1, texto);
        pst.setInt(2, 0);
        pst.setDate(3, fecha);
        pst.setInt(4, Main.id_usuario);
        pst.executeUpdate();
    }

    public static void listarPosts() throws SQLException {
        Connection con = Main.connection;

        PreparedStatement pst = con.prepareStatement("SELECT p.*, u.nombre " + "FROM posts as p " + "INNER JOIN usuarios as u ON p.id_usuario = u.id");
        ResultSet rs = pst.executeQuery();

        System.out.println("ID  Usuarios " + " Posts " + " Likes " + " Fecha ");
        System.out.println("-----------------------------------------------");
        while (rs.next()) {
            resultadoPosts(rs);
            GestionComentarios.resultadoComentarios(rs.getInt(1));
        }
        System.out.println("-----------------------------------------------");
    }

    public static void resultadoPosts(ResultSet rs) throws SQLException {
        System.out.print((rs.getInt(1) + "| " + rs.getString("nombre")) + " | " + rs.getString(2));
        System.out.print(" | " +rs.getInt(3));
        System.out.println(" | " + rs.getDate(4));

    }
}