function configLogo(){
	logo.reload=function(){
        var dataForSend = {};
        dataForSend[COMMAND_PARAMETER] = GET_LOGO_COMMAND;
	    $.ajax({url: mainUrl + "?" + getRandomSeed(),
	        type: "POST",
	        data: dataForSend,
	        success: function(data, status) {
            	if (data.exception){
            		showError(data);
//            	}else if (data.accessDenied){
//                    logout();
            	}else{
            		logo.fadeOut(function(){
            			logo.html(data);
            			logo.fadeIn();
            		});
            	}
	        }
	    });
	};
}
