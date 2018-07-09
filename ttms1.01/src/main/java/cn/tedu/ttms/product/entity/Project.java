package cn.tedu.ttms.product.entity;
import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import cn.tedu.ttms.common.web.JsonDateTypeConvert;
/**
 * 项目模块的实体对象(
 * 类中属性一般建议和表中字段对应)
 * 1)查询时封装表中查询的数据
 * 例如DAO: List<Project> findPageObjects();
 * 2)插入或更新时封装页面参数
 * 例如DAO: InsertObject(Project entity);
 * 例如DAO: updateObject(Project entity);
 * */
public class Project implements Serializable{
	private static final long serialVersionUID = -8617376423425257328L;
    /**项目id*/
	private Integer id;//? int 
	/**项目编码*/
    private String code;
    /**项目名称*/
    private String name;
    /**项目开始日期*/
    @DateTimeFormat(pattern="yyyy/MM/dd")
    private Date beginDate;//java.util.Date
    /**项目结束日期*/
    @DateTimeFormat(pattern="yyyy/MM/dd")
    private Date endDate;
    /**项目状态*/
    private Integer valid=1;//1代表启用,0代表禁用
    /**项目备注*/
    private String note;
    /**项目的创建时间*/
    private Date createdTime;
    /**项目的修改时间*/
    private Date modifiedTime;//datetime
    /**项目的创建人*/
    private String createdUser;
    /**项目的修改人*/
    private String modifiedUser;//varchar
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@JsonSerialize(using=JsonDateTypeConvert.class)
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	@JsonSerialize(using=JsonDateTypeConvert.class)
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
		return "Project [id=" + id + ", code=" + code + ", name=" + name + ", beginDate=" + beginDate + ", endDate="
				+ endDate + ", valid=" + valid + ", note=" + note + ", createdTime=" + createdTime + ", modifiedTime="
				+ modifiedTime + ", createdUser=" + createdUser + ", modifiedUser=" + modifiedUser + "]";
	}
    
}
/**
 * 回顾知识点:(课后思考)
 * 1.何为对象序列化和反序列化?
 * 2.实体对象为什么要实现序列化接口?
 * 3.实例对象中为什么要添加序列化id?
 * 4.对象的序列化是安全吗?
 * 5...............
 */



