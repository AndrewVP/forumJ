function linkObjects(){
	layout = $("#layout"); 
	logo = $("#logo"); 
	topMenu = $("#topMenu"); 
	bottomMenu = $("#bottomMenu"); 
	content = $("#content"); 
	usersOnline = $("#usersOnline"); 
	footer = $("#footer"); 
}

function configObjects(){
	configLogo();	
	configMenu();	
	configForumIndex();
}

function initObjects(){
	logo.reload();
	topMenu.reload(currentComponent);
	content.reload();
}

$(document).ready(function(){
	linkObjects();
	configObjects();
	initObjects();
});

function getRandomSeed(){
	return "seed=" + (new Date()).getTime();
}
