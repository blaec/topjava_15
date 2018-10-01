<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
<table border="1">
    <caption>Lists user meals</caption>
    <tr>
        <th>Date/Time</th>
        <th>Description</th>
        <th>Calories</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <jsp:useBean id="meals" scope="request" type="java.util.List<ru.javawebinar.topjava.model.MealWithExceed>"/>
    <c:forEach var="meal" items="${meals}">
        <tr style="${meal.exceed ? 'color: green':'color: red'}">
            <td><javatime:format value="${meal.dateTime}" pattern="dd/MM/yy HH:mm" /></td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td>Edit</td>
            <td>Delete</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>