<%-- 
    Document   : form
    Created on : 12 мая 2020 г., 8:59:06
    Author     : Skakun
--%>

<%@page import="ru.sfedu.ptu_web.Constants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1>${not empty item ? "Edit type" : "Create new type"}</h1>
<a class="btn btn-outline-primary" href="<%=Constants.ADMIN_TYPE%>">Back</a><br><br>
<form method="post" action="<%=Constants.SR_ADMIN_TYPE%>${not empty item ? item.getId() : ""}">
  <div class="form-group">
    <label for="name">Name</label>
    <input type="text" name="name" class="form-control" placeholder="Enter name" value="${not empty item ? item.getName() : ""}">
  </div>
  <div class="form-group">
    <label for="description">Description</label>
    <textarea name="description" class="form-control" rows="3">${not empty item ? item.getDescription() : ""}</textarea>
  </div>
  <button type="submit" name="submit" value="submit" class="btn btn-outline-warning">Save</button>
</form>