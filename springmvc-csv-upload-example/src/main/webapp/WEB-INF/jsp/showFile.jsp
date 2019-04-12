<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h3>File Uploaded to:</h3>
<c:if test="${not empty serverFilePath}">
    ${serverFilePath} 
</c:if>

<h3>CSV Entries</h3>
<table>
	<tr>
		<th>Id</th>
		<th>Name</th>
		<th>Account Type</th>
		<th>Amount</th>
	</tr>
	
	<c:forEach var="entry" items="${csvFileEntries}">
		<tr>
			<td><c:out value="${ entry.id }" /></td>
			<td><c:out value="${ entry.name }" /></td>
			<td><c:out value="${ entry.accountType }" /></td>
			<td><c:out value="${ entry.amount }" /></td>
		</tr>
	</c:forEach>
	
</table>