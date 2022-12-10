<%-- 
    Document   : welcome
    Created on : 3 ??? 2020 ?., 11:13:58
    Author     : Skakun
--%>

<%@page import="ru.sfedu.productturnover.model.Client"%>
<%String title="Administration";%>

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

            if(client==null || client.getRole()<Constants.ROLE_SALER){
        %>
                <jsp:include page="<%=Constants.HTTP_FORBIDDEN%>" />
        <%}else{%>
            <h1>Welcome, <%=client.getLogin()%>, to Administrative panel</h1>
            <p>
                This is an administrative panel that is used to manage deliveries, goods, types of goods, suppliers, users and sales.
            </p>
        <%}%>
<jsp:include page="<%=Constants.FOOTER%>" />