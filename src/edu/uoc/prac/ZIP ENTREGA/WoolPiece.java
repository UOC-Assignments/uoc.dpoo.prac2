package edu.uoc.prac;

/**
 * 
 * @author DPOO
 *
 */
public class WoolPiece extends Piece {
	
	public static final Double PRICE_BASE = 5.0;

	private Integer woolLength;

	/**
	 * 
	 * @param length
	 * @throws BaseFloorException 
	 */
	public WoolPiece (Integer length) throws BaseFloorException {
		// check if ok
		if ((length <1) || (length > 2)) {
			throw new BaseFloorException (BaseFloorException.INCORRECT_WOOL_TYPE);
		}
		
		this.woolLength = length;
	}

	/*
	 * (non-Javadoc)
	 * @see Piece#getPrice()
	 */
	@Override
	public Double getPrice() {
		return getColor().getPrice() + PRICE_BASE;
	}
	

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String tag = (woolLength == 1) ? " w  " : " ww ";
		return tag + getColor() + "    ";
	}

}
