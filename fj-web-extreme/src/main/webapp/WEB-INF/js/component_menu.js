function configMenu(){
	topMenu.reload=function(component){
        var dataForSend = {};
        dataForSend[COMMAND_PARAMETER] = GET_MENU_COMMAND;
        dataForSend[COMPONENT_PARAMETER] = component;
	    $.ajax({url: mainUrl + "?" + getRandomSeed(),
	        type: "POST",
	        data: dataForSend,
	        success: function(data, status) {
//            	if (data.exception){
//            		showError(data);
//            	}else if (data.accessDenied){
//                    logout();
//            	}else{
	        	topMenu.fadeOut(function(){
	        		topMenu.html(data);
	        		topMenu.fadeIn();
            		});
	        	bottomMenu.fadeOut(function(){
	        		bottomMenu.html(data);
	        		bottomMenu.fadeIn();
	        	});
//            	}
	        }
	    });
	};
}
	
function logoutClick(){
    var dataForSend = {};
    dataForSend[COMMAND_PARAMETER] = LOGOUT_COMMAND;
    $.ajax({url: mainUrl + "?" + getRandomSeed(),
        type: "POST",
        data: dataForSend,
        success: function(data, status) {
//            	if (data.exception){
//            		showError(data);
//            	}else if (data.accessDenied){
//                    logout();
//            	}else{
        	logo.reload();
        	topMenu.reload(currentComponent);
//            	}
        }
    });
}

function loginClick(){
	var dataForSend = {};
	dataForSend[COMMAND_PARAMETER] = GET_LOGIN_COMMAND;
	$.ajax({url: mainUrl + "?" + getRandomSeed(),
		type: "POST",
		data: dataForSend,
		success: function(data, status) {
//            	if (data.exception){
//            		showError(data);
//            	}else if (data.accessDenied){
//                    logout();
//            	}else{
			content.fadeOut(function(){
				content.html(data);
				content.fadeIn();
        		});
//            	}
		}
	});
}
