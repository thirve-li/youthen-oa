// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.oa.service.dto;

import java.awt.geom.Area;
import java.util.Date;
import com.youthen.framework.common.annotation.Dto;
import com.youthen.master.persistence.entity.Company;
import com.youthen.master.persistence.entity.Department;
import com.youthen.master.service.dto.MasterEntryDto;

/**
 * 。
 * 
 * @author Dbl
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
@Dto
public class OaRoomDto extends MasterEntryDto {

    private static final long serialVersionUID = 3406671050897684514L;

    private Long id;

    private Long departmentId;

    private Department department;

    private Long areaId;

    private Area area;

    private String roomName;

    private String roomCode;

    private String bakColumn1;

    private String bakColumn2;

    private String bakColumn3;

    private String bakColumn4;

    private String bakColumn5;

    private String bakColumn6;

    private String bakColumn7;

    private String bakColumn8;

    private String bakColumn9;

    private String bakColumn10;

    private String createrId;

    private Long companyId;

    private Company company;

    private Date createTime;

    private Long status;

    private Long bigColumnId;

    /**
     * getter for id.
     * 
     * @return id
     */
    @Override
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
     * getter for departmentId.
     * 
     * @return departmentId
     */
    public Long getDepartmentId() {
        return this.departmentId;
    }

    /**
     * setter for departmentId.
     * 
     * @param aDepartmentId departmentId
     */
    public void setDepartmentId(final Long aDepartmentId) {
        this.departmentId = aDepartmentId;
    }

    /**
     * getter for department.
     * 
     * @return department
     */
    public Department getDepartment() {
        return this.department;
    }

    /**
     * setter for department.
     * 
     * @param aDepartment department
     */
    public void setDepartment(final Department aDepartment) {
        this.department = aDepartment;
    }

    /**
     * getter for areaId.
     * 
     * @return areaId
     */
    public Long getAreaId() {
        return this.areaId;
    }

    /**
     * setter for areaId.
     * 
     * @param aAreaId areaId
     */
    public void setAreaId(final Long aAreaId) {
        this.areaId = aAreaId;
    }

    /**
     * getter for area.
     * 
     * @return area
     */
    public Area getArea() {
        return this.area;
    }

    /**
     * setter for area.
     * 
     * @param aArea area
     */
    public void setArea(final Area aArea) {
        this.area = aArea;
    }

    /**
     * getter for roomName.
     * 
     * @return roomName
     */
    public String getRoomName() {
        return this.roomName;
    }

    /**
     * setter for roomName.
     * 
     * @param aRoomName roomName
     */
    public void setRoomName(final String aRoomName) {
        this.roomName = aRoomName;
    }

    /**
     * getter for roomCode.
     * 
     * @return roomCode
     */
    public String getRoomCode() {
        return this.roomCode;
    }

    /**
     * setter for roomCode.
     * 
     * @param aRoomCode roomCode
     */
    public void setRoomCode(final String aRoomCode) {
        this.roomCode = aRoomCode;
    }

    /**
     * getter for bakColumn1.
     * 
     * @return bakColumn1
     */
    public String getBakColumn1() {
        return this.bakColumn1;
    }

    /**
     * setter for bakColumn1.
     * 
     * @param aBakColumn1 bakColumn1
     */
    public void setBakColumn1(final String aBakColumn1) {
        this.bakColumn1 = aBakColumn1;
    }

    /**
     * getter for bakColumn2.
     * 
     * @return bakColumn2
     */
    public String getBakColumn2() {
        return this.bakColumn2;
    }

    /**
     * setter for bakColumn2.
     * 
     * @param aBakColumn2 bakColumn2
     */
    public void setBakColumn2(final String aBakColumn2) {
        this.bakColumn2 = aBakColumn2;
    }

    /**
     * getter for bakColumn3.
     * 
     * @return bakColumn3
     */
    public String getBakColumn3() {
        return this.bakColumn3;
    }

    /**
     * setter for bakColumn3.
     * 
     * @param aBakColumn3 bakColumn3
     */
    public void setBakColumn3(final String aBakColumn3) {
        this.bakColumn3 = aBakColumn3;
    }

    /**
     * getter for bakColumn4.
     * 
     * @return bakColumn4
     */
    public String getBakColumn4() {
        return this.bakColumn4;
    }

    /**
     * setter for bakColumn4.
     * 
     * @param aBakColumn4 bakColumn4
     */
    public void setBakColumn4(final String aBakColumn4) {
        this.bakColumn4 = aBakColumn4;
    }

    /**
     * getter for bakColumn5.
     * 
     * @return bakColumn5
     */
    public String getBakColumn5() {
        return this.bakColumn5;
    }

    /**
     * setter for bakColumn5.
     * 
     * @param aBakColumn5 bakColumn5
     */
    public void setBakColumn5(final String aBakColumn5) {
        this.bakColumn5 = aBakColumn5;
    }

    /**
     * getter for bakColumn6.
     * 
     * @return bakColumn6
     */
    public String getBakColumn6() {
        return this.bakColumn6;
    }

    /**
     * setter for bakColumn6.
     * 
     * @param aBakColumn6 bakColumn6
     */
    public void setBakColumn6(final String aBakColumn6) {
        this.bakColumn6 = aBakColumn6;
    }

    /**
     * getter for bakColumn7.
     * 
     * @return bakColumn7
     */
    public String getBakColumn7() {
        return this.bakColumn7;
    }

    /**
     * setter for bakColumn7.
     * 
     * @param aBakColumn7 bakColumn7
     */
    public void setBakColumn7(final String aBakColumn7) {
        this.bakColumn7 = aBakColumn7;
    }

    /**
     * getter for bakColumn8.
     * 
     * @return bakColumn8
     */
    public String getBakColumn8() {
        return this.bakColumn8;
    }

    /**
     * setter for bakColumn8.
     * 
     * @param aBakColumn8 bakColumn8
     */
    public void setBakColumn8(final String aBakColumn8) {
        this.bakColumn8 = aBakColumn8;
    }

    /**
     * getter for bakColumn9.
     * 
     * @return bakColumn9
     */
    public String getBakColumn9() {
        return this.bakColumn9;
    }

    /**
     * setter for bakColumn9.
     * 
     * @param aBakColumn9 bakColumn9
     */
    public void setBakColumn9(final String aBakColumn9) {
        this.bakColumn9 = aBakColumn9;
    }

    /**
     * getter for bakColumn10.
     * 
     * @return bakColumn10
     */
    public String getBakColumn10() {
        return this.bakColumn10;
    }

    /**
     * setter for bakColumn10.
     * 
     * @param aBakColumn10 bakColumn10
     */
    public void setBakColumn10(final String aBakColumn10) {
        this.bakColumn10 = aBakColumn10;
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
     * getter for companyId.
     * 
     * @return companyId
     */
    public Long getCompanyId() {
        return this.companyId;
    }

    /**
     * setter for companyId.
     * 
     * @param aCompanyId companyId
     */
    public void setCompanyId(final Long aCompanyId) {
        this.companyId = aCompanyId;
    }

    /**
     * getter for company.
     * 
     * @return company
     */
    public Company getCompany() {
        return this.company;
    }

    /**
     * setter for company.
     * 
     * @param aCompany company
     */
    public void setCompany(final Company aCompany) {
        this.company = aCompany;
    }

    /**
     * getter for createTime.
     * 
     * @return createTime
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * setter for createTime.
     * 
     * @param aCreateTime createTime
     */
    public void setCreateTime(final Date aCreateTime) {
        this.createTime = aCreateTime;
    }

    /**
     * getter for status.
     * 
     * @return status
     */
    public Long getStatus() {
        return this.status;
    }

    /**
     * setter for status.
     * 
     * @param aStatus status
     */
    public void setStatus(final Long aStatus) {
        this.status = aStatus;
    }

    /**
     * getter for bigColumnId.
     * 
     * @return bigColumnId
     */
    public Long getBigColumnId() {
        return this.bigColumnId;
    }

    /**
     * setter for bigColumnId.
     * 
     * @param aBigColumnId bigColumnId
     */
    public void setBigColumnId(final Long aBigColumnId) {
        this.bigColumnId = aBigColumnId;
    }
}
