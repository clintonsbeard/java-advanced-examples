<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

<c:url var="imgUrl" value="/image/testImage" />
<img src="${imgUrl}" />

<c:if test="${not empty message}">
    ${message} 
</c:if>

<c:import url="/WEB-INF/jsp/footer.jsp" />