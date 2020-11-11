package edu.uoc.prac;

/**
 * 
 * @author DPOO
 *
 */
public class SyntheticPiece extends Piece{

	/*
	 * (non-Javadoc)
	 * @see Piece#getPrice()
	 */
	@Override
	public Double getPrice() {
		return getColor().getPrice();
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String tag = "    ";
		return tag + getColor() + tag;
	}
}
