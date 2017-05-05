package car.tp4.entity;


import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	private boolean validated = false;
	private ArrayList<Book> books;
	
	public Cart() {}
	
	public long getId() {
		return id;
	}


	public boolean isValidated() {
		return validated;
	}
	

	public void setValidated(boolean validated) {
		this.validated = validated;
	}
	
	public void addBook(Book nBook){
		books.add(nBook);
	}
	
	public void removeBook(Book rBook){
		books.remove(rBook);
	}
	
}
