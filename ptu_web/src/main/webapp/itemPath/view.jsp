<%-- 
    Document   : create
    Created on : 15 мая 2020 г., 21:43:50
    Author     : Skakun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="ru.sfedu.productturnover.model.Client"%>
<%String title="Item";%>

<%@page import="ru.sfedu.ptu_web.Constants"%>
<jsp:include page="<%=Constants.HEADER%>">
    <jsp:param name="title" value="<%=title%>"/>
</jsp:include>

<%
            Client client=(Client)session.getAttribute("client");
//            response.setHeader("Cache-control", "no-cache");
//            response.setHeader("Cache-control", "no-store");
//            response.setHeader("Pragma", "no-cache");
//            response.setDateHeader("Expire", 0);

            if(client==null){
        %>
                <jsp:include page="<%=Constants.HTTP_FORBIDDEN%>" />
        <%
            }else{
        %>
            <h1><%=title%>: ${item.getName()}</h1>
            <a class="btn btn-outline-primary" href="<%=Constants.ITEM%>">Back</a>
            <br><br>
            <table class="table table-hover">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">Field</th>
                        <th scope="col">Value</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th scope="row">id</th>
                        <td>${item.getId()}</td>
                    </tr>
                    <tr>
                        <th scope="row">name</th>
                        <td>${item.getName()}</td>
                    </tr>
                    <tr>
                        <th scope="row">description</th>
                        <td>${item.getDescription()}</td>
                    </tr>
                    <tr>
                        <th scope="row">type</th>
                        <td>${item.getType().getName()}</td>
                    </tr>
                </tbody>
            </table>
        <%}%>

<jsp:include page="<%=Constants.FOOTER%>" />