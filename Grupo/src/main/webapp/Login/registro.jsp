<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 17/06/2023
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

  <head>
    <title>Nuevo Registro</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <style>
      body {
        background-color: #f8f9fa;
      }

      .container {
        max-width: 500px;
        margin: 50px auto;
        background-color: #ffffff;
        padding: 30px;
        border-radius: 5px;
        box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
      }

      .form-group label {
        font-weight: bold;
      }

      .btn-register {
        background-color: #007bff;
        color: #ffffff;
        border: none;
      }

      .btn-register:hover {
        background-color: #0069d9;
      }
    </style>
  </head>

  <body>
    <div class="container">
      <h1>Nuevo registro</h1>

      <form method="post" action="<%=request.getContextPath()%>/UsuarioServlet?action=crear">
        <div class="form-group">
          <label for="nombre">Nombre:</label>
          <input type="text" class="form-control" id="nombre" name="nombre" required="required" placeholder="Ingrese su nombre">
        </div>

        <div class="form-group">
          <label for="apellido">Apellido:</label>
          <input type="text" class="form-control" id="apellido" name="apellido" required="required" placeholder="Ingrese su apellido">
        </div>

        <div class="form-group">
          <label for="edad">Edad:</label>
          <input type="number" class="form-control" id="edad" name="edad" required="required" placeholder="Ingrese su edad">
        </div>

        <div class="form-group">
          <label for="codigo">Código PUCP:</label>
          <input type="number" class="form-control" id="codigo" name="codigo" required="required" placeholder="Ingrese su código PUCP">
        </div>

        <div class="form-group">
          <label for="correo">Correo PUCP:</label>
          <input type="email" class="form-control" id="correo" name="correo" required="required" placeholder="Ingrese su correo">
        </div>

        <div class="form-group">
          <label for="especialidad">Especialidad:</label>
          <input type="text" class="form-control" id="especialidad" name="especialidad" required="required" placeholder="Ingrese su especialidad">
        </div>

        <div class="form-group">
          <label for="password">Contraseña:</label>
          <input type="password" class="form-control" id="password" name="password" required="required" placeholder="Ingrese su contraseña">
        </div>

        <div class="form-group">
          <label for="confirmar-contrasena">Confirmar Contraseña:</label>
          <input type="password" class="form-control" id="confirmar-contrasena" required="required" placeholder="Confirme su contraseña">
        </div>

        <button type="submit" class="btn btn-primary btn-register">Registrarse</button>
      </form>
    </div>

    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

    <script>
      document.getElementById('registro-form').addEventListener('submit', function(event) {
        var contrasenaInput = document.getElementById('contrasena');
        var contrasenaError = document.getElementById('contrasena-error');
        var contrasena = contrasenaInput.value;

        // Validar
        var uppercaseRegex = /[A-Z]/;
        var numberRegex = /[0-9]/;
        var specialCharRegex = /[!@#$%^&*()_+\-=[\]{};':"\\|,.<>/?]/;

        if (!uppercaseRegex.test(contrasena) || !numberRegex.test(contrasena) || !specialCharRegex.test(contrasena)) {
          contrasenaError.textContent = 'La contraseña debe tener al menos una letra mayúscula, un número y un carácter especial';
          event.preventDefault(); // Evitar el envío del formulario si la validación falla
        } else {
          contrasenaError.textContent = '';
        }
      });
    </script>


  </body>

</html>
