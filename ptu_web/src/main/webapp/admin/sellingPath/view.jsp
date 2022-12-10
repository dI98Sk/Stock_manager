<%-- 
    Document   : create
    Created on : 15 мая 2020 г., 21:43:50
    Author     : Skakun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="ru.sfedu.productturnover.model.Client"%>
<%String title="Selling";%>

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

            if(client==null || !(client.getRole()==Constants.ROLE_ADMIN || client.getRole()==Constants.ROLE_SALER || client.getRole()==Constants.ROLE_SALER_ACCEPTOR)){
        %>
                <jsp:include page="<%=Constants.HTTP_FORBIDDEN%>" />
        <%
            }else{
        %>
            <h1><%=title%>: ${item.getItem().getName()} from ${item.getClient().getName()}</h1>
            <a class="btn btn-outline-primary" href="<%=Constants.ADMIN_SELLING%>">Back</a>
            <br>
            <c:if test="${item.getStatus()==1}">
                <form method="post" action="<%=Constants.SR_ADMIN_SELLING%>${not empty item ? item.getId() : ""}">
                    <div class="form-group">
                        <c:if test="${item.getPrice()==0}">
                            <label>Change item price</label>
                            <input type="text" name="price" class="form-control" placeholder="Enter a price" value="0.00">
                        </c:if>
                        <label>Change item status</label>
                        <select class="form-control" name="status">
                            <option value="0" ${item.getStatus()==0?"selected":""}>Canceled</option>
                            <option value="1" ${item.getStatus()==1?"selected":""}>Waiting</option>
                            <option value="2" ${item.getStatus()==2?"selected":""}>Received</option>
                        </select>
                    </div>
                    <button type="submit" name="submit" value="submit" class="btn btn-outline-warning" onclick="if(confirm('Are you sure?')){ return true;} else {return false;}">Save</button>
                </form>
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
                        <td>${item.getItem().getId()}:${item.getItem().getName()}(${item.getItem().getBalance()/1000})</td>
                    </tr>
                    <tr>
                        <th scope="row">client</th>
                        <td>${item.getClient().getId()}:${item.getClient().getName()}</td>
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
        <%}%>

<jsp:include page="<%=Constants.FOOTER%>" />