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
        <h2>Existing books</h2>
        <%
            Collection<Book> books = (Collection<Book>) request.getAttribute("books");
            if (books.isEmpty()) {
                    out.print("<tr><td colspan='5'>No book listed</td></tr>");
            }
            for (Book book: books) {
                out.print("Author: " + book.getAuthor() + ", Title: " + book.getTitle() + "\n");
                out.print("Year: " + book.getYear() + ", Stock: " + book.getStock(););
                out.print("<a href='/add_cart?id=" + book.getId() + "'>Add to cart</a>")
                out.print("\n");
            }
        %>
    </body>
</html>
