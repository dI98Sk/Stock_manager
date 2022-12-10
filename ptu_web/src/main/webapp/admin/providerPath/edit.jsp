<%-- 
    Document   : create
    Created on : 15 мая 2020 г., 21:43:50
    Author     : Skakun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="ru.sfedu.productturnover.model.Client"%>
<%String title="Edit provider";%>

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
        
        <jsp:include page="form.jsp" />
        
        <%}%>

<jsp:include page="<%=Constants.FOOTER%>" />