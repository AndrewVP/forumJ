/**
 * 
 */
package org.forumj.web.servlet.tool;

import java.util.*;

/**
 * @author Andrew V. Pogrebnyak
 *
 */
public class ResourcesCache{
    
    private static ResourcesCache instance = new ResourcesCache(); 

    private Map<String, List<byte[]>> cache = new HashMap<String, List<byte[]>>();
    
    private ResourcesCache(){
        super();
    }
    
    public static ResourcesCache getInstance(){
        return instance;
    }
    

}
