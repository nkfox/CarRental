package model;

/**
 * This class is for payment types (payment for rent, repair or other).
 * Created by Nataliia Kozoriz on 24.01.2016.
 *
 * @see Payment
 */
public class PaymentName {

    /**
     * Payment name number
     */
    private int id;

    /**
     * Payment name in English
     */
    private String nameEN;

    /**
     * Payment name in UKrainian
     */
    private String nameUK;

    public PaymentName(int id, String nameEN, String nameUK) {
        this.id = id;
        this.nameEN = nameEN;
        this.nameUK = nameUK;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameEN() {
        return nameEN;
    }

    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
    }

    public String getNameUK() {
        return nameUK;
    }

    public void setNameUK(String nameUK) {
        this.nameUK = nameUK;
    }
}
