package identity;

import java.sql.Date;

public class Customer {
    private int user_id;
    private String name, mail, phone;
    private Date dob; 	
    private Boolean sex;
    private String password;
    
    public Customer(String name, String mail, String phone, Date dob, Boolean sex, String password) {
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.dob = dob;
        this.sex = sex;
        this.password = password;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public String getPhone() {
        return phone;
    }

    public Date getDob() {
        return dob;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    
}
