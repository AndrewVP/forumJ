<?php
include_once 'classes/ua/com/diletant/exception/invalidkeyexception.php';
/**
 * ����� ��� ����������� ��������� � ���� ����������
 */
class LocaleString{
	/**
	 * ������ ��������� �����
	 * @var unknown_type
	 */
	private $arrStrings = array();
	
	/**
	 * �����������
	 *
	 * @param unknown_type $strLang
	 * @param unknown_type $strFileName
	 * @param unknown_type $strDefaultLang
	 */
	public function __construct($strLang, $strFileName, $strDefaultLang){
		$_string = array();
		switch ($strLang) {
			case "ru":
				include_once $strFileName."ru".".php";
				break;
			case "ua":
				include_once $strFileName."ua".".php";
				break;
			default:
				include_once $strFileName.$strDefaultLang.".php";
				break;
		}
		$this->arrStrings = $_string;		
	}
	
	/**
	 * ���������� �������������� ������
	 *
	 * @param unknown_type $strKey
	 * @return unknown
	 */
	public function getString($strKey){
		if (isset($this->arrStrings[$strKey])){
			return $this->arrStrings[$strKey];
		}else{
			if ($strKey != NULL){
				throw new InvalidKeyException("Invalid key \"".$strKey."\"!"); 
			}else{
				throw new InvalidKeyException("Key can not be null!"); 
			}
		}
	}
}
?>