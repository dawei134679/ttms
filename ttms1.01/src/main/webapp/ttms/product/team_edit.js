$(function(){
   //在模态框对应按钮上注册click事件
   $("#modal-dialog")
   .on("click",".ok",doSaveOrUpdate);
   //在模态框隐藏时,移除注册click事件
   //假如不移除可能会导致表单重复提交问题
   $("#modal-dialog")
   .on("hidden.bs.modal",function(){
	  $(this).off("click",".ok"); 
   });
   doGetIdAndNames();
});
/**获取项目下拉列表数据*/
function doGetIdAndNames(){
	 var url="team/doFindIdAndNames.do"
	 $.getJSON(url,function(result){
		 if(result.state==1){
			 doInitProjectSelect(result.data);
		 }else{
			 alert(result.message);
		 }
	 });
}
/*通过数据初始化select列表*/
function doInitProjectSelect(list){
	var select=$("#projectId");
	select.empty();
	var option=
	'<option value="[id]">[name]</option>'
	for(var i in list){
	 select.append(
	 option.replace("[id]",list[i].id)
	       .replace("[name]",list[i].name)); 
	}
}
/*执行保存或更新操作*/
function doSaveOrUpdate(){
	var url="team/doSaveObject.do";
	var params=getEditFormData();
	$.post(url,params,function(result){
		if(result.state==1){
			$("#modal-dialog").modal("hide");
			$("#pageId").data("pageCurrent",1);
			doGetObjects();
		}else{
			alert(result.message)
		}
	});
}
/*获得编辑框数据*/
function getEditFormData(){
	var params={
	 "name":$("#nameId").val(),
	 "projectId":$("#projectId").val(),
	 "valid":$("input[name='valid']:checked").val(),
	 "note":$("#noteId").val()	
	};
	return params;
}






