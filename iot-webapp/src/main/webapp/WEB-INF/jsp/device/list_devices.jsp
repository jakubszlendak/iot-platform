<%@ include file="/WEB-INF/template/includes.jsp" %>

<form action="/device/remove" method="post" id="employeeForm" role="form" >
    <c:choose>
        <c:when test="${not empty deviceList}">
            <c:set var="count" value="0" scope="page" />

            <div class="panel-group" id="accordion">
                <c:forEach var="device" items="${deviceList}">
                    <c:set var="count" value="${count+1}" scope="page" />
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion" href="#collapse${count}">
                                    ${device.producerName} ${device.name} (${device.deviceClass.className})
                                </a>
                            </h4>
                        </div>
                        <div id="collapse${count}" class="panel-collapse collapse in">
                            <div class="panel-body">
                                <table class="table table-hover">
                                    <thead>
                                        <th>ID</th>
                                        <th>Measurand</th>
                                        <th>Unit</th>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="sensor" items="${device.sensors}">
                                            <tr>
                                                <td>${sensor.ID}</td>
                                                <td>${sensor.measurand.measurandName}</td>
                                                <td>${sensor.unit}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </c:forEach>






            </div>
        </c:when>
        <c:otherwise>
            <br>  </br>
            <div class="alert alert-info">
               You have no devices yet!
            </div>
        </c:otherwise>
    </c:choose>
</form>