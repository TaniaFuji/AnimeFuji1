<%-- 
    Document   : eliminarGenero
    Created on : 6/12/2015, 03:42:19 PM
    Author     : Seb
--%>
<%@page import="com.tania.anime.model.Genero"%>
<%@page import="com.tania.anime.model.GeneroDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            GeneroDAO gd = new GeneroDAO();
            Genero g;
            if (request.getParameter("gid") != null && request.getParameter("id") == null) {
                g = gd.select("*", "id=" + request.getParameter("gid"));
        %>
        <form name="add" method="POST" action="eliminarGenero.jsp">
            <input type="hidden" name="id" value="<%= g.getId()%>" />
            <span><%= g.getId()%></span> 
            <input type="hidden" name="nombre" value="<%= g.getNombre()%>" />
            <span><%= g.getNombre() %></span> 
            <input type="submit" value="Eliminar" />
        </form>
        <%
        } else if (request.getParameter("gid") == null && request.getParameter("id") == null) {
        %>
        No hubo parametros
        <%
            } else if (request.getParameter("gid") == null && request.getParameter("id") != null) {
                g=new Genero();
            
                g.setId(Integer.parseInt(request.getParameter("id")));
                g.setNombre(request.getParameter("nombre"));
                
                gd.delete(g);
                
            }
        %>
    </body>

</html>
