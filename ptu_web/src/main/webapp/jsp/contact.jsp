<%-- 
    Document   : welcome
    Created on : 3 ??? 2020 ?., 11:13:58
    Author     : Skakun
--%>

<%String title="Welcome page";%>

<%@page import="ru.sfedu.ptu_web.Constants"%>
<jsp:include page="<%=Constants.HEADER%>">
    <jsp:param name="title" value="<%=title%>"/>
</jsp:include>

        <h1>Our contacts</h1>
        <p>
            Address: Rostov-on-Don
        </p>

<jsp:include page="<%=Constants.FOOTER%>" />