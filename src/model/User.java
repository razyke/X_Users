package model;


import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Daniil Smirnov on 27.07.2017.
 * All copy registered MF.
 */

public class User {

    private int idUser;
    private String
            firstName,
            lastName,
            loginName,
            password,
            email;
    private Date
            birthday,
            createdTimeStamp;
    private Timestamp
            lastupdateTimeStamp;
    private boolean
            active,
            admin;

    public Adress adressClass;
    public Group groupClass;

    public User() {
        adressClass = new Adress();
        groupClass = new Group();
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idAdress) {
        this.idUser = idAdress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Date getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    public void setCreatedTimeStamp(Date createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }

    public Date getLastupdateTimeStamp() {
        return lastupdateTimeStamp;
    }

    public void setLastupdateTimeStamp(Timestamp lastupdateTimeStamp) {
        this.lastupdateTimeStamp = lastupdateTimeStamp;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Adress getAdressClass() {
        return adressClass;
    }

    public void setAdressClass(Adress adressClass) {
        this.adressClass = adressClass;
    }

    public Group getGroupClass() {
        return groupClass;
    }

    public void setGroupClass(Group groupClass) {
        this.groupClass = groupClass;
    }

    @Override
    public String toString() {
        return "User [idUser=" + idUser + ", firstName=" + firstName
                +", loginName="+ loginName
                +", LastName=" + lastName + ", birthday=" + birthday + ", email="
                + email +",Adress country "+adressClass.country +"  active " + active +" admin "+ admin +"]";
    }

    public class Adress{
        private int
                idAdress,
                zip;
        private String
                country,
                city,
                district,
                street;

        public int getIdAdress() {
            return idAdress;
        }

        public void setIdAdress(int idAdress) {
            this.idAdress = idAdress;
        }

        public int getZip() {
            return zip;
        }

        public void setZip(int zip) {
            this.zip = zip;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }
    }

    public class Group{
        private int idGroup;
        private String role;

        public int getIdGroup() {
            return idGroup;
        }

        public void setIdGroup(int idGroup) {
            this.idGroup = idGroup;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }

}
