/*设置分页信息
 * Pagination(表示分页)
 */
$(document).ready(function(){
  $("#pageId")
  .on("click",".pre,.next,.first,.last",jumpToPage)
});
function setPagination(pageObject){
	//1.初始化页面总页数
	//1.1首先获得pageCount类选择器代表的对象
	//1.2在对象内部写入总页数
	$(".pageCount")
    .html("总页数("+pageObject.pageCount+")");
	//2.初始化页面当前页
	$(".pageCurrent")
	.html("当前页("+pageObject.pageCurrent+")");
	//2.数据绑定(当前页,总页数),就是将一个数据
	//绑定到对应的对象上
	$("#pageId")
	.data("pageCurrent",pageObject.pageCurrent);
	$("#pageId")
	.data("pageCount",pageObject.pageCount);
}
function jumpToPage(){
	//1.需要获得当前页
	var pageCurrent=$("#pageId").data("pageCurrent");
	//2.需要获得总页数
	var pageCount=$("#pageId").data("pageCount");
	//3.获得点击的对象上的类选择器
	var clazz=$(this).attr("class")
	//4.执行分页查询
	//4.1点击next要跳转到下一页
	if(clazz=="next"&&pageCurrent<pageCount){
		pageCurrent++;
	}
	//4.2点击pre要跳转到上一页
	if(clazz=="pre"&&pageCurrent>1){
		pageCurrent--;
	}
	//4.3点击首页first要跳转到首页
	if(clazz=="first"){
		pageCurrent=1;
	}
	//4.4点击尾页last要跳转到尾页
	if(clazz=="last"){
		pageCurrent=pageCount;
	}
	//重新绑定pageCurrent的值(当前页的值)
	$("#pageId").data("pageCurrent",pageCurrent);
	//重新执行查询
	doGetObjects();
}

















