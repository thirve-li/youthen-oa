// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.oa.persistence.entity;

import com.youthen.framework.persistence.entity.AbstractCommonEntity;
import com.youthen.master.persistence.entity.LoginUser;

/**
 * 。
 * 
 * @author Dbl
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
public class OaPicture extends AbstractCommonEntity {

    private static final long serialVersionUID = -1393066067391786371L;

    private Long id;

    private String pictureName;

    private LoginUser creater;// 创建者

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
     * getter for pictureName.
     * 
     * @return pictureName
     */
    public String getPictureName() {
        return this.pictureName;
    }

    /**
     * setter for pictureName.
     * 
     * @param aPictureName pictureName
     */
    public void setPictureName(final String aPictureName) {
        this.pictureName = aPictureName;
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

}
