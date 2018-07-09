package product;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import cn.tedu.ttms.product.entity.Team;
import cn.tedu.ttms.product.service.TeamService;

public class TestTeamService 
   extends TestBase{
	@Test
	public void testFindPageObjects(){
		TeamService teamService=(TeamService)
		ctx.getBean("teamServiceImpl",
				TeamService.class);
		
		Map<String,Object> map=
		teamService.findPageObjects(
				"环球",
				1);
		Assert.assertNotEquals(null, map);
        System.out.println(map);		
	}
	@Test
	public void testSaveObject(){
		TeamService teamService=(TeamService)
				ctx.getBean("teamServiceImpl",
						TeamService.class);
		Team t=new Team();
		t.setName("月球游3日团");
		t.setProjectId(3);
		t.setValid(1);
		t.setNote("月球游3日团......");
		teamService.saveObject(t);
	}
	

}
