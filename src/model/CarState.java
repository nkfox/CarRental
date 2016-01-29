package model;

/**
 * This class is for states of car (vacant, broken,in rent).
 * Created by Nataliia Kozoriz on 24.01.2016.
 *
 * @see Car
 */
public class CarState {

    /**
     * State number
     */
    private int id;

    /**
     * State in English
     */
    private String stateEN;

    /**
     * State in Ukrainian
     */
    private String stateUK;

    public CarState(int id, String stateEN, String stateUK) {
        this.id = id;
        this.stateEN = stateEN;
        this.stateUK = stateUK;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStateEN() {
        return stateEN;
    }

    public void setStateEN(String stateEN) {
        this.stateEN = stateEN;
    }

    public String getStateUK() {
        return stateUK;
    }

    public void setStateUK(String stateUK) {
        this.stateUK = stateUK;
    }
}
