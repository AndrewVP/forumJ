package org.forumj.common.db.service;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.common.db.entity.IFJAction;

public interface ActionService {

   public void create(IFJAction action) throws ConfigurationException, SQLException, IOException;
   
   public IFJAction getObject();

}