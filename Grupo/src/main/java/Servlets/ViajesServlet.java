package Servlets;

import Beans.Viajes;
import Daos.ViajesDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.time.LocalDate;


@WebServlet(name = "ViajesServlet", urlPatterns = {"/ViajesServlet", ""})
public class ViajesServlet extends HttpServlet {

    private ArrayList listaOpciones;
    private ArrayList listaSeguros;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        Viajes viajes=new Viajes();
        ViajesDao viajesDao=new ViajesDao();
        RequestDispatcher view;

        ArrayList<String> opciones = new ArrayList<>();
        opciones.add("CiudadDestino");
        opciones.add("CiudadOrigen");

        setListaOpciones(opciones);

        switch (action) {

            case "buscar":

                String searchText = request.getParameter("searchText");
                String opcionjsp = request.getParameter("tipo");

                if (opcionjsp.equals("CiudadDestino")){
                    ArrayList<Viajes> listaBuscarViaje =viajesDao.busquedaCiudadDestino(searchText);
                    request.setAttribute("listarViajes", listaBuscarViaje);
                    request.setAttribute("listarFiltro", getListaOpciones());
                } else if (opcionjsp.equals("CiudadOrigen")) {
                    ArrayList<Viajes> listaBuscarViaje =viajesDao.busquedaCiudadOrigen(searchText);
                    request.setAttribute("listarViajes", listaBuscarViaje);
                    request.setAttribute("listarFiltro", getListaOpciones());
                }

                view = request.getRequestDispatcher("/viajes/list.jsp");
                view.forward(request, response);
                break;

            case "guardar":


                viajes.setIdViaje(Integer.parseInt(request.getParameter("IdViaje")));
                viajes.setFechaReserva(Date.valueOf(request.getParameter("FechaReserva")));
                viajes.setFechaViaje(Date.valueOf(request.getParameter("FechaViaje")));
                viajes.setCiudadOrigen(request.getParameter("CiudadOrigen"));
                viajes.setCiudadOrigen(request.getParameter("CiudadDestino"));
                viajes.setEmpresaSeguro(request.getParameter("EmpresaSeguro"));
                viajes.setCantBoletos(Integer.parseInt(request.getParameter("CantBoleto")));
                viajes.setCostoTotal(Integer.parseInt(request.getParameter("CostoTotal")));

                int centinela=0;

                LocalDate fechaActual = LocalDate.now();

                String fechaViajeString = request.getParameter("FechaViaje");
                LocalDate fechaViaje = LocalDate.parse(fechaViajeString);

                if (fechaViaje.isAfter(fechaActual)) {
                    centinela = 1;
                }

                if (centinela!=1){
                    response.sendRedirect(request.getContextPath()+"/ViajesServlet?action=crear");
                }else {
                    viajesDao.crearViaje(viajes);
                    response.sendRedirect(request.getContextPath()+"/ViajesServlet");
                }
                break;

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        RequestDispatcher view;
        ViajesDao viajesDao = new ViajesDao();

        ArrayList<String> seguros = new ArrayList<>();

        String idViaje;

        seguros.add("Rimac");
        seguros.add("Pacifico");
        seguros.add("La Positiva");
        seguros.add("Seguro Internacional");
        seguros.add("Otro");

        ArrayList<String> opciones = new ArrayList<>();
        opciones.add("CiudadDestino");
        opciones.add("CiudadOrigen");

        setListaOpciones(opciones);
        setListaSeguros(seguros);


        switch (action) {
            case "lista":
                String codigopucp = request.getParameter("codigopucp");
                request.setAttribute("listarViajes", viajesDao.listarViajes(codigopucp));
                request.setAttribute("listarFiltro", getListaOpciones());

                view = request.getRequestDispatcher("/viajes/list.jsp");
                view.forward(request, response);
                break;
            case "crear":
                request.setAttribute("listaSeguros", getListaSeguros());
                view = request.getRequestDispatcher("/viajes/form.jsp");
                view.forward(request, response);
                break;
            case "borrar":
                idViaje = request.getParameter("id");
                viajesDao.borrarViaje(idViaje);

                response.sendRedirect(request.getContextPath() + "/ViajesServlet");
                break;
        }
    }

    public ArrayList getListaOpciones() {
        return listaOpciones;
    }

    public void setListaOpciones(ArrayList listaOpciones) {
        this.listaOpciones = listaOpciones;
    }

    public ArrayList getListaSeguros() {
        return listaSeguros;
    }

    public void setListaSeguros(ArrayList listaSeguros) {
        this.listaSeguros = listaSeguros;
    }

}
