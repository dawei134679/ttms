package cn.tedu.ttms.common.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.web.JsonResult;

/**借助此类实现控制层统一异常处理
 * 使用@ControllerAdvice注解标识要此类
 * 处理SpringMVC中控制层的异常
 * */
@ControllerAdvice
public class ControllerExceptionHandler {
	 /**通过@ExceptionHandler
	  * 注解声明要此方法能处理
	  * 的异常的具体类型*/
     @ExceptionHandler(ServiceException.class)
     @ResponseBody
	 public JsonResult 
      handleServiceException(
    		  ServiceException e){
    	 e.printStackTrace();
    	 //封装异常,并将异常中的信息返回到客户端
    	 return new JsonResult(e);
     }
}













