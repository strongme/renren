package org.scbit.lsbi.renren.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.scbit.lsbi.renren.config.AppConfig;

import com.renren.api.client.RenrenApiConfig;

public class ApiInitListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
	}

	public void contextInitialized(ServletContextEvent arg0) {
		
		RenrenApiConfig.renrenApiKey = AppConfig.API_KEY;
		RenrenApiConfig.renrenApiSecret = AppConfig.APP_SECRET;
		
	}

	
	
	
}
