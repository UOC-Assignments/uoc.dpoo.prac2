package edu.uoc.prac;

/**
 * 
 * @author DPOO
 *
 */
public class Color {

	private String name;
	private String rgb;
	private Double price;
	
	/**
	 * 
	 * @param name
	 */
	public Color (String name, Double price, String rgb) {
		this.name = name;
		this.price = price;
		this.rgb = rgb;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	
	/**
	 * 
	 * @return
	 */
	public Double getPrice () {
		return price;
	}
	
	/**
	 * @return the rgb
	 */
	public String getRgb() {
		return rgb;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return rgb;
	}
}
