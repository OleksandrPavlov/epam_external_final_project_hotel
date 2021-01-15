package hotel.task;

import hotel.constant.Constants;
import hotel.exception.handler.ApplicationException;
import hotel.service.ServiceManager;

import javax.servlet.ServletContext;

public class RemoveStitchedBooks extends HotelTask {

    public RemoveStitchedBooks(ServletContext servletContext) {
        super(servletContext);
    }

    @Override
    public void run() {
        ServiceManager ms = (ServiceManager) servletContext.getAttribute(Constants.SERVICE_MANAGER);
        try {
            ms.getCommonService().removeAllStitchedBooks();
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }

}
