<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<c:url var="registerUrl" value="/register"/>

<div id="surveyForm">
<h2>Register for National Park Geek</h2>

<form:form id="registrationForm" action="${registerUrl}" method="POST" modelAttribute="user">
    <div class="form-group">
        <label for="username">Username</label>
        <form:input class="form-control" path="username" placeholder="Username"/>
        <form:errors path="username" class="text-danger font-weight-bold"/>
    </div>
    <div class="form-group">
        <label for="password">Password</label>
        <form:password class="form-control" path="password" placeholder="Password"/>
        <form:errors path="password" class="text-danger font-weight-bold"/>
    </div>
    <div class="form-group">
        <label for="confirmPassword">Confirm Password</label>
        <form:password class="form-control" path="confirmPassword" placeholder="Confirm Password"/>
        <form:errors path="passwordMatching" class="text-danger font-weight-bold"/>
    </div>
        <button type="submit" class="btn btn-primary">Save User</button>
</form:form>

</div>



<c:import url="/WEB-INF/jsp/common/footer.jsp" />