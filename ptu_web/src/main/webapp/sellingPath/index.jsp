<%-- 
    Document   : index
    Created on : 11 ??? 2020 ?., 17:15:44
    Author     : sp2
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="ru.sfedu.productturnover.model.Client"%>
<%String title="Buying list";%>

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

            if(client==null){
        %>
                <jsp:include page="<%=Constants.HTTP_FORBIDDEN%>" />
        <%
            }else{
        %>
            <h1><%=title%></h1>
            <a class="btn btn-outline-success" href="<%=Constants.SELLING_CREATE%>">Buy new Item</a><br><br>
            <table class="table table-hover">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Item</th>
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
                        <c:forEach var="item" varStatus="i" items="${itemList}" >
                            <tr class="${item.getStatus()==0?"table-danger":(item.getStatus()==2?"table-success":"")}">
                                <th scope="row">${i.count}</th>
                                <td>${item.getItem().getName()}</td>
                                <!--<td>${item.getClient().getName()}</td>-->
                                <td>${item.getNumber()/1000}</td>
                                <td>${item.getDate()}</td>
                                <td>${item.getPrice()/100}</td>
                                <td>${item.stringifyStatus()}</td>
                                <td>
                                    <a href="<%=Constants.SELLING%>/${item.getId()}"><img src="<%=Constants.IMAGES%>visibility-24px.svg"></a>
                                    <c:if test="${item.getStatus()==1}">
                                        <a href="<%=Constants.SELLING_EDIT%>/${item.getId()}"><img src="<%=Constants.IMAGES%>edit-24px.svg"></a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </tbody>
            </table>
        <%}%>
<jsp:include page="<%=Constants.FOOTER%>" />