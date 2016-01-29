package dao;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is factory for all DAOs
 * Created by Nataliia Kozoriz on 17.01.2016.
 */
public class DAOFactory {

    /**
     * The only one instance of factory
     */
    private static DAOFactory instance = null;

    /**
     * Path to database
     */
    DataSource ds;

    private DAOFactory() throws NamingException {
        InitialContext initContext = new InitialContext();
        ds = (DataSource) initContext.lookup("java:comp/env/jdbc/CarRental");

    }

    /**
     * Returns factory
     *
     * @return factory
     */
    public static synchronized DAOFactory getInstance() {
        if (instance == null) {
            try {
                instance = new DAOFactory();
            } catch (NamingException ex) {
                Logger.getLogger(DAOFactory.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return instance;
    }

    public CarGroupDAO getCarGroupDAO() {
        return new CarGroupDAO(ds);
    }

    public ClientDAO getClientDAO() {
        return new ClientDAO(ds);
    }

    public PlaceDAO getPlaceDAO() {
        return new PlaceDAO(ds);
    }

    public CarOrderDAO getCarOrderDAO() {
        return new CarOrderDAO(ds);
    }

    public PaymentDAO getPaymentDAO() {
        return new PaymentDAO(ds);
    }

    public RentDAO getRentDAO() {
        return new RentDAO(ds);
    }

    public CarDAO getCarDAO() {
        return new CarDAO(ds);
    }
}
