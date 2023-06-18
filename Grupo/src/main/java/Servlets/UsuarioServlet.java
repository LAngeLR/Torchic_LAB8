package Servlets;

import Daos.UsuarioDao;
import Beans.Usuario;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
public class UsuarioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "login" : request.getParameter("action");
        RequestDispatcher requestDispatcher;
        UsuarioDao usuarioDao = new UsuarioDao();
        HttpSession session = request.getSession();

        switch (action) {

            case "login":
                Usuario usuario = (Usuario) session.getAttribute("UsuarioLog");
                if (usuario != null && Integer.parseInt(usuario.getCodigopucp()) != 0) {
                    response.sendRedirect(request.getContextPath() + "/viajesServlet");
                } else {
                    requestDispatcher = request.getRequestDispatcher("index.jsp");
                    requestDispatcher.forward(request, response);
                }
                break;

            case "logout":
                session.invalidate();
                response.sendRedirect(request.getContextPath() + "/index.jsp");
                break;

            case "registrar":
                requestDispatcher = request.getRequestDispatcher("Login/registro.jsp");
                requestDispatcher.forward(request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String correopucp = request.getParameter("correo");
        //String contrasenia= request.getParameter("contrasenia");

        UsuarioDao usuarioDao = new UsuarioDao();
        RequestDispatcher requestDispatcher;

        String action = request.getParameter("action") == null ? "ingresar" : request.getParameter("action");

        switch (action) {
            case "ingresar":
                String correo = request.getParameter("correo");
                String password = request.getParameter("password");
                RequestDispatcher view;

                Usuario usuario = usuarioDao.validar(correo, password);

                if (usuario != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("usuarioLogueado", usuario);
                    //session.setMaxInactiveInterval(900);

                    view = request.getRequestDispatcher("viajes/list.jsp");
                    view.forward(request, response);
                }
                else  {
                    response.sendRedirect(request.getContextPath() + "/UsuarioServlet?error");
                }
                break;

            case "crear":
                Usuario usuario1 = new Usuario();
                String codigo= request.getParameter("codigo");
                String nombre = request.getParameter("nombre");
                String apellido = request.getParameter("apellido");
                int edad = Integer.parseInt(request.getParameter("edad"));
                String correo1 = request.getParameter("correo");
                String especialidad = request.getParameter("especialidad");
                String password1 = request.getParameter("password");

                usuario1.setCodigopucp(codigo);
                usuario1.setNombre(nombre);
                usuario1.setApellido(apellido);
                usuario1.setEdad(edad);
                usuario1.setCorreopucp(correo1);
                usuario1.setEspecialidad(especialidad);
                usuario1.setContrasenia(password1);

                usuarioDao.crear(usuario1);

                response.sendRedirect(request.getContextPath() + "/UsuarioServlet?registro");

                break;
        }

    }

}
