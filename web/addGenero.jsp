<%-- 
    Document   : addGenero
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
        <form name="add" method="POST">
            <input type="text" name="id" value="0" />
            <input type="text" name="nombre" value="" />
            <input type="reset" value="Reset" />
            <input type="submit" value="Enviar" />
        </form>


        <%
            if(request.getParameter("nombre")!=null && !request.getParameter("nombre").equals("") )
            {
                GeneroDAO gd=new GeneroDAO();
                //out.println(request.getParameter("id"));  
                //out.println(request.getParameter("nombre"));
                Genero nuevo=new Genero();
                nuevo.setId(Integer.parseInt(request.getParameter("id")));
                nuevo.setNombre(request.getParameter("nombre"));
                
                gd.insert(nuevo);
            }  
        %>
    </body>
    
</html>
