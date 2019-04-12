<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!-- The form should post to your Controller.  The name of input type=file should be name of MultipartFile parameter
     enctype must be set to  multipart/form-data -->
 <form method="POST" action="uploadFile" enctype="multipart/form-data">
    File to upload: <input type="file" name="csvFile" >
    <input type="submit" value="Upload">
</form>

<c:if test="${not empty message}">
    ${message} 
</c:if>
