package com.aek.ebey.qc.request;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author aek
 * @since 2017-11-03
 */
public class PmImplementDetail{
	
	/**
	 * 设备编号
	 */
	@ApiModelProperty(value="设备编号")
	private String no;
	
	/**
	 * 设备名称
	 */
	@ApiModelProperty(value="设备名称")
	private String name;
	/**
	 * 设备型号
	 */
	@ApiModelProperty(value="设备型号")
	private String model;
	
	/**
	 * 设备所在科室名称
	 */
	@ApiModelProperty(value="设备所在科室名称")
	private String departmentName;
	
	/**
	 * PM周期
	 */
	@ApiModelProperty(value="PM周期")
	private Integer cycle;

	/**
	 * PM等级
	 */
	@ApiModelProperty(value="PM等级")
	private Integer level;
	
	/**
	 * 负责人姓名
	 */
	@ApiModelProperty(value="负责人姓名")
	private String directorName;
	
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value="创建时间")
	private Date createTime;
	
	/**
	 * 上次实施时间
	 */
	@ApiModelProperty(value="上次实施时间")
	private Date prevDate;
	
	/**
	 * 下次实施时间
	 */
	@ApiModelProperty(value="下次实施时间")
	private Date nextDate;
	
	/**
	 * 设备状态
	 */
	@ApiModelProperty(value="设备状态")
	private String equipmentStatus;
	
	/**
	 * 提示语
	 */
	@ApiModelProperty(value="提示语")
	private String attention;
	
	/**
	 * 实际开始巡检时间
	 */
	@ApiModelProperty(value="实际开始巡检时间")
	private Date actualStartDate;
	
	/**
	 * 实际结束巡检时间
	 */
	@ApiModelProperty(value="实际结束巡检时间")
	private Date actualEndDate;
	
	/**
	 * 报告编号
	 */
	@ApiModelProperty(value="报告编号")
	private String reportNo;
	
	/**
	 * items
	 */
	@ApiModelProperty(value="items")
	private List<PmAnswerRequest> items;
	
	/**
	 * 设备现状1：正常工作 2：小问题不影响使用 3：有故障需要维修 4：不能使用
	 */
	@ApiModelProperty(value="设备现状1：正常工作 2：小问题不影响使用 3：有故障需要维修 4：不能使用")
	private Integer live;
	/**
	 * 工时
	 */
	@ApiModelProperty(value="下工时")
	private BigDecimal workTime;
	
	/**
	 * 文件
	 */
	@ApiModelProperty(value="文件")
	private List<PmImplementFileRequest> files;
	
	/**
	 * 备注
	 */
	@ApiModelProperty(value="备注")
	private String remarks;
	
	
	/**
	 * 验收人名称
	 */
	@ApiModelProperty(value = "验收人名称")
	private String checkName;
	
	/**
	 * 验收人ID
	 */
	@ApiModelProperty(value = "验收人ID")
	private Long checkId;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Integer getCycle() {
		return cycle;
	}

	public void setCycle(Integer cycle) {
		this.cycle = cycle;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getDirectorName() {
		return directorName;
	}

	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getPrevDate() {
		return prevDate;
	}

	public void setPrevDate(Date prevDate) {
		this.prevDate = prevDate;
	}

	public Date getNextDate() {
		return nextDate;
	}

	public void setNextDate(Date nextDate) {
		this.nextDate = nextDate;
	}

	public String getEquipmentStatus() {
		return equipmentStatus;
	}

	public void setEquipmentStatus(String equipmentStatus) {
		this.equipmentStatus = equipmentStatus;
	}

	public String getAttention() {
		return attention;
	}

	public void setAttention(String attention) {
		this.attention = attention;
	}

	public Date getActualStartDate() {
		return actualStartDate;
	}

	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}

	public Date getActualEndDate() {
		return actualEndDate;
	}

	public void setActualEndDate(Date actualEndDate) {
		this.actualEndDate = actualEndDate;
	}

	public String getReportNo() {
		return reportNo;
	}

	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
	}

	public List<PmAnswerRequest> getItems() {
		return items;
	}

	public void setItems(List<PmAnswerRequest> items) {
		this.items = items;
	}

	public Integer getLive() {
		return live;
	}

	public void setLive(Integer live) {
		this.live = live;
	}

	public BigDecimal getWorkTime() {
		return workTime;
	}

	public void setWorkTime(BigDecimal workTime) {
		this.workTime = workTime;
	}

	public List<PmImplementFileRequest> getFiles() {
		return files;
	}

	public void setFiles(List<PmImplementFileRequest> files) {
		this.files = files;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCheckName() {
		return checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

	public Long getCheckId() {
		return checkId;
	}

	public void setCheckId(Long checkId) {
		this.checkId = checkId;
	}

}
