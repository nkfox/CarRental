package commands;

import commands.adminPage.cars.CarsCommand;
import commands.adminPage.cars.ChangeCarStateCommand;
import commands.adminPage.cars.SearchCarsCommand;
import commands.adminPage.clients.ClientsCommand;
import commands.adminPage.clients.MakeAdminCommand;
import commands.adminPage.clients.SearchClientsCommand;
import commands.adminPage.orders.*;
import commands.adminPage.payments.ChangePaymentCommand;
import commands.adminPage.payments.PaymentsCommand;
import commands.adminPage.payments.SearchPaymentsCommand;
import commands.carOrder.CarGroupChoiceCommand;
import commands.carOrder.ClientDataInputCommand;
import commands.carOrder.DateChoiceCommand;
import commands.carOrder.PlaceChoiceCommand;
import commands.language.ChangeLanguageCommand;
import commands.personalPage.*;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is factory for all Commands
 * Created by Nataliia Kozoriz on 17.01.2016.
 */
public class CommandFactory {

    /**
     * Map of all commands
     */
    private final Map<String, Command> commandMap = new HashMap<>();

    /**
     * The only one instance of factory
     */
    private static CommandFactory instance = null;

    private CommandFactory() throws NamingException {
    }

    /**
     * Returns factory
     *
     * @return factory
     */
    public static synchronized CommandFactory getInstance() {
        if (instance == null) {
            try {
                instance = new CommandFactory();
            } catch (NamingException ex) {
                Logger.getLogger(CommandFactory.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return instance;
    }

    {
        commandMap.put("null", new EmptyCommand());
        commandMap.put("dateChoice", new DateChoiceCommand());
        commandMap.put("placeChoice", new PlaceChoiceCommand());
        commandMap.put("carGroupChoice", new CarGroupChoiceCommand());
        commandMap.put("clientDataInput", new ClientDataInputCommand());
        commandMap.put("changeLanguage", new ChangeLanguageCommand());
        commandMap.put("login", new LoginCommand());
        commandMap.put("personalOrders", new PersonalOrdersCommand());
        commandMap.put("cancelOrder", new CancelOrderCommand());
        commandMap.put("personalPayments", new PersonalPaymentsCommand());
        commandMap.put("personalRents", new PersonalRentsCommand());
        commandMap.put("personalData", new PersonalDataCommand());
        commandMap.put("changePersonalData", new ChangePersonalDataCommand());
        commandMap.put("changePassword", new ChangePasswordCommand());
        commandMap.put("orders", new OrdersCommand());
        commandMap.put("payments", new PaymentsCommand());
        commandMap.put("changeOrder", new ChangeOrderCommand());
        commandMap.put("changeOrderStatus", new ChangeOrderStatusCommand());
        commandMap.put("searchOrders", new SearchOrdersCommand());
        commandMap.put("changeRentStatus", new ChangeRentStatusCommand());
        commandMap.put("payForRepair", new PayForRepairCommand());
        commandMap.put("changePayment", new ChangePaymentCommand());
        commandMap.put("searchPayments", new SearchPaymentsCommand());
        commandMap.put("chooseCar", new ChooseCarCommand());
        commandMap.put("addRent", new AddRentCommand());
        commandMap.put("clients", new ClientsCommand());
        commandMap.put("makeAdmin", new MakeAdminCommand());
        commandMap.put("searchClients", new SearchClientsCommand());
        commandMap.put("cars", new CarsCommand());
        commandMap.put("searchCars", new SearchCarsCommand());
        commandMap.put("changeCarState", new ChangeCarStateCommand());
    }

    /**
     * Returns command that are requested
     *
     * @param request request
     * @return command
     * @see Command
     */
    public Command getCommand(HttpServletRequest request) {
        String value = request.getParameter("act");
        Command command = commandMap.get(value);
        if (command == null) {
            command = commandMap.get("null");
        }
        return command;
    }
}
