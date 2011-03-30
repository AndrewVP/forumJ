<?php
class User{
	var $userNick;
	function User($userNick){
		$this->userNick=$userNick;
	}
	function getUserNick(){
		return $this->userNick;
	}
	function setUserNick($userNick){
		$this->userNick=$userNick;
	}
}
?>