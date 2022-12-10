<%-- 
    Document   : index
    Created on : 11 ??? 2020 ?., 17:15:44
    Author     : sp2
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="ru.sfedu.productturnover.model.Client"%>
<%String title="Type list";%>

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

            if(client==null || client.getRole()!=Constants.ROLE_ADMIN){
        %>
                <jsp:include page="<%=Constants.HTTP_FORBIDDEN%>" />
        <%
            }else{
        %>
            <h1><%=title%></h1>
            <a class="btn btn-outline-success" href="<%=Constants.ADMIN_TYPE_CREATE%>">Create new</a><br><br>
            <table class="table table-hover">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Name</th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${itemList==null}">
                        <tr>
                            <td colspan="3">
                                Data is empty
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${itemList!=null}">
                        <c:forEach var="item" items="${itemList}" >
                            <tr>
                                <th scope="row">${item.getId()}</th>
                                <td>${item.getName()}</td>
                                <td>
                                    <a href="<%=Constants.ADMIN_TYPE%>/${item.getId()}"><img src="<%=Constants.IMAGES%>visibility-24px.svg"></a>
                                    <a href="<%=Constants.ADMIN_TYPE_EDIT%>/${item.getId()}"><img src="<%=Constants.IMAGES%>edit-24px.svg"></a>
                                    <a href="<%=Constants.ADMIN_TYPE_DELETE%>/${item.getId()}" onclick="if(confirm('Are you sure?')){return true;}else{return false;}"><img src="<%=Constants.IMAGES%>delete_forever-24px.svg"></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </tbody>
            </table>
        <%}%>
<jsp:include page="<%=Constants.FOOTER%>" />