// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.oa.persistence.entity;

import java.util.Date;
import com.youthen.framework.persistence.entity.AbstractCommonEntity;
import com.youthen.master.persistence.entity.Kbn;
import com.youthen.master.persistence.entity.LoginUser;

/**
 * 。
 * 
 * @author Dbl
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
public class OaDataCenter extends AbstractCommonEntity {

    private static final long serialVersionUID = -1393066067391786371L;

    private Long id;

    private LoginUser creater;// 创建者

    private int status;// 状态

    private String filePath;// 文件路径

    private String fileName;// 文件名

    private Long bigColumnId;
    private Kbn bigColumn;

    private Long columnId;// 列id

    private Kbn smallColumn;

    private Date createTime;// 创建时间

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
     * getter for status.
     * 
     * @return status
     */
    public int getStatus() {
        return this.status;
    }

    /**
     * setter for status.
     * 
     * @param aStatus status
     */
    public void setStatus(final int aStatus) {
        this.status = aStatus;
    }

    /**
     * getter for filePath.
     * 
     * @return filePath
     */
    public String getFilePath() {
        return this.filePath;
    }

    /**
     * setter for filePath.
     * 
     * @param aFilePath filePath
     */
    public void setFilePath(final String aFilePath) {
        this.filePath = aFilePath;
    }

    /**
     * getter for fileName.
     * 
     * @return fileName
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * setter for fileName.
     * 
     * @param aFileName fileName
     */
    public void setFileName(final String aFileName) {
        this.fileName = aFileName;
    }

    /**
     * getter for columnId.
     * 
     * @return columnId
     */
    public Long getColumnId() {
        return this.columnId;
    }

    /**
     * setter for columnId.
     * 
     * @param aColumnId columnId
     */
    public void setColumnId(final Long aColumnId) {
        this.columnId = aColumnId;
    }

    /**
     * getter for smallColumn.
     * 
     * @return smallColumn
     */
    public Kbn getSmallColumn() {
        return this.smallColumn;
    }

    /**
     * setter for smallColumn.
     * 
     * @param aSmallColumn smallColumn
     */
    public void setSmallColumn(final Kbn aSmallColumn) {
        this.smallColumn = aSmallColumn;
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

    /**
     * getter for bigColumn.
     * 
     * @return bigColumn
     */
    public Kbn getBigColumn() {
        return this.bigColumn;
    }

    /**
     * setter for bigColumn.
     * 
     * @param aBigColumn bigColumn
     */
    public void setBigColumn(final Kbn aBigColumn) {
        this.bigColumn = aBigColumn;
    }

}
