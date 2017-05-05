package car.tp4.servlet;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import car.tp4.entity.Book;
import car.tp4.entity.BookBean;

@WebServlet("/search")
public class SearchBookServlet extends HttpServlet {

	  @EJB
	  private BookBean bookBean;

	
	  @Override
	  protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
			getServletContext().getRequestDispatcher("/jsp/search.jsp").forward(request, response);
	  }
	   	
		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			String searchRequest = request.getParameter("search_request");
			String searchMode = request.getParameter("search_mode");
			List<Book> searchResult ;
			
			if(searchRequest != null) {
				if(searchRequest.isEmpty()) {
					
					searchResult = bookBean.getAvailableBooks();
				}
				else if ("title".equals(searchMode))
					searchResult = bookBean.getByTitle(searchRequest);
				else if ("author".equals(searchMode))
					searchResult = bookBean.getByAuthor(searchRequest);
				else {
					request.setAttribute("error", "No mode selected");
					getServletContext().getRequestDispatcher("/jsp/search.jsp").forward(request, response);
					return;
				}
			}else {
				request.setAttribute("error", "Request not found");
				getServletContext().getRequestDispatcher("/jsp/search.jsp").forward(request, response);
				return;
			}
			
			if(request.getParameter("sort") != null) {
				searchResult = searchResult.subList(0, searchResult.size());
				searchResult.sort(new Comparator<Book>() {

					@Override
					public int compare(Book b1, Book b2) {
						return Integer.compare(b1.getYear(), b2.getYear());
					}
				
				});
			}
			request.setAttribute("books", searchResult);
			request.getRequestDispatcher("/jsp/book.jsp").forward(request, response);
	}
}
