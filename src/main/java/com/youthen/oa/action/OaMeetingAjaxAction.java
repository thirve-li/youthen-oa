// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.oa.action;

import java.util.List;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.youthen.framework.presentation.action.AbstractAjaxAction;
import com.youthen.master.util.CommonUtil;
import com.youthen.oa.service.OaMeetingService;
import com.youthen.oa.service.dto.OaMeetingDto;

/**
 * 。
 * 
 * @author Dbl
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
@SuppressWarnings("serial")
@Namespace("/oa-meeting")
@Controller
public class OaMeetingAjaxAction extends AbstractAjaxAction {

    OaMeetingDto dto;

    List<OaMeetingDto> meetingList;

    int updateid;

    @Autowired
    OaMeetingService meetingService;

    /**
     * @see com.youthen.framework.presentation.action.AbstractAjaxAction#doExecute(java.lang.Object)
     */

    @Override
    protected Object doExecute(final Object aArgs) throws Exception {
        return null;
    }

    public String getMsg() {
        String Msg = null;
        final CommonUtil cu = new CommonUtil();

        if (this.dto.getRoomId() != null && this.dto.getStartTime() != null && this.dto.getEndTime() != null) {
            System.out.println(this.updateid);
            if (this.updateid != 1) {
                return Msg;
            }

            if (!cu.before(cu.dateToStrLong(this.dto.getStartTime()), this.dto.getEndTime())) {
                Msg = "会议开始时间不能大于会议结束时间";
                return Msg;
            }

            this.meetingList = this.meetingService.getWeekMeetingList(this.dto);

            if (this.meetingList.size() > 0) {
                if (this.meetingList.size() == 1) {
                    if (this.meetingList.get(0).getId().equals(this.dto.getId())) {
                        return Msg;
                    }
                }

                Msg = "该会议室在这个时间段已经有人预定了";
            }
        }
        return Msg;
    }

    /**
     * getter for dto.
     * 
     * @return dto
     */
    public OaMeetingDto getDto() {
        return this.dto;
    }

    /**
     * setter for dto.
     * 
     * @param aDto dto
     */
    public void setDto(final OaMeetingDto aDto) {
        this.dto = aDto;
    }

    /**
     * getter for meetingList.
     * 
     * @return meetingList
     */
    public List<OaMeetingDto> getMeetingList() {
        return this.meetingList;
    }

    /**
     * setter for meetingList.
     * 
     * @param aMeetingList meetingList
     */
    public void setMeetingList(final List<OaMeetingDto> aMeetingList) {
        this.meetingList = aMeetingList;
    }

    /**
     * getter for updateid.
     * 
     * @return updateid
     */
    public int getUpdateid() {
        return this.updateid;
    }

    /**
     * setter for updateid.
     * 
     * @param aUpdateid updateid
     */
    public void setUpdateid(final int aUpdateid) {
        this.updateid = aUpdateid;
    }

}
