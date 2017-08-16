package com.example.mulwa.mc_care.Pojo;

/**
 * Created by mulwa on 7/27/17.
 */

public class Expert {
    private int id;
    private String name;
    private String specialist;
    private String mobileNumber;
    private String email;
    private String dateRegistered;

    public Expert(int id, String name, String specialist, String mobileNumber, String email, String dateRegistered) {
        this.id = id;
        this.name = name;
        this.specialist = specialist;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.dateRegistered = dateRegistered;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecialist() {
        return specialist;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getDateRegistered() {
        return dateRegistered;
    }
}
