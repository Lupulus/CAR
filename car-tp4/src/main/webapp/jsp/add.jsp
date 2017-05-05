<%@page import="car.tp4.entity.Book"%>
<%@page import="java.util.Collection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        <h2>Adding books</h2>
         <form action="/add" method="POST">
            Title : <input name="title"> <br/>
            Author : <input name="author"> <br/>
            Year : <input type="number" name="year"> <br/>
            Stock : <input type="number" name="stock"> <br/>
            <br/>
            <input type="submit" value="Add">
        </form>
    </body>
</html>
