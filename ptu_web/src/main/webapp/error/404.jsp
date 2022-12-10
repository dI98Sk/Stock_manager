<%-- 
    Document   : welcome
    Created on : 3 ??? 2020 ?., 11:13:58
    Author     : Skakun
--%>
<%@ page language="java" isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%String title="Page not found";%>

<%@page import="ru.sfedu.ptu_web.Constants"%>

<jsp:include page="<%=Constants.HEADER%>">
    <jsp:param name="title" value="<%=title%>"/>
</jsp:include>

<div class="container py-5">
        <div class="row">
            <div class="col-md-2 text-center">
                <p><i aria-hidden="true" class="fa fa-exclamation-triangle fa-5x"></i><br/>Status Code: 404</p>
            </div>
            <div class="col-md-10">
                <h3>OPPSSS!!!! Sorry...</h3>
                <p>Page not found.</p>
                <a class="btn btn-danger" href="javascript:history.back()">Go Back</a>
            </div>
        </div>
    </div>

<jsp:include page="<%=Constants.FOOTER%>" />