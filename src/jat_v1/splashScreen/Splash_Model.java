package jat_v1.splashScreen;



import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import jat_v1.ServiceLocator;
import jat_v1.abstractClasses.Model;
import javafx.concurrent.Task;

public class Splash_Model extends Model {
	ServiceLocator serviceLocator;
	
	public Splash_Model(){
		super();
	}
	
	final Task<Void> initializer = new Task<Void>(){
		
		protected Void call() throws Exception {
			
			//First, take some time, update progress
			Integer i = 0;
			for (; i< 1000000000; i++){
				if ((i % 100000)==0)
					this.updateProgress(i,1000000000);
			}
			
			//create the service locator to hold our resources
			serviceLocator = ServiceLocator.getServiceLocator();
			
			
			serviceLocator.setLogger(configureLogging());
			
			return null;
		
		}

		private Logger configureLogging() {
			Logger rootLogger = Logger.getLogger("");
			rootLogger.setLevel(Level.FINEST);
			
			// By default there is one handler: the console
			Handler[] defaultHandlers = Logger.getLogger("").getHandlers();
			defaultHandlers[0].setLevel(Level.INFO);
			
			//Add our logger
			Logger ourLogger = Logger.getLogger(serviceLocator.getAPP_NAME());
			ourLogger.setLevel(Level.FINEST);
			
			// Add a file Handler, putting the rotating files in the tmp direcotry
			try {
				Handler logHandler = new FileHandler("%/"
						+serviceLocator.getAPP_NAME() + "_%u" + "_%g" + ".log",
						1000000, 9);
				logHandler.setLevel(Level.FINEST);
				ourLogger.addHandler(logHandler);
			} catch (Exception e) { // If we are unable to create log files
				throw new RuntimeException("Unable to initialize log files: "
						+ e.toString());
				
			}
			
			
			return ourLogger;
		}
		
	};


	public void initialize() {
		new Thread(initializer).start();
		
	}
	
	
	
	
	}
