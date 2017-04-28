// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.oa.persistence.entity;

import com.youthen.framework.persistence.entity.AbstractCommonEntity;
import com.youthen.master.persistence.entity.Department;
import com.youthen.master.persistence.entity.LoginUser;

/**
 * 。
 * 
 * @author Administrator
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
public class OaAim extends AbstractCommonEntity {

    /**
     * 。
     */
    private static final long serialVersionUID = -1393066067391786371L;
    private Long id;
    private String executorId;// 执行人ID
    private Long executorDeptId;// 执行部门ID
    private Department executorDept;// 执行部门
    private String taskName;// 目标卡管理名称
    private String taskDesc;// 任务描述
    private String taskDesc1;// 任务定量
    private String taskAim;// 任务目标
    private Long fee;// 费用
    private String assist1;// 协助
    private String assist2;// 协调
    private String supervision;// 督办
    private String createrId;// 签发人
    private String createTime;// 下达日期
    private String finishDate;// 任务完成日期
    private Integer status;// 状态
    private LoginUser creater;

    /**
     * getter for id.
     * 
     * @return id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * setter for id.
     * 
     * @param aId id
     */
    public void setId(final Long aId) {
        this.id = aId;
    }

    /**
     * getter for executorId.
     * 
     * @return executorId
     */
    public String getExecutorId() {
        return this.executorId;
    }

    /**
     * setter for executorId.
     * 
     * @param aExecutorId executorId
     */
    public void setExecutorId(final String aExecutorId) {
        this.executorId = aExecutorId;
    }

    /**
     * getter for executorDeptId.
     * 
     * @return executorDeptId
     */
    public Long getExecutorDeptId() {
        return this.executorDeptId;
    }

    /**
     * setter for executorDeptId.
     * 
     * @param aExecutorDeptId executorDeptId
     */
    public void setExecutorDeptId(final Long aExecutorDeptId) {
        this.executorDeptId = aExecutorDeptId;
    }

    /**
     * getter for taskName.
     * 
     * @return taskName
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * setter for taskName.
     * 
     * @param aTaskName taskName
     */
    public void setTaskName(final String aTaskName) {
        this.taskName = aTaskName;
    }

    /**
     * getter for taskDesc.
     * 
     * @return taskDesc
     */
    public String getTaskDesc() {
        return this.taskDesc;
    }

    /**
     * setter for taskDesc.
     * 
     * @param aTaskDesc taskDesc
     */
    public void setTaskDesc(final String aTaskDesc) {
        this.taskDesc = aTaskDesc;
    }

    /**
     * getter for taskAim.
     * 
     * @return taskAim
     */
    public String getTaskAim() {
        return this.taskAim;
    }

    /**
     * setter for taskAim.
     * 
     * @param aTaskAim taskAim
     */
    public void setTaskAim(final String aTaskAim) {
        this.taskAim = aTaskAim;
    }

    /**
     * getter for finishDate.
     * 
     * @return finishDate
     */
    public String getFinishDate() {
        return this.finishDate;
    }

    /**
     * setter for finishDate.
     * 
     * @param aFinishDate finishDate
     */
    public void setFinishDate(final String aFinishDate) {
        this.finishDate = aFinishDate;
    }

    /**
     * getter for createrId.
     * 
     * @return createrId
     */
    public String getCreaterId() {
        return this.createrId;
    }

    /**
     * setter for createrId.
     * 
     * @param aCreaterId createrId
     */
    public void setCreaterId(final String aCreaterId) {
        this.createrId = aCreaterId;
    }

    /**
     * getter for createTime.
     * 
     * @return createTime
     */
    public String getCreateTime() {
        return this.createTime;
    }

    /**
     * setter for createTime.
     * 
     * @param aCreateTime createTime
     */
    public void setCreateTime(final String aCreateTime) {
        this.createTime = aCreateTime;
    }

    /**
     * getter for status.
     * 
     * @return status
     */
    public Integer getStatus() {
        return this.status;
    }

    /**
     * setter for status.
     * 
     * @param aStatus status
     */
    public void setStatus(final Integer aStatus) {
        this.status = aStatus;
    }

    /**
     * getter for creater.
     * 
     * @return creater
     */
    public LoginUser getCreater() {
        return this.creater;
    }

    /**
     * setter for creater.
     * 
     * @param aCreater creater
     */
    public void setCreater(final LoginUser aCreater) {
        this.creater = aCreater;
    }

    /**
     * getter for executorDept.
     * 
     * @return executorDept
     */
    public Department getExecutorDept() {
        return this.executorDept;
    }

    /**
     * setter for executorDept.
     * 
     * @param aExecutorDept executorDept
     */
    public void setExecutorDept(final Department aExecutorDept) {
        this.executorDept = aExecutorDept;
    }

    /**
     * getter for taskDesc1.
     * 
     * @return taskDesc1
     */
    public String getTaskDesc1() {
        return this.taskDesc1;
    }

    /**
     * setter for taskDesc1.
     * 
     * @param aTaskDesc1 taskDesc1
     */
    public void setTaskDesc1(final String aTaskDesc1) {
        this.taskDesc1 = aTaskDesc1;
    }

    /**
     * getter for assist1.
     * 
     * @return assist1
     */
    public String getAssist1() {
        return this.assist1;
    }

    /**
     * setter for assist1.
     * 
     * @param aAssist1 assist1
     */
    public void setAssist1(final String aAssist1) {
        this.assist1 = aAssist1;
    }

    /**
     * getter for assist2.
     * 
     * @return assist2
     */
    public String getAssist2() {
        return this.assist2;
    }

    /**
     * setter for assist2.
     * 
     * @param aAssist2 assist2
     */
    public void setAssist2(final String aAssist2) {
        this.assist2 = aAssist2;
    }

    /**
     * getter for supervision.
     * 
     * @return supervision
     */
    public String getSupervision() {
        return this.supervision;
    }

    /**
     * setter for supervision.
     * 
     * @param aSupervision supervision
     */
    public void setSupervision(final String aSupervision) {
        this.supervision = aSupervision;
    }

    /**
     * getter for fee.
     * 
     * @return fee
     */
    public Long getFee() {
        return this.fee;
    }

    /**
     * setter for fee.
     * 
     * @param aFee fee
     */
    public void setFee(final Long aFee) {
        this.fee = aFee;
    }

}
