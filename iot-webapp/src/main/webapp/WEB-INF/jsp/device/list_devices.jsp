<%@ include file="/WEB-INF/template/includes.jsp" %>

<form action="/device/remove" method="post" id="employeeForm" role="form" >
    <c:choose>
        <c:when test="${not empty deviceList}">
            <table  class="table table-striped">
                <thead>
                <tr>
                    <td>#</td>
                    <td>Device class</td>
                    <td>Name</td>
                    <td>Producer</td>
                </tr>
                </thead>
                <c:forEach var="device" items="${deviceList}">
                    <c:set var="classSucess" value=""/>
                    <c:if test ="${idEmployee == employee.id}">
                        <c:set var="classSucess" value="info"/>
                    </c:if>
                    <tr class="${classSucess}">
                        <td>${device.ID}</td>
                        <td>${device.deviceClass.className}</td>
                        <td>${device.name}</td>
                        <td>${device.producerName}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <br>  </br>
            <div class="alert alert-info">
                No people found matching your search criteria
            </div>
        </c:otherwise>
    </c:choose>
</form>