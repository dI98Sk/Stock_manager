<%-- 
    Document   : loguot
    Created on : 3 ??? 2020 ?., 11:25:29
    Author     : Skakun
--%>

<%String title="Logout";%>

<%@page import="ru.sfedu.ptu_web.Constants"%>
<jsp:include page="<%=Constants.HEADER%>">
    <jsp:param name="title" value="<%=title%>"/>
</jsp:include>

        <%
        session.invalidate();
        response.sendRedirect(ru.sfedu.ptu_web.Constants.INDEX);
        %>
        
        
<jsp:include page="<%=Constants.FOOTER%>" />