<%-- 
    Document   : index
    Created on : 11 ??? 2020 ?., 17:15:44
    Author     : sp2
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="ru.sfedu.productturnover.model.Client"%>
<%String title="Selling list";%>

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

            if(client==null || !(client.getRole()==Constants.ROLE_ADMIN || client.getRole()==Constants.ROLE_SALER || client.getRole()==Constants.ROLE_SALER_ACCEPTOR)){
        %>
                <jsp:include page="<%=Constants.HTTP_FORBIDDEN%>" />
        <%
            }else{
        %>
            <h1><%=title%></h1>
            <table class="table table-hover">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Item</th>
                        <th scope="col">Client</th>
                        <th scope="col">Number</th>
                        <th scope="col">Date</th>
                        <th scope="col">Price</th>
                        <th scope="col">Status</th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${itemList==null}">
                        <tr>
                            <td colspan="7">
                                Data is empty
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${itemList!=null}">
                        <c:forEach var="item" items="${itemList}" >
                            <tr class="${item.getStatus()==2?"table-success":(item.getStatus()==0?"table-danger":"")}">
                                <th scope="row">${item.getId()}</th>
                                <td>${item.getItem().getId()}:${item.getItem().getName()}(${item.getItem().getBalance()/1000})</td>
                                <td>${item.getClient().getId()}:${item.getClient().getName()}</td>
                                <td>${item.getNumber()/1000}</td>
                                <td>${item.getDate()}</td>
                                <td>${item.getPrice()/100}</td>
                                <td>${item.stringifyStatus()}</td>
                                <td>
                                    <a href="<%=Constants.ADMIN_SELLING%>/${item.getId()}"><img src="<%=Constants.IMAGES%>visibility-24px.svg"></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </tbody>
            </table>
        <%}%>
<jsp:include page="<%=Constants.FOOTER%>" />