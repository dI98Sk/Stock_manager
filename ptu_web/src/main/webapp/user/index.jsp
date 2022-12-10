<%-- 
    Document   : index
    Created on : 10 ??? 2020 ?., 16:11:31
    Author     : Skakun
--%>

<%@page import="ru.sfedu.productturnover.model.Client"%>
<%@page import="ru.sfedu.ptu_web.Constants"%>
<%@page import="java.util.StringJoiner"%>
<%
    Client client=(Client)session.getAttribute("client");
            
    if(client==null)
        response.sendRedirect(ru.sfedu.ptu_web.Constants.LOGOUT);

    response.setHeader("Cache-control", "no-cache");
    response.setHeader("Cache-control", "no-store");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expire", 0);
    
    String title="User data";
%>

<jsp:include page="<%=Constants.HEADER%>">
    <jsp:param name="title" value="<%=title%>"/>
</jsp:include>

<form action="<%=Constants.SR_USER%>" method="post">
    <div class="form-group">
        <label for="login">Login: <%=client.getLogin()%></label>
    </div>
    <div class="form-group">
        <label for="name">Name</label>
        <input type="text" name="name" value="<%=client.getName()%>" class="form-control" placeholder="Enter your name" required="required">
    </div>
    <div class="form-group">
        <label for="password">Password</label>
        <input type="password" name="password" class="form-control" placeholder="Password">
    </div>
    <div class="form-group">
        <label for="new_password">New password</label>
        <input type="password" name="new_password" class="form-control" placeholder="New password">
    </div>
    <div class="form-group">
        <label for="confirm_password">Confirm new password</label>
        <input type="password" name="confirm_password" class="form-control" placeholder="Confirm new password">
    </div>
    <button type="submit" class="btn btn-primary">Save</button>
</form>

<jsp:include page="<%=Constants.FOOTER%>" />