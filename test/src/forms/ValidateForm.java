package forms;

import beans.City;
import beans.Member;
import beans.Travel;
import dao.DAOCity;
import dao.DAOCityImpl;
import dao.DAOException;
import dao.DAOFactory;
import dao.DAOMember;
import dao.DAOTravel;
import servlets.Connexion;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <b>La classe ValideForm, vérifie les informations reçues des membres de l'application</b>
 * 
 * <p>Elle s'occpue aussi de générer les erreurs liés aux manipulations des membres </p>
 * 
 * @author 
 * 
 */

public final class ValidateForm {
       
	/**
	 * Member
	 */
    private static final String FIELD_EMAIL  = "email";
    private static final String FIELD_PASS   = "password";
    private static final String FIELD_CONF   = "password2";
    /**
     * Travel
     */	
    private static final String FIELD_DEPARTURE_CITY	= "departure_city";
    private static final String FIELD_ARRIVAL_CITY		= "arrival_city";
    private static final String FIELD_DEPARTURE_DATE 	= "departure_date";
    private static final String FIELD_DEPARTURE_TIME 	= "departure_time";
    private static final String FIELD_ARRIVAL_DATE 		= "arrival_date";	
    private static final String FIELD_ARRIVAL_TIME 		= "arrival_time";
    private static final String FIELD_LENGTH 			= "length";
    private static final String FIELD_SEAT_MAX 			= "seat_max";
    private static final String FIELD_LUGGAGE_MAX 		= "luggage_max";
    


    private static final String CHAMP_FIRSTNAME      = "firstname";
    private static final String CHAMP_LASTNAME       = "lastname";
    private static final String CHAMP_BIRTHDAY 	     = "birthday";
    private static final String CHAMP_LICENSE 	  	 = "license";
    private static final String CHAMP_EMAIL 	  	 = "email";
    private static final String CHAMP_PHONE 	  	 = "phone";
    
    

    /**
     * Generic
     */
    private String              result;
    private Map<String, String> errors      = new HashMap<String, String>();
    
    private Member member = new Member();
    private DAOMember daoMember;
    private DAOTravel daoTravel;
    private DAOCity   daoCity;
    
    public ValidateForm( DAOMember daoMember ) {
    	this.daoMember = daoMember;
    }
    public ValidateForm( DAOTravel daoTravel ) {
    	this.daoTravel = daoTravel;
    }

    public String getResult() {
        return result;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    private Member checkMinForm(HttpServletRequest request, Member member) {
        /* Récupération des champs du formulaire */
        String email = getFieldValue( request, FIELD_EMAIL );
        String password = getFieldValue( request, FIELD_PASS );

        /* Validation du champ email. */
        try {
            treatEmail( email, false, member );
        } catch ( DAOException e ) {
            setError( FIELD_EMAIL, e.getMessage() );
        }
        member.setEmail( email );

        /* Validation du champ mot de passe. */
        try {
            treatPassword( password, member );
        } catch ( Exception e ) {
            setError( FIELD_PASS, e.getMessage() );
        }
        member.setPassword( password );
        return member;
    }

	public Member registerMember(HttpServletRequest request) {
	    String email = getFieldValue( request, FIELD_EMAIL );
	    String password = getFieldValue( request, FIELD_PASS );
	    String confirmation = getFieldValue( request, FIELD_CONF );

	    member = new Member();
	    try {
	        treatEmail( email, true, member );
	        treatPassword( password, confirmation, member );
	        /*	TEMPORAIRE !!!! pour pallier à l'obligation d'un téléphone	*/
	        member.setPhone("0");
	        
	        if ( errors.isEmpty() ) {
	        	/*	enregistrement en BDD	*/
	        	daoMember.create( member );
	            result = "Succès de l'inscription.";
	        } else {
	            result = "Échec de l'inscription.";
	        }
	    } catch ( DAOException e ) {
	        result = "Échec de l'inscription : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
	        e.printStackTrace();
	    }

	    return member;
	}

    private void treatPassword(String password, String confirmation, Member member) {
        try {
            validatePassword( password, confirmation );
        } catch ( FormValidationException e ) {
            setError( FIELD_PASS, e.getMessage() );
            setError( FIELD_CONF, null );
        }

        member.setPassword( password );
    }
    private void treatPassword(String password, Member member) {
    	try {
    		validatePassword( password );
    	} catch ( Exception e ) {
    		setError( FIELD_PASS, e.getMessage() );
    		setError( FIELD_CONF, null );
    	}
    	
    	member.setPassword( password );
    }

	private void validatePassword(String password, String confirmation) throws FormValidationException {
        if ( password != null && confirmation != null ) {
            if ( !password.equals( confirmation ) ) {
                throw new FormValidationException( "Les mots de passe entrés sont différents, merci de les saisir à nouveau." );
            } else if ( password.length() < 3 ) {
                throw new FormValidationException( "Les mots de passe doivent contenir au moins 3 caractères." );
            }
        } else {
            throw new FormValidationException( "Merci de saisir et confirmer votre mot de passe." );
        }
	}

	private void treatEmail(String email, boolean register, Member member) {
    	try {
            validateEmail( email, register );
        } catch ( FormValidationException e ) {
            setError( FIELD_EMAIL, e.getMessage() );
        }
        member.setEmail( email );
    }

	public Member connectMember( HttpServletRequest request ) throws FormValidationException {
		member = checkMinForm(request, member);
        if ( errors.isEmpty() ) {
        	try {
            	if ( daoMember.find2connect(member.getEmail(), member.getPassword()) == null ) {
            		result = "Échec de la connexion.";
                    throw new FormValidationException( "Non trouvé en BDD." );
                }
        	} catch (NullPointerException e) {
        		System.out.println(e.getMessage());
			}
            result = "Succès de la connexion.";
        } else {
            result = "Échec de la connexion.";
        }

        return member;
    }

    /**
     * Valide l'adresse email saisie.
     */
    private void validateEmail( String email, boolean register ) throws FormValidationException {
        if ( email != null ) {
            if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
                throw new FormValidationException( "Merci de saisir une adresse mail valide." );
            } else {
            	try {
	            	if ( daoMember.find( email ) != null && register) {
	                    throw new FormValidationException( "Cette adresse email est déjà utilisée, merci d'en choisir une autre." );
	                }
            	} catch (NullPointerException e) {
					// TODO: handle exception
				}
            }
        } else {
            throw new FormValidationException( "Merci de saisir une adresse mail." );
        }
    }

    /**
     * Valide le mot de passe saisi.
     */
    private void validatePassword( String password ) throws Exception {
        if ( password != null ) {
            if ( password.length() < 3 ) {
                throw new Exception( "Le mot de passe doit contenir au moins 3 caractères." );
            }
        } else {
            throw new Exception( "Merci de saisir votre mot de passe." );
        }
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setError( String field, String message ) {
        errors.put( field, message );
    }

    /**
     * Méthode permettant de récupérer les informations saisies par le conducteur
     * @param request
     * @param fieldName
     * @return String valeur saisies par l'utilisateur
     */
    private static String getFieldValue( HttpServletRequest request, String fieldName ) {
        String val = request.getParameter( fieldName );
        if ( val == null || val.trim().length() == 0 ) {
            return null;
        } else {
            return val;
        }
    }

    public LocalDateTime convertToLocalDateTime(Date dateToConvert) {
        
    	Instant instant = Instant.ofEpochMilli(dateToConvert.getTime());
        LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
    	
    	return ldt;
    }
    
    /**
     * Méthode permettant de vérifier les informations saisies par le conducteur
     * @param request
     * @return bean Travel permettant d'enregistrer les informations du nouveau trajet saisies.
     */
    public Travel checkForm(HttpServletRequest request) throws FormValidationException {
    	String departure_city	= getFieldValue(request, FIELD_DEPARTURE_CITY);
    	String arrival_city		= getFieldValue(request, FIELD_ARRIVAL_CITY);
    	String departure_date	= getFieldValue(request, FIELD_DEPARTURE_DATE);
    	String departure_time	= getFieldValue(request, FIELD_DEPARTURE_TIME);
    	String arrival_date		= getFieldValue(request, FIELD_ARRIVAL_DATE);
    	String arrival_time		= getFieldValue(request, FIELD_ARRIVAL_TIME);
    	String length 			= getFieldValue(request, FIELD_LENGTH);
    	String seat_max 		= getFieldValue(request, FIELD_SEAT_MAX);
    	String luggage_max 		= getFieldValue(request, FIELD_LUGGAGE_MAX);
    	
    	Long length_num 		= null;
    	Long seat_max_num 		= null;
    	Long luggage_max_num 	= null;
    	City departure			= new City();
    	City arrival			= new City();
    	Member driver 			= new Member();
    	HashMap<City, Date> steps			= new HashMap<City, Date>();
    	HashMap<Member, Long> passengers 	= new HashMap<Member, Long>();
    	
    	SimpleDateFormat sdf 	= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    	String dep_sch			= "";
    	String arr_sch			= "";
    	Date departure_schedule = null;
    	Date arrival_schedule	= null;
    	Date today 				= new Date();
    	Duration duration;
    	
    	
    	Travel travel 			= new Travel();

    	errors.clear();
    	
    	/* Récupération de la ville de départ */
    	if (departure_city == null) {
    		setError(FIELD_DEPARTURE_CITY, "Veuillez saisir le nom de la ville de départ.");
    	} 
    	else {
    		try {
    			departure = daoCity.find(departure_city);
    			if (departure.getName() == null) {
    				setError(FIELD_DEPARTURE_CITY, "Veuillez saisir un nom de ville valide.");
    			}
    		}catch (Exception e) {
    			setError(FIELD_DEPARTURE_CITY, "Veuillez saisir le nom d'une ville en France.");
    		}
    	}
    	
		/* Récupération de la date et de l'heure de départ */
    	if (departure_date == null) {
    		setError(FIELD_DEPARTURE_DATE, "Veuillez saisir une date de départ.");
    	} 
    	if (departure_time == null) {
    		setError(FIELD_DEPARTURE_TIME, "Veuillez saisir un horaire de départ.");
    	}
    	else {
    		dep_sch.concat(departure_date);
    		dep_sch.concat(departure_time);
    		try {
    			departure_schedule = sdf.parse(dep_sch);
    			if (departure_schedule.compareTo(today) < 0) {
    				setError(FIELD_DEPARTURE_DATE, "Veuillez saisir une date de départ postérieure.");
    			}
    		}catch (Exception e) {
    			setError(FIELD_DEPARTURE_DATE, "Veuillez saisir une date de départ valide.");
    		}
    	}
    	steps.put(departure, departure_schedule);
    	
    	/* Récupération de la ville d'arrivée */
    	if (arrival_city == null) {
    		setError(FIELD_ARRIVAL_CITY, "Veuillez saisir le nom de la ville d'arrivée.");
    	} 
    	else {
    		try {
    			arrival = daoCity.find(arrival_city);
    			if (arrival.getName() == null) {
    				setError(FIELD_ARRIVAL_CITY, "Veuillez saisir un nom de ville valide.");
    			}
    		}catch (Exception e) {
    			setError(FIELD_ARRIVAL_CITY, "Veuillez saisir le nom d'une ville en France.");
    		}
    	}
    	
    	/* Récupération de la date et de l'heure d'arrivée */
    	if (arrival_date == null) {
    		setError(FIELD_ARRIVAL_DATE, "Veuillez saisir une date de départ.");
    	} 
    	if (arrival_time == null) {
    		setError(FIELD_ARRIVAL_TIME, "Veuillez saisir un horaire de départ.");
    	}
    	else {
    		arr_sch.concat(arrival_date);
    		arr_sch.concat(arrival_time);
    		try {
    			arrival_schedule = sdf.parse(arr_sch);
    			if (arrival_schedule.compareTo(departure_schedule) < 0) {
    				setError(FIELD_ARRIVAL_DATE, "Veuillez saisir une date de départ postérieure au départ.");
    			}
    		}catch (Exception e) {
    			setError(FIELD_ARRIVAL_DATE, "Veuillez saisir une date de départ valide.");
    		}
    	}
    	steps.put(arrival, arrival_schedule);
		travel.setSteps(steps);
    	
    	/* Récupération de la durée estimée du trajet en minutes */
    	try {
    		/*LocalDateTime ldt_dep = convertToLocalDateTime(departure_schedule);
    		LocalDateTime ldt_arr = convertToLocalDateTime(arrival_schedule);
    		duration = Duration.between(ldt_dep, ldt_arr );
    		length_num = duration.toHours();*/
      	} catch (NumberFormatException e) {
    		setError(FIELD_LENGTH, "Veuillez saisir la durée estimée.");
    		// durée estimée du trajet entre les deux villes de France les plus éloignées avec pauses
    	}
    	travel.setLength(length_num);
    	
    	/* Récupération du nombre de passagers maximum */
    	if (seat_max == null) {
    		setError(FIELD_SEAT_MAX, "Veuillez saisir le nombre de places disponibles pour ce trajet.");
    	}
    	else {
    		try {
    			seat_max_num = Long.parseLong(length);
    			if (seat_max_num < 1 || seat_max_num > 4) {
    				setError(FIELD_SEAT_MAX, "Veuillez saisir un nombre de places situé entre 1 et 4 passagers.");
    			}
    		} catch (NumberFormatException e) {
    			setError(FIELD_SEAT_MAX, "Veuillez saisir un nombre numérique.");
    		}
    	}
    	travel.setSeat_max(seat_max_num);
    	
    	/* Récupération du nombre de bagages maximum */
    	if (luggage_max == null) {
    		setError(FIELD_LUGGAGE_MAX, "Veuillez saisir le nombre de bagages maximum sur ce trajet.");
    	}
    	else {
    		try {
    			luggage_max_num = Long.parseLong(luggage_max);
    			if (luggage_max_num < 0) {
    				setError(FIELD_LENGTH, "Veuillez saisir une nombre positif.");
    			}
    		} catch (NumberFormatException e) {
    			setError(FIELD_LUGGAGE_MAX, "Veuillez saisir un nombre numérique.");
    		}
    	}
    	travel.setLuggage_max(luggage_max_num);
   	
    	/* Enregistrement du conducteur */
    	HttpSession session = request.getSession();     	
    	driver = (Member) session.getAttribute(Connexion.ATT_SESSION_USER);
    	travel.setDriver(driver);
    	
        if (errors.isEmpty()) {
        	daoTravel.create( travel );
            result = "Félicitations ! Le trajet a bien été créé.";
        } else {
            result = "Dommage ! Le trajet n'a pas été créé.";
        }
        
        return travel;
    }
    
    public Member checkMemberForm(HttpServletRequest request) throws FormValidationException {
		
	/*
	 * Conversion java.util.Date en String, avec la classe SimpleDateFormat .
	 */
	//DateFormat birthdayFormat = new SimpleDateFormat("dd/mm/yyyy");
	
	//String stringBirthday = birthdayFormat.format(birthday);
    	


    	String firstname = getFieldValue(request, CHAMP_FIRSTNAME);
    	String lastname    = getFieldValue(request, CHAMP_LASTNAME);
    	String birthday    = getFieldValue(request, CHAMP_BIRTHDAY);
    	String license   = getFieldValue(request, CHAMP_LICENSE);
    	String email    = getFieldValue(request, CHAMP_EMAIL);
    	String phone    = getFieldValue(request, CHAMP_PHONE);
    	
    	Member member = new Member();

    	member.setFirstName(firstname);

    	member.setLastName(lastname);

    	
    	//member.setBirthday(birthday);

    	member.setLicense(license);

		member.setPhone(phone);

		member.setEmail(email);
		validateEmail(email, false);
		
		return member;
	
    }
}
