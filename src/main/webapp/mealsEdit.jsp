<%--
  Created by IntelliJ IDEA.
  User: blaec
  Date: 27\10\18
  Time: 10:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Meal</title>
</head>
<body>
    <h1>Edit Meal</h1>
    <form>
        <table width="50%">
            <tr>
                <td width="10%">Description</td>
                <td width="90%">
                    <input type="text" name="description" value="name"/>
                </td>
            </tr>
            <tr>
                <td width="10%">DateTime</td>
                <td width="90%">
                    <input type="text" name="dateTime"/>
                </td>
            </tr>
            <tr>
                <td width="10%">Calories</td>
                <td width="90%">
                    <input type="text" name="calories"/>
                </td>
            </tr>
        </table>
        <p>
            <input type="submit" name="Submit" value="Submit meal"/>
            <input type="submit" name="Cancel" value="Cancel form"/>
        </p>
    </form>
</body>
</html>
