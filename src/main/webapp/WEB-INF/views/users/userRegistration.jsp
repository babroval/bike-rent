<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="signin">

	<h2>Registration Form</h2>

	<form:form method="POST" modelAttribute="user">
		<p>* required fields</p>

		<fieldset>
			<form:input type="hidden" path="id" />
			<p>
				<label>Login *</label>
				<c:choose>
					<c:when test="${edit}">
						<form:input type="hidden" path="login" />
						<label>${user.login}</label>
					</c:when>
					<c:otherwise>
						<form:input path="login" autofocus="true" />
						<form:errors path="login" cssClass="err" />
					</c:otherwise>
				</c:choose>
			</p>
			<p>
				<label>E-mail *</label>
				<form:input type="email" path="email" />
				<form:errors path="email" cssClass="err" />
			</p>
			<p>
				<label>Password *</label>
				<form:password path="password" />
				<form:errors path="password" cssClass="err" />
			</p>
			<p>
				<label> Retype Password *</label>
				<form:password path="retypePassword" />
				<form:errors path="retypePassword" cssClass="err" />
			</p>
		</fieldset>
		<fieldset>
			<legend>Personal info</legend>
			<p>
				<label>First Name *</label>
				<form:input path="firstName" />
				<form:errors path="firstName" cssClass="err" />
			</p>
			<p>
				<label>Last Name *</label>
				<form:input path="lastName" />
				<form:errors path="lastName" cssClass="err" />
			</p>
			<p>
				<label>Phone </label>
				<form:input path="tel" pattern="[^a-z-A-Z?]+" />
			</p>
			<p>
				<label>City *</label>
				<form:input path="city" />
				<form:errors path="city" cssClass="err" />
			</p>
			<p>
				<label>Country *</label>
				<form:select path="country">
					<option value="">--- Select ---</option>
					<c:forEach items="${allCountries}" var="country">
						<option value="${country}"
							${country == user.country ? 'selected':''}>${country}</option>
					</c:forEach>
				</form:select>
				<form:errors path="country" cssClass="err" />
			</p>
		</fieldset>
		<fieldset>
			<legend>Additional info</legend>
			<p>
				<span class="pol">Gender</span>
				<form:radiobutton path="gender" value="male" />
				<label>male</label>
				<form:radiobutton path="gender" value="female" />
				<label>female</label>
			</p>
			<p class="red">
				<label>Date of birth (dd.MM.yyyy)</label>
				<form:input path="birth" />
				<form:errors path="birth" cssClass="err" />
			</p>
		</fieldset>
		<p>
			<input type="checkbox" id="check" name="terms" required /> <label
				for="check">I agree with <a href="#">terms</a></label>

			<c:choose>
				<c:when test="${edit}">
					<input type="submit" value="Update" />
				</c:when>
				<c:otherwise>
					<input type="submit" value="Register" />
				</c:otherwise>
			</c:choose>

			<a href="<c:url value='/'/>">Cancel</a>
		</p>

	</form:form>

</div>