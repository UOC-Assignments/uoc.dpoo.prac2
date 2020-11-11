package edu.uoc.prac;
import java.util.ArrayList;


/**
 * 
 * @author DPOO
 *
 */
public class Company {

	private String name;
	
	private ArrayList<Location> locations;
	private ArrayList<Order> orders;
	
	/**
	 * 
	 * @param name
	 */
	public Company (String name) {
		this.name = name;
		locations = new ArrayList<Location>();
		orders = new ArrayList<Order>();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}
		
	/**
	 * @return the locations
	 */
	public Location[] getLocations() {
		
		//Location[] loc = this.locations.toArray(new Location[this.locations.size()]); //Aquesta opció fa el mateix que la linia anterior però és més eficient. http://stackoverflow.com/questions/7969023/from-arraylist-to-array
		
		return (Location[]) locations.toArray(new Location[locations.size()]);
	}

	/**
	 * Add a new location to the set. If the location does not exists in the
	 * Company, it is added. If the location already exists, the values of the
	 * existing company are updated.<br>
	 * The Design associated with this location remains unmodified<br>
	 *  
	 * @param location the locations added or modified
	 */
	public Location addLocation(Location l) { //REVISAT!!!
		int i;
		
		//Copiar les locations a un vector per a buscar si existeix la Location.address al ArrayList.	
		
		Location[] loc = this.getLocations();
		for (i=0;i<loc.length;i++) {			
			
			//Si existeix l.name a locations, "machaquem" la posició del arrayList on tenim la location (indicada per "i").		
			
			if (l.getName().equals(loc[i].getName())) {		
				this.locations.set(i,l);	
				return l;
			}			
		}	
		
		//Si no existeix, llavors la location és nova i l'afegim al final del ArrayList locations		
		
		this.locations.add(l); 
		return l;
	}
	
	/**
	 * @return the orders
	 */
	public Order[] getOrders() {
		//TODO
		return null;
	}

	/**
	 * @param rates the rates to set
	 */
	public void addOrder(Order order) {
		// TODO
	}

	public Location getLocation(String location,  String companyName) throws BaseFloorException{
		int i;
		int j = 0;
		String comp="";
		
			//Busquem les locations al ArrayList. Si la trobem la retornem, sino, llançem exepció LOCATION_NOT_FOUND
			for(i=0;i<locations.size();i++){
					if ((locations.get(i).getCompany().getName().equals(companyName))){
						for (j=0;j<locations.size();j++){
							if (locations.get(j).getName().equals(location)){
								return locations.get(j);
								}else{
									comp="error";
								}
						    }
						}
			       }
			
			if (comp!="error"){
				return locations.get(j);
			}else{
				throw new BaseFloorException(BaseFloorException.LOCATION_NOT_FOUND);
			}
	}		
}
