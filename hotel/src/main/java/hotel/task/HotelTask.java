package hotel.task;

import javax.servlet.ServletContext;


public abstract class HotelTask implements Runnable {


    protected ServletContext servletContext;

    public HotelTask(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    public abstract void run();

}
