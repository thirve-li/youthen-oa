package com.youthen.oa.action;

import java.util.List;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.youthen.framework.common.PageBean;
import com.youthen.framework.common.StringUtils;
import com.youthen.framework.common.annotation.ExecAuthority;
import com.youthen.framework.common.exception.ObjectNotFoundException;
import com.youthen.framework.presentation.action.BaseAction;
import com.youthen.framework.util.BeanUtils;
import com.youthen.master.persistence.entity.Company;
import com.youthen.master.persistence.entity.LoginUser;
import com.youthen.master.persistence.entity.SubSystem;
import com.youthen.master.service.DepartmentService;
import com.youthen.master.service.KbnService;
import com.youthen.master.service.MasterDataMantanceService;
import com.youthen.master.service.dto.CompanyDto;
import com.youthen.master.service.dto.DepartmentDto;
import com.youthen.master.service.dto.KbnDto;
import com.youthen.oa.persistence.entity.OaRoom;
import com.youthen.oa.service.OaRoomService;
import com.youthen.oa.service.dto.OaRoomDto;

/**
 * @author Administrator
 */
@Namespace("/oa-room")
@Results({
        @Result(name = "list", location = "/WEB-INF/jsp/oa/admin/room/list.jsp"),
        @Result(name = "tolistRoom", location = "/oa-room/list.action", type = "redirect"),
        @Result(name = "view", location = "/WEB-INF/jsp/oa/admin/room/view.jsp"),
        @Result(name = "entityInit", location = "/WEB-INF/jsp/oa/admin/room/entity.jsp")})
@Controller
@ExecAuthority(functioncd = "1")
public class OaRoomAction extends BaseAction {

    OaRoomDto dto;
    List<OaRoomDto> mstRoomDtoList;

    List<CompanyDto> companyList;

    List<DepartmentDto> departmentDtoList;

    List<KbnDto> areaList;

    PageBean<OaRoom> pageBean;

    private final int tabId = 7;

    // 首页大栏位
    private List<KbnDto> bigColumList;

    // 小栏位
    private List<KbnDto> smallColumList;

    private List<SubSystem> systemList;

    private String namespace;

    List<LoginUser> userList;

    @Autowired
    OaRoomService mstRoomService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    MasterDataMantanceService masterDataMantanceService;

    @Autowired
    KbnService kbnService;

    /**
     * 显示所有的会议室
     * 。
     * 
     * @return
     */
    @Action("list")
    public String list() {
        this.namespace = "oa-room";
        this.bigColumList = this.kbnService.getBigColum();
        this.smallColumList = this.kbnService.getKbnListByType("OA_SMALL_MENU");
        if (this.dto == null) {
            this.dto = new OaRoomDto();
        }

        final OaRoom room = new OaRoom();
        BeanUtils.copyAllNullableProperties(this.dto, room);

        if (this.pageBean == null) {
            this.pageBean = new PageBean<OaRoom>(1, this.dto.getPageSize());
        }

        String condition = "";
        if (StringUtils.isNotEmpty(room.getRoomName())) {
            condition += " and tmpObj.roomName like'%" + room.getRoomName() + "%'";
        }
        if (this.dto.getCompanyId() != null) {
            condition += " and tmpObj.companyId='" + this.dto.getCompanyId() + "'";
        }

        if (this.dto.getDepartmentId() != null) {
            condition += " and tmpObj.departmentId='" + this.dto.getDepartmentId() + "'";
        }

        this.pageBean.setWhereHql(condition);
        this.pageBean.setSortColumnName("tmpObj.companyId,tmpObj.departmentId");

        this.masterDataMantanceService.setType(OaRoom.class);
        this.pageBean = this.masterDataMantanceService.getByPageBean(this.pageBean);

        return "list";
    }

    private void init() {

        // 初始化下拉框
        try {
            this.departmentDtoList = this.departmentService.getByCompanyId(getSessionUser().getCompanyId());
            this.areaList = this.kbnService.getKbnListByType("AREA");
        } catch (final ObjectNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * 跳到新增目标管理卡页面
     * 。
     * 
     * @return
     */
    @Action("add")
    public String add() {
        if (this.dto != null && this.dto.getId() != null) {
            this.mstRoomService.edit(this.dto);
        } else {
            this.mstRoomService.add(this.dto);
            final OaRoomDto aDto = new OaRoomDto();
            this.mstRoomDtoList = this.mstRoomService.list(aDto);
        }
        return "tolistRoom";
    }

    @Action("entityInit")
    public String entityInit() {
        try {
            this.bigColumList = this.kbnService.getBigColum();
            this.smallColumList = this.kbnService.getKbnListByType("OA_SMALL_MENU");
            if (this.dto != null && this.dto.getId() != null) {
                this.dto = this.mstRoomService.getById(this.dto.getId());
            }

            this.masterDataMantanceService.setType(Company.class);
            this.companyList = this.masterDataMantanceService.queryByCondition(" status=1 ");

            this.departmentDtoList = this.departmentService.getByCompanyId(getSessionUser().getCompanyId());

            init();// 初始化下拉框
        } catch (final ObjectNotFoundException e) {
            e.printStackTrace();
        }

        return "entityInit";
    }

    @Action("view")
    public String view() {
        this.bigColumList = this.kbnService.getBigColum();
        this.smallColumList = this.kbnService.getKbnListByType("OA_SMALL_MENU");
        final OaRoomDto aDto = this.mstRoomService.getById(this.dto.getId());
        BeanUtils.copyAllNullableProperties(aDto, this.dto);
        return "view";
    }

    @Action("edit")
    public String edit() {
        this.mstRoomService.edit(this.dto);
        final OaRoomDto aDto = new OaRoomDto();
        this.mstRoomDtoList = this.mstRoomService.list(aDto);
        return "tolistRoom";
    }

    @Action("del")
    public String del() {

        final OaRoomDto kbnDto = this.mstRoomService.getById(this.dto.getId());
        kbnDto.setStatus(0L);
        kbnDto.setReason(this.dto.getReason());
        kbnDto.setActionName("失效");
        this.mstRoomService.edit(kbnDto);
        final OaRoomDto aDto = new OaRoomDto();
        this.mstRoomDtoList = this.mstRoomService.list(aDto);
        return "tolistRoom";
    }

    /**
     * getter for dto.
     * 
     * @return dto
     */
    public OaRoomDto getDto() {
        return this.dto;
    }

    /**
     * setter for dto.
     * 
     * @param aDto dto
     */
    public void setDto(final OaRoomDto aDto) {
        this.dto = aDto;
    }

    /**
     * getter for mstRoomDtoList.
     * 
     * @return mstRoomDtoList
     */
    public List<OaRoomDto> getMstRoomDtoList() {
        return this.mstRoomDtoList;
    }

    /**
     * setter for mstRoomDtoList.
     * 
     * @param aMstRoomDtoList mstRoomDtoList
     */
    public void setMstRoomDtoList(final List<OaRoomDto> aMstRoomDtoList) {
        this.mstRoomDtoList = aMstRoomDtoList;
    }

    /**
     * getter for departmentDtoList.
     * 
     * @return departmentDtoList
     */
    public List<DepartmentDto> getDepartmentDtoList() {
        return this.departmentDtoList;
    }

    /**
     * setter for departmentDtoList.
     * 
     * @param aDepartmentDtoList departmentDtoList
     */
    public void setDepartmentDtoList(final List<DepartmentDto> aDepartmentDtoList) {
        this.departmentDtoList = aDepartmentDtoList;
    }

    /**
     * getter for areaList.
     * 
     * @return areaList
     */
    public List<KbnDto> getAreaList() {
        return this.areaList;
    }

    /**
     * setter for areaList.
     * 
     * @param aAreaList areaList
     */
    public void setAreaList(final List<KbnDto> aAreaList) {
        this.areaList = aAreaList;
    }

    /**
     * getter for pageBean.
     * 
     * @return pageBean
     */
    public PageBean<OaRoom> getPageBean() {
        return this.pageBean;
    }

    /**
     * setter for pageBean.
     * 
     * @param aPageBean pageBean
     */
    public void setPageBean(final PageBean<OaRoom> aPageBean) {
        this.pageBean = aPageBean;
    }

    /**
     * getter for companyList.
     * 
     * @return companyList
     */
    public List<CompanyDto> getCompanyList() {
        return this.companyList;
    }

    /**
     * setter for companyList.
     * 
     * @param aCompanyList companyList
     */
    public void setCompanyList(final List<CompanyDto> aCompanyList) {
        this.companyList = aCompanyList;
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
     * getter for smallColumList.
     * 
     * @return smallColumList
     */
    public List<KbnDto> getSmallColumList() {
        return this.smallColumList;
    }

    /**
     * setter for smallColumList.
     * 
     * @param aSmallColumList smallColumList
     */
    public void setSmallColumList(final List<KbnDto> aSmallColumList) {
        this.smallColumList = aSmallColumList;
    }

    /**
     * getter for systemList.
     * 
     * @return systemList
     */
    public List<SubSystem> getSystemList() {
        return this.systemList;
    }

    /**
     * setter for systemList.
     * 
     * @param aSystemList systemList
     */
    public void setSystemList(final List<SubSystem> aSystemList) {
        this.systemList = aSystemList;
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

}
