<%-- 
    Document   : create
    Created on : 15 мая 2020 г., 21:43:50
    Author     : Skakun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="ru.sfedu.productturnover.model.Client"%>
<%String title="Buying";%>

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
            <h1><%=title%>: ${item.getItem().getName()}</h1>
            <h3>Status: ${item.getStatus()==1 && item.getPrice()==0?"Waiting...":(item.getStatus()==1?"Considered. You can pick up the order on the specified date.":(item.getStatus()==0?"Canceled":"Received"))}</h3>
            <a class="btn btn-outline-primary" href="<%=Constants.SELLING%>">Back</a>&nbsp;&nbsp;&nbsp;&nbsp;
            <c:if test="${not empty item && item.getStatus()==1}">
                <a class="btn btn-outline-success" href="<%=Constants.SELLING_EDIT%>/${item.getId()}">Edit</a>
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
<!--                    <tr>
                        <th scope="row">id</th>
                        <td>${item.getId()}</td>
                    </tr>-->
                    <tr>
                        <th scope="row">item</th>
                        <td>${item.getItem().getName()}</td>
                    </tr>
                    <tr>
                        <th scope="row">number</th>
                        <td>${item.getNumber()/1000}</td>
                    </tr>
                    <tr>
                        <th scope="row">date</th>
                        <td>${item.getDate()}</td>
                    </tr>
                    <tr>
                        <th scope="row">price</th>
                        <td>${item.getPrice()/100}</td>
                    </tr>
                </tbody>
            </table>
            <c:if test="${not empty item && item.getStatus()==1}">
                <br><br><a href="<%=Constants.SELLING_DELETE%>/${not empty item ? item.getId() : ""}" onclick="if(confirm('Are you sure?')){return true;}else{return false;}" class="btn btn-outline-danger">Delete!</a>
            </c:if>
        <%}%>

<jsp:include page="<%=Constants.FOOTER%>" />