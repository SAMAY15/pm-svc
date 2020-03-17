package com.cts.am.pmsvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "project_weekly_table" )
public class ProjectWeekly {
	
	@Id
    @Column(name="project_id")
    private Long projectId;
	@Column(name="pocID")
	private Long pocID;
	@Column(name="tableName")
	private String tableName;
	@Column(name="createdBy")
	private String createdBy;
	@Column(name="currentProjectStatus")
	private String currentProjectStatus;
	@Column(name="customerValueAdditions")
	private String customerValueAdditions;
	@Column(name="overallStatus")
	private String overallStatus;
	@Column(name="resourceFulfillment")
	private String resourceFulfillment;
	@Column(name="resourceCompetency")
	private String resourceCompetency;
	@Column(name="qualityOfDeliverables")
	private String qualityOfDeliverables;	
	@Column(name="customerFeedback")
	private String customerFeedback;
	@Column(name="financialPerformance")
	private String financialPerformance;
	@Column(name="ciiScore")
	private String ciiScore;
	@Column(name="ccaCompliance")
	private String ccaCompliance;
	@Column(name="updatedBy")
	private String updatedBy;
	@Column(name="status")
	private String status;
	
	
	public ProjectWeekly() {
		super();
	}


	public ProjectWeekly(Long projectId,String tableName, Long pocID,
			String currentProjectStatus, String customerValueAdditions, String overallStatus,
			String resourceFulfillment, String resourceCompetency, String qualityOfDeliverables,
			String customerFeedback, String financialPerformance, String ciiScore, String ccaCompliance,
			String updateBy, String status) {
		super();
		this.projectId = projectId;
		this.tableName = tableName;
		this.pocID = pocID;
		this.currentProjectStatus = currentProjectStatus;
		this.customerValueAdditions = customerValueAdditions;
		this.overallStatus = overallStatus;
		this.resourceFulfillment = resourceFulfillment;
		this.resourceCompetency = resourceCompetency;
		this.qualityOfDeliverables = qualityOfDeliverables;
		this.customerFeedback = customerFeedback;
		this.financialPerformance = financialPerformance;
		this.ciiScore = ciiScore;
		this.ccaCompliance = ccaCompliance;
		this.updatedBy = updateBy;
		this.status = status;
	}
	
	
	public Long getPocID() {
		return pocID;
	}
	public void setPocID(Long pocID) {
		this.pocID = pocID;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProject_id(Long projectId) {
		this.projectId = projectId;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getCurrentProjectStatus() {
		return currentProjectStatus;
	}
	public void setCurrentProjectStatus(String currentProjectStatus) {
		this.currentProjectStatus = currentProjectStatus;
	}
	public String getCustomerValue_Additions() {
		return customerValueAdditions;
	}
	public void setCustomerValue_Additions(String customerValueAdditions) {
		this.customerValueAdditions = customerValueAdditions;
	}
	public String getOverallStatus() {
		return overallStatus;
	}
	public void setOverallStatus(String overallStatus) {
		this.overallStatus = overallStatus;
	}
	public String getResourceFulfillment() {
		return resourceFulfillment;
	}
	public void setResourceFulfillment(String resourceFulfillment) {
		this.resourceFulfillment = resourceFulfillment;
	}
	public String getResourceCompetency() {
		return resourceCompetency;
	}
	public void setResourceCompetency(String resourceCompetency) {
		this.resourceCompetency = resourceCompetency;
	}
	public String getQualityOfDeliverables() {
		return qualityOfDeliverables;
	}
	public void setQualityOfDeliverables(String qualityOfDeliverables) {
		this.qualityOfDeliverables = qualityOfDeliverables;
	}
	public String getCustomerFeedback() {
		return customerFeedback;
	}
	public void setCustomerFeedback(String customerFeedback) {
		this.customerFeedback = customerFeedback;
	}
	public String getFinancialPerformance() {
		return financialPerformance;
	}
	public void setFinancialPerformance(String financialPerformance) {
		this.financialPerformance = financialPerformance;
	}
	public String getCiiScore() {
		return ciiScore;
	}
	public void setCiiScore(String ciiScore) {
		this.ciiScore = ciiScore;
	}
	public String getCcaCompliance() {
		return ccaCompliance;
	}
	public void setCcaCompliance(String ccaCompliance) {
		this.ccaCompliance = ccaCompliance;
	}
	public String getUpdateBy() {
		return updatedBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updatedBy = updateBy;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
