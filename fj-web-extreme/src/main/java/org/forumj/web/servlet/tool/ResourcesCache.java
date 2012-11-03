/**
 * 
 */
package org.forumj.web.servlet.tool;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Andrew V. Pogrebnyak
 *
 */
public class ResourcesCache{
    
    private static ResourcesCache instance = new ResourcesCache(); 

    private Map<String, List<byte[]>> cache = new ConcurrentHashMap<String, List<byte[]>>();
    
    private ResourcesCache(){
        super();
    }
    
    public static ResourcesCache getInstance(){
        return instance;
    }
    
    public List<byte[]> get(String key){
    	return cache.get(key);
    }
    

    public void put(String key, List<byte[]> value){
    	cache.put(key, value);
    }
    
    public void remove(String key){
    	cache.remove(key);
    }
}
