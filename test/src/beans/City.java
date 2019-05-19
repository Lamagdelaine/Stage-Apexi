package beans;

import dao.DAO;

/**
 * <b>City est le modèle d'une ville vue par l'application</b>
 * 
 * 
 * @see DAO
 * @author 
 *
 */
public class City {
	Long id_city;
	private String name;
	private String postcode;
	private String lat; 
	private String lon;

	public City() {

	}
	public Long getId_city() {
		return id_city;
	}
	
	public void setId_city(Long id_city) {
		this.id_city = id_city;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}


}

