package model;

/**
 * This class includes all information about car.
 * Created by Nataliia Kozoriz on 18.01.2016.
 *
 * @see CarGroup
 * @see Place
 * @see CarState
 */
public class Car {

    /**
     * Car number
     */
    private String id;

    /**
     * Car model in English
     */
    private String modelEN;

    /**
     * Car model in Ukrainian
     */
    private String modelUK;

    /**
     * Car group
     */
    private String carGroup;

    /**
     * Car location
     */
    private Place place;

    /**
     * Car state (vacant, in rent or broken)
     */
    private CarState state;

    public Car(String modelEN, String modelUK, String carGroup, Place place, CarState state) {
        this.id = "";
        this.modelEN = modelEN;
        this.modelUK = modelUK;
        this.carGroup = carGroup;
        this.place = place;
        this.state = state;
    }

    public Car(String id, String modelEN, String modelUK, String carGroup, Place place, CarState state) {
        this.id = id;
        this.modelEN = modelEN;
        this.modelUK = modelUK;
        this.carGroup = carGroup;
        this.place = place;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModelEN() {
        return modelEN;
    }

    public void setModelEN(String modelEN) {
        this.modelEN = modelEN;
    }

    public String getModelUK() {
        return modelUK;
    }

    public void setModelUK(String modelUK) {
        this.modelUK = modelUK;
    }

    public String getCarGroup() {
        return carGroup;
    }

    public void setCarGroup(String carGroup) {
        this.carGroup = carGroup;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public CarState getState() {
        return state;
    }

    public void setState(CarState state) {
        this.state = state;
    }
}
