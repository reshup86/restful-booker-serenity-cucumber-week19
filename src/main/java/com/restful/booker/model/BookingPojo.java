package com.restful.booker.model;

import java.util.HashMap;

public class BookingPojo {

    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private HashMap<String,String> bookingdates;
    private String additionalneeds;



    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public boolean getDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public HashMap<String, String> getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(HashMap<String, String> bookingdates) {
        this.bookingdates = bookingdates;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }

    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }

}
