/*
 * ............................................. 
 * 
 * 				    _ooOoo_ 
 * 		  	       o8888888o 
 * 	  	  	       88" . "88 
 *                 (| -_- |) 
 *                  O\ = /O 
 *              ____/`---*\____ 
 *               . * \\| |// `. 
 *             / \\||| : |||// \ 
 *           / _||||| -:- |||||- \ 
 *             | | \\\ - /// | | 
 *            | \_| **\---/** | | 
 *           \  .-\__ `-` ___/-. / 
 *            ___`. .* /--.--\ `. . __ 
 *        ."" *< `.___\_<|>_/___.* >*"". 
 *      | | : `- \`.;`\ _ /`;.`/ - ` : | | 
 *         \ \ `-. \_ __\ /__ _/ .-` / / 
 *======`-.____`-.___\_____/___.-`____.-*====== 
 * 
 * ............................................. 
 *              佛祖保佑 永无BUG 
 *
 * 佛曰: 
 * 写字楼里写字间，写字间里程序员； 
 * 程序人员写程序，又拿程序换酒钱。 
 * 酒醒只在网上坐，酒醉还来网下眠； 
 * 酒醉酒醒日复日，网上网下年复年。 
 * 但愿老死电脑间，不愿鞠躬老板前； 
 * 奔驰宝马贵者趣，公交自行程序员。 
 * 别人笑我忒疯癫，我笑自己命太贱； 
 * 不见满街漂亮妹，哪个归得程序员？
 *
 * 北纬30.√  154518484@qq.com
 */
package com.github.model;

import org.apache.commons.lang3.builder.*;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity(name = "t_permission")
@Table(appliesTo = "t_permission", comment = "权限表 - 资源 + 操作")
public class Permission {
	
	@Id
	@GeneratedValue
	private Integer id;

	@Column(columnDefinition = "int(11) COMMENT '父级菜单id'")
	private Integer parentId;

	@Column(columnDefinition = "varchar(64) COMMENT '权限名称'")
	private String name;

	@Column(columnDefinition = "varchar(64) COMMENT '权限代码'")
	private String code;

	@Column(columnDefinition = "varchar(64) COMMENT '菜单前面的图标css'")
	private String icon;

	@Column(columnDefinition = "varchar(64) COMMENT '资源链接地址 - 生成菜单时使用'")
	private String url;

	@Column(columnDefinition = "varchar(128) COMMENT '权限描述'")
	private String description;

	@Column(columnDefinition = "int(1) COMMENT '是否启用: 0禁用 1启用'")
	private Integer status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "datetime COMMENT '创建时间'")
	private Date createTime;




	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "parentId")
	private Set<Permission> permissionSet;

	public Permission(){
	}

	public Permission(
		Integer id
	){
		this.id = id;
	}

	public void setId(Integer value) {
		this.id = value;
	}
	public Integer getId() {
		return this.id;
	}
	public void setParentId(Integer value) {
		this.parentId = value;
	}
	public Integer getParentId() {
		return this.parentId;
	}
	public void setName(String value) {
		this.name = value;
	}
	public String getName() {
		return this.name;
	}
	public void setCode(String value) {
		this.code = value;
	}
	public String getCode() {
		return this.code;
	}
	public void setDescription(String value) {
		this.description = value;
	}
	public String getDescription() {
		return this.description;
	}
	public void setUrl(String value) {
		this.url = value;
	}
	public String getUrl() {
		return this.url;
	}
	public void setStatus(Integer value) {
		this.status = value;
	}
	public Integer getStatus() {
		return this.status;
	}
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Set<Permission> getPermissionSet() {
		return permissionSet;
	}

	public void setPermissionSet(Set<Permission> permissionSet) {
		this.permissionSet = permissionSet;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}

