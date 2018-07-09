package product;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import cn.tedu.ttms.product.service.ProductTypeService;

public class TestProductTypeService 
   extends TestBase {

	@Test
	public void testFindGridTreeNodes(){
		ProductTypeService typeService=
	    (ProductTypeService)
		ctx.getBean("productTypeServiceImpl");
		List<Map<String,Object>> list=
		typeService.findGridTreeNodes();
		System.out.println(list);
	}
}
