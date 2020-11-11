package edu.uoc.prac;

/**
 * Exception class for BaseFloor operations
 * 
 * @author bob
 *
 */
public class BaseFloorException extends Exception {
	
	private static final long serialVersionUID = 4587956997006621393L;	

	public static final String NaN = "Value is not a number (NaN)";

	public static final String COMPANY_ALREADY_EXISTS = "Company already exists";
	public static final String COMPANY_NOT_FOUND = "Company was not found";
	public static final String LOCATION_NOT_FOUND = "Location was not found at the specified company";
	public static final String COLOR_NOT_FOUND = "Color was not found at Stock";
	public static final String DESIGN_NOT_FOUND = "Passed location does not have a design. Generate a new design for the location";

	public static final String EMPTY_TEXT = "No text found for a text piece";
	public static final String INCORRECT_WOOL_TYPE= "Bad type of wool for a wool piece";

	public static final String PIECE_ALREADY_ASSIGNED = "Piece already assigned to another design";
	
	public static final String X_OUT_OF_BOUND = "Position X (column) out of bound";
	public static final String Y_OUT_OF_BOUND = "Position Y (row) out of bound";
	
	public static final String NO_STOCK = "Not enough quantity of color in the stock";

	/**
	 * Constructor
	 * 
	 * @param message error message
	 */
	public BaseFloorException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
