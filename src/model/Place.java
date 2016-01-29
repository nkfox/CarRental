package model;

/**
 * This class is for car locations. It includes city and address where cars can be rented.
 * Created by Nataliia Kozoriz on 23.01.2016.
 */
public class Place {

    /**
     * Place number
     */
    private int id;

    /**
     * Place city in English
     */
    private String cityEN;

    /**
     * Place city in Ukrainian
     */
    private String cityUK;

    /**
     * Place address in English
     */
    private String addressEN;

    /**
     * Place address in Ukrainian
     */
    private String addressUK;

    public Place(int id, String cityEN, String cityUK, String addressEN, String addressUK) {
        this.id = id;
        this.cityEN = cityEN;
        this.cityUK = cityUK;
        this.addressEN = addressEN;
        this.addressUK = addressUK;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityEN() {
        return cityEN;
    }

    public void setCityEN(String cityEN) {
        this.cityEN = cityEN;
    }

    public String getCityUK() {
        return cityUK;
    }

    public void setCityUK(String cityUK) {
        this.cityUK = cityUK;
    }

    public String getAddressEN() {
        return addressEN;
    }

    public void setAddressEN(String addressEN) {
        this.addressEN = addressEN;
    }

    public String getAddressUK() {
        return addressUK;
    }

    public void setAddressUK(String addressUK) {
        this.addressUK = addressUK;
    }
}
