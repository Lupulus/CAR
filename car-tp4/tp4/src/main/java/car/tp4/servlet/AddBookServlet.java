package car.tp4.servlet;

import car.tp4.entity.Book;
import car.tp4.entity.BookBean;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add")
public class AddBookServlet extends HttpServlet {

	  @EJB
	  private BookBean bookBean;
	  
	  @Override
	  protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/add.jsp");
	    dispatcher.forward(request, response);
	  }
	  
	  @Override
	  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			try {
				String author = notNullParameter(request, "author", "Author's name is not given");
				String title = notNullParameter(request, "title", "Title's name is not given");
				String yearS = notNullParameter(request, "year", "Year is not given");
				int year = intParameter(yearS, "Year number is not integer");
				String stockS = notNullParameter(request, "stock", "Stock is not given");
				int stock = intParameter(stockS, "Stock number is not integer");

				bookBean.addBook(new Book(author, title, year, stock));

				response.sendRedirect("/list");
			} catch (RuntimeException e) {
				request.setAttribute("error", e.getMessage());
				getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(request, response);
				return;
			}

	  }
	  
	  protected String notNullParameter(HttpServletRequest request, 
				String parameter, String messageError) {
			
			String param = request.getParameter(parameter);
			
			if (param == null) {
				throw new RuntimeException(messageError);
			}
			
			return param;
	}
	  
	 protected int intParameter(String integer, String messageError) {
			
			int res = 0;
			try {
				res = Integer.parseInt(integer);
			} catch (NumberFormatException e) {
				throw new RuntimeException(messageError);
			}
			
			return res;
	}
}
