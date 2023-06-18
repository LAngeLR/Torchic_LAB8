package Daos;

import Beans.Viajes;
import Beans.Usuarios;
import Beans.Status;

import java.sql.*;
import java.util.ArrayList;

public class ViajesDao extends BaseDao{

    public ArrayList<Viajes> listarViajes(int codigo){

        ArrayList<Viajes> listaDeViajes = new ArrayList<>();

        String sql = "select * from viajes";

        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Viajes viajes = new Viajes();
                fetchViajesData(viajes, rs);

                listaDeViajes.add(viajes);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listaDeViajes;
    }
    private void fetchViajesData(Viajes viaje, ResultSet rs) throws SQLException {

        viaje.setIdViaje(rs.getInt(1));
        viaje.setFechaReserva(rs.getDate(2));
        viaje.setFechaViaje(rs.getDate(3));
        viaje.setCiudadOrigen(rs.getString(4));
        viaje.setCiudadDestino(rs.getString(5));
        viaje.setEmpresaSeguro(rs.getString(6));
        viaje.setCantBoletos(rs.getInt(7));
        viaje.setCostoTotal(rs.getInt(8));
        viaje.setEmpresaSeguro(rs.getString(6));

        Usuarios usuario = new Usuarios();

        usuario.setNombre(rs.getString(10));
        usuario.setApellido(rs.getString(11));
        usuario.setEdad(rs.getInt(12));
        usuario.setCodigopucp(rs.getInt(13));
        usuario.setCorreopucp(rs.getString(14));
        usuario.setEspecialidad(rs.getString(15));
        usuario.setContrasenia(rs.getString(16));
        usuario.setStatus(rs.getString(17));

        viaje.setUsuario(usuario);
    }

    public ArrayList<Viajes> busquedaCiudadOrigen(String ciudadOrigen) {

        ArrayList<Viajes> listaDeViajes = new ArrayList<>();

        String sql = "SELECT * FROM viajes WHERE lower(ciudad_origen) like ?";

        try(Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%"+ciudadOrigen+"%");

            try (ResultSet rs = pstmt.executeQuery()){
                while (rs.next()){
                    Viajes viajes = new Viajes();

                    fetchViajesData(viajes, rs);

                    listaDeViajes.add(viajes);
                }
            }

        }catch (SQLException e){
            throw new RuntimeException();
        }

        return listaDeViajes;
    }

    public ArrayList<Viajes> busquedaCiudadDestino(String ciudadDestino) {

        ArrayList<Viajes> listaDeViajes = new ArrayList<>();

        String sql = "SELECT * FROM viajes WHERE lower(ciudad_destino) like ?";

        try(Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%"+ciudadDestino+"%");

            try (ResultSet rs = pstmt.executeQuery()){
                while (rs.next()){
                    Viajes viajes = new Viajes();

                    fetchViajesData(viajes, rs);
                    listaDeViajes.add(viajes);

                }
            }
        }catch (SQLException e){
            throw new RuntimeException();
        }
        return listaDeViajes;
    }

    public void crearViaje(Viajes viajes) {

        String sql = "INSERT INTO viajes (idviajes, fecha_reserva, fecha_viaje, ciudad_origen, ciudad_destino, empresa_seguro, cantidad_boletos, costo_total) VALUES (?,?,?,?,?,?,?,?)";

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {


            pstmt.setInt(1, viajes.getIdViaje());
            pstmt.setDate(2, (Date) viajes.getFechaReserva());
            pstmt.setDate(3, (Date) viajes.getFechaViaje());
            pstmt.setString(4, viajes.getCiudadOrigen());
            pstmt.setString(5, viajes.getCiudadDestino());
            pstmt.setString(6, viajes.getEmpresaSeguro());
            pstmt.setInt(7, viajes.getCantBoletos());
            pstmt.setInt(8,viajes.getCostoTotal());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void borrarViaje(String id) {

        String sql = "DELETE from viajes WHERE idviajes = ?";

        try(Connection connection = getConnection();
            PreparedStatement pstmt=connection.prepareStatement(sql))
        {

            pstmt.setString(1,id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
