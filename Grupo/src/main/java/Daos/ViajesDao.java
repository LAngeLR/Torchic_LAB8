package Daos;

import Beans.Viajes;
import Beans.Usuario;
import Beans.Status;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ViajesDao extends BaseDao{

    public ArrayList<Viajes> listarviajes(){

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

        Usuario usuario = new Usuario();
        usuario.setIdusuario(rs.getInt(9));
        usuario.setNombre(rs.getString(10));
        usuario.setApellido(rs.getString(11));
        usuario.setEdad(rs.getInt(12));
        usuario.setCodigopucp(rs.getInt(13));
        usuario.setCorreopucp(rs.getString(14));
        usuario.setEspecialidad(rs.getString(15));
        usuario.setContrasenia(rs.getString(16));

        Status status = new Status();
        status.setIdstatus(rs.getInt(17));
        status.setStatus(rs.getString(18));
        status.setGasto_asociado_min(rs.getInt(19));

        usuario.setStatus(status);
        viaje.setUsuario(usuario);
    }

}
