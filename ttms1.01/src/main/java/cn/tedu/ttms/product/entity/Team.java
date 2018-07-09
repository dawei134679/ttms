package cn.tedu.ttms.product.entity;
import java.io.Serializable;
import java.util.Date;
/**实体对象(entity):团目对象(有对应的表)
 * 值对象(VO):只是用于封装数据,没有对应的表
 * FAQ?
 * 1)此对象的set方法,哪里可能会用到?
 * a)从数据库查询数据,在封装数据时.
 * b)控制层从页面接收数据,实现参数数据注入时.
 * 2)此对象的get方法,哪里可能会用到?
 * 在控制层将对象转换为json字符串时,此时会调用
 * 对象的get方法获取值.
 * 3)本类需要提供无参的构造函数吗?(需要)
 * 可以去掉无参的构造函数吗?(不可以)
 * 底层:在构建类的时经常会
 * Class.forName("包名.类名").newInstance();
 */
public class Team implements Serializable{
	private static final long serialVersionUID = 368009064732092303L;
	private Integer id;
	private String name;
	/**项目ID*/
	private Integer projectId;
	private Integer valid;
	private String note;
	private Date createdTime;
	private Date modifiedTime;
	private String createdUser;
	private String modifiedUser;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public Integer getValid() {
		return valid;
	}
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public String getModifiedUser() {
		return modifiedUser;
	}
	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}
	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + ", projectId=" + projectId + ", valid=" + valid + ", note=" + note
				+ ", createdTime=" + createdTime + ", modifiedTime=" + modifiedTime + ", createdUser=" + createdUser
				+ ", modifiedUser=" + modifiedUser + "]";
	}
	
}
