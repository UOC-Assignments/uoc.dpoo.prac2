package edu.uoc.prac;

/**
 * 
 * @author DPOO
 *
 */
public abstract class Piece {

	private Integer x;
	private Integer y;
	protected Double price;

	private Design design;
	private Color color;
	
	
	public Piece (Integer x, Integer y,  Double price){
		this.x=x;
		this.y=y;
		}
	
	public Piece (){
	
		}
	/**
	 * 
	 * @return
	 */
	public abstract Double getPrice();
	
	/**
	 * @return the x
	 */
	public Integer getX() {
		return x;
	}
	
	/**
	 * @return the y
	 */
	public Integer getY() {
		return y;
	}
	
	/**
	 * @param x the x to set
	 * @throws BaseFloorException 
	 */
	public void setX(Integer x) throws BaseFloorException {
		this.x = x;
	}
	
	/**
	 * @param y the y to set
	 * @throws BaseFloorException X_OUT_OF_BOUND, Y_OUT_OF_BOUND
	 */
	public void setY(Integer y) throws BaseFloorException {
		this.y = y;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	/**
	 * @return the design
	 */
	public Design getDesign() {
		return design;
	}

	/**
	 * @return the design
	 * @throws BaseFloorException X_OUT_OF_BOUND, Y_OUT_OF_BOUND
	 */
	public void setDesign(Design design) throws BaseFloorException {
		
		checkBoundaries(this.x, this.y, design); //CODI AFEGIT: Es controla que la peça que afegim a design està dins el rang del disseny. Si no, es llança una exepció.
		
		this.design = design;
	}
	
	/**
     * check if x and y are inside the region defined by design (boundaries)
     */
	private void checkBoundaries (Integer x, Integer y, Design d) throws BaseFloorException {
		/**
		if ((x > d.getColumns())||(x < 0)){ //Si la posició Y de la peca és inferior o supera la quantitat de files llençem excepció (compte que el mètode compta de 0 a 6, però el disseny s'imprimeix de 1 a 7)
			throw new BaseFloorException(BaseFloorException.X_OUT_OF_BOUND);
		}
		if ((y > d.getColumns())||(y < 0)){ //Si la posició Y de la peca és inferior o supera la quantitat de files llençem excepció (compte que el mètode compta de 0 a 6, però el disseny s'imprimeix de 1 a 7)
			throw new BaseFloorException(BaseFloorException.Y_OUT_OF_BOUND);
		}**/
	}
}
