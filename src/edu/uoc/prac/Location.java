package edu.uoc.prac;

import java.util.ArrayList;

/**
 * 
 * @author DPOO
 *
 */
public class Location {

	private static final Double TILE_LENGTH = 30.0;
	private static final Double TILE_WIDTH = 30.0;
	
	private String name;
	private Integer length;
	private Integer width;
	private String address;
	
	private Company company;
	private Design design;
	
	
	/**
	 * 
	 * @param name
	 */
	public Location (String name) {
		this.name = name;
		this.length = null;
		this.width = null;
		this.address = null;
		
		this.company = null;
		this.design = null;		
	}

	/**
	 * @return the length
	 */
	public Integer getLength() {
		return length;
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(Integer length) {
		this.length = length;
	}

	/**
	 * @return the width
	 */
	public Integer getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(Integer width) {
		this.width = width;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the company
	 */
	public Company getCompany() {
		return company;
	}

	/**
	 * @return the dessign
	 */
	public Design getDessign() {
		return design;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(Company company) {
		this.company = company;
	}

	/**
	 * Generates a new design. Tiles are 30x30 centimeters
	 * 
	 * @param c color for the new design
	 * @return the generated design
	 */
	public Design generateDesign() {
		Integer columns;
		Integer rows;
		columns = this.getLength() / 30;
		rows = this.getWidth() / 30;

		Design d = new Design(this, columns, rows);
		return d;
	}
	
	public void setDessign(Design d) {
		this.design = d;		
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString () {
		String locationDescription;
		locationDescription = this.name + ". " + this.address + " " + "(" + this.length + "x" + this.width + ")";
		return locationDescription;
	}

	public void setName(String locationName) {
		this.name=name;
		
	}
}