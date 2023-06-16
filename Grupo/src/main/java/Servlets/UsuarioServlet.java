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
                if (usuario != null && (usuario.getIdusuario()) != 0) {
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
                Usuario usuario1 = new Usuario();
                String idusuario = request.getParameter("idusuario");
                String nombre = request.getParameter("nicknameTrabajador");
                String apellido = request.getParameter("nombreTrabajador");
                String edad = request.getParameter("apellidoTrabajador");
                String codigopucp = request.getParameter("correo");
                String correopucp = request.getParameter("contrasenia");
                String especialidad = request.getParameter("especialidad");
                String contrasenia = request.getParameter("contrasenia");

                usuarioDao.crear(usuario1);
                response.sendRedirect(request.getContextPath());

                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String correopucp = req.getParameter("inputcorreo");
        String contrasenia= req.getParameter("inputcontrasenia");

        UsuarioDao usuarioDao = new UsuarioDao();
        RequestDispatcher view;

        String action = req.getParameter("action") == null ? "ingresar" : req.getParameter("action");

        switch (action) {
            case "ingresar":
                Usuario usuario = usuarioDao.validar(correopucp, contrasenia);

                if (usuario != null) {
                    HttpSession session = req.getSession();
                    session.setAttribute("usuarioSession", usuario);

                    //session.setMaxInactiveInterval(300);

                    resp.sendRedirect(req.getContextPath());
                } else {
                    req.setAttribute("error", "Usuario o contrasenia incorrectos");
                    req.getRequestDispatcher("index.jsp").forward(req, resp);
                }
                break;

            case "agregar":
                view = req.getRequestDispatcher("/registro_usuario.jsp");
                view.forward(req,resp);
                break;
        }

    }

}
