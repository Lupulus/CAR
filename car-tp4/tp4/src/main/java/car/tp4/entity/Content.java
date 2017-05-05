package car.tp4.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Représente un élément d'un panier d'achat, c'est à dire un couple
 * (identifiant d'un livre ; quantité)
 */
@Entity
public class Content {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	private long shoppingCartId;
	private long bookId;
	private int quantity;
	
	/**
	 * Créer une nouvelle élément de panier d'achat.
	 * @param scId l'identifiant du panier d'achat
	 * @param bId l'identifiant du livre
	 * @param qtt la quantitée du livre dans le panier d'achat
	 */
	public Content(long scId, long bId, int qtt) {
		setBookId(bId);
		setShoppingCartId(scId);
		setQuantity(qtt);
	}
	
	public long getId() {
		return id;
	}

	public long getShoppingCartId() {
		return shoppingCartId;
	}

	protected void setShoppingCartId(long shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}

	public long getBookId() {
		return bookId;
	}

	protected void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}