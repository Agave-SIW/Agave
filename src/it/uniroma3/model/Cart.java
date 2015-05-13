package it.uniroma3.model;

import java.util.LinkedList;
import java.util.List;


public class Cart {

	private List<CartLine> cartLines;

	
	public Cart() {
		this.cartLines = new LinkedList<CartLine>();
	}

	public List<CartLine> getCartLines() {
		return cartLines;
	}

	public void setCartLines(List<CartLine> cartLines) {
		this.cartLines = cartLines;
	}
	
	public boolean addCartLine(CartLine cartline){
		return this.cartLines.add(cartline);
	}
	
	public CartLine removeCartLine(int index){
		return this.cartLines.remove(index);
	}
	
	public int getIndex(Product product){
		return this.cartLines.indexOf(product);
	}

	@Override
	public String toString() {
		return "Cart [cartLines="
				+ cartLines + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cartLines == null) ? 0 : cartLines.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		if (cartLines == null) {
			if (other.cartLines != null)
				return false;
		} else if (!cartLines.equals(other.cartLines))
			return false;
		return true;
	}	

}
