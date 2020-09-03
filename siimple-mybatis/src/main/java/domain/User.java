package domain;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private Integer id;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private Date birthday;
    private String sex;
    private String address;
    public String toString(){
        return id.toString()+ " "+username.toString() + " " + birthday.toString() + " " + sex.toString() + " " +address.toString();
        //return null;
    }
}
