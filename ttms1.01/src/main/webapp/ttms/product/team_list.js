$(document).ready(function(){
	doGetObjects();
	//注册事件
	$("#queryFormId")
	.on("click",".btn-search",doQueryObjects)
	.on("click",".btn-add",doLoadEditPage)
});
/*在模态框异步加载编辑页面*/
function doLoadEditPage(){
	var title;
	if($(this).hasClass("btn-add")){
		title="添加团信息";
	}
	var url="team/editUI.do";
	//在index.jsp页面中默认有一个模态框
	$("#modal-dialog .modal-body")
	.load(url,function(){//匿名回调函数
		$(".modal-title").html(title);
		$("#modal-dialog").modal("show");
	});
}



/*查询按钮的事件处理函数*/
function doQueryObjects(){
	//1.设置分页第1页
	$("#pageId").data("pageCurrent",1);
	//2.重用doGetObjects函数执行查询操作
	doGetObjects();
}
/*查询时获取表单数据*/
function getQueryFormData(){
	return {"projectName":$("#projectNameId").val()};
}
function doGetObjects(){
   //定义分页查询显示url
   var url="team/doFindPageObjects.do";
   //获取表单中参数信息
   var params=getQueryFormData();
   //获取当前页pageCurrent的值
   var pageCurrent=$("#pageId").data("pageCurrent");
   //假如pageCurrent没有值,默认设置为第一页
   if(!pageCurrent)pageCurrent=1;
   params.pageCurrent=pageCurrent;
   //发异步请求加载数据
   $.post(url,params,function(result){
	  if(result.state==1){
	    console.log(result);
	    //填充表单数据
	    setTableBodyRows(result.data.list);
	    //设置分页显示
	    setPagination(result.data.pageObject);
	  }else{
		alert(result.message);  
	  }
   });
}
/*将服务端返回的json数据填充到tbody中*/
function setTableBodyRows(result){
	//1.获得tbody对象
	var tBody=$("#tbodyId");//$("tbody")
	//2.清空tbody内容
	tBody.empty();
	//3.迭代result对象(当前页数据)
	for(var i in result){//循环一次去一行
	  //3.1构建tr对象
	  var tr=$("<tr></tr>");
	  //3.2tr对象上追加列
	  createTds(tr,result[i]);
	  //3.3将tr对象添加到tBody
	  tBody.append(tr);
	}
}
/*在tr中创建并追加td*/
function createTds(tr,r){
	var template=
   '<td><input type="checkBox" name="checkedItem" value="[id]"></td>'
	+'<td>[name]</td>'
	+'<td>[pName]</td>'
	+'<td>[state]</td>'
	+'<td>修改</td>';
	tr.append(template.replace("[id]",r.id)
	.replace("[name]",r.name)
	.replace("[pName]",r.projectName)
    .replace("[state]",
    		(r.valid?"启用":"禁用")));
}





