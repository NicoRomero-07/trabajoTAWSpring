<%-- 
    Document   : listaComprador
    Created on : 22-abr-2022, 10:29:30
    Author     : Nicolás Zhao (100%)
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page import="es.trabajotaw.trabajotaw.entity.ListaUsuario" %>
<%@ page import="es.trabajotaw.trabajotaw.entity.Usuario" %>
<%--
<%@page import="Usuario"%>
<%@page import="ListaUsuario"%>
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nueva lista</title>
    </head>
    <%
            ListaUsuario listaComprador = (ListaUsuario)request.getAttribute("listaComprador");
            Boolean error = (Boolean) request.getAttribute("error");
            Map<Usuario,List<ListaUsuario>> relaciones = (Map<Usuario,List<ListaUsuario>>) request.getAttribute("relaciones");
    %>    
    <body>
        <jsp:include page="cabeceraMarketing.jsp" /> 
        <h1>Datos de la lista</h1>
        <%
            if (error== null || !error){
           
        %>
        <form method="POST" action="save">
            <input type="hidden" name="id" value="<%= listaComprador==null? "": listaComprador.getIdListaUsuario() %>" />
            <table>
                <tr>
                    <th>Nombre:</th>
                    <td><input type="text" size="30" name="nombre" value="<%= listaComprador==null? "": listaComprador.getNombre() %>" required/></td>
                </tr>
                <tr>
                    <th>Compradores: </th>
                </tr>
                    <%                  
                        String checked;
                        for (Map.Entry<Usuario,List<ListaUsuario>> entry : relaciones.entrySet()){
                            List<ListaUsuario> listasRelacionadas = entry.getValue();
                            checked = "";
                            if(listaComprador != null && listasRelacionadas != null && listasRelacionadas.contains(listaComprador)){
                                checked = "checked";
                            }

                %>
                <tr><td><input type="checkbox" name="compradores" value="<%= entry.getKey().getIdUsuario() %>"  <%= checked %>/> <%= entry.getKey().getNombreUsuario()%></td></tr>
                <%
                        }
                        
                %>
                
            </table>
                <br/>
            <input type="submit" value="Confirmar" />
        </form>
            <%
                }else{
            %>
            <h2>NO SE HA ELEGIDO NINGUN COMPRADOR</h2>
            <%
                }
            %>
            <br/>
            <a href="ListaCompradorServlet"><input type="button" value="Volver"/></a>
    </body>
</html>
