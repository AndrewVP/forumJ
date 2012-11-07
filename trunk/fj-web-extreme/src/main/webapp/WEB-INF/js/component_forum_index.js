function configForumIndex(){
	content.reload=function(){
        var dataForSend = {};
        dataForSend[COMMAND_PARAMETER] = FORUM_INDEX_COMMAND;
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
	        		currentComponent=FORUM_INDEX_COMPONENT;
            		});
//            	}
	        }
	    });
	};
}
	
function threadClick(id){
	currentComponent=FORUM_THREAD_COMPONENT;
	configForumThread(id);
	content.reload();
	topMenu.reload(currentComponent);
	return false;
}
