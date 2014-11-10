function linkObjects(){
	layout = $("#layout"); 
	logo = $("#logo"); 
	topMenu = $("#topMenu"); 
	bottomMenu = $("#bottomMenu"); 
	content = $("#content"); 
	usersOnline = $("#usersOnline"); 
	footer = $("#footer"); 
	errorDiv = $("#error");
}

function configObjects(){
	configLogo();	
	configMenu();	
	configForumIndex();
}

function showError(errObject){
	var errText = "<h2>" + errObject.message + "</h2>";
	for (var errorCount in errObject.stackTrace){
		errText +='<p>' + errObject.stackTrace[errorCount] + '</p>';
	}
	errorDiv.html(errText);
	errorDiv.dialog({
		resizable: true,
		height:500,
		width:700,
		modal: true,
		buttons: {
			"Ok": function() {
				$( this ).dialog( "close" );
			}
		}
	});
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
