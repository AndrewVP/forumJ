/**
 * 
 */
package org.forumj.checkip.connector.stopforumspamcom;

/**
 * @author andrew
 *
 */
public class IpResponseContent {
   private boolean success = false;
   private String type;
   private String appears;
   private String lastseen;
   private long frequency;
   public boolean isSuccess() {
      return success;
   }
   public void setSuccess(boolean success) {
      this.success = success;
   }
   public String getType() {
      return type;
   }
   public void setType(String type) {
      this.type = type;
   }
   public String getAppears() {
      return appears;
   }
   public void setAppears(String appears) {
      this.appears = appears;
   }
   public String getLastseen() {
      return lastseen;
   }
   public void setLastseen(String lastseen) {
      this.lastseen = lastseen;
   }
   public long getFrequency() {
      return frequency;
   }
   public void setFrequency(long frequency) {
      this.frequency = frequency;
   }
   
   public boolean isSpammer(){
      return getAppears() != null && getAppears().equalsIgnoreCase("yes");
   }
}
