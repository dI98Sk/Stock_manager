<%-- 
    Document   : form
    Created on : 12 мая 2020 г., 8:59:06
    Author     : Skakun
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="ru.sfedu.ptu_web.Constants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1>${not empty item ? "Edit delivery" : "Create new delivery"}</h1>
<a class="btn btn-outline-primary" href="<%=Constants.ADMIN_DELIVERY%>">Back</a><br><br>
<form method="post" action="<%=Constants.SR_ADMIN_DELIVERY%>${not empty item ? item.getId() : ""}">
  <div class="form-group">
    <label class="my-1 mr-2" for="type">Item</label>
    <select name="item" class="custom-select my-1 mr-sm-2">
        <option value="0" ${not empty item && not empty item.getItem() ? "selected" : ""}>Choose...</option>
        <c:if test="${itemList!=null}">
            <c:forEach var="it" items="${itemList}" >
                <option value="${it.getId()}" ${not empty item && it.getId()==item.getItem().getId()? "selected" : ""}>${it.getName()}</option>
            </c:forEach>
        </c:if>
    </select>
  </div>
        
  <div class="form-group">
    <label class="my-1 mr-2" for="type">Provider</label>
    <select name="provider" class="custom-select my-1 mr-sm-2">
        <option value="0" ${not empty item && not empty item.getProvider() ? "selected" : ""}>Choose...</option>
        <c:if test="${providerList!=null}">
            <c:forEach var="provider" items="${providerList}" >
                <option value="${provider.getId()}" ${not empty item && provider.getId()==item.getProvider().getId()? "selected" : ""}>${provider.getName()}</option>
            </c:forEach>
        </c:if>
    </select>
  </div>      
        
  <div class="form-group">
    <label for="dateStart">Date Start</label>
    <input type="date" name="dateStart" class="form-control" placeholder="Choose date start" value="${not empty item ? item.getDatestart().toString() : ""}">
  </div>
  
  <div class="form-group">
    <label for="dateEnd">Date End</label>
    <input type="date" name="dateEnd" class="form-control" placeholder="Choose date end" value="${not empty item ? item.getDateend().toString() : ""}">
  </div>
  
  <div class="form-group">
    <label for="name">Number</label>
    <input type="text" name="number" class="form-control" placeholder="Enter a number" value="${not empty item ? item.getNumber()/1000 : ""}">
  </div>
  
  <div class="form-group">
    <label class="my-1 mr-2" for="type">Status</label>
    <select name="status" class="custom-select my-1 mr-sm-2">
        <option value="0" ${not empty item && not empty item.getStatus() ? "selected" : ""}>Choose...</option>
        <c:if test="${statusList!=null}">
            <c:forEach var="status" varStatus="i" items="${statusList}" >
                <option value="${i.count-1}" ${not empty item && (i.count-1)==item.getStatus()? "selected" : ""}>${status}</option>
            </c:forEach>
        </c:if>
    </select>
  </div> 
        
  <div class="form-group">
    <label for="name">Price</label>
    <input type="text" name="price" class="form-control" placeholder="Enter a price" value="${not empty item ? item.getPrice()/100 : ""}">
  </div>
  
  <button type="submit" name="submit" value="submit" class="btn btn-outline-warning">Save</button>
</form>