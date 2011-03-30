<?php
//require_once("constants.php");
//require_once("constraint.php");
class Constraintfailure {
  private $_strParameterName;
  private $_intVerbMethod;
  private $_objFailedConstraintObject;
  
  function __construct($strParameterName, $intVerbMethod, $objFailedConstraintObject) {
    $this->_strParameterName = $strParameterName;
    $this->_intVerbMethod = $intVerbMethod;
    $this->_objFailedConstraintObject = $objFailedConstraintObject;
  }
  
  function GetParameterName() {
    return($this->_strParameterName);
  }
  
  function GetVerbMethod() {
    return($this->_intVerbMethod);
  }
  
  function GetFailedConstraintObject() {
    return($this->_objFailedConstraintObject);
  }  
}
