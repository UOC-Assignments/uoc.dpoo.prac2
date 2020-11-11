package edu.uoc.prac;

/**
 * User valoration of an Order
 * 
 * @author DPOO
 *
 */
public class Rate {

	private Integer rating;
	private String text;
	private Order order;
	
	/**
	 * 
	 * @param value
	 * @param text
	 */
	public Rate (Integer value, String text, Order order) {
		this.rating = value;
		this.text = text;
	}

	/**
	 * @return the ratting
	 */
	public Integer getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	public Order getOrder () {
		return order;
	}
	
}
