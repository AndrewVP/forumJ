/**
 * 
 */
package org.forumj.checkip;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author andrew
 *
 */
public class SaxHandler extends DefaultHandler{

   private String tempVal;
   
   //to maintain context
   private IpResponseContent content;
   
   //Event Handlers
   public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
      //reset
      tempVal = "";
      if(qName.equalsIgnoreCase("response")) {
         content = new IpResponseContent();
         content.setSuccess(Boolean.parseBoolean(attributes.getValue("success")));
      }
   }
   

   public void characters(char[] ch, int start, int length) throws SAXException {
      tempVal = new String(ch,start,length);
   }
   
   public void endElement(String uri, String localName, String qName) throws SAXException {
      if(qName.equalsIgnoreCase("type")) {
         content.setType(tempVal);
      }else if (qName.equalsIgnoreCase("appears")) {
         content.setAppears(tempVal);
      }else if (qName.equalsIgnoreCase("lastseen")) {
         content.setLastseen(tempVal);
      }else if (qName.equalsIgnoreCase("frequency")) {
         content.setFrequency(Long.parseLong(tempVal));
      }
   }


   public IpResponseContent getContent() {
      return content;
   }
}
