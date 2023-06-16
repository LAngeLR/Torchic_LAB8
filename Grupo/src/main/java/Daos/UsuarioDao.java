package Daos;

import Beans.Usuario;
import java.sql.*;

public class UsuarioDao extends BaseDao {
    public void crear(Usuario usuario){
        String sql="insert into usuario (nombre,apellido,edad,codigopucp,correopucp,especialidad,contrasenia) values \n" +
                "(?,?,?,?,?,?,sha2(?,256));";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getApellido());
            pstmt.setInt(3, usuario.getEdad());
            pstmt.setInt(4, usuario.getCodigopucp());
            pstmt.setString(5, usuario.getCorreopucp());
            pstmt.setString(6, usuario.getEspecialidad());
            pstmt.setString(7, usuario.getContrasenia());

            //no se si poner status en usuario o hacer lo de status status nomas

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Usuario obtenerUsuario(String idusuario){
        Usuario usuario = null;

        String sql = "SELECT * from usuario WHERE idusuario = ?";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, idusuario);

            try (ResultSet rs = pstmt.executeQuery();) {
                while(rs.next()){
                    usuario = new Usuario();
                    usuario.setIdusuario(rs.getInt(1));
                    usuario.setNombre(rs.getString(2));
                    usuario.setApellido(rs.getString(3));
                    usuario.setEdad(rs.getInt(4));
                    usuario.setCodigopucp(rs.getInt(5));
                    usuario.setCorreopucp(rs.getString(6));
                    usuario.setEspecialidad(rs.getString(7));
                    usuario.setContrasenia(rs.getString(8));
                    //usuario.setStatus(rs.getInt(10));

                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return usuario;
    }

    public Usuario validar(String correo, String contrasenia) {

        Usuario usuario = null;

        String sql = "SELECT codigopucp,correopucp,contrasenia from usuario WHERE correopucp = ? AND contrasenia = sha2(?,256)";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, correo);
            pstmt.setString(2, contrasenia);

            try (ResultSet rs = pstmt.executeQuery();) {
                if(rs.next()){
                    String idusuario = rs.getString(1);
                    usuario = this.obtenerUsuario(idusuario);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return usuario;
    }
}
