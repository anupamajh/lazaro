package com.carmel.common.helpers.model;

import com.carmel.common.helpers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserInfo {

    @Autowired
    UserRepository repository;

    private String id;
    private String fullName;
    private String userName;
    private String phone;
    private int gender;
    private String[] addressBook;
    private String[] userPreferences;


    public UserInfo(){
        this.phone = "";
        this.gender = 0;
        this.addressBook = new String[0];
        this.userPreferences = new String[0];


    }

    public UserInfo(User user) {
        this.fullName =user.getUserName();
        this.userName = user.getUserName();
        this.id = user.getId();
        this.phone = "";
        this.gender = 0;
        this.addressBook = new String[0];
        this.userPreferences = new String[0];


    }

    public UserInfo(String userName){
        Optional<User> optionalUser = repository.findByUserName(userName);
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
             this.fullName = user.getUserName();
            this.userName = user.getUserName();
            this.id = user.getId();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String[] getAddressBook() {
        return addressBook;
    }

    public void setAddressBook(String[] addressBook) {
        this.addressBook = addressBook;
    }

    public String[] getUserPreferences() {
        return userPreferences;
    }

    public void setUserPreferences(String[] userPreferences) {
        this.userPreferences = userPreferences;
    }
}
