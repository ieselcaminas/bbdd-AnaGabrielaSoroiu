import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GestionUsuario {
    public static void gestionMenu() throws SQLException {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        String usuario;
        while (opcion != -1) {
            System.out.print(" 1 - Login | ");
            System.out.print(" 2 - Nuevo usuario |");
            System.out.print(" 3 - Borrar usuario | ");
            System.out.print(" 4 - Listar usuarios | ");
            System.out.print("-1 - Salir");

            System.out.println();

            opcion = sc.nextInt();
            if (opcion == 1) {
                usuario = existeUsuario();

                if (!usuario.isEmpty()) {
                    Main.usuario = usuario;
                    System.out.println("Bienvenido " + usuario);
                    break;
                } else {
                    System.out.println("Usuario no encontrado.");
                }
            } else if (opcion == 2) {
                usuario = insertarUsuario();
                Main.usuario = usuario;
                break;
            }
        }
    }

    public static String existeUsuario() throws SQLException {
        java.sql.Connection con = Main.connection;

        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce tu nombre de usuario: ");
        String usuario = sc.nextLine();
        System.out.println("Introduce tu password: ");
        String password = sc.nextLine();

        PreparedStatement st = con.prepareStatement("SELECT * FROM usuarios WHERE nombre = ? AND password = ?");
        st.setString(1, usuario);
        st.setString(2, password);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            Main.id_usuario = rs.getInt(1);
            return rs.getString(2);
        }
        return "";
    }

    public static String insertarUsuario() throws SQLException {
        java.sql.Connection con = Main.connection;
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce tu nombre de usuario: ");
        String usuario = sc.nextLine();

        System.out.println("Introduce tus apellidos: ");
        String apellidos = sc.nextLine();

        System.out.println("Introduce tu password: ");
        int password = sc.nextInt();

        PreparedStatement st = con.prepareStatement("INSERT INTO usuarios(nombre, apellidos, password) VALUES(?,?,?)");

        st.setString(1, usuario);
        st.setString(2, apellidos);
        st.setInt(3, password);
        st.executeUpdate();

        return usuario;
    }}
