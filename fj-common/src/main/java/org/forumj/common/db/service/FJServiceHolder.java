/**
 * 
 */
package org.forumj.common.db.service;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.common.config.FJConfiguration;

/**
 * @author andrew
 *
 */
public class FJServiceHolder {
   
   private static ControlService controlService = null;
   
   @SuppressWarnings("unused")
   private FJServiceHolder instance = new FJServiceHolder();
   
   private FJServiceHolder() throws ConfigurationException, InstantiationException, IllegalAccessException, ClassNotFoundException{
      String controlServiceClassName = FJConfiguration.getConfig().getString("service.controlService.class");
      controlService = (ControlService) Class.forName(controlServiceClassName).newInstance();
   }
   
   public static ControlService getControlService(){
      return controlService;
   }

}
