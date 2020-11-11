package edu.uoc.prac;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.*;

/**
 * 
 * @author DPOO
 * 
 */
public class BaseFloor {

	protected final static String NL = System.getProperty("line.separator");

	private Stock stock;
	private ArrayList<Company> companies;

	/**
	 * 
	 */
	public BaseFloor() {
		stock = new Stock();
		companies = new ArrayList<Company>();
	}

	/**
	 * UC01. Add a company into the system
	 * 
	 * @param name
	 *            name of the company
	 * @return the inserted company
	 * @throws BaseFloorException
	 *             COMPANY_ALREADY_EXISTS
	 */
	public Company addCompany(String name) throws BaseFloorException {
		
		Company c = new Company(name); //Creem un nou objecte de classe Company
		
		Company[] cmp = companies.toArray(new Company[companies.size()]); //Copiem l'arrayList companies al vector cmp per tal de poder buscar una company
		int i = 0;
		while (i < cmp.length)
		{
			if (cmp[i].getName().equals(name)) //Si el company name que rebem com a parametre d'entrada existeix al vector, llavors llançem exepció 
			{
				throw new BaseFloorException(BaseFloorException.COMPANY_ALREADY_EXISTS);
			}
			i++;
		}
		companies.add(c); //Com ja sabem que no existeix la company, llavors l'afegim al ArrayList companies
		return c;
	}
		
	/**
	 * UC02. Add a new location into the company.<br>
	 * If a location with the same name already exists into the company, the
	 * existing location remains but its attributes are updated<br>
	 * The company has to be located using its name.<br>
	 * Strings representing Integers must be converted to real Integers
	 * 
	 * @param company
	 *            name of the company
	 * @param locationName
	 *            name of the new location
	 * @param address
	 *            address of the location
	 * @param length
	 *            length value in centimeters
	 * @param width
	 *            width value in centimeters
	 * @return
	 * @throws BaseFloorException
	 *             COMPANY_NOT_FOUND, NaN
	 */
	 public Location addLocation(String company, String locationName,String address, String length, String width)throws BaseFloorException {
		  Integer l=null;
		  Integer w=null;
		   try { 
		    l=Integer.parseInt(length);
		    w=Integer.parseInt(width);
		      } catch(NumberFormatException e) { 
		       throw new BaseFloorException(BaseFloorException.NaN);
		      }
		   Location loc = new Location(locationName);
		   if (findCompany(company)!=null){
			   if (findLocation(company, locationName)==null){
				   loc.setCompany(findCompany(company));
				   loc.setName(locationName);
				   loc.setAddress(address);
				   loc.setLength(l);
				   loc.setWidth(w);
				   findCompany(company).addLocation(loc);
			  }else{
				  	loc=findLocation(company, locationName);
				  	loc.setCompany(findCompany(company));
			    	loc.setName(locationName);
				  	loc.setAddress(address);
			    	loc.setLength(l);
			    	loc.setWidth(w);
			    	 findCompany(company).addLocation(loc);
			      	}
		   }else{
			   throw new BaseFloorException(BaseFloorException.COMPANY_NOT_FOUND);
		   }
		   return loc;
		 }

	/**
	 * UC03. Add a color into the stock. <br>
	 * If the color does not exists must be created and added to the stock. If
	 * the color exists its amount must be updated The parameter "price" must be
	 * converted from String to Double.
	 * 
	 * @param amount
	 *            amount of color
	 * @param colorName
	 *            name of the color (descriptive)
	 * @param rgb
	 *            RGB representation of the color (unique)
	 * @param price
	 *            price for each piece of this color
	 * @return the new color if created or the already existing color
	 * @throws BaseFloorException
	 *             COMPANY_NOT_FOUND, LOCATION_NOT_FOUND, NaN
	 */
	public Color addColor(String amount, String colorName, String rgb,
			String price) throws BaseFloorException {
		double priceDouble;
		int amountInt;
		try {
			priceDouble = Double.parseDouble(price); //Convertir String a Integer
			amountInt = Integer.parseInt(amount); //Convertir String a Integer
		}
		catch (NumberFormatException error) {
			
			throw new BaseFloorException(BaseFloorException.NaN);
		}
				
		priceDouble = Double.parseDouble(price);
		amountInt = Integer.parseInt(amount);
		
		//Creem objecte de clase color on desem les característiques del color que volem afegir
		
		Color c = new Color(colorName, priceDouble, rgb);
		
		//Afegim el color al stock de colors
		
		this.stock.addStock(c, amountInt);
		return c;
	}

	/**
	 * UC04. Create a new design for a location.<br>
	 * When the new design is created, the number of columns and rows are
	 * calculated based on the location length and width. Each piece measures
	 * 30x30 centimeters<br>
	 * 
	 * @param company
	 *            name of the company
	 * @param location
	 *            name of the location
	 * @return the new design for the location
	 * @throws BaseFloorException
	 *             COMPANY_NOT_FOUND, LOCATION_NOT_FOUND
	 */
	public Design newDesign(String company, String location)
			throws BaseFloorException {
	
		int i=0;
		Design d=null;
		
		//Si la company no existeix llançem una exepcio
		 Location loc = findLocation(company,location);
		if (findCompany(company)==null){ 
			throw new BaseFloorException(BaseFloorException.COMPANY_NOT_FOUND);
		}
				for (i=0;i<companies.size();i++){
					
					//Si la location que tenim al ArrayList correspon amb la que passem com a paràmetre, llavors assignem el disseny al objecte Dessing d per a passar-ho com a paràmetre					
					if (companies.get(i).getLocation(location, company).getName().equals(location)){
						loc=companies.get(i).getLocation(location, company);
						d=new Design(loc, loc.getLength(),loc.getWidth());
						d=loc.generateDesign();
						companies.get(i).getLocation(location, company).setDessign(d);
						}
			}
				
		
			return d;	
	}

	/**
	 * UC05. Add a new Synthetic piece to the design.<br>
	 * If there is already a piece at the position, the older piece is removed
	 * from the design
	 * 
	 * @param company
	 *            name of the company
	 * @param location
	 *            location
	 * @param x
	 *            column
	 * @param y
	 *            row
	 * @param color
	 *            color for the piece
	 * @return The new piece
	 * @throws BaseFloorException
	 *             COMPANY_NOT_FOUND, LOCATION_NOT_FOUND, DESIGN_NOT_FOUND,
	 *             COLOR_NOT_FOUND,X_OUT_OF_BOUND, Y_OUT_OF_BOUND
	 */
	
	public Piece addSyntheticPiece(String company, String location, String x,
			String y, String color) throws BaseFloorException {	
		
		//FEM EL CONTROL D'EXCEPCIONS (S'han creat mètodes customitzats per a reciclar codi)			
			
		genericExceptionControl(company, location, color, x, y);	//Control d'excepcions genèric. Serveix per a totes les subclasses de Piece
			
		//PROCEDIM A AFEGIR LA PEÇA AL DISSENY	
		
		//1. Crear instància de syntheticPiece
		Piece p = new SyntheticPiece();
		
		// 2. trobes la instancia de Design i Color a paritr dels paramatres.

		
		Design d = findLocation(company, location).getDessign();
		
		
		Color c = stock.getColor(color);
		
		// 3. Setegem 
		p.setX(Integer.parseInt(x)); 
		p.setY(Integer.parseInt(y));
		p.setColor(c); 
		
		//4. Associem
		
			d.addPiece(p);
		
		//5. Retornem p
		return p;
	}

	
	/**
	 * UC05. Add a new Text piece to the design.<br>
	 * If there is already a piece at the position, the older piece is removed
	 * from the design
	 * 
	 * @param company
	 *            name of the company
	 * @param location
	 *            location
	 * @param x
	 *            column
	 * @param y
	 *            row
	 * @param color
	 *            color for the piece
	 * @param text
	 *            text to write in the piece
	 * @return The new piece
	 * @throws BaseFloorException
	 *             COMPANY_NOT_FOUND, LOCATION_NOT_FOUND, DESIGN_NOT_FOUND,
	 *             COLOR_NOT_FOUND, X_OUT_OF_BOUND, Y_OUT_OF_BOUND, EMPTY_TEXT
	 */
	public Piece addTextPiece(String company, String location, String x,
			String y, String color, String text) throws BaseFloorException {
		
		//FEM EL CONTROL D'EXCEPCIONS (S'han creat mètodes customitzats per a reciclar codi)
		
		genericExceptionControl(company, location, color, x, y);	
			
		//PROCEDIM A AFEGIR LA PEÇA AL DISSENY
		
		// 1. Crear instància de textPiece
		Piece p = new TextPiece(text, null);
		
		// 2. trobar la instancia de Design i Color a paritr dels paramatres. 
		Design d = getDesign(company, location);
		Color c = stock.getColor(color);
		
		// 3. Setegem 
		p.setX(Integer.parseInt(x)); 
		p.setY(Integer.parseInt(y));
		p.setColor(c); 
		
		//4. Associem
		d.addPiece(p);
		
		//5. Retornem p
		return p;		
	}

	/**
	 * UC05. Add a new Wool piece to the design.<br>
	 * If there is already a piece at the position, the older piece is removed
	 * from the design
	 * 
	 * @param company
	 *            name of the company
	 * @param location
	 *            location
	 * @param x
	 *            column
	 * @param y
	 *            row
	 * @param color
	 *            color for the piece
	 * @param type
	 *            type of wool (1 or 2)
	 * @return The new piece
	 * @throws BaseFloorException
	 *             COMPANY_NOT_FOUND, LOCATION_NOT_FOUND, DESIGN_NOT_FOUND,
	 *             COLOR_NOT_FOUND, X_OUT_OF_BOUND, Y_OUT_OF_BOUND, EMPTY_TEXT,
	 *             INCORRECT_WOOL_TYPE
	 */
	
	
	public Piece addWoolPiece(String company, String location, String x,
			String y, String color, String type) throws BaseFloorException {
		
		//FEM EL CONTROL D'EXCEPCIONS (S'han creat mètodes customitzats per a reciclar codi)
		
		try {
			Integer.parseInt(type); // Convertir string a int	
		}
		catch (NumberFormatException error) {
			
			throw new BaseFloorException(BaseFloorException.NaN); //Comprovem si lenght i width s'han pogut convertir a enters. Si no, es llança una excepció
		}
		
		genericExceptionControl(company, location, color, x, y);
		
			
		//PROCEDIM A AFEGIR LA PEÇA AL DISSENY
		
		// 1. Crear instància de WoolPiece
		Piece p = new WoolPiece(Integer.parseInt(type)); 
		
		// 2. trobar la instancia de Design i Color a paritr dels paramatres. 
		Design d = getDesign(company, location);
		Color c = stock.getColor(color);
		
		// 3. Setegem 
		p.setX(Integer.parseInt(x)); 
		p.setY(Integer.parseInt(y));
		p.setColor(c); 
		
		//4. Associem
		d.addPiece(p);
		
		//5. Retornem p
		return p;
	}

	/**
	 * UC05. Remove a pece from the passed location.<br>
	 * If there is no a piece in that position, the methods does nothing
	 * 
	 * @param company
	 *            name of the company
	 * @param location
	 *            location
	 * @param x
	 *            column
	 * @param y
	 *            row
	 * @return
	 * @throws BaseFloorException
	 */
	public Design removePiece(String company, String location, String x,
			String y) throws BaseFloorException {
			
		Integer r;
		Integer s;
		int j;
		j = 0;
		
		//CONTROL D'ERRORS
		try { 
			    r=Integer.parseInt(x);
			    s=Integer.parseInt(y);
			      } catch(NumberFormatException e) { 
			       throw new BaseFloorException(BaseFloorException.NaN);
			      }
		
		//Assignem la peça al objecte Piece p
			Piece p = new SyntheticPiece ();
	        Design d = findLocation(company, location).getDessign();
	      
	        //Eliminem la peça
	       d.removePiece(r, s);
			
		
		return d;
	}

	/**
	 * UC06. Tests that the application is controlling the boundaries of a
	 * design. Methods setX and setY can send an exception only when the piece
	 * is assigned to a design and limits exists.<br>
	 * It also controls that the application works fine with the composition
	 * relation between Design and Piece. Each piece can only be associated to
	 * one Design.<br>
	 * 
	 * @return a formatted string
	 * @throws BaseFloorException
	 */
	public String testComposition() throws BaseFloorException {
		StringBuffer sb = new StringBuffer();

		// create demo designs (2x2)
		Design d1 = generateDemo();
		Design d2 = generateDemo();

		Color color = new Color("tmp", 1.1, "111");

		// test boundaries. A piece can be located at any position, but if
		// is assigned to a design, it must respect boundaries

		Piece p = new SyntheticPiece();
		p.setX(1000);
		p.setY(1000);
		p.setColor(color);
		sb.append("Piece created: ").append(p).append(NL);

		try {
			d1.addPiece(p);
		} catch (Exception e) {
			sb.append("ERROR. ").append(e.getMessage()).append(NL);
		}

		p.setX(0);
		p.setY(1);
		d1.addPiece(p);
		sb.append("Piece assigned: ").append(d1).append(NL);

		try {
			p.setY(3);
		} catch (Exception e) {
			sb.append("ERROR. ").append(e.getMessage()).append(NL);
		}

		// test composition. A piece can only belongs to one dessign. It must be
		// removed from one desing before assigned to another

		try {
			d2.addPiece(p);
		} catch (Exception e) {
			sb.append("ERROR. ").append(e.getMessage()).append(NL);
		}

		d1.removePiece(0, 1);
		sb.append("Piece removed: ").append(d1).append(NL);

		d2.addPiece(p);
		sb.append("Piece assidned: ").append(d2).append(NL);

		p.setY(0);
		d2.addPiece(p);
		sb.append("Piece assidned: ").append(d2).append(NL);

		return sb.toString();
	}

	/**
	 * UC07. Create a new order for the passed location<br>
	 * The method must: 1) check all colors are available in the stock. 2)
	 * Reduce the stock with the colors is going to be used. 3) Create an Order
	 * based on the current design (if any)
	 * 
	 * 
	 * @param company
	 *            name of the company
	 * @param location
	 *            name of the location
	 * @return a new Order for the design at the indicated location
	 * @throws BaseFloorException
	 *             COMPANY_NOT_FOUND, LOCATION_NOT_FOUND, DESIGN_NOT_FOUND
	 */
	public Order createOrder(String company, String location)
			throws BaseFloorException {
		// TODO
		return null;
	}

	/**
	 * UC08. tests that the Order class is comparable.<br>
	 * This small demo creates three ordes that later will be compared
	 * 
	 * @return an array with the three orders
	 * @throws BaseFloorException
	 */
	public Order[] testNatualOrder() throws BaseFloorException {

		// create temporal design
		Design d = generateDemo();
		Color color = new Color("tmp", 1.1, "111");

		// add pieces
		Piece p = new SyntheticPiece();
		p.setX(1);
		p.setY(1);
		p.setColor(color);
		d.addPiece(p);

		// generate two orders with the same price
		Order o1 = d.createOrder();
		Order o2 = d.createOrder();

		// add pieces
		p = new SyntheticPiece();
		p.setX(0);
		p.setY(0);
		p.setColor(color);
		d.addPiece(p);

		// generates a new order (more expensive)
		Order o3 = d.createOrder();

		// return all three orders
		Order[] ret = new Order[3];
		ret[0] = o1;
		ret[1] = o2;
		ret[2] = o3;

		return ret;
	}

	/**
	 * UC09. List all orders in the system<br>
	 * The list must be ordered by price, following the natural order for the
	 * class Order
	 * 
	 * @return string with the text representation of the list
	 * @throws BaseFloorException
	 */
	public String listOrders() throws BaseFloorException {
		// TODO
		return null;
	}

	/**
	 * UC10. List all orders in the system<br>
	 * The list must be ordered by pieces, more pieces first.
	 * 
	 * @return string with the text representation of the list
	 * @throws BaseFloorException
	 */
	public String listOrdersByPiece() throws BaseFloorException {
		// TODO
		return null;
	}

	/**
	 * UC11. Return an array with the history of designs for the given location.<br>
	 * First element of the collection is the oldest dessing and the last
	 * element of the collection is the current dessign.
	 * 
	 * @param company
	 *            name of the company
	 * @param location
	 *            name of the location
	 * @return array with the history designs
	 * @throws BaseFloorException
	 *             COMPANY_NOT_FOUND, LOCATION_NOT_FOUND
	 */
	public Design[] history(String company, String location)
			throws BaseFloorException {
		// TODO
		return null;
	}

	/**
	 * Return the design for the passed company and location
	 * 
	 * @param company
	 *            company name
	 * @param location
	 *            location name
	 * @throws BaseFloorException
	 *             COMPANY_NOT_FOUND, LOCATION_NOT_FOUND, DESIGN_NOT_FOUND
	 */
	public Design getDesign(String company, String location)
			throws BaseFloorException {
		
		Location l = findLocation(company, location);
		return l.getDessign();
		}
		//el getDesign  sólo debe retornar un design para una localización determinada.!!!!! REIMPLEMENTAR
		
		/** VERSIÓ 2
		int rows, columns;
		
		//BUSQUEM LA COMPANY I LA LOCATION I LES ASSIGNES A UN OBJECTE DE LA SEVA CLASSE
		Location l = findLocation(company, location);
		
		//ASSIGNEM I FEM EL CALCUL DE COLUMNES i FILES EN FUNCIÓ DE LENGHT I WIDTH
		rows = l.getLength() / 30;
		columns = l.getWidth() / 30;
		
		//FINALMENT FEM EL ADD DESIGN I EL RETORNEM		
		Design d = new Design (l,columns,rows);		
		return d;
	}**/

	/**
	 * Generate a demo environment for text extra funcionality
	 * 
	 * @return
	 */
	private Design generateDemo() {
		Company c = new Company("tmp");
		Location l = new Location("tmpLocation");
		c.addLocation(l);
		l.setAddress("none");
		l.setLength(80);
		l.setWidth(80);
		return l.generateDesign();
	}


	/**
	 * @return the stock
	 */
	public Stock getStock() {
		return stock;
	}
	
	//CUSTOM METHODS
	
	/**
	 * Busca una company i retorna true o false depenenent de si la troba o no.
	 * 
	 * @param company
	 * @return bool
	 */	
	public Boolean checkCompany(String company) {		
		int i;
		for (i=0; i<companies.size();i++){
			if (companies.get(i).getName().equals(company)) {
				return true;	
			}		
		}
		return false;
	}
	
	
	/**
	 * Busca una location i retorna true o false depenenent de si la troba o no.
	 * 
	 * @param company
	 * @param location
	 * @return bool
	 */
	public Boolean checkLocation(String company, String location) {
		int i,j;
		
		for (i=0; i<companies.size();i++)
		{
			if (companies.get(i).getName().equals(company)) {
				Location[] locs = new Location[companies.get(i).getLocations().length]; 
				locs = companies.get(i).getLocations();
				for (j=0;j<locs.length;j++) {
					if (locs[j].getName().equals(location)) {
						return true;
					}
				}
			}
		}
		return false;
	}
			
	public Boolean checkDesign(String company, String location, String columns, String rows) {
		//TODO
		return false;
	}
	/**
	 * Busca un color i retorna true o false depenenent de si el troba o no.
	 * @param color
	 * @return bool
	 */
	public Boolean checkColor(String color) {
		int i;
		for (i=0;i<stock.getColors().length;i++) {
			if(stock.getColors()[i].getRgb().equals(color)) {
				return true;
			}						
		}	
		return false;
	}
	

	/**
	 * Buscar una company i retornarla a la sortida
	 * @param company (String)
	 * @return Company (Company)
	 */
	public Company findCompany(String company) {
		int i;	
		for (i=0; i<companies.size();i++)
		{
			if (companies.get(i).getName().equals(company)) {
				Company c = companies.get(i);
				return c;
				}
		}
		return null;
	}
	
	/**
	 * Buscar una company i retornarla a la sortida
	 * @param company
	 * @param location (String)
	 * @return location (Location)
	 */
	public Location findLocation(String company, String location) throws BaseFloorException{
		/**int i,j;	
		Location l = new Location(location);
		Location[] locs;		
		for (i=0; i<companies.size();i++)
		{
			if (companies.get(i).getName().equals(company)) {
				locs = new Location[companies.get(i).getLocations().length]; 
				System.out.print("-------------------------GET LOCATIONS LENGHT----------------------------->" + companies.get(i).getLocations().length);
				locs = companies.get(i).getLocations();
				for (j=0;j<locs.length;j++) {
					if (locs[j].getName().equals(location)) {
						l = locs[j];						
						return l;
					}
				}
			}		
		}
		return null;
	}**/
		
			  //Location lcs=null;
			  Location loca=null;
			   String a="";
			   if (findCompany(company)==null){
			     throw new BaseFloorException(BaseFloorException.COMPANY_NOT_FOUND);
			   }
			       if (findCompany(company).getLocations().length==0){
			        a="not_found";
			        }else  
			        for(int i=0; i<findCompany(company).getLocations().length;i++){ 
			            if (findCompany(company).getLocations()[i].getName().equals(location)){
			             loca=findCompany(company).getLocations()[i];
			             a="found";
			             break;
			          }
			      }
			   if (a!="found"){
			    return null;
			    }
			   return loca;
			 }

	
	
	//Buscar una location i retornarla a la sortida
	
	public void genericExceptionControl(String company, String location, String color, String x, String y) 
			throws BaseFloorException {
		//Comprovar que les dades numèriques d'entrada de tipus String es puguin convertir a Integer
		
				try {			
					Integer.parseInt(x); // Convertir string a int	
					Integer.parseInt(y); // Convertir string a int
				}
				catch (NumberFormatException error) {
					
					throw new BaseFloorException(BaseFloorException.NaN); //Comprovem si lenght i width s'han pogut convertir a enters. Si no, es llança una excepció
				}
				
				//Comprovar si existeix la company		
				if (checkCompany(company) == false) {
					throw new BaseFloorException(BaseFloorException.COMPANY_NOT_FOUND);
				}
				
				//Comprovar si existeix la location		
				if (checkLocation(company, location) == false) {
						throw new BaseFloorException(BaseFloorException.LOCATION_NOT_FOUND);
				}
				
				//Comprovar si existeix el disseny		
				/**if (checkDesign(company,location) == false) {
					throw new BaseFloorException(BaseFloorException.DESIGN_NOT_FOUND);			
				}**/
				
				//Comprovar si existeix el color	
				if (checkColor(color) == false) {
					throw new BaseFloorException(BaseFloorException.COLOR_NOT_FOUND);
				}
		}
}