<%-- 
    Document   : form
    Created on : 12 мая 2020 г., 8:59:06
    Author     : Skakun
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="ru.sfedu.ptu_web.Constants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1>${not empty item ? "Edit item" : "Create new item"}</h1>
<a class="btn btn-outline-primary" href="<%=Constants.ADMIN_ITEM%>">Back</a><br><br>
<form method="post" action="<%=Constants.SR_ADMIN_ITEM%>${not empty item ? item.getId() : ""}">
  <div class="form-group">
    <label for="name">Name</label>
    <input type="text" name="name" class="form-control" placeholder="Enter name" value="${not empty item ? item.getName() : ""}">
  </div>
  <div class="form-group">
    <label for="description">Description</label>
    <textarea name="description" class="form-control" rows="3">${not empty item ? item.getDescription() : ""}</textarea>
  </div>
  <div class="form-group">
    <label class="my-1 mr-2" for="type">Type</label>
    <select name="type" class="custom-select my-1 mr-sm-2" id="inlineFormCustomSelectPref">
        <option value="0" ${not empty item && not empty item.getType() ? "selected" : ""}>Choose...</option>
        <c:if test="${typeList!=null}">
            <c:forEach var="type" items="${typeList}" >
                <option value="${type.getId()}" ${not empty item && type.equals(item.getType())? "selected" : ""}>${type.getName()}</option>
            </c:forEach>
        </c:if>
    </select>
  </div>
  <button type="submit" name="submit" value="submit" class="btn btn-outline-warning">Save</button>
</form>