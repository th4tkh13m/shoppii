package model;

import java.sql.Date;

import com.google.gson.annotations.Expose;
import com.password4j.Argon2Function;
import com.password4j.Hash;
import com.password4j.Password;

public class Customer {
    @Expose
    private int userId;
    @Expose
    private String name;
    @Expose
    private String mail;
    @Expose
    private String phone;
    @Expose
    private Date dob;
    @Expose
    private boolean sex;
    private String encryptedPassword;
    private String encryptedCode;

    public Customer(String name, String mail, String phone, Date dob, boolean sex, String password) {
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.dob = dob;
        this.sex = sex;
        this.encryptedPassword = password;
    }
   
    public Customer(int userId, String name, String mail, String phone, Date dob, boolean sex, String password,
            Argon2Function argon2) {
        this.userId = userId;
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.dob = dob;
        this.sex = sex;
        Hash hash = Password.hash(password).with(argon2);
        this.encryptedPassword = hash.getResult();
    }

    public Customer(String name, String mail, String phone, Date dob, boolean sex, String password,
            Argon2Function argon2) {
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.dob = dob;
        this.sex = sex;
        Hash hash = Password.hash(password).with(argon2);
        this.encryptedPassword = hash.getResult();
    }

    public Customer(int userId, String name, String mail, String phone, Date dob, boolean sex, String password) {
        this.userId = userId;
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.dob = dob;
        this.sex = sex;
        this.encryptedPassword = password;
    }

    public Customer(int userId, String name, String mail, String phone, Date dob, boolean sex, String encryptedPassword,
            String encryptedCode) {
        this.userId = userId;
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.dob = dob;
        this.sex = sex;
        this.encryptedPassword = encryptedPassword;
        this.encryptedCode = encryptedCode;
    }

    public Customer(String name, String mail, String phone, String password, String code, Argon2Function argon2) {
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        Hash hash = Password.hash(password).with(argon2);
        this.encryptedPassword = hash.getResult();
        hash = Password.hash(code).with(argon2);
        this.encryptedCode = hash.getResult();
    }

    public String getEncryptedCode() {
        return encryptedCode;
    }

    public Customer(String name, String mail, String phone) {
        this.name = name;
        this.mail = mail;
        this.phone = phone;
    }

    public Customer(int userId, String name, String mail, String phone, Date dob, boolean sex) {
        this.userId = userId;
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.dob = dob;
        this.sex = sex;
    }

    public void encryptPassword(String plainPassword, Argon2Function argon2) {
        Hash hash = Password.hash(plainPassword).with(argon2);
        this.encryptedPassword = hash.getResult();
    }

    public void encryptSecuityCode(String plainPassword, Argon2Function argon2) {
        Hash hash = Password.hash(plainPassword).with(argon2);
        this.encryptedPassword = hash.getResult();
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public void setEncryptedCode(String encryptedCode) {
        this.encryptedCode = encryptedCode;
    }

    public int getUserId() {
        return userId;
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

    public boolean getSex() {
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

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Customer other = (Customer) obj;
        if (dob == null) {
            if (other.dob != null)
                return false;
        } else if (!dob.equals(other.dob))
            return false;
        if (encryptedPassword == null) {
            if (other.encryptedPassword != null)
                return false;
        } else if (!encryptedPassword.equals(other.encryptedPassword))
            return false;
        if (mail == null) {
            if (other.mail != null)
                return false;
        } else if (!mail.equals(other.mail))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (phone == null) {
            if (other.phone != null)
                return false;
        } else if (!phone.equals(other.phone))
            return false;
        if (sex != other.sex)
            return false;
        return true;
    }

    public boolean verifyPassword(String plainPassword) {
        Argon2Function argon2 = Argon2Function.getInstanceFromHash(encryptedPassword);
        return Password.check(plainPassword, encryptedPassword).with(argon2);
    }

    public boolean verifyCode(String plainCode) {
        Argon2Function argon2 = Argon2Function.getInstanceFromHash(encryptedCode);
        return Password.check(plainCode, encryptedCode).with(argon2);
    }

    @Override
    public String toString() {
        return "Customer [dob=" + dob + ", encryptedPassword=" + encryptedPassword + ", mail=" + mail + ", name=" + name
                + ", phone=" + phone + ", sex=" + sex + ", userId=" + userId + "]";
    }

}
