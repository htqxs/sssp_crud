<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>add</title>
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script>
        $(function () {
            $("#lastName").change(function () {
                var val = $(this).val().trim();
                $(this).val(val);

                //若修改后的lastName与之前的lastName相同，则不发送ajax请求
                var oldLastName = $("#old_lastName").val().trim();
                if(oldLastName != null && oldLastName != "" && oldLastName == val){
                    alert("lastName 可用!");
                    return;
                }

                var url = "${pageContext.request.contextPath}/ajaxValidaLastName";
                var args = {"lastName":val,"date":new Date()};
                $.post(url, args, function (data) {
                    if(data == "0"){
                        alert("lastName 可用!");
                    } else if(data == "1"){
                        alert("lastName 不可用!");
                    } else {
                        alert("出错啦!");
                    }
                });
            });
        })
    </script>
</head>
<body>

    <%--<c:set value="${pageContext.request.contextPath}/emp" var="url"/>
    <c:if test="${employee.id != null}">
        <c:set value="${pageContext.request.contextPath}/emp/${employee.id}" var="url"/>
    </c:if>--%>
    <form:form action="${pageContext.request.contextPath}/emp" method="post" modelAttribute="employee">
        <c:if test="${employee.id != null}">
            <input id = "old_lastName" type="hidden" value="${employee.lastName}"/>
            <form:hidden path="id"/>
            <input type="hidden" name="_method" value="PUT"/>
        </c:if>
        LastName: <form:input path="lastName" id="lastName"/><br/>
        Email: <form:input path="email"/><br/>
        Birth: <form:input path="birth"/><br/>
        Department:
            <form:select path="department.id" items="${departments}" itemLabel="departmentName" itemValue="id"/><br/>
        <input type="submit" value="Submit"/>
    </form:form>
</body>
</html>
