package product;

import java.text.SimpleDateFormat;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.ttms.product.entity.Project;
import cn.tedu.ttms.product.service.ProjectService;

public class TestProjectService {
    ClassPathXmlApplicationContext ctx;
	@Before
	public void init(){
		ctx=new ClassPathXmlApplicationContext(
		"spring-pool.xml",
		"spring-mybatis.xml",
		"spring-mvc.xml");
	}
	@Test
	public void testSaveObject()throws Exception{
		ProjectService projectService=
		ctx.getBean("projectServiceImpl",
		ProjectService.class);
		Project entity=new Project();
		entity.setName("渤海游");
		entity.setCode("tt-20170907-CN-BJ");
		entity.setValid(1);
		entity.setNote("渤海....");
		SimpleDateFormat sdf=
		new SimpleDateFormat("yyyy/MM/dd");
		entity.setBeginDate(sdf.parse("2017/09/12"));
		entity.setEndDate(sdf.parse("2017/09/12"));
		projectService.saveObject(entity);
	}
	@Test
	public void testFindPageObjects(){
		//获得Service对象(此对象由spring创建)
		ProjectService projectService=
		ctx.getBean("projectServiceImpl",
				ProjectService.class);
		//访问业务层对象方法
		Map<String,Object> list=
		projectService
		.findPageObjects("环球",1,1);
		//测试list集合是否不等于null
		Assert.assertNotEquals(null, list);
		System.out.println(list);
	}
	
	@Test
	public void testValidById(){
		ProjectService projectService=
		ctx.getBean("projectServiceImpl",
					ProjectService.class);
		projectService.validById("1,3",1);
		
	}
	@Test
	public void testFindObjectById(){
		ProjectService projectService=
				ctx.getBean("projectServiceImpl",
							ProjectService.class);
		Project project=
		projectService.findObjectById(1);
		Assert.assertNotEquals(null, project);
	}
	@Test
	public void testUpdateObject(){
		ProjectService projectService=
		ctx.getBean("projectServiceImpl",
		ProjectService.class);
		Project pro=
		projectService.findObjectById(24);
		pro.setName("ooo2");
		projectService
		.updateObject(pro);
	}
	
	
	@After
	public void destory(){
		ctx.close();
	}
}
/***
 * 排错
 * 1)四个W
 * a)When (何时出的错)
 * b)What (什么错)
 * c)Where(哪里的错)
 * d)Why (为什么会出这个错)
 * 2)一个H (How,如何解决这个错)
 */

