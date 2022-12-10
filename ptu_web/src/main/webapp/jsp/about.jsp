<%-- 
    Document   : welcome
    Created on : 3 ??? 2020 ?., 11:13:58
    Author     : Skakun
--%>

<%String title="About";%>
<%@page import="ru.sfedu.ptu_web.Constants"%>
<jsp:include page="<%=Constants.HEADER%>">
    <jsp:param name="title" value="<%=title%>"/>
</jsp:include>
        
        <h1>Information about this application</h1>
        <p>
            The application is aimed at helping to work with the remains of goods in warehouses.
        </p>
        <p>
            It allows you to create and edit types of goods, suppliers, goods, sales and deliveries.
        </p>

<jsp:include page="<%=Constants.FOOTER%>" />