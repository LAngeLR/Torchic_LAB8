package Beans;

public class Usuario {
    private int idusuario;
    private String nombre;
    private String apellido;
    private int edad;
    private int codigopucp;
    private String correopucp;
    private String especialidad;
    private String contrasenia;
    private Status status;

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getCodigopucp() {
        return codigopucp;
    }

    public void setCodigopucp(int codigopucp) {
        this.codigopucp = codigopucp;
    }

    public String getCorreopucp() {
        return correopucp;
    }

    public void setCorreopucp(String correopucp) {
        this.correopucp = correopucp;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
