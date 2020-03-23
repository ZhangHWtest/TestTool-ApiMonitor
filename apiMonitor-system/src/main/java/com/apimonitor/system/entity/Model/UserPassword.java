package com.apimonitor.system.entity.Model;

public class UserPassword {

    private String name;
    private String password;
    private String newPassword;
    private String checkPassword;


    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getCheckPassword() {
        return checkPassword;
    }

    public void setCheckPassword(String checkPassword) {
        this.checkPassword = checkPassword;
    }

    @Override
    public String toString() {
        return "UserPassword{" +
                "password='" + password + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", checkPassword='" + checkPassword + '\'' +
                '}';
    }
}
