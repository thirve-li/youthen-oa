// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.oa.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Vector;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.youthen.framework.common.exception.DuplicateKeyException;
import com.youthen.framework.presentation.action.BaseAction;
import com.youthen.framework.util.CommonUtils;
import com.youthen.master.persistence.entity.LoginUser;
import com.youthen.master.persistence.entity.Role;
import com.youthen.master.service.KbnService;
import com.youthen.master.service.LoginUserService;
import com.youthen.master.service.OsAudittrailService;
import com.youthen.master.service.dto.KbnDto;
import com.youthen.master.util.CommonUtil;
import com.youthen.oa.service.OaRoomService;
import com.youthen.oa.service.OaMeetingService;
import com.youthen.oa.service.dto.OaRoomDto;
import com.youthen.oa.service.dto.OaMeetingDto;

/**
 * 。
 * 
 * @author Dbl
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */

@Namespace("/oa-meeting")
@Results({
        @Result(name = "list", location = "/WEB-INF/jsp/oa/admin/meeting/list.jsp"),
        @Result(name = "view", location = "/WEB-INF/jsp/oa/admin/meeting/view.jsp"),
        @Result(name = "tolist", location = "/oa-meeting/list.action", type = "redirect"),
        @Result(name = "entityInit", location = "/WEB-INF/jsp/oa/admin/meeting/entity.jsp"),
        @Result(name = "list1", location = "/WEB-INF/jsp/oa/admin/meeting/list1.jsp"),
        @Result(name = "getWeek", location = "/WEB-INF/jsp/oa/admin/personage/meet_list.jsp")

})
@Controller
public class OaMeetingAction extends BaseAction {

    private static final long serialVersionUID = -4372884128344672292L;

    private OaMeetingDto dto = new OaMeetingDto();

    private List<OaMeetingDto> meetingList;

    // 周一到周日会议的list
    private List<OaMeetingDto> monList;
    private List<OaMeetingDto> tuesList;
    private List<OaMeetingDto> wednesList;
    private List<OaMeetingDto> thursList;
    private List<OaMeetingDto> friList;
    private List<OaMeetingDto> saturList;
    private List<OaMeetingDto> sunList;

    // 周一到周日的日期
    private String monDay;
    private String tuesDay;
    private String wednesDay;
    private String thursDay;
    private String friDay;
    private String saturDay;
    private String sunDay;

    private String weekStart;
    private String weekEnd;

    private List<KbnDto> bigColumList;

    private List<OaRoomDto> roomList;

    private List<KbnDto> purList;

    List<LoginUser> userList;

    private String namespace;

    private int tabId = 8;

    private Vector days;

    private String[] dayHours;

    private String returnid;

    @Autowired
    private KbnService kbnService;

    @Autowired
    private OaRoomService roomService;

    @Autowired
    private LoginUserService loginUserService;

    @Autowired
    private OaMeetingService meetingService;

    @Autowired
    private OsAudittrailService audittrailService;

    @Action("list")
    public String list() {
        final LoginUser loginUser = this.loginUserService.getUserByUserId(this.getSessionUser().getUserId());

        boolean isAdmin = false;
        for (final Role role : loginUser.getRoles()) {
            if ("ADMIN".equals(role.getCode())) {
                isAdmin = true;
            }
        }
        this.namespace = "oa-meeting";
        this.bigColumList = this.kbnService.getBigColum();
        this.dto.setRoomId(this.dto.getRoomId());
        this.dto.setPurposeId(this.dto.getPurposeId());
        this.meetingList = this.meetingService.getMeetingList(this.dto);
        final int listSize = this.meetingService.getMeetingCount(this.dto);
        final int pages = CommonUtils.countPages(listSize, this.dto.getPageSize());
        this.dto.setPages(pages);
        this.dto.setListSize(listSize);

        final OaRoomDto roomDto = new OaRoomDto();
        this.roomList = this.roomService.list(roomDto);
        this.purList = this.kbnService.getKbnListByType("OA_BOOK_ROOM");

        return "list";
    }

    @Action("entityInit")
    public String entityInit() {
        this.returnid = getReturnid();
        this.bigColumList = this.kbnService.getBigColum();
        if (this.dto != null && this.dto.getId() != null) {
            this.dto = this.meetingService.getById(this.dto.getId());
        }
        final OaRoomDto roomDto = new OaRoomDto();
        this.roomList = this.roomService.list(roomDto);
        this.purList = this.kbnService.getKbnListByType("OA_BOOK_ROOM");

        return "entityInit";
    }

    @Action("saveMeeting")
    public String saveMeeting() {
        try {
            if (this.dto != null && this.dto.getId() != null) {

                this.meetingService.update(this.dto);

            } else {

                this.dto.setUserId(getSessionUser().getUserId());
                this.meetingService.insert(this.dto);
            }

        } catch (final DuplicateKeyException e) {
            e.printStackTrace();
        }
        return "tolist";
    }

    @Action("deleteMeeting")
    public String deleteMeeting() {

        this.meetingService.delete(this.dto);

        return list();
    }

    @Action("view")
    public String view() {
        this.returnid = getReturnid();
        this.bigColumList = this.kbnService.getBigColum();
        if (this.dto != null && this.dto.getId() != null) {
            this.dto = this.meetingService.getById(this.dto.getId());
        }
        return "view";
    }

    @Action("getWeek")
    public String getWeek() {

        this.bigColumList = this.kbnService.getBigColum();
        final CommonUtil cu = new CommonUtil();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        final SimpleDateFormat df = new SimpleDateFormat("dd", Locale.CHINA);
        final SimpleDateFormat sdfsdf = new SimpleDateFormat("M月d日", Locale.CHINA);
        final Calendar cd = Calendar.getInstance(Locale.CHINA);
        cd.setFirstDayOfWeek(Calendar.MONDAY);
        cd.setTimeInMillis(System.currentTimeMillis());

        // 周一会议
        cd.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        this.monDay = df.format(cd.getTime());
        this.weekStart = sdfsdf.format(cd.getTime());
        final String monStart = sdf.format(cd.getTime()) + " 00:00:00";
        final String monEnd = sdf.format(cd.getTime()) + " 23:59:59";
        this.dto.setStartTime(cu.strToDateLong(monStart));
        this.dto.setEndTime(cu.strToDateLong(monEnd));
        this.monList = this.meetingService.getWeekMeetingList(this.dto);

        // 周二会议
        cd.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
        this.tuesDay = df.format(cd.getTime());
        final String tuesStart = sdf.format(cd.getTime()) + " 00:00:00";
        final String tuesEnd = sdf.format(cd.getTime()) + " 23:59:59";
        this.dto.setStartTime(cu.strToDateLong(tuesStart));
        this.dto.setEndTime(cu.strToDateLong(tuesEnd));
        this.tuesList = this.meetingService.getWeekMeetingList(this.dto);

        // 周三会议
        cd.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
        this.wednesDay = df.format(cd.getTime());
        final String wednesStart = sdf.format(cd.getTime()) + " 00:00:00";
        final String wednesEnd = sdf.format(cd.getTime()) + " 23:59:59";
        this.dto.setStartTime(cu.strToDateLong(wednesStart));
        this.dto.setEndTime(cu.strToDateLong(wednesEnd));
        this.wednesList = this.meetingService.getWeekMeetingList(this.dto);

        // 周四会议
        cd.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
        this.thursDay = df.format(cd.getTime());
        final String thursStart = sdf.format(cd.getTime()) + " 00:00:00";
        final String thursEnd = sdf.format(cd.getTime()) + " 23:59:59";
        this.dto.setStartTime(cu.strToDateLong(thursStart));
        this.dto.setEndTime(cu.strToDateLong(thursEnd));
        this.thursList = this.meetingService.getWeekMeetingList(this.dto);

        // 周五会议
        cd.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        this.friDay = df.format(cd.getTime());
        final String friStart = sdf.format(cd.getTime()) + " 00:00:00";
        final String friEnd = sdf.format(cd.getTime()) + " 23:59:59";
        this.dto.setStartTime(cu.strToDateLong(friStart));
        this.dto.setEndTime(cu.strToDateLong(friEnd));
        this.friList = this.meetingService.getWeekMeetingList(this.dto);

        // 周六会议
        cd.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        this.saturDay = df.format(cd.getTime());
        final String saturStart = sdf.format(cd.getTime()) + " 00:00:00";
        final String saturEnd = sdf.format(cd.getTime()) + " 23:59:59";
        this.dto.setStartTime(cu.strToDateLong(saturStart));
        this.dto.setEndTime(cu.strToDateLong(saturEnd));
        this.saturList = this.meetingService.getWeekMeetingList(this.dto);

        // 周日会议
        cd.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        this.sunDay = df.format(cd.getTime());
        this.weekEnd = sdfsdf.format(cd.getTime());
        final String sunStart = sdf.format(cd.getTime()) + " 00:00:00";
        final String sunEnd = sdf.format(cd.getTime()) + " 23:59:59";
        this.dto.setStartTime(cu.strToDateLong(sunStart));
        this.dto.setEndTime(cu.strToDateLong(sunEnd));
        this.sunList = this.meetingService.getWeekMeetingList(this.dto);

        return "getWeek";
    }

    @Action("list1")
    public String list1() {
        final LoginUser loginUser = this.loginUserService.getUserByUserId(this.getSessionUser().getUserId());
        boolean isAdmin = false;
        for (final Role role : loginUser.getRoles()) {
            if ("ADMIN".equals(role.getCode())) {
                isAdmin = true;
            }
        }
        this.namespace = "oa-meeting";
        this.bigColumList = this.kbnService.getBigColum();
        final OaRoomDto roomDto = new OaRoomDto();
        this.roomList = this.roomService.list(roomDto);
        this.dayHours = new String[] {
                " 8:00:00", " 8:30:00", " 9:00:00", " 9:30:00", " 10:00:00", " 10:30:00",
                " 11:00:00", " 11:30:00", " 12:00:00", " 12:30:00", " 13:00:00", " 13:30:00",
                " 14:00:00", " 14:30:00", " 15:00:00", " 15:30:00", " 16:00:00", " 16:30:00",
                " 17:00:00", " 17:30:00", " 18:00:00", " 18:30:00", " 19:00:00", " 19:30:00",
                " 20:00:00"
        };
        if (this.dto.getStartTime() != null && this.dto.getEndTime() != null) {
            final CommonUtil cu = new CommonUtil();
            final String startTime = cu.dateToStr((this.dto.getStartTime()));
            final String endTime = cu.dateToStr(this.dto.getEndTime());
            this.days = cu.getDays(startTime, endTime);
            this.meetingList = this.meetingService.getMeetingList(this.dto);

        } else {
            final Calendar ca = Calendar.getInstance();
            final int year = ca.get(Calendar.YEAR);// 获取年份
            final int month = ca.get(Calendar.MONTH) + 1;// 获取月份
            final int day = ca.get(Calendar.DATE);// 获取日
            final String time = year + "-" + month + "-" + day;
            final CommonUtil cu = new CommonUtil();
            this.days = cu.getDays(time, time);
            this.dto.setStartTime(cu.strToDate(time));
            this.dto.setEndTime(cu.strToDate(time));
            this.meetingList = this.meetingService.getMeetingList(this.dto);
        }

        return "list1";
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
     * getter for bigColumList.
     * 
     * @return bigColumList
     */
    public List<KbnDto> getBigColumList() {
        return this.bigColumList;
    }

    /**
     * setter for bigColumList.
     * 
     * @param aBigColumList bigColumList
     */
    public void setBigColumList(final List<KbnDto> aBigColumList) {
        this.bigColumList = aBigColumList;
    }

    /**
     * getter for namespace.
     * 
     * @return namespace
     */
    public String getNamespace() {
        return this.namespace;
    }

    /**
     * setter for namespace.
     * 
     * @param aNamespace namespace
     */
    public void setNamespace(final String aNamespace) {
        this.namespace = aNamespace;
    }

    /**
     * getter for userList.
     * 
     * @return userList
     */
    public List<LoginUser> getUserList() {
        return this.userList;
    }

    /**
     * setter for userList.
     * 
     * @param aUserList userList
     */
    public void setUserList(final List<LoginUser> aUserList) {
        this.userList = aUserList;
    }

    /**
     * getter for tabId.
     * 
     * @return tabId
     */
    public int getTabId() {
        return this.tabId;
    }

    /**
     * setter for tabId.
     * 
     * @param aTabId tabId
     */
    public void setTabId(final int aTabId) {
        this.tabId = aTabId;
    }

    /**
     * getter for roomList.
     * 
     * @return roomList
     */
    public List<OaRoomDto> getRoomList() {
        return this.roomList;
    }

    /**
     * setter for roomList.
     * 
     * @param aRoomList roomList
     */
    public void setRoomList(final List<OaRoomDto> aRoomList) {
        this.roomList = aRoomList;
    }

    /**
     * getter for purList.
     * 
     * @return purList
     */
    public List<KbnDto> getPurList() {
        return this.purList;
    }

    /**
     * setter for purList.
     * 
     * @param aPurList purList
     */
    public void setPurList(final List<KbnDto> aPurList) {
        this.purList = aPurList;
    }

    /**
     * getter for days.
     * 
     * @return days
     */
    public Vector getDays() {
        return this.days;
    }

    /**
     * setter for days.
     * 
     * @param aDays days
     */
    public void setDays(final Vector aDays) {
        this.days = aDays;
    }

    /**
     * getter for dayHours.
     * 
     * @return dayHours
     */
    public String[] getDayHours() {
        return this.dayHours;
    }

    /**
     * setter for dayHours.
     * 
     * @param aDayHours dayHours
     */
    public void setDayHours(final String[] aDayHours) {
        this.dayHours = aDayHours;
    }

    /**
     * getter for monList.
     * 
     * @return monList
     */
    public List<OaMeetingDto> getMonList() {
        return this.monList;
    }

    /**
     * setter for monList.
     * 
     * @param aMonList monList
     */
    public void setMonList(final List<OaMeetingDto> aMonList) {
        this.monList = aMonList;
    }

    /**
     * getter for tuesList.
     * 
     * @return tuesList
     */
    public List<OaMeetingDto> getTuesList() {
        return this.tuesList;
    }

    /**
     * setter for tuesList.
     * 
     * @param aTuesList tuesList
     */
    public void setTuesList(final List<OaMeetingDto> aTuesList) {
        this.tuesList = aTuesList;
    }

    /**
     * getter for wednesList.
     * 
     * @return wednesList
     */
    public List<OaMeetingDto> getWednesList() {
        return this.wednesList;
    }

    /**
     * setter for wednesList.
     * 
     * @param aWednesList wednesList
     */
    public void setWednesList(final List<OaMeetingDto> aWednesList) {
        this.wednesList = aWednesList;
    }

    /**
     * getter for thursList.
     * 
     * @return thursList
     */
    public List<OaMeetingDto> getThursList() {
        return this.thursList;
    }

    /**
     * setter for thursList.
     * 
     * @param aThursList thursList
     */
    public void setThursList(final List<OaMeetingDto> aThursList) {
        this.thursList = aThursList;
    }

    /**
     * getter for friList.
     * 
     * @return friList
     */
    public List<OaMeetingDto> getFriList() {
        return this.friList;
    }

    /**
     * setter for friList.
     * 
     * @param aFriList friList
     */
    public void setFriList(final List<OaMeetingDto> aFriList) {
        this.friList = aFriList;
    }

    /**
     * getter for saturList.
     * 
     * @return saturList
     */
    public List<OaMeetingDto> getSaturList() {
        return this.saturList;
    }

    /**
     * setter for saturList.
     * 
     * @param aSaturList saturList
     */
    public void setSaturList(final List<OaMeetingDto> aSaturList) {
        this.saturList = aSaturList;
    }

    /**
     * getter for sunList.
     * 
     * @return sunList
     */
    public List<OaMeetingDto> getSunList() {
        return this.sunList;
    }

    /**
     * setter for sunList.
     * 
     * @param aSunList sunList
     */
    public void setSunList(final List<OaMeetingDto> aSunList) {
        this.sunList = aSunList;
    }

    /**
     * getter for monDay.
     * 
     * @return monDay
     */
    public String getMonDay() {
        return this.monDay;
    }

    /**
     * setter for monDay.
     * 
     * @param aMonDay monDay
     */
    public void setMonDay(final String aMonDay) {
        this.monDay = aMonDay;
    }

    /**
     * getter for tuesDay.
     * 
     * @return tuesDay
     */
    public String getTuesDay() {
        return this.tuesDay;
    }

    /**
     * setter for tuesDay.
     * 
     * @param aTuesDay tuesDay
     */
    public void setTuesDay(final String aTuesDay) {
        this.tuesDay = aTuesDay;
    }

    /**
     * getter for wednesDay.
     * 
     * @return wednesDay
     */
    public String getWednesDay() {
        return this.wednesDay;
    }

    /**
     * setter for wednesDay.
     * 
     * @param aWednesDay wednesDay
     */
    public void setWednesDay(final String aWednesDay) {
        this.wednesDay = aWednesDay;
    }

    /**
     * getter for thursDay.
     * 
     * @return thursDay
     */
    public String getThursDay() {
        return this.thursDay;
    }

    /**
     * setter for thursDay.
     * 
     * @param aThursDay thursDay
     */
    public void setThursDay(final String aThursDay) {
        this.thursDay = aThursDay;
    }

    /**
     * getter for friDay.
     * 
     * @return friDay
     */
    public String getFriDay() {
        return this.friDay;
    }

    /**
     * setter for friDay.
     * 
     * @param aFriDay friDay
     */
    public void setFriDay(final String aFriDay) {
        this.friDay = aFriDay;
    }

    /**
     * getter for saturDay.
     * 
     * @return saturDay
     */
    public String getSaturDay() {
        return this.saturDay;
    }

    /**
     * setter for saturDay.
     * 
     * @param aSaturDay saturDay
     */
    public void setSaturDay(final String aSaturDay) {
        this.saturDay = aSaturDay;
    }

    /**
     * getter for sunDay.
     * 
     * @return sunDay
     */
    public String getSunDay() {
        return this.sunDay;
    }

    /**
     * setter for sunDay.
     * 
     * @param aSunDay sunDay
     */
    public void setSunDay(final String aSunDay) {
        this.sunDay = aSunDay;
    }

    /**
     * getter for weekStart.
     * 
     * @return weekStart
     */
    public String getWeekStart() {
        return this.weekStart;
    }

    /**
     * setter for weekStart.
     * 
     * @param aWeekStart weekStart
     */
    public void setWeekStart(final String aWeekStart) {
        this.weekStart = aWeekStart;
    }

    /**
     * getter for weekEnd.
     * 
     * @return weekEnd
     */
    public String getWeekEnd() {
        return this.weekEnd;
    }

    /**
     * setter for weekEnd.
     * 
     * @param aWeekEnd weekEnd
     */
    public void setWeekEnd(final String aWeekEnd) {
        this.weekEnd = aWeekEnd;
    }

    /**
     * getter for returnid.
     * 
     * @return returnid
     */
    public String getReturnid() {
        return this.returnid;
    }

    /**
     * setter for returnid.
     * 
     * @param aReturnid returnid
     */
    public void setReturnid(final String aReturnid) {
        this.returnid = aReturnid;
    }

}
