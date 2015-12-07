<%-- 
    Document   : addAutor
    Created on : 6/12/2015, 03:42:19 PM
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
        <form name="add" method="POST">
            <input type="text" name="id" value="0" />
            <input type="text" name="nombre" value="" />
            <input type="text" name="pais" value="" />
            <input type="reset" value="Reset" />
            <input type="submit" value="Enviar" />
        </form>


        <%
            if(request.getParameter("nombre")!=null && !request.getParameter("nombre").equals("") )
            {
                AutorDAO ad=new AutorDAO();
                //out.println(request.getParameter("id"));  
                //out.println(request.getParameter("nombre"));
                //out.println(request.getParameter("pais"));
                Autor nuevo=new Autor();
                nuevo.setId(Integer.parseInt(request.getParameter("id")));
                nuevo.setNombre(request.getParameter("nombre"));
                nuevo.setPais(request.getParameter("pais"));
                
                ad.insert(nuevo);
            }  
        %>
    </body>
    
</html>

