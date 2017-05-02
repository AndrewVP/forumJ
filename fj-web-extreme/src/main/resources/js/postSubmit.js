function post_submit(comand){
    if (document.forms.namedItem("post").NHEAD.value.replace(/(^\\s*)|(\\s*$)/g, "").length==0){
        alert(HEADER_IS_EMPTY);
    }else if (document.forms.namedItem("post").A2.value.replace(/(^\\s*)|(\\s*$)/g, "").length==0){
        alert(POST_IS_EMPTY);
    }else{
        document.forms.namedItem("post").comand.value = comand;
        document.forms.namedItem("post").submit();
    }
}
