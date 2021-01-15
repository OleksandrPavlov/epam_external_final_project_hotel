package hotel.listener;

import hotel.constant.ApplicationInitConstants;
import hotel.constant.Constants;
import hotel.service.HotelManagerService;
import hotel.service.ServiceManager;
import hotel.task.RemoveOverdueRequests;
import hotel.task.RemoveOverdueResponses;
import hotel.task.RemoveStitchedBooks;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static hotel.constant.PathConstants.APPLICATION_PROPERTIES_FILE;

@WebListener
public class HotelContextListener implements ServletContextListener {
    public static final Logger LOGGER = LoggerFactory.getLogger(HotelManagerService.class);
    private Properties applicationProperties;
    private BasicDataSource basicDataSource;
    private ScheduledExecutorService ses = null;

    public void contextInitialized(ServletContextEvent sce) {
        initApplicationProperties();
        initDataSource();
        ServiceManager.getInstance(sce.getServletContext(), basicDataSource);
        ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleAtFixedRate(new RemoveStitchedBooks(sce.getServletContext()), 0, 1, TimeUnit.MINUTES);
        ses.scheduleAtFixedRate(new RemoveOverdueRequests(sce.getServletContext()), 0, 1, TimeUnit.MINUTES);
        ses.scheduleAtFixedRate(new RemoveOverdueResponses(sce.getServletContext()), 0, 1, TimeUnit.MINUTES);
    }

    public void contextDestroyed(ServletContextEvent sce) {
        ServiceManager sm = (ServiceManager) sce.getServletContext().getAttribute(Constants.SERVICE_MANAGER);
        sm.poolShutDown();
    }

    private void initApplicationProperties() {
        applicationProperties = new Properties();
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(APPLICATION_PROPERTIES_FILE)) {
            applicationProperties.load(inputStream);
        } catch (IOException e) {
            LOGGER.info("IO exception was occurred during loading application properties");
        }
        LOGGER.debug("Application properties object has been initialized!");
    }
    public void initDataSource(){
        basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(applicationProperties.getProperty(ApplicationInitConstants.DB_DRIVER));
        basicDataSource.setUrl(applicationProperties.getProperty(ApplicationInitConstants.DB_URL));
        basicDataSource.setUsername(applicationProperties.getProperty(ApplicationInitConstants.DB_USERNAME));
        basicDataSource.setPassword(applicationProperties.getProperty(ApplicationInitConstants.DB_PASSWORD));
        basicDataSource.setInitialSize(NumberUtils.createInteger(applicationProperties.getProperty(ApplicationInitConstants.DB_INIT_POOL_SIZE)));
        basicDataSource.setMaxTotal(NumberUtils.createInteger(applicationProperties.getProperty(ApplicationInitConstants.DB_MAX_POOL_SIZE)));
        LOGGER.debug("Connection pool has been initialized!");
    }
}
