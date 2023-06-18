<%@ page import="Servlets.ViajesServlet" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Beans.Viajes" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<Viajes> listaViajes = (ArrayList<Viajes>) request.getAttribute("listarViajes");
    ArrayList<ViajesServlet> listarFiltro = (ArrayList<ViajesServlet>) request.getAttribute("listarFiltro");
%>
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css'/>
    <title>LABORATORIO</title>
</head>
<body>
<div class='container'>
    <div class="row mb-5 mt-4">
        <div class="col-lg-6">
            <h1 class=''>Lista de Viajes</h1>
        </div>
        <div class="col-lg-6 my-auto text-lg-right">
            <a href="<%= request.getContextPath()%>/ViajesServlet?action=crear" class="btn btn-primary">Crear
                Viaje</a>
        </div>
        <form method="post" action="<%= request.getContextPath()%>/ViajesServlet?action=buscar" class="row">
            <div class="col-lg-3">

                <select name="tipo" id="tipo" placeholder="tipo" class="form-control">
                    <%for (int i=0;i<=listarFiltro.size()-1;i++){%>
                    <option value="<%=listarFiltro.get(i)%>"><%=listarFiltro.get(i)%></option>
                    <%}%>
                </select>
            </div>

            <div class="col-lg-5">
                <input type="text" class="form-control" name="buscar">
            </div>
            <div class="col-lg-2">
                <button type="submit" class="btn btn-primary">Buscar</button>
            </div>
            <div class="col-lg-2">
                <a href="<%= request.getContextPath()%>/ViajesServlet" class="btn btn-danger">Limpiar
                    Búsqueda</a>
            </div>
        </form>
    </div>
    <table class="table">
        <tr>
            <th>id</th>
            <th>Fecha Reserva</th>
            <th>Fecha Viaje</th>
            <th>Ciudad Origen</th>
            <th>Ciudad Destino</th>
            <th>Empresa Seguro</th>
            <th>Nro Boletos</th>
            <th>Costo total</th>
        </tr>
        <%
            int i=1;
            for (Viajes viajes: listaViajes){%>
        <tr>
            <td><%=i%></td>
            <td><%=viajes.getIdViaje()%></td>
            <td><%=viajes.getFechaReserva()%></td>
            <td><%=viajes.getFechaViaje()%></td>
            <td><%=viajes.getCiudadOrigen()%></td>
            <td><%=viajes.getCiudadDestino()%></td>
            <td><%=viajes.getEmpresaSeguro()%></td>
            <td><%=viajes.getCantBoletos()%></td>
            <td><%=viajes.getCostoTotal()%></td>
            <td>
                <a href="<%=request.getContextPath()%>/ViajesServlet?action=borrar&id=<%=viajes.getIdViaje()%>">
                    Borrar
                </a>
            </td>

            <%i++;
            }%>
        </tr>
    </table>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>
</html>
