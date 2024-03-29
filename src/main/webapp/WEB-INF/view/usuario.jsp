<%-- 
    Document   : usuario
    Created on : 20-abr-2022, 17:12:51
    Author     : nicor(95%) Victor(5%)
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@page import="java.util.Iterator"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="es.trabajotaw.trabajotaw.entity.Categoria"%>
<%@page import="es.trabajotaw.trabajotaw.entity.Usuario"%>
<%@page import="es.trabajotaw.trabajotaw.entity.TipoUsuario"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@ page import="es.trabajotaw.trabajotaw.dto.TipoUsuarioDTO" %>
<%@ page import="es.trabajotaw.trabajotaw.dto.CategoriaDTO" %>
<%@ page import="es.trabajotaw.trabajotaw.dto.UsuarioDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%
        UsuarioDTO usuario = (UsuarioDTO) request.getAttribute("usuario");
        List<CategoriaDTO> categorias = (List) request.getAttribute("categorias");
    %>
    <body>
    <jsp:include page="cabecera.jsp" />

    <a methods="post" href="/administrador/administrarUsuarios">Volver</a>
    <h1>Datos del usuario</h1>
    <form:form method="POST" action="/administrador/guardarUsuario" modelAttribute="usuario">
        <form:hidden path="idUsuario"/>
        Nombre de Usuario: <form:input type="text" size="30" path="nombreUsuario" required="true" /> <br>
        Contraseña: <form:input type="password" size="30" path="contrasenya" required="true"/> <br>
        Nombre: <form:input type="text" size="30" path="nombre" required="true"/> <br>
        Apellidos: <form:input type="text" size="30" path="primerApellido" required="true"/> <form:input type="text" name="segundoApellido" size="30" path="segundoApellido" /><br>
        Email:<form:input type="text" size="40" path="email" required="true"/> <br>
        Sexo:
        <form:select path="sexo">
            <form:option value="H"/>
            <form:option value="M"/>
        </form:select>
        <br>
        Fecha Nacimiento: <form:input type="date" size="30" path="fechaNacimiento" required="true"/> <br>
        Tipo Usuario:
        <form:select path="tipoUsuario.idTipoUsuario">
            <form:options items="${tipoUsuarios}" itemLabel="tipo" itemValue="idTipoUsuario" />
        </form:select><br>
        <form:hidden path="categoriasFavoritasEntity"/>
        Categorias Favoritas:
        <form:checkboxes path="categoriasFavoritas" items="${categorias}" itemValue="idCategoria" itemLabel="nombre"/>
        <br>

        <form:hidden path="direccion.idDireccion"/>
        Tipo de via:
        <form:select name = "tipoVias" path="direccion.tipo">
            <form:option value="OFICINA"/>
            <form:option value="CALLE"/>
        </form:select><br>
        Calle:<form:input type="text" size="40" path="direccion.calle" /> <br>
        Numero:<form:input type="number" size="40" path="direccion.numero" /> <br>
        Codigo Postal:<form:input type="number" size="40" path="direccion.codigoPostal" /> <br>
        Planta:<form:input type="number" size="40" path="direccion.planta" /> <br>
        Puerta:<form:input type="text" size="40" path="direccion.puerta" /> <br>

        <form:button>Enviar</form:button>
    </form:form>
    </body>
</html>