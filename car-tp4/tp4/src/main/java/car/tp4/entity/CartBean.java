package car.tp4.entity;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
@Local
public class CartBean {
	
	@PersistenceContext(unitName = "cart-pu")
	private EntityManager entityManager;
	
	public Cart newCart(){
		return entityManager.merge(new Cart());
	}
	
	public Cart getById(long id) {
		return entityManager.find(Cart.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Cart> getValidatedShoppingCarts() {
		Query query = entityManager.createQuery("SELECT c FROM Cart as c WHERE c.validated is true");
		return query.getResultList();
	}
	
	
	
}