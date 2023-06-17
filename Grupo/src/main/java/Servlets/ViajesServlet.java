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
        String idViaje;
        ArrayList<String> opciones = new ArrayList<>();
        opciones.add("CiudadDestino");
        opciones.add("CiudadOrigen");

        setListaOpciones(opciones);


        switch (action) {
            case "lista":
                request.setAttribute("listarViajes", viajesDao.listarViajes());
                request.setAttribute("listarFiltro", getListaOpciones());

                view = request.getRequestDispatcher("/viajes/list.jsp");
                view.forward(request, response);
                break;
            case "crear":
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

}
