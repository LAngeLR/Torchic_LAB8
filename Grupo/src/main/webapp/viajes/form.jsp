<%@ page import="Servlets.ViajesServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%ArrayList<ViajesServlet> listaSeguros = (ArrayList<ViajesServlet>) request.getAttribute("listaSeguros");%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css'/>
    <title>LAB 9</title>
</head>
<body>
<nav class="navbar navbar-expand-md navbar-light bg-light">
    <a class="navbar-brand" href="#">Formulario registro viajes</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
        <ul class="navbar-nav">
            <li class="nav-item" >
                <a class="nav-link" href="<%=request.getContextPath()%>/ViajesServlet">Viajes</a>
            </li>
        </ul>
    </div>
</nav>
<div class='container'>
    <div class="row mb-4">
        <div class="col"></div>
        <div class="col-md-6">
            <h1 class='mb-3'>Crear un Viaje</h1>
            <form method="POST" action="<%=request.getContextPath()%>/ViajesServlet?action=guardar">

                <div class="form-group">
                    <label>Fecha</label>
                    <input required class="form-control datetimepicker" id="fecha" name="FechaViaje"
                           type="date"/>
                </div>

                <div >
                    <label>Seguro</label>
                    <select required name="Seguro" id="Pais" placeholder="Seguro" class="form-control">
                        <option value="">Seleccione una opción</option>
                        <%for (int i=0;i<=listaSeguros.size()-1;i++){%>
                        <option value="<%=listaSeguros.get(i)%>"><%=listaSeguros.get(i)%></option>
                        <%}%>
                    </select>
                </div>

                <div class="form-group">
                    <label>Cantidad Boletos</label>
                    <input required type="number" class="form-control" name="boletos">
                </div>

                <button type="submit" class="btn btn-primary">Guardar</button>
                <a href="<%=request.getContextPath()%>/ViajesServlet" class="btn btn-danger">Cancelar</a>
            </form>
        </div>
        <div class="col"></div>
    </div>
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

