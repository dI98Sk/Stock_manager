<%-- 
    Document   : index
    Created on : 2 ??? 2020 ?., 15:41:13
    Author     : Skakun
--%>
<%@page import="ru.sfedu.ptu_web.Constants"%>
<%@page import="java.util.StringJoiner"%>

<%String title="Home page";%>

<jsp:include page="<%=Constants.HEADER%>">
    <jsp:param name="title" value="<%=title%>"/>
</jsp:include>

<h1>Warehouse management system</h1>

<p>
    This system is designed to help manage warehouse space and balance management.
</p>

<jsp:include page="<%=Constants.FOOTER%>" />
