function configForumIndex(){
	content.reload=function(){
        var dataForSend = {};
        dataForSend[COMMAND_PARAMETER] = FORUM_INDEX_COMMAND;
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
	        		linkForumIndexObjects();
	        		content.fadeIn();
	        		currentComponent=FORUM_INDEX_COMPONENT;
            		});
            	}
	        }
	    });
	};
}

function linkForumIndexObjects(){
	$( "#interfaces" ).tabs();
  	$("#top_pager").jPaginator({
        nbPages:FORUM_PAGES,
//        widthPx:20,  
        nbVisible:100,
        overBtnLeft:'#top_pager_o_left',
        overBtnRight:'#top_pager_o_right',
        maxBtnLeft:'#top_pager_m_left',
        maxBtnRight:'#top_pager_m_right',
    		coeffAcceleration:5,
        onPageClicked: function(a,num) {
//            $("#page3").html("demo3 - page : "+num);
        }
  	});
}
	
function threadClick(id){
	currentComponent=FORUM_THREAD_COMPONENT;
	configForumThread(id);
	content.reload();
	topMenu.reload(currentComponent);
	return false;
}
