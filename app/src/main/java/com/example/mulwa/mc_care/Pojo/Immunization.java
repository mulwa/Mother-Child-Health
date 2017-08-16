package com.example.mulwa.mc_care.Pojo;

/**
 * Created by mulwa on 7/30/17.
 */

public class Immunization {
    private String name;
    private String date_given;
    private String expected_date;

    public Immunization(String name, String date_given, String expected_date) {
        this.name = name;
        this.date_given = date_given;
        this.expected_date = expected_date;
    }

    public String getName() {
        return name;
    }

    public String getDate_given() {
        return date_given;
    }

    public String getExpected_date() {
        return expected_date;
    }
}
