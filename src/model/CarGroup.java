package model;

/**
 * This class includes all information about car groups.
 * Created by Nataliia Kozoriz on 17.01.2016.
 *
 * @see Car
 */
public class CarGroup {

    /**
     * Car group name
     */
    private String id;

    /**
     * Car capacity (amount of passengers)
     */
    private int capacity;

    /**
     * Car body (amount of doors)
     */
    private int carBody;

    /**
     * Car transmission (automatic or manual)
     */
    private String transmission;

    /**
     * Car rent price for one day
     */
    private double price;

    public CarGroup() {
        this.id = "";
        this.capacity = 1;
        this.carBody = 1;
        this.transmission = "Automatic";
        this.price = 1;
    }

    public CarGroup(String id, int capacity, int carBody, String transmission, double price) {
        this.id = id;
        this.capacity = capacity;
        this.carBody = carBody;
        this.transmission = transmission;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCarBody() {
        return carBody;
    }

    public void setCarBody(int carBody) {
        this.carBody = carBody;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
