<%-- 
    Document   : create
    Created on : 15 мая 2020 г., 21:43:50
    Author     : Skakun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="ru.sfedu.productturnover.model.Client"%>
<%String title="Delivery";%>

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

            if(client==null || !(client.getRole()==Constants.ROLE_ADMIN || client.getRole()==Constants.ROLE_ACCEPTOR || client.getRole()==Constants.ROLE_SALER_ACCEPTOR)){
        %>
                <jsp:include page="<%=Constants.HTTP_FORBIDDEN%>" />
        <%
            }else{
        %>
            <h1><%=title%>: ${item.getItem().getName()} from ${item.getProvider().getName()}</h1>
            <a class="btn btn-outline-primary" href="<%=Constants.ADMIN_DELIVERY%>">Back</a>
            <c:if test="${item.getStatus()!=5}">
                &nbsp;&nbsp;&nbsp;&nbsp;
                <a class="btn btn-outline-success" href="<%=Constants.ADMIN_DELIVERY_EDIT%>/${not empty item ? item.getId() : ""}">Edit</a>
            </c:if>
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
                        <th scope="row">item</th>
                        <td>${item.getItem().getId()}:${item.getItem().getName()}</td>
                    </tr>
                    <tr>
                        <th scope="row">provider</th>
                        <td>${item.getProvider().getId()}:${item.getProvider().getName()}</td>
                    </tr>
                    <tr>
                        <th scope="row">dateStart</th>
                        <td>${item.getDatestart()}</td>
                    </tr>
                    <tr>
                        <th scope="row">dateEnd</th>
                        <td>${item.getDateend()}</td>
                    </tr>
                    <tr>
                        <th scope="row">number</th>
                        <td>${item.getNumber()/1000}</td>
                    </tr>
                    <tr>
                        <th scope="row">status</th>
                        <td>${item.stringifyStatus()}</td>
                    </tr>
                    <tr>
                        <th scope="row">price</th>
                        <td>${item.getPrice()/100}</td>
                    </tr>
                </tbody>
            </table>
            <c:if test="${item.getStatus()!=5}">
                <br><br><a href="<%=Constants.ADMIN_DELIVERY_DELETE%>/${not empty item ? item.getId() : ""}" onclick="if(confirm('Are you sure?')){return true;}else{return false;}" class="btn btn-outline-danger">Delete!</a>
            </c:if>
            
        <%}%>

<jsp:include page="<%=Constants.FOOTER%>" />