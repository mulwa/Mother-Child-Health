package com.example.mulwa.mc_care.Pojo;

import java.io.Serializable;

/**
 * Created by mulwa on 7/29/17.
 */

public class Child implements Serializable {
    private String name;
    private String patient_id;
    private String hospital_name;
    private String date_of_birth;
    private String gender;

    public Child(String name, String patient_id, String hospital_name, String date_of_birth, String gender) {
        this.name = name;
        this.patient_id = patient_id;
        this.hospital_name = hospital_name;
        this.date_of_birth = date_of_birth;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public String getHospital_name() {
        return hospital_name;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public String getGender() {
        return gender;
    }
}
