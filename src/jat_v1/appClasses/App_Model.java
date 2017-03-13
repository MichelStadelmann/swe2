package jat_v1.appClasses;

import jat_v1.ServiceLocator;
import jat_v1.abstractClasses.Model;

public class App_Model extends Model {
	ServiceLocator serviceLocator;
	private int value;
	
	public App_Model(){
		value = 0;
		
		serviceLocator = ServiceLocator.getServiceLocator();
		serviceLocator.getLogger().info("Application model intialized");
	}

	public int incrementValue() {
		value++;
		serviceLocator.getLogger().info("Application model: value incremented to " + value);
		return value;
	}

	public int getValue() {
		return value;
	}

}
