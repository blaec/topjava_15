<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Meal</title>
</head>
<body>
    <h3><a href="index.html">Home</a></h3>
    <h2>${param.action == 'create' ? 'Create meal' : 'Edit meal'}</h2>
    <hr>
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <form method="post" action="meals">
        <input type="hidden" name="id" value="${meal.id}">
        <table width="50%">
            <tr>
                <td width="10%">Description</td>
                <td width="90%">
                    <input type="text" name="description" value="${meal.description}" required/>
                </td>
            </tr>
            <tr>
                <td width="10%">DateTime</td>
                <td width="90%">
                    <input type="datetime-local" name="dateTime" value="${meal.dateTime}" required/>
                </td>
            </tr>
            <tr>
                <td width="10%">Calories</td>
                <td width="90%">
                    <input type="text" name="calories" value="${meal.calories}" required/>
                </td>
            </tr>
        </table>
        <p>
            <button type="submit">Save</button>
            <button onclick="window.history.back()" type="button">Cancel</button>
        </p>
    </form>
</body>
</html>
