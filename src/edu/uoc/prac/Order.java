package edu.uoc.prac;

/**
 * 
 * @author DPOO
 *
 */
public class Order {

	private static int identifier = 1;
	
	private Integer id;
	private OrderStatus status;
	private Double price;
	private Integer pieces;
	
	private Delivery delivery;
	private Company company;
	
	/**
	 * 
	 * @param pieces
	 */
	public Order (Design design, Double price) {
		// TODO
	}

	/**
	 * @return the status
	 */
	public OrderStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}


	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}


	/**
	 * @return the delivery
	 */
	public Delivery getDelivery() {
		return delivery;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString () {
		//TODO
		return null;
	}
}
