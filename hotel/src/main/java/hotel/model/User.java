package hotel.model;

import hotel.constant.Constants;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -2453331887249004888L;

    private int id;
    private String name;
    private String surname;
    private String patronimic;
    private String login;
    private String password;
    private String mail;
    private String phone;
    private int gender;
    private Date registerDate;
    private String role;
    private int roleId;
    private String activeStatus = Constants.ENABLED_USER;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronimic() {
        return patronimic;
    }

    public void setPatronimic(String patronimic) {
        this.patronimic = patronimic;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int isGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", patronimic=" + patronimic + ", login="
                + login + ", password=" + password + ", mail=" + mail + ", phone=" + phone + ", gender=" + gender
                + ", registerDate=" + registerDate + ", role=" + role + ", roleId=" + roleId + "]";
    }


}
