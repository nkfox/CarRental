package model;

/**
 * This class includes all information about clients. Also shows if this client is admin.
 * Created by Nataliia Kozoriz on 17.01.2016.
 */
public class Client {

    /**
     * Client passport number
     */
    private String id;

    /**
     * Client name and surname in English
     */
    private String nameSurnameEN;

    /**
     * Client name and surname in Ukrainian
     */
    private String nameSurnameUK;

    /**
     * Client mail address
     */
    private String mail;

    /**
     * Client driver license number
     */
    private String driverLicense;

    /**
     * Client telephone number
     */
    private String telephone;

    /**
     * True if client is admin
     */
    private boolean admin;

    /**
     * Client password
     */
    private String password;

    public Client(String id, String nameSurname, String mail, String driverLicense,
                  String telephone, boolean admin, String password) {
        this.id = id;
        this.nameSurnameEN = nameSurname;
        this.nameSurnameUK = nameSurname;
        this.mail = mail;
        this.driverLicense = driverLicense;
        this.telephone = telephone;
        this.admin = admin;
        this.password = password;
    }

    public Client(String id, String nameSurnameEN, String nameSurnameUK, String mail,
                  String driverLicense, String telephone, boolean admin, String password) {
        this.id = id;
        this.nameSurnameEN = nameSurnameEN;
        this.nameSurnameUK = nameSurnameUK;
        this.mail = mail;
        this.driverLicense = driverLicense;
        this.telephone = telephone;
        this.admin = admin;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameSurnameEN() {
        return nameSurnameEN;
    }

    public void setNameSurnameEN(String nameSurnameEN) {
        this.nameSurnameEN = nameSurnameEN;
    }

    public String getNameSurnameUK() {
        return nameSurnameUK;
    }

    public void setNameSurnameUK(String nameSurnameUK) {
        this.nameSurnameUK = nameSurnameUK;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
