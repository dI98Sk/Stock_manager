<%-- 
    Document   : welcome
    Created on : 3 ??? 2020 ?., 11:13:58
    Author     : Skakun
--%>

<%@page import="ru.sfedu.productturnover.model.Client"%>
<%String title="Welcome page";%>
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
            
            if(client==null)
                response.sendRedirect(ru.sfedu.ptu_web.Constants.LOGOUT);
        %>
        
        <h1>Welcome, <%=client.getLogin()%></h1>

<jsp:include page="<%=Constants.FOOTER%>" />