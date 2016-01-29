package model;

/**
 * This class is for statuses of rent (rent is in progress, car has been returned or not returned).
 * Created by Nataliia Kozoriz on 23.01.2016.
 *
 * @see Rent
 */
public class RentStatus {

    /**
     * Rent status number
     */
    private int id;

    /**
     * Rent status in English
     */
    private String statusEN;

    /**
     * Rent status in Ukrainian
     */
    private String statusUK;

    public RentStatus(int id, String statusEN, String statusUK) {
        this.id = id;
        this.statusEN = statusEN;
        this.statusUK = statusUK;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatusEN() {
        return statusEN;
    }

    public void setStatusEN(String statusEN) {
        this.statusEN = statusEN;
    }

    public String getStatusUK() {
        return statusUK;
    }

    public void setStatusUK(String statusUK) {
        this.statusUK = statusUK;
    }
}
