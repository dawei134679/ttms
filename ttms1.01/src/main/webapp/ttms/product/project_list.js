$(document).ready(function(){
//1.页面加载完成,通过doGetObjects方法
	//异步请求数据
	doGetObjects();
//2.在查询按钮上注册点击事件
	$("#queryFormId")
    .on("click",".btn-search",doQueryObjects)
	.on("click",".btn-valid,.btn-invalid",doValidById)
	.on("click",".btn-add,.btn-update",doLoadEditPage)
	
});//$(function(){})
/*加载(异步加载)编辑页面*/
function doLoadEditPage(){
	//判定是添加还是修改
	var title;
	if($(this).hasClass("btn-add")){
		title="添加项目";
	}
	if($(this).hasClass("btn-update")){
		title="修改项目"
		//在模态框上绑定一个id值,在修改页面
		//可以获得此值,然后根据有没有值执行
		//不同操作
	    $("#modal-dialog").data(
	    "id",
	    $(this).parent().parent().data("id"));
		
	}
	
	var url="project/editUI.do"
	//在id为modal-dialog的对象中找到
	//类选择器为modal-body的对象,然后
	//在此位置异步加载url
	$("#modal-dialog .modal-body")
	.load(url,function(){
		//设置标题
		$(".modal-title").html(title);
		//显示模态框
		$("#modal-dialog").modal("show");
	});
}
/**实现项目对象的禁用和启用操作*/
function doValidById(){
   //debugger
   //1.获得点击的对象的class属性
   //要根据此属性的值判定是执行禁用还是启用
   //var clazz=$(this).attr("class");//<button .. class="btn brn-primary btn-valid">
   //2.执行禁用启用操作
   //2.1设置状态值(后续要将此状态值传到服务端)
   var state;
   if($(this).hasClass("btn-valid")){
	   state=1;
   }
   if($(this).hasClass("btn-invalid")){
	   state=0
   }
   //2.2 获得选中的checkbox的值
   var checkedIds=getCheckedIds();
   if(checkedIds==""){
	   alert("请选择一个");
	   return;
   }
   console.log(checkedIds);
   //2.3 定义url,封装参数数据
  var params={"valid":state,"checkedIds":checkedIds}
  var url="project/doValidById.do";
   //2.4 发起异步请求并处理结果
  $.post(url,params,function(result){
	   if(result.state==1){
		   //再次查询显示修改结果
		   alert(result.message);
		   doGetObjects();
	   }else{
		   alert(result.message);
	   }
   });

}
/*通过此函数获得选中的checkboxid*/
function getCheckedIds(){
	var checkedIds="";//1,2,3,4,5,6
	//each用于迭代名字为checkedName的input对象
	$('#tbodyId input[name="checkedName"]')
	.each(function(){
		//验证当前对象中有没有checked属性
		if($(this).prop("checked")){
			if(checkedIds==""){
			checkedIds+=$(this).val();
			}else{
			checkedIds+=","+$(this).val();	
			}
		}
	});
	return checkedIds;
}
/*定义查询数据的方法*/
function doQueryObjects(){
  //1.初始化分页的pageCurrent的值
  $("#pageId").data("pageCurrent",1);
  //2.获得并提交参数,执行查询操作
  doGetObjects();//重用此方法
}
/*定义函数获得查询表单中的参数值*/
function getQueryFormData(){
	//获得表单数据,然后构建JSON对象
	var params={
		"name":$("#searchNameId").val(),
		"valid":$("#searchValidId").val()
	};
	//console.log(JSON.stringify(params))
	return params;
}
/*定义分页查询,列表显示数据的方法*/
function doGetObjects(){
   //定义访问服务端的url
   var url="project/doFindObjects.do"
   //查询时获取表单数据
   var params=getQueryFormData();
   //底层发起ajax异步POST请求
   //客户端会将服务端返回的json串转换
   //为json对象赋值给result参数
   //获得当前页的数据
   var pageCurrent=
   $("#pageId").data("pageCurrent");
   if(!pageCurrent)pageCurrent=1;
   //动态为params对应的json对象添加key/value
   params.pageCurrent=pageCurrent;
   //$.getJSON(url,params,function(result))
   $.post(url,params,function(result){
	   //输出JSON对象
	   //console.log(result);
	   //输出JSON对象中code的值
	   //console.log(result.code);
	   //输出JSON字符串
	   //console.log(JSON.stringify(result));
	   //假如result对象对应的是一个JSON数组
	   //console.log(result[0].id);
	   //for(var i in result){
		//  console.log(result[i].id)
	   //}
	   if(result.state==1){
	   setTableBodyRows(result.data.list);
	   //设置分页信息(参考page.js)
	   setPagination(result.data.pageObject);
	   }else{
	   alert(result.message);
	   }
   });
}
/*将服务端返回的数据添加在页面table的
 * tbody中*/
function setTableBodyRows(result){
	//1.获得tbody对象
	var tBody=$("#tbodyId");
	//2.清空tBody中数据(类似刷新,假如有数据先清除)
	tBody.empty();
	//3.迭代result这个json对象
	for(var i in result){//循环一次取一行
	//3.1构建tr对象
	var tr=$("<tr></tr>");
	//在tr对象上绑定一个数据
	tr.data("id",result[i].id);
	//3.2tr中追加td字符串对象
	var firstTd=
   '<td><input type="checkbox" name="checkedName" value="[id]"></td>';
	tr.append(firstTd.replace("[id]",result[i].id));
	tr.append('<td>'+result[i].code+'</td>');
	tr.append('<td>'+result[i].name+'</td>');
	tr.append('<td>'+result[i].beginDate+'</td>');
	tr.append('<td>'+result[i].endDate+'</td>');
	tr.append('<td>'+(result[i].valid?"启用":"禁用")+'</td>');
	tr.append('<td><input type="button" class="btn btn-default btn-update" value="修改"/></td>');
	//3.3将tr追到到tbody中
	tBody.append(tr);
	}
}







