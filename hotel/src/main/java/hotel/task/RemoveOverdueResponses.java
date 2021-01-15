package hotel.task;

import hotel.constant.Constants;
import hotel.exception.handler.ApplicationException;
import hotel.service.ServiceManager;

import javax.servlet.ServletContext;

public class RemoveOverdueResponses extends HotelTask {

    public RemoveOverdueResponses(ServletContext sce) {
        super(sce);
    }

    @Override
    public void run() {
        ServiceManager ms = (ServiceManager) servletContext.getAttribute(Constants.SERVICE_MANAGER);
        try {
            ms.getCommonService().removeOverdueResponses();
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }
}
