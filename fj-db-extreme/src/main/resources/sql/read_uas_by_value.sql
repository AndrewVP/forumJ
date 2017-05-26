SELECT `user_agent_string`.`id`,
  `user_agent_string`.`value`,
  `user_agent_string`.`botId`,
  `user_agent_string`.`operationSystemId`,
  `user_agent_string`.`browserId`,
  `user_agent_string`.`deviceType`
FROM `diletant`.`user_agent_string`
WHERE `value` = ?;
