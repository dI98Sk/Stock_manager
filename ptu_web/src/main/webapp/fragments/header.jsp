<%-- 
    Document   : header
    Created on : 3 мая 2020 г., 17:04:42
    Author     : Skakun
--%>
<%@page import="java.util.regex.Pattern"%>
<%@page import="ru.sfedu.ptu_web.Constants"%>
<%@page import="ru.sfedu.productturnover.model.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%String title = request.getParameter("title");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%if(title!=null){out.println(title);}%></title>
        
        <link rel="stylesheet" type="text/css" href="<%=Constants.URL%>css/bootstrap.css">
        <script src="<%=Constants.URL%>js/jquery.min.js"></script>
        <script src="<%=Constants.URL%>js/pto.js"></script>
        <script src="<%=Constants.URL%>js/bootstrap.min.js"></script>
    </head>
    <body>            
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="<%=Constants.INDEX%>">PTO</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item<%if((Constants.URL==request.getRequestURL().toString())||Pattern.matches(Constants.INDEX,request.getRequestURL())){out.print(" active");}%>"><a class="nav-link" href="<%=Constants.INDEX%>">Home</a></li>
                    <% 
                    if(session.getAttribute("client")==null){
                    %>
                    <li class="nav-item">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Sign Up
                        </a>
                        <div class="dropdown-menu">
                            <form class="px-4 py-3" method="post" action="<%=Constants.SR_REGISTRATION%>">
                                <div class="form-group">
                                    <label for="name">Name</label>
                                    <input type="text" name="name" class="form-control" placeholder="Enter your name" required="required">
                                </div>
                                <div class="form-group">
                                    <label for="login">Login</label>
                                    <input type="text" name="login" class="form-control" required="required" placeholder="Enter your login">
                                </div>
                                <div class="form-group">
                                    <label for="password">Password</label>
                                    <input type="password" name="password" class="form-control" required="required" placeholder="Enter your password">
                                </div>
                                <div class="form-group">
                                    <label for="conf_pass">Confirm password</label>
                                    <input type="password" name="conf_pass" class="form-control" required="required" placeholder="Confirm your password">
                                </div>
                                <button type="submit" class="btn btn-primary">Sign up</button>
                            </form>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Sign In
                        </a>
                        <div class="dropdown-menu">
                            <form class="px-4 py-3" method="post" action="<%=Constants.SR_LOGIN%>">
                                <div class="form-group">
                                    <label for="login">Login</label>
                                    <input type="text" name="login" class="form-control" placeholder="Enter your login" required="required">
                                </div>
                                <div class="form-group">
                                    <label for="password">Password</label>
                                    <input type="password" name="password" class="form-control" placeholder="Enter your password" required="required">
                                </div>
                                <button type="submit" class="btn btn-primary">Sign in</button>
                            </form>
                        </div>
                    </li>
                    <%}else if(((Client)session.getAttribute("client")).getRole()>=Constants.ROLE_SALER){%>
                    <li class="nav-item dropdown<%if(Pattern.matches(Constants.ADMINISTRATION+".*",request.getRequestURL())){out.print(" active");}%>">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Administration</a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <a class="dropdown-item<%if(Pattern.matches(Constants.ADMINISTRATION,request.getRequestURL())){out.print(" active");}%>" href="<%=Constants.ADMINISTRATION%>">Main</a>
                            <div class="dropdown-divider"></div>
                            <%if(((Client)session.getAttribute("client")).getRole()==Constants.ROLE_ADMIN){%>
                                <a class="dropdown-item<%if(Pattern.matches(Constants.ADMIN_TYPE+".*",request.getRequestURL())){out.print(" active");}%>" href="<%=Constants.ADMIN_TYPE%>">Type</a>
                                <a class="dropdown-item<%if(Pattern.matches(Constants.ADMIN_PROVIDER+".*",request.getRequestURL())){out.print(" active");}%>" href="<%=Constants.ADMIN_PROVIDER%>">Provider</a>
                                <a class="dropdown-item<%if(Pattern.matches(Constants.ADMIN_USER+".*",request.getRequestURL())){out.print(" active");}%>" href="<%=Constants.ADMIN_USER%>">User</a>
                                <a class="dropdown-item<%if(Pattern.matches(Constants.ADMIN_ITEM+".*",request.getRequestURL())){out.print(" active");}%>" href="<%=Constants.ADMIN_ITEM%>">Item</a>
                            <%}
                            if(((Client)session.getAttribute("client")).getRole()==Constants.ROLE_ACCEPTOR || ((Client)session.getAttribute("client")).getRole()==Constants.ROLE_SALER_ACCEPTOR || ((Client)session.getAttribute("client")).getRole()==Constants.ROLE_ADMIN){%>
                                <a class="dropdown-item<%if(Pattern.matches(Constants.ADMIN_DELIVERY+".*",request.getRequestURL())){out.print(" active");}%>" href="<%=Constants.ADMIN_DELIVERY%>">Delivery</a>
                            <%}
                            if(((Client)session.getAttribute("client")).getRole()==Constants.ROLE_SALER || ((Client)session.getAttribute("client")).getRole()==Constants.ROLE_SALER_ACCEPTOR || ((Client)session.getAttribute("client")).getRole()==Constants.ROLE_ADMIN){%>
                                <a class="dropdown-item<%if(Pattern.matches(Constants.ADMIN_SELLING+".*",request.getRequestURL())){out.print(" active");}%>" href="<%=Constants.ADMIN_SELLING%>">Selling</a>
                            <%}%>
                        </div>
                    </li>
                    <%}%>
                    <%if(session.getAttribute("client")!=null){%>
                        <li class="nav-item<%if(Pattern.matches(Constants.USER+".*",request.getRequestURL())){out.print(" active");}%>"><a class="nav-link" href="<%=Constants.USER%>">Profile</a></li>
                        <li class="nav-item<%if(Pattern.matches(Constants.ITEM+".*",request.getRequestURL())){out.print(" active");}%>"><a class="nav-link" href="<%=Constants.ITEM%>">Items</a></li>
                        <li class="nav-item<%if(Pattern.matches(Constants.SELLING+".*",request.getRequestURL())){out.print(" active");}%>"><a class="nav-link" href="<%=Constants.SELLING%>">Buying</a></li>
                    <%}%>
                    <li class="nav-item<%if(Pattern.matches(Constants.ABOUT,request.getRequestURL())){out.print(" active");}%>"><a class="nav-link" href="<%=Constants.ABOUT%>">About</a></li>
                    <li class="nav-item<%if(Pattern.matches(Constants.CONTACT,request.getRequestURL())){out.print(" active");}%>"><a class="nav-link" href="<%=Constants.CONTACT%>">Contact</a></li>
                </ul>
                <%if(session.getAttribute("client")!=null){%>
                    <ul class="nav navbar-nav navbar-right">
                        <li class="nav-item"><a class="nav-link" href="<%=Constants.LOGOUT%>">Logout</a></li>
                    </ul>
                <%}%>
            </div>
        </nav>
        <%
            if(session.getAttribute("info")!=null){
                out.print("<div class=\"alert alert-info fixed-top\" role=\"alert\">"+(String)session.getAttribute("info")+"</div>");
                session.removeAttribute("info");
            }
            if(session.getAttribute("success")!=null){
                out.print("<div class=\"alert alert-success fixed-top\" role=\"alert\">"+(String)session.getAttribute("success")+"</div>");
                session.removeAttribute("success");
            }
            if(session.getAttribute("warning")!=null){
                out.print("<div class=\"alert alert-warning fixed-top\" role=\"alert\">"+(String)session.getAttribute("warning")+"</div>");
                session.removeAttribute("warning");
            }
            if(session.getAttribute("error")!=null){
                out.print("<div class=\"alert alert-danger fixed-top\" role=\"alert\">"+(String)session.getAttribute("error")+"</div>");
                session.removeAttribute("error");
            }
        %>
        <main role="main" class="container bd-example">