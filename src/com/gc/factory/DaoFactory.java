/**
 * 
 */
package com.gc.factory;

/**
 * @author Serhiy Bardysh
 *
 */
public class DaoFactory {
	
		public static final  String AttendeesDao = "attendeesdao";

		public static AttendeesDao getInstance (String type) {
			AttendeesDaoImpl daoimpl = null;
			switch (type) {
			case AttendeesDao:
				daoimpl = new AttendeesDaoImpl();
				break;
				
				
				
			}
			return daoimpl;
		}

}
