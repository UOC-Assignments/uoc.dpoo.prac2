package edu.uoc.prac;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import edu.uoc.prac.Color;

/**
 * 
 * @author DPOO
 *
 */
public class Stock {

	private ArrayList<ColorStock> colors;
	
	/**
	 * 
	 */
	public Stock () {
		this.colors = new ArrayList<ColorStock>();
	}
	
	/**
	 * 
	 * @param color
	 * @param amount
	 */
	public void addStock(Color color, Integer amount) {
		//Stock s = new Stock();
		ColorStock cs = new ColorStock(color, this, amount);
		
		//Copiem array
		
		Color[] colorsArray = this.getColors();
		int i;
		
		//Busquem 
		
		for (i=0;i<this.colors.size();i++)
		{
			//si el color existeix "machaquem"
			
			if (cs.getColor().getRgb().equals(colorsArray[i].getRgb())) {
				this.colors.set(i, cs);
				return;
			}				
		}
		
		// Si no, afegim al final de la llista
		
		this.colors.add(cs);
	}
	
	/**
	 * 
	 * @param color
	 * @return
	 */
	public Boolean checkStock (Color color, Integer amount) {
		// TODO
		return null;
	}
	
	/**
	 * 
	 * @param color
	 * @param amount
	 * @return
	 */
	public Boolean reduceStock(Color color, Integer amount) {
		// TODO
		return null;
	}

	/**
	 * Return the color with the specified rgb representation
	 * 
	 * @param rgb RGB representation of the color
	 * @return the color if found (exception if not)
	 * @throws BaseFloorException COLOR_NOT_FOUND if the color is not found at the stock
	 */
	public Color getColor (String rgb) throws BaseFloorException {
		Color c = new Color(null, null, null);
		int i;
		for (i = 0; i<this.colors.size();i++) {
			if (colors.get(i).getColor().getRgb().equals(rgb)) {
				c = colors.get(i).getColor();
				return c;
			}			
		}		
	return null; // <---- THROW EXCEPTION!		
	}
	
	/**
	 * Return all colors in this Stock
	 * 
	 * @return an array with the colors
	 */
	public Color[] getColors () {
		
		//Creem un vector tipus Color i copiem l'arrayList colors a dins seu 
		
		//ColorStock[] c = this.colors.toArray(new ColorStock[this.colors.size()]);
		Color[] c = new Color[this.colors.size()];
		int i;
		for (i=0;i<this.colors.size();i++) {
			c[i] = this.colors.get(i).getColor();
		}
		return c;
	}
	
	/**
	 * return the quantity of the passed color stored in the stok
	 * 
	 * @param color the color
	 * @return amount of color stored or 0 if no color found
	 */
	public Integer getAmount (Color color) {
		//TODO
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString () {
		
		String st1, st2, st3;
		
		st1 = Arrays.toString(this.colors.toArray());	

	    st2 = st1.replace("[", ""); //Eliminem el "corchete" de la esquerra amb String.replace
	    
	    st3 = st2.replace("]", ""); //Eliminem el "corchete" de la esquerra amb String.replace

		return "Stock with " + this.colors.size() + " colors. " + st3;
		
	   /** Iterator<ColorStock> itr = colors.iterator();
	    System.out.println("Iterating through ArrayList elements...");
	    while(itr.hasNext())
	      System.out.println(itr.next());
		return null;
	    
	    use hasNext() and next() methods of Iterator to iterate through the elements**/

	}
}