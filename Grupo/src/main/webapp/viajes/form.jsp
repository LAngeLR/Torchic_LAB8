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

    <div class="container">
        <hr>
        <h2>Crear Viaje</h2>
        <form method="POST" action="<%=request.getContextPath()%>/ViajesServlet?action=guardar">
            <div class="form-group">
                <label for="FechaReserva">Fecha de Reserva:</label>
                <input type="date" class="form-control" id="FechaReserva" name="FechaReserva" placeholder="Ingrese la fecha de reserva" required>
            </div>
            <div class="form-group">
                <label for="FechaViaje">Fecha de Viaje:</label>
                <input type="date" class="form-control" id="FechaViaje" naem="FechaViaje" placeholder="Ingrese la fecha de viaje" required>
            </div>
            <div class="form-group">
                <label for="CiudadOrigen">Ciudad de Origen:</label>
                <input type="text" class="form-control" id="CiudadOrigen" name="CiudadOrigen" placeholder="Ingrese la ciudad de origen" required>
            </div>
            <div class="form-group">
                <label for="CiudadDestino">Ciudad de Destino:</label>
                <input type="text" class="form-control" id="CiudadDestino" name="CiudadDestino" placeholder="Ingrese la ciudad de destino" required>
            </div>
            <div class="form-group">
                <label for="EmpresaSeguro">Empresa de Seguro:</label>
                <select required name="EmpresaSeguro" id="EmpresaSeguro" placeholder="Seguro" class="form-control">
                    <option value="">Seleccione una opción</option>
                    <%for (int i=0;i<=listaSeguros.size()-1;i++){%>
                    <option value="<%=listaSeguros.get(i)%>"><%=listaSeguros.get(i)%></option>
                    <%}%>
                </select>
            </div>
            <div class="form-group">
                <label for="CantBoleto">Número de Boletos:</label>
                <input type="number" class="form-control" id="CantBoleto" name="CantBoleto" placeholder="Ingrese el número de boletos" required>
            </div>
            <div class="form-group">
                <label for="CostoTotal">Costo Total del Viaje:</label>
                <input type="number" class="form-control" id="CostoTotal" name="CostoTotal" placeholder="Ingrese el costo total" required>
            </div>
            <button type="submit" class="btn btn-primary">Guardar</button>
            <a href="<%=request.getContextPath()%>/ViajesServlet" class="btn btn-danger">Cancelar</a>
        </form>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <!--
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>-->
<script>
    function validarFormulario() {
        var cantBoletos = parseInt(document.getElementById("CantBoletos").value);
        var costoTotal = parseInt(document.getElementById("CostoTotal").value);

        if (isNaN(cantBoletos) || cantBoletos <= 0) {
            alert("Cantidad no válida");
            return false;
        }

        if (isNaN(costoTotal) || costoTotal <= 0) {
            alert("Lo ingresado no es válido");
            return false;
        }
        return true;
    }
</script>

</body>
</html>

