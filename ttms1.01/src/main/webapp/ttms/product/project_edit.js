$(document).ready(function(){
  //1.在模态框的save按钮上上注册点击事件
   $("#modal-dialog")
   .on("click",".ok",doSaveOrUpdate);
   //在模态框上绑定或移除事件时
   //最好参考官方文档用id,例如$("#modal-dialog")
   $("#modal-dialog")
   .on("hidden.bs.modal",function(){
    //当模态框隐藏时移除.ok上注册的click事件
	  //console.log("===hidden===")
	  $(this).off("click",".ok"); 
	  // $("#modal-dialog").off("click",".ok");
      $(this).removeData("id");//假如有
   });
   var id=$("#modal-dialog").data("id");
   console.log("id="+id);
   //假如id有值则可以根据此值执行下一步操作
   if(id)doFindObjectById(id);
});
 /**根据id执行查询操作*/
 function doFindObjectById(id){
	var params={"id":id}
	var url="project/doFindObjectById.do"
	$.post(url,params,function(result){
		if(result.state==1){
		 //初始化页面数据
		 doInitEditFormData(result.data);
		}else{
		alert(result.message);
		}
	});
 };//出现400异常(服务端拒绝接受数据(一般是数据格式不对))
 function doInitEditFormData(data){
	 $("#nameId").val(data.name);
	 $("#codeId").val(data.code);
	 $("#beginDateId").val(data.beginDate);
	 $("#endDateId").val(data.endDate);
	 $("#noteId").html(data.note);
	 $("#editFormId input[name='valid']")
	 .each(function(){
		 if($(this).val()==data.valid){
			 //选中
			 $(this).prop("checked",true);
		 }
	 })
 }

/*保存或更新数据*/
function doSaveOrUpdate(){
	//1.验证表单数据是否为空
	if(!$("#editFormId").valid())return;
	//2.获得表单数据
	var params=getEditFormData();
	//当修改记录时需要id值,所以要在参数中
	//要动态添加一个key/value,例如{"id":1}
	var id=$("#modal-dialog").data("id");
	if(id)params.id=id;//{....,"id":1}
	//3.异步提交表单数据
	var insert="project/doSaveObject.do";
    var update="project/doUpdateObject.do";
    var url=id?update:insert;
    console.log(params);
	$.post(url,params,function(result){
		if(result.state==1){
		   $("#modal-dialog").modal("hide");
		   doGetObjects();
		}else{
		   alert(result.message);
		}
	});
}
/*获取表单数据*/
function getEditFormData(){
	var params={
		"code":$("#codeId").val(),
		"name":$("#nameId").val(),
		"beginDate":$("#beginDateId").val(),
		"endDate":$("#endDateId").val(),
		"valid":$("input[name='valid']:checked").val(),
		"note":$("#noteId").val()
	}
	return params;
}





