package car.tp4.entity;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
@Local
public class BookBean {

  @PersistenceContext(unitName = "book-pu")
  private EntityManager entityManager;

  public void addBook(Book book) {
    entityManager.persist(book);
  }

  @SuppressWarnings("unchecked")
  public List<Book> getAllBooks() {
    Query query = entityManager.createQuery("SELECT m FROM Book as m");
    return query.getResultList();
  }
  
  @SuppressWarnings("unchecked")
  public List<Book> getByAuthor(String author){
	  	Query query = entityManager.createQuery("SELECT m FROM Book as m WHERE m.author = :author");
		query.setParameter("author", author);
		return query.getResultList();
  }
  
  @SuppressWarnings("unchecked")
  public List<Book> getByTitle(String title) {
    Query query = entityManager.createQuery("SELECT m FROM Book as m WHERE m.title LIKE :title");
    query.setParameter("title", "%" + title + "%");
    return query.getResultList();
  }
  
  public List<Book> getAvailableBooks(){
	  Query query = entityManager.createQuery("SELECT m FROM Book as m WHERE m.stock > 0");
	  return query.getResultList();
  }
  
  public Book getById(long id){
	  return entityManager.find(Book.class, id);
  }
}