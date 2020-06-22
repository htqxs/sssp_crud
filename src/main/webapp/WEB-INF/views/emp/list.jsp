<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>list</title>
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script>
        $(function () {
            $(".delete").click(function () {
                var lastName = $(this).next("input:hidden").val();
                var id = $(this).next("input:hidden").attr("emp_id");
                if(confirm("确定要删除" + lastName + "的信息吗")){
                    $.ajax({
                        url:"${pageContext.request.contextPath}/emp/" + id,
                        type:"DELETE",
                        success:function () {
                            location.reload();
                        }
                    });
                }
            });
        })
    </script>
</head>
<body>
    <c:if test="${page == null || page.numberOfElements == 0}">
        没有任何记录！
    </c:if>
    <c:if test="${page != null && page.numberOfElements > 0}">
        <table border="1" cellpadding="10" cellspacing="0">
            <tr>
                <th>ID</th>
                <th>LastName</th>
                <th>Email</th>
                <th>Birth</th>
                <th>CreateTime</th>
                <th>Department</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <c:forEach items="${page.content}" var="emp">
                <tr>
                    <td>${emp.id}</td>
                    <td>${emp.lastName}</td>
                    <td>${emp.email}</td>
                    <td>
                        <fmt:formatDate value="${emp.birth}" pattern="yyyy-MM-dd"/>
                    </td>
                    <td>
                        <fmt:formatDate value="${emp.createTime}" pattern="yyyy-MM-dd hh:mm"/>
                    </td>
                    <td>${emp.department.departmentName}</td>
                    <td><a href="${pageContext.request.contextPath}/emp/${emp.id}">Edit</a></td>
                    <td>
                        <a href="javascript:void(0)" class="delete">Delete</a>
                        <input type="hidden" emp_id="${emp.id}" value="${emp.lastName}"/>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="8">
                    共${page.totalElements}条记录
                    共${page.totalPages}页
                    当前${page.number + 1}页
                    <a href="?pageNum=${page.number + 1 - 1 < 1 ? 1 : page.number + 1 - 1}">上一页</a>
                    <a href="?pageNum=${page.number + 1 + 1}">下一页</a>
                </td>
            </tr>
        </table>
    </c:if>
</body>
</html>
