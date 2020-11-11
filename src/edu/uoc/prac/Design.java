package edu.uoc.prac;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * @author DPOO
 *
 */
public class Design {

	private Integer columns;
	private Integer rows;

	private Location location = null; // not used in this exercise
	private ArrayList<Piece> pieces;
	
	/**
	 * 
	 * @param location
	 * @param columns
	 * @param rows
	 */
	public Design (Location location, Integer columns, Integer rows) {
		this.location = location;
		this.columns = columns;
		this.rows = rows;
		this.pieces = new ArrayList<Piece>();
	}
	
	/** ADD PIECE
	 * 
	 * @param p
	 * @throws BaseFloorException X_OUT_OF_BOUND, Y_OUT_OF_BOUND
	 */
	public void addPiece(Piece p) throws BaseFloorException {
		if (p.getX()>=columns || p.getX()<0){
			throw new BaseFloorException(BaseFloorException.X_OUT_OF_BOUND);
		}
		if (p.getY()>=rows || p.getY()<0){
			throw new BaseFloorException(BaseFloorException.Y_OUT_OF_BOUND);
		}
		
		// add piece to design and design to piece
		p.setDesign (this);
		
		// remove piece if any
		removePiece(p.getX(), p.getY());
		
		// add new piece
		pieces.add(p);	 //ORIGINAL
		

	}

	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public Piece getPiece (Integer x, Integer y) {
		for (Piece p: pieces) {
			if ((p.getX() == x) && (p.getY() == y)) {
				return p;
			}
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	
	public void removePiece (Integer x, Integer y) {
		// get piece at position
		Piece p = getPiece(x, y);
		
		// if piece at position delete it
		if (p != null) {
			pieces.remove(p);
		}
	}

	
	/**
	 * Return the price with only two decimal places (ROUNDED)
	 * 
	 * @return
	 */
	public Double calculatePrice () {
		double price = 0.0;
		
		for (Piece p : pieces) {
			price += p.getPrice();
		}
		
		// round 2 decimals
		BigDecimal n = new BigDecimal(price);
		n = n.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		return n.doubleValue();
	}

	/**
	 * @return the columns
	 */
	public Integer getColumns() {
		return columns;
	}

	/**
	 * @return the rows
	 */
	public Integer getRows() {
		return rows;
	}
	
	public void setColumns(Integer columns) {
		this.columns = columns;
	}

	/**
	 * @return the rows
	 */
	public void setRows(Integer rows) {
		this.rows = rows;
	}

	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @return the pieces
	 */
	public Piece[] getPieces() {
		
		return (Piece[]) pieces.toArray(new Piece[pieces.size()]);
	}

	/**
	 * 
	 * @return
	 */
	public Piece[][] getTiles() {
		// create double array
		Piece [][] tiles = new Piece[rows][columns];
		for (Piece p : pieces) {
			tiles[p.getY()][p.getX()] = p;
		}
		
		return tiles;
	}

	/**
	 * Create a new order based on this design

	 * @return
	 */
	public Order createOrder() {
		// TODO
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString () {
		StringBuilder sb = new StringBuilder ();
		
		sb.append("Design ").append(columns).append("x").append(rows)
			.append(" ($" + calculatePrice()).append(") ")
			.append(pieces.size() + " pieces") ;
		
		return sb.toString();
	}
}
