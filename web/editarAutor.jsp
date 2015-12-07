<%-- 
    Document   : editarAutor
    Created on : 6/12/2015, 11:22:40 PM
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
        <form name="add" method="POST" action="editarAutor.jsp">
            <input type="text" name="id" value="<%= a.getId()%>" />
            <input type="text" name="nombre" value="<%= a.getNombre()%>" />
            <input type="text" name="pais" value="<%= a.getPais()%>" />
            <input type="reset" value="Reset" />
            <input type="submit" value="Enviar" />
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
                a.setNombre(request.getParameter("pais"));
                
                ad.update(a);
                
            }
        %>
    </body>

</html>
