<%-- 
    Document   : eliminarAutor
    Created on : 6/12/2015, 10:29:15 PM
    Author     : Seb
--%>

<%@page import="com.tania.anime.model.Autor"%>
<%@page import="com.tania.anime.model.AutorDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            AutorDAO ad = new AutorDAO();
            Autor a;
            if (request.getParameter("gid") != null && request.getParameter("id") == null) {
                a = ad.select("*", "id=" + request.getParameter("gid"));
        %>
        <form name="add" method="POST" action="eliminarAutor.jsp">
            <input type="hidden" name="id" value="<%= a.getId()%>" />
            <span><%= a.getId()%></span> 
            <input type="hidden" name="nombre" value="<%= a.getNombre()%>" />
            <span><%= a.getNombre() %></span> 
            <input type="hidden" name="pais" value="<%= a.getPais()%>" />
            <span><%= a.getPais() %></span> 
            <input type="submit" value="Eliminar" />
        </form>
        <%
        } else if (request.getParameter("gid") == null && request.getParameter("id") == null) {
        %>
        No hubo parametros
        <%
            } else if (request.getParameter("gid") == null && request.getParameter("id") != null) {
                a=new Autor();
            
                a.setId(Integer.parseInt(request.getParameter("id")));
                a.setNombre(request.getParameter("nombre"));
                a.setPais(request.getParameter("Pais"));
                
                ad.delete(a);
                
            }
        %>
    </body>

</html>

