package tag;

import model.CarGroup;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This is html tag that shows all information about car group.
 * Created by Nataliia Kozoriz on 26.01.2016.
 *
 * @see CarGroup
 */
@SuppressWarnings("serial")
public class CarGroupTag extends TagSupport {

    /**
     * Car croup that is shown
     */
    CarGroup group;

    /**
     * Language in which information is shown
     */
    String lang;

    public void setGroup(CarGroup group) {
        this.group = group;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    @Override
    public int doStartTag() throws JspException {

        Locale locale = new Locale(lang);
        ResourceBundle rb = ResourceBundle.getBundle("property.text", locale);
        String groupG = rb.getString("group");
        String capacity = rb.getString("car.capacity");
        String capacityAmount = rb.getString("car.capacity.amount");
        String carBody = rb.getString("car.body");
        String carBodyAmount = rb.getString("car.body.amount");
        String transmission = rb.getString("car.transmission");
        String transmissionType = rb.getString("car.transmission." + group.getTransmission());
        String price = rb.getString("car.price");
        String priceAmount = rb.getString("car.price.amount");

        try {

            JspWriter out = pageContext.getOut();
            out.write("<img src=\"/image/" + group.getId() + ".jpg\" width=\"200\" height=\"132\"/><br>");
            out.write("<font color=\"white\"><h2><div name=carGroup>" + groupG + "   <strong>" + group.getId()
                    + "</strong></div></h2>");
            out.write("<p>" + capacity + "   <strong>" + group.getCapacity() + "   " + capacityAmount + "</strong><br>");
            out.write(carBody + "   <strong>" + group.getCarBody() + "   " + carBodyAmount + "</strong><br>");
            out.write(transmission + "   <strong>   " + transmissionType + "</strong><br>");
            out.write(price + "   <strong>" + group.getPrice() + "   " + priceAmount + "</strong><br></p></font>");

        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
}