package edu.uoc.prac;
import java.util.Date;


/**
 * 
 * @author DPOO
 *
 */
public class Delivery {

	private Date departureDate;
	private Date deliveryDate;
	private String address;
	
	/**
	 * 
	 * @param address
	 */
	public Delivery (Order order) {
		this.address = null;
	}
	
	/**
	 * @return the departureDate
	 */
	public Date getDepartureDate() {
		return departureDate;
	}
	/**
	 * @param departureDate the departureDate to set
	 */
	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}
	/**
	 * @return the deliveryDate
	 */
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	/**
	 * @param deliveryDate the deliveryDate to set
	 */
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	
	
}
