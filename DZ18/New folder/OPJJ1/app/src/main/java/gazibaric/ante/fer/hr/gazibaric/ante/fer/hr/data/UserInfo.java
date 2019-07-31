package gazibaric.ante.fer.hr.gazibaric.ante.fer.hr.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserInfo implements Serializable{

    @SerializedName("avatar_location")
    private String avatar;
    @SerializedName("first_name")
    private String name;
    @SerializedName("last_name")
    private String surname;
    @SerializedName("phone_no")
    private String email;
    @SerializedName("email_sknf")
    private String phoneNumber;
    private int age;


    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
