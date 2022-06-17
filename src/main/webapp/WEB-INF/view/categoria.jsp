<%-- 
    Document   : categoria
    Created on : 30-abr-2022, 14:13:46
    Author     : nicor
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@page import="es.trabajotaw.trabajotaw.entity.Categoria"%>
<%@page import="es.trabajotaw.trabajotaw.dto.CategoriaDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>

    <body>
        <jsp:include page="cabecera.jsp" /> 
        <a href="/administrador/administrarCategorias">Volver</a>
        <h1>Datos del usuario</h1>
        <form:form method="POST" action="/administrador/guardarCategoria" modelAttribute="categoria">
            <form:hidden path ="idCategoria" />
            Nombre: <form:input type="text" size="30" path="nombre" /> <br/>
            <form:button>Enviar</form:button>
        </form:form>
    </body>
</html>
