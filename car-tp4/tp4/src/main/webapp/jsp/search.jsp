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
        <h2>Searching book(s)</h2>
          <form action="/search" method="post">
            Search request : <input name="search_request"> <br/>
            Filter by :
            <input type="radio" name="search_mode" value="title" checked="checked"> Title
            <input type="radio" name="search_mode" value="author"> Author<br/>
            Sort by Date <input type="checkbox" name="sort" checked="checked"><br/>
            <input type="submit" value="Search">
        </form>
    </body>
</html>
