/*
 * Copyright Andrew V. Pogrebnyak
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.forumj.common;


/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public enum Component {

    FORUM_INDEX(0),
    FORUM_THREAD(1);

    private int componentId;
    
    public static final Component DEFAUL_COMPONENT = FORUM_INDEX;  

    /**
     * @param command
     */
    private Component(int componentId) {
       this.componentId = componentId;
    }

    public int getId() {
       return componentId;
    }
    
    public static Component fromId(int id){
          for(Component result : values()){
             if (result.getId() == id) return result;
          }
          return DEFAUL_COMPONENT;
    }
}
