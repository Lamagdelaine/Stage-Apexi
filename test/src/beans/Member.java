package beans;

import java.util.Date;

/**
 * <b>Member est le bean modélisant un membre de l'application</b>
 * 
 * @author
 * 
 */
public class Member {
	private Long id_member;
	private String email;
    private String password;
    private String lastname;
    private String firstname;
    private String license;
    private String phone;
    private Date birthday; 
    
    public Member() {}

    public void setEmail(String email) {
    	this.email = email;
    }
    public String getEmail() {
    	return email;
    }

    public void setPassword(String password) {
    	this.password = password;
    }
    public String getPassword() {
    	return password;
    }

    public void setLastName(String lastname) {
    	this.lastname = lastname;
    }
    public String getLastName() {
    	return lastname;
    }

    public void setFirstName(String firstname) {
    	this.firstname = firstname;
    }
    public String getFirstName() {
    	return firstname;
    }
    
    public void setLicense(String license) {
    	this.license = license;
    }
    public String getLicense() {
    	return license;
    }
    
    public void setBirthday(Date birthday) {
    	this.birthday = birthday;
    }
    public Date getBirthday() {
    	return birthday;
    }
    
    public void setPhone(String phone) {
    	this.phone = phone;
    }
    public String getPhone() {
    	return phone;
    }
    
    public void setId_member(Long id_member) {
    	this.id_member = id_member;
    }
    public Long getId_member() {
    	return id_member;
    }

}
