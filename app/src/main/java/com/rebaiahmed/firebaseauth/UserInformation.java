package com.rebaiahmed.firebaseauth;

/**
 * Created by Rebai Ahmed on 25/02/2018.
 */

public class UserInformation {
    public String fullName;
    public String phoneNumber;
    public String postalAddress;

    public UserInformation() {

    }

    public UserInformation(String fullName, String phoneNumber, String postalAddress) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.postalAddress = postalAddress;
    }

}
