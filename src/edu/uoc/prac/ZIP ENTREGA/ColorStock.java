package edu.uoc.prac;

/**
 * 
 * @author DPOO
 *
 */
public class ColorStock {

	private int amount;
	
	private Color color;
	private Stock stock;
	
	/**
	 * 
	 * @param color
	 */
	public ColorStock (Color color, Stock stock, Integer amount) { //S'ha afegit amount al constructor, ja que no he trobat cap altra forma d'afegir el valor al atribut "amount" de l'ArrayList "colors" 
		this.amount = amount;
		this.color = color;
		this.stock = stock;
	}
	
	/**
	 * 
	 * @param amount
	 */
	public void increase (Integer amount) {
		// TODO
	}

	/**
	 * 
	 * @param amount
	 */
	public void decrease (Integer amount) {
		// TODO
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @return the stock
	 */
	public Stock getStock() {
		return stock;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String s;
		s = color.getName() + " " + "(" + this.getAmount() + ")"; 
		return s;
	}
}