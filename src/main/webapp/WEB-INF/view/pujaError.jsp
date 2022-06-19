<%-- 
    Document   : pujaError
    Created on : 13-may-2022, 13:34:04
    Author     : Victor
--%>

<%@page import="es.trabajotaw.trabajotaw.dto.ProductoDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>puja Error</title>
    </head>
    <body>
        <h1>ERROR</h1>
        <%
          ProductoDTO p = (ProductoDTO) request.getAttribute("producto");
        %>
        No puedes pujar con una cantidad menor al precio actual<br><br>
        <a href="/comprador/verPuja/<%=p.getIdProducto()%>"><button> De acuerdo</button></a>
    </body>
</html>
