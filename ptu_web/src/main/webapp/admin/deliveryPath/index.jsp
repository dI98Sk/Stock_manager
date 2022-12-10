<%-- 
    Document   : index
    Created on : 11 ??? 2020 ?., 17:15:44
    Author     : sp2
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="ru.sfedu.productturnover.model.Client"%>
<%String title="Delivery list";%>

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

            if(client==null || !(client.getRole()==Constants.ROLE_ADMIN || client.getRole()==Constants.ROLE_ACCEPTOR || client.getRole()==Constants.ROLE_SALER_ACCEPTOR)){
        %>
                <jsp:include page="<%=Constants.HTTP_FORBIDDEN%>" />
        <%
            }else{
        %>
            <h1><%=title%></h1>
            <a class="btn btn-outline-success" href="<%=Constants.ADMIN_DELIVERY_CREATE%>">Create new</a><br><br>
            <table class="table table-hover">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Item</th>
                        <th scope="col">Provider</th>
                        <th scope="col">Date Start</th>
                        <th scope="col">Date End</th>
                        <th scope="col">Number</th>
                        <th scope="col">Status</th>
                        <th scope="col">Price</th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${itemList==null}">
                        <tr>
                            <td colspan="9">
                                Data is empty
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${itemList!=null}">
                        <c:forEach var="item" items="${itemList}" >
                            <tr class="${item.getStatus()==5?"table-success":(item.getStatus()==4?"table-warning":(item.getStatus()==3?"table-info":(item.getStatus()==2?"table-secondary":"")))}">
                                <th scope="row">${item.getId()}</th>
                                <td>${item.getItem().getId()}:${item.getItem().getName()}</td>
                                <td>${item.getProvider().getId()}:${item.getProvider().getName()}</td>
                                <td>${item.getDatestart()}</td>
                                <td>${item.getDateend()}</td>
                                <td>${item.getNumber()/1000}</td>
                                <td>${item.stringifyStatus()}</td>
                                <td>${item.getPrice()/100}</td>
                                <td>
                                    <a href="<%=Constants.ADMIN_DELIVERY%>/${item.getId()}"><img src="<%=Constants.IMAGES%>visibility-24px.svg"></a>
                                    <c:if test="${item.getStatus()!=5}">
                                        <a href="<%=Constants.ADMIN_DELIVERY_EDIT%>/${item.getId()}"><img src="<%=Constants.IMAGES%>edit-24px.svg"></a>
                                        <a href="<%=Constants.ADMIN_DELIVERY_DELETE%>/${item.getId()}" onclick="if(confirm('Are you sure?')){return true;}else{return false;}"><img src="<%=Constants.IMAGES%>delete_forever-24px.svg"></a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </tbody>
            </table>
        <%}%>
<jsp:include page="<%=Constants.FOOTER%>" />