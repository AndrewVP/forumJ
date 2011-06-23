<?php
class Constraint {
	
	public static $VERB_METHOD_COOKIE = 1;
	public static $VERB_METHOD_GET = 2;
	public static $VERB_METHOD_POST = 4;

	public static $CT_MINLENGTH = 1;
	public static $CT_MAXLENGTH = 2;
	public static $CT_PERMITTEDCHARACTERS = 3;
	public static $CT_NONPERMITTEDCHARACTERS = 4;
	public static $CT_LESSTHAN = 5;
	public static $CT_EQUALTO = 6;
	public static $CT_MORETHAN = 7;
	public static $CT_NOTEQUALTO = 8;
	public static $CT_MUSTMATCHREGEXP = 9;
	public static $CT_MUSTNOTMATCHREGEXP = 10;

	private $_intConstraintType;
	private $_strConstraintOperand;

	function __construct($intConstraintType, $strConstraintOperand) {
		$this->_intConstraintType = $intConstraintType;
		$this->_strConstraintOperand = $strConstraintOperand;
	}

	function GetConstraintType() {
		return($this->_intConstraintType);
	}

	function GetConstraintOperand() {
		return($this->_strConstraintOperand);
	}
}?>
