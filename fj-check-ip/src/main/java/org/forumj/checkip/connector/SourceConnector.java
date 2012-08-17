/**
 * 
 */
package org.forumj.checkip.connector;


/**
 * @author andrew
 *
 */
public interface SourceConnector {

   public String getSourceName();
   
   public Boolean isListedAsSpammer(String ip) throws ConnectorException;
}
