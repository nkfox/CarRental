package model;

/**
 * This class is for statuses of order (registered, accepted, declined or canceled).
 * Created by Nataliia Kozoriz on 23.01.2016.
 *
 * @see CarOrder
 */
public class OrderStatus {

    /**
     * Order status number
     */
    private int id;

    /**
     * Order status in English
     */
    private String statusEN;

    /**
     * Order status in Ukrainian
     */
    private String statusUK;

    public OrderStatus(int id, String statusEN, String statusUK) {
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
