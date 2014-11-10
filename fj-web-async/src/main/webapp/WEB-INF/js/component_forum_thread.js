function configForumThread(id){
	content.reload=function(){
        var dataForSend = {};
        dataForSend[COMMAND_PARAMETER] = FORUM_THREAD_COMMAND;
        dataForSend["id"] = id;
	    $.ajax({url: mainUrl + "?" + getRandomSeed(),
	        type: "POST",
	        data: dataForSend,
	        success: function(data, status) {
            	if (data.exception){
            		showError(data);
//            	}else if (data.accessDenied){
//                    logout();
            	}else{
	        	content.fadeOut(function(){
	        		content.html(data);
	        		content.fadeIn();
            		});
            	}
	        }
	    });
	};
}
	
