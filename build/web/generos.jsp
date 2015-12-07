<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : generos
    Created on : 6/12/2015, 03:22:16 PM
    Author     : Seb
--%>

<%@page import="java.util.List"%>
<%@page import="com.tania.anime.model.Genero"%>
<%@page import="com.tania.anime.model.GeneroDAO"%>
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
            GeneroDAO gd = new GeneroDAO();
            List<Genero> lista = gd.selectAll("*", "1=1");
        %>
        <ul>

            <%
                for (Genero genero : lista) {
            %>
            <li><%= genero.getId() %> <%= genero.getNombre() %></li>   
            <%
                }
            %>
        </ul>
    </body>
</html>
