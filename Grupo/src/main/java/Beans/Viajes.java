package Beans;
import java.util.Date;

public class Viajes {

    private int idViaje;
    private Date fechaReserva;
    private Date fechaViaje;
    private String ciudadOrigen;
    private String ciudadDestino;
    private String empresaSeguro;
    private int cantBoletos;
    private int costoTotal;
    private Usuarios usuario;

    public int getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public Date getFechaViaje() {
        return fechaViaje;
    }

    public void setFechaViaje(Date fechaViaje) {
        this.fechaViaje = fechaViaje;
    }

    public String getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setCiudadOrigen(String ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public String getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(String ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public String getEmpresaSeguro() {
        return empresaSeguro;
    }

    public void setEmpresaSeguro(String empresaSeguro) {
        this.empresaSeguro = empresaSeguro;
    }

    public int getCantBoletos() {
        return cantBoletos;
    }

    public void setCantBoletos(int cantBoletos) {
        this.cantBoletos = cantBoletos;
    }

    public int getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(int costoTotal) {
        this.costoTotal = costoTotal;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
}
