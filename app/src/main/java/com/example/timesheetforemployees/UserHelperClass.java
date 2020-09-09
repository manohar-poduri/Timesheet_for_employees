package com.example.timesheetforemployees;

import java.util.Date;

public class UserHelperClass {

    String name, designation, birth, father, email, phone, password;

    String start_leave, end_leave, reason, phonenumber, spinner;

    String report;

    String annual,sick, personal,holiday,name_leave, approval;

    String punchin, punchout;

    int date;

    public UserHelperClass() {
    }


    public UserHelperClass(String name, String designation, String birth, String father,
                           String email, String phone, String password, String start_leave,
                           String end_leave, String reason, String phonenumber, String spinner,
                           String report, String annual, String sick, String personal,
                           String holiday, String name_leave, String approval, String punchin,
                           String punchout, int date) {
        this.name = name;
        this.designation = designation;
        this.birth = birth;
        this.father = father;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.start_leave = start_leave;
        this.end_leave = end_leave;
        this.reason = reason;
        this.phonenumber = phonenumber;
        this.spinner = spinner;
        this.report = report;
        this.annual = annual;
        this.sick = sick;
        this.personal = personal;
        this.holiday = holiday;
        this.name_leave = name_leave;
        this.approval = approval;
        this.punchin = punchin;
        this.punchout = punchout;
        this.date = date;
    }


    public int getDate() {
        return  date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getName_leave() {
        return name_leave;
    }

    public void setName_leave(String name_leave) {
        this.name_leave = name_leave;
    }

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

    public String getPunchin() {
        return punchin;
    }

    public void setPunchin(String punchin) {
        this.punchin = punchin;
    }

    public String getPunchout() {
        return punchout;
    }

    public void setPunchout(String punchout) {
        this.punchout = punchout;
    }

    public String getAnnual() {
        return annual;
    }

    public void setAnnual(String annual) {
        this.annual = annual;
    }

    public String getSick() {
        return sick;
    }

    public void setSick(String sick) {
        this.sick = sick;
    }

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }

    public String getHoliday() {
        return holiday;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
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
