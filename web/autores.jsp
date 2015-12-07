<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : autores
    Created on : 6/12/2015, 03:22:16 PM
    Author     : Seb
--%>

<%@page import="java.util.List"%>
<%@page import="com.tania.anime.model.Autor"%>
<%@page import="com.tania.anime.model.AutorDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>JSP Page</title>
    </head>
    <body>
        <%
            AutorDAO ad = new AutorDAO();
            List<Autor> lista = ad.selectAll("*", "1=1");
        %>
        <ul>

            <%
                for (Autor autor : lista) {
            %>
            <li><%= autor.getId() %> <%= autor.getNombre() %> <%= autor.getPais() %></li>   
            <%
                }
            %>
        </ul>
    </body>
</html>

