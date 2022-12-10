<%-- 
    Document   : create
    Created on : 15 мая 2020 г., 21:43:50
    Author     : Skakun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="ru.sfedu.productturnover.model.Client"%>
<%String title="User";%>

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

            if(client==null || client.getRole()!=Constants.ROLE_ADMIN){
        %>
                <jsp:include page="<%=Constants.HTTP_FORBIDDEN%>" />
        <%
            }else{
        %>
            <h1><%=title%>: ${item.getName()}</h1>
            <a class="btn btn-outline-primary" href="<%=Constants.ADMIN_USER%>">Back</a>
            <c:if test="${item.getRole()!=99}">
                <br>
                <form method="post" action="<%=Constants.SR_ADMIN_USER%>${not empty item ? item.getId() : ""}">
                    <div class="form-group">
                        <label>Change user role</label>
                        <select class="form-control" name="role">
                            <option value="1" ${item.getRole()==1?"selected":""}>User</option>
                            <option value="2" ${item.getRole()==2?"selected":""}>Saler</option>
                            <option value="3" ${item.getRole()==3?"selected":""}>Acceptor</option>
                            <option value="4" ${item.getRole()==4?"selected":""}>Saler+Acceptor</option>
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
                        <th scope="row">name</th>
                        <td>${item.getName()}</td>
                    </tr>
                    <tr>
                        <th scope="row">Login</th>
                        <td>${item.getLogin()}</td>
                    </tr>
                    <tr>
                        <th scope="row">Role</th>
                        <td>${item.stringifyRole()}</td>
                    </tr>
                </tbody>
            </table>
        <%}%>

<jsp:include page="<%=Constants.FOOTER%>" />