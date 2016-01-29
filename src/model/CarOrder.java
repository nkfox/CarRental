package model;

import java.sql.Date;

/**
 * This class includes all information about orders.
 * Created by Nataliia Kozoriz on 18.01.2016.
 *
 * @see Client
 * @see CarGroup
 * @see Place
 * @see OrderStatus
 */
public class CarOrder {

    /**
     * Order number
     */
    private int id;

    /**
     * Client that has done this order
     */
    private Client client;

    /**
     * Car group that is ordered
     */
    private CarGroup carGroup;

    /**
     * Start of rent period
     */
    private Date startDate;

    /**
     * End of rent period
     */
    private Date endDate;

    /**
     * Location, where car is rented
     */
    private Place startPlace;

    /**
     * Location where car is given back
     */
    private Place endPlace;

    /**
     * Order status
     */
    private OrderStatus status;

    /**
     * Comment for order. If status has been changed, admin can save reason in this field
     */
    private String comment;

    public CarOrder(Client client, CarGroup carGroup, Date startDate, Date endDate,
                    Place startPlace, Place endPlace) {
        this.client = client;
        this.carGroup = carGroup;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startPlace = startPlace;
        this.endPlace = endPlace;
        this.status = null;
        this.comment = "";
    }

    public CarOrder(int id, Client client, CarGroup carGroup, Date startDate, Date endDate,
                    Place startPlace, Place endPlace, OrderStatus status, String comment) {
        this.id = id;
        this.client = client;
        this.carGroup = carGroup;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startPlace = startPlace;
        this.endPlace = endPlace;
        this.status = status;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public CarGroup getCarGroup() {
        return carGroup;
    }

    public void setCarGroup(CarGroup carGroup) {
        this.carGroup = carGroup;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Place getStartPlace() {
        return startPlace;
    }

    public void setStartPlace(Place startPlace) {
        this.startPlace = startPlace;
    }

    public Place getEndPlace() {
        return endPlace;
    }

    public void setEndPlace(Place endPlace) {
        this.endPlace = endPlace;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
