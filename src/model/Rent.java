package model;

/**
 * This class is for rent. Each rent is connected with only one CarOrder.
 * Created Nataliia Kozoriz on 18.01.2016.
 *
 * @see CarOrder
 * @see RentStatus
 */
public class Rent {

    /**
     * Rent number
     */
    private int id;

    /**
     * CarOrder which this rent is connected with
     */
    private int carOrder;

    /**
     * Number of car that is rented
     */
    private Car car;

    /**
     * Status or rent
     */
    private RentStatus status;

    public Rent(int carOrder, Car car, RentStatus status) {
        this.carOrder = carOrder;
        this.car = car;
        this.status = status;
    }

    public Rent(int id, int carOrder, Car car, RentStatus status) {
        this.id = id;
        this.carOrder = carOrder;
        this.car = car;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarOrder() {
        return carOrder;
    }

    public void setCarOrder(int carOrder) {
        this.carOrder = carOrder;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public RentStatus getStatus() {
        return status;
    }

    public void setStatus(RentStatus status) {
        this.status = status;
    }
}
