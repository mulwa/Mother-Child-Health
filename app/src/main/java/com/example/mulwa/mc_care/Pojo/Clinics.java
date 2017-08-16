package com.example.mulwa.mc_care.Pojo;

/**
 * Created by mulwa on 7/24/17.
 */

public class Clinics {
    private String  clinicName;
    private String clinicDescription;
    private String clinicType;
    private String mobileNumber;
    private double latitude;
    private double longitude;

    public Clinics(String clinicName, String clinicDescription, String clinicType, String mobileNumber, double latitude, double longitude) {
        this.clinicName = clinicName;
        this.clinicDescription = clinicDescription;
        this.clinicType = clinicType;
        this.mobileNumber = mobileNumber;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getClinicName() {
        return clinicName;
    }

    public String getClinicDescription() {
        return clinicDescription;
    }

    public String getClinicType() {
        return clinicType;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
