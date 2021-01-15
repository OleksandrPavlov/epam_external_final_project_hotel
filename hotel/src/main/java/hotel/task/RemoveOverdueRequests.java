package hotel.task;

import hotel.constant.Constants;
import hotel.exception.handler.ApplicationException;
import hotel.service.ServiceManager;

import javax.servlet.ServletContext;

public class RemoveOverdueRequests extends HotelTask {

    public RemoveOverdueRequests(ServletContext servletContext) {
        super(servletContext);
    }

    @Override
    public void run() {
        ServiceManager sm = (ServiceManager) servletContext.getAttribute(Constants.SERVICE_MANAGER);
        try {
            sm.getCommonService().removeOverdueRequests();
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }

}
