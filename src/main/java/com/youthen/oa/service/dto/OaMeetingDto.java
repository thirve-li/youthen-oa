// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.oa.service.dto;

import java.util.Date;
import com.youthen.framework.common.annotation.Dto;
import com.youthen.master.persistence.entity.Kbn;
import com.youthen.master.persistence.entity.LoginUser;
import com.youthen.master.service.dto.MasterEntryDto;
import com.youthen.oa.persistence.entity.OaRoom;

/**
 * 。
 * 
 * @author Dbl
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */

@Dto
public class OaMeetingDto extends MasterEntryDto {

    private static final long serialVersionUID = 4749886797725984236L;

    private Long id;

    private String userId;

    private LoginUser creater;

    private Long roomId;

    private OaRoom room;

    private String meetingName;

    private Date startTime;

    private Date endTime;

    private Long purposeId;

    private Kbn meetingType;

    private int projectorCount;

    private String meetingContent;

    private String attendUser;

    private Short type;

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
     * getter for userId.
     * 
     * @return userId
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * setter for userId.
     * 
     * @param aUserId userId
     */
    public void setUserId(final String aUserId) {
        this.userId = aUserId;
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
     * getter for roomId.
     * 
     * @return roomId
     */
    public Long getRoomId() {
        return this.roomId;
    }

    /**
     * setter for roomId.
     * 
     * @param aRoomId roomId
     */
    public void setRoomId(final Long aRoomId) {
        this.roomId = aRoomId;
    }

    /**
     * getter for meetingName.
     * 
     * @return meetingName
     */
    public String getMeetingName() {
        return this.meetingName;
    }

    /**
     * setter for meetingName.
     * 
     * @param aMeetingName meetingName
     */
    public void setMeetingName(final String aMeetingName) {
        this.meetingName = aMeetingName;
    }

    /**
     * getter for purposeId.
     * 
     * @return purposeId
     */
    public Long getPurposeId() {
        return this.purposeId;
    }

    /**
     * setter for purposeId.
     * 
     * @param aPurposeId purposeId
     */
    public void setPurposeId(final Long aPurposeId) {
        this.purposeId = aPurposeId;
    }

    /**
     * getter for projectorCount.
     * 
     * @return projectorCount
     */
    public int getProjectorCount() {
        return this.projectorCount;
    }

    /**
     * setter for projectorCount.
     * 
     * @param aProjectorCount projectorCount
     */
    public void setProjectorCount(final int aProjectorCount) {
        this.projectorCount = aProjectorCount;
    }

    /**
     * getter for meetingContent.
     * 
     * @return meetingContent
     */
    public String getMeetingContent() {
        return this.meetingContent;
    }

    /**
     * setter for meetingContent.
     * 
     * @param aMeetingContent meetingContent
     */
    public void setMeetingContent(final String aMeetingContent) {
        this.meetingContent = aMeetingContent;
    }

    /**
     * getter for room.
     * 
     * @return room
     */
    public OaRoom getRoom() {
        return this.room;
    }

    /**
     * setter for room.
     * 
     * @param aRoom room
     */
    public void setRoom(final OaRoom aRoom) {
        this.room = aRoom;
    }

    /**
     * getter for meetingType.
     * 
     * @return meetingType
     */
    public Kbn getMeetingType() {
        return this.meetingType;
    }

    /**
     * setter for meetingType.
     * 
     * @param aMeetingType meetingType
     */
    public void setMeetingType(final Kbn aMeetingType) {
        this.meetingType = aMeetingType;
    }

    /**
     * getter for startTime.
     * 
     * @return startTime
     */
    public Date getStartTime() {
        return this.startTime;
    }

    /**
     * setter for startTime.
     * 
     * @param aStartTime startTime
     */
    public void setStartTime(final Date aStartTime) {
        this.startTime = aStartTime;
    }

    /**
     * getter for endTime.
     * 
     * @return endTime
     */
    public Date getEndTime() {
        return this.endTime;
    }

    /**
     * setter for endTime.
     * 
     * @param aEndTime endTime
     */
    public void setEndTime(final Date aEndTime) {
        this.endTime = aEndTime;
    }

    /**
     * getter for attendUser.
     * 
     * @return attendUser
     */
    public String getAttendUser() {
        return this.attendUser;
    }

    /**
     * setter for attendUser.
     * 
     * @param aAttendUser attendUser
     */
    public void setAttendUser(final String aAttendUser) {
        this.attendUser = aAttendUser;
    }

    /**
     * getter for type.
     * 
     * @return type
     */
    public Short getType() {
        return this.type;
    }

    /**
     * setter for type.
     * 
     * @param aType type
     */
    public void setType(final Short aType) {
        this.type = aType;
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
