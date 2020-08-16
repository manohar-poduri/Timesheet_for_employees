package com.example.timesheetforemployees;

public class UserHelperClass {

    String name, designation, birth, father, email, phone, password;

    String start_leave, end_leave, reason, phonenumber, spinner;

    public UserHelperClass() {
    }

    public UserHelperClass(String name, String designation, String birth, String father, String email, String phone, String password) {
        this.name = name;
        this.designation = designation;
        this.birth = birth;
        this.father = father;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public String getSpinner() {
        return spinner;
    }

    public void setSpinner(String spinner) {
        this.spinner = spinner;
    }

    public String getStart_leave() {
        return start_leave;
    }

    public void setStart_leave(String start_leave) {
        this.start_leave = start_leave;
    }

    public String getEnd_leave() {
        return end_leave;
    }

    public void setEnd_leave(String end_leave) {
        this.end_leave = end_leave;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
