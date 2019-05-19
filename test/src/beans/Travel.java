package beans;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;

/**
 * <b>Le bean Travel sert de modèle pour les trajets de l'application</b>
 * 
 * @author 
 *
 */
public class Travel {
	private Long id_travel;
	private String departureDate;
	private String departureHour;
	private Long length;
	private Long seat_max;
	private Long luggage_max;
	private HashMap<City, Date> steps;
	private HashMap<String, String> stepsString;
	private String firstCity;
	private String lastCity;
	private Member driver;
	private HashMap<Member, Long> passengers;
	
	public Travel() {
		stepsString = new HashMap<>();
	}
	
	public Long getId_travel() {
		return id_travel;
	}

	public void setId_travel(Long id_travel) {
		this.id_travel = id_travel;
	}
	
	private String convertDateToString(String inputDateStr) {
		String dateString = null;
		DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try {
			date = inputFormat.parse(inputDateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dateString = outputFormat.format(date);
		return dateString;
	}
	
	private String convertTimeToString(String inputTime) {
		String timeString = null;
		String t[]= inputTime.split(":");
		timeString = t[0]+"h"+t[1];
		return timeString;
	}
	
	public void setDeparture(String departure) {
		String d[]= departure.split(" ");
		setDepartureDate(convertDateToString(d[0]));
		setDepartureHour(convertTimeToString(d[1]));
	}
	/**
	 * @return the departureDate
	 */
	public String getDepartureDate() {
		return departureDate;
	}

	/**
	 * @param departureDate the departureDate to set
	 */
	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	/**
	 * @return the departureHour
	 */
	public String getDepartureHour() {
		return departureHour;
	}

	/**
	 * @param departureHour the departureHour to set
	 */
	public void setDepartureHour(String departureHour) {
		this.departureHour = departureHour;
	}

	public Long getLength() {
		return length;
	}
	
	public void setLength (Long length) {
		this.length = length;
	}
	
	public Long getSeat_max() {
		return seat_max;
	}
	
	public void setSeat_max(Long seat_max) {
		this.seat_max = seat_max;
	}
	
	public Long getLuggage_max() {
		return luggage_max;
	}
	
	public void setLuggage_max(Long luggage_max) {
		this.luggage_max = luggage_max;
	}

	public HashMap<City, Date> getSteps() {
		return steps;
	}

	public void setSteps(HashMap<City, Date> steps) {
		this.steps = steps;
	}

	public Member getDriver() {
		return driver;
	}
	
	public void setDriver(Member driver) {
		this.driver = driver;
	}
	
	public HashMap<Member, Long> getPassengers() {
		return passengers;
	}

	public void setPassengers(HashMap<Member, Long> passengers) {
		this.passengers = passengers;
	}

	public void addSteps(String city, String schedule) {
		this.stepsString.put(city, schedule);
	}

	public HashMap<String, String> getStepsString() {
		return this.stepsString;
	}

	/**
	 * @return the firstCity
	 */
	public String getFirstCity() {
		return firstCity;
	}

	/**
	 * @param firstCity the firstCity to set
	 */
	public void setFirstCity(String firstCity) {
		this.firstCity = firstCity;
	}

	/**
	 * @return the lastCity
	 */
	public String getLastCity() {
		return lastCity;
	}

	/**
	 * @param lastCity the lastCity to set
	 */
	public void setLastCity(String lastCity) {
		this.lastCity = lastCity;
	}
}
