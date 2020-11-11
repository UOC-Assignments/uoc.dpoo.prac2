package edu.uoc.prac;

/**
 * 
 * @author DPOO
 *
 */
public class TextPiece extends SyntheticPiece {

	public static final Double PRICE_FACTOR = 1.25;
	
	private String text;
	
	/**
	 * 
	 * @param text
	 * @param size
	 * @throws BaseFloorException 
	 */
	public TextPiece (String text, Integer size) throws BaseFloorException {
		
		// check if ok
		if ((text == null) || (text.trim().length() == 0)) {
			throw new BaseFloorException(BaseFloorException.EMPTY_TEXT);
		}
		
		this.text = text;
	}
	
	/*
	 * (non-Javadoc)
	 * @see Piece#getPrice()
	 */
	@Override
	public Double getPrice() {
		return super.getPrice() * PRICE_FACTOR;
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


	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String tag = (text.length() > 11) ? text.substring(0,11) : text;
		
		while (tag.length() < 11) {
			tag += " ";
		}
		
		return tag;
	}

}
