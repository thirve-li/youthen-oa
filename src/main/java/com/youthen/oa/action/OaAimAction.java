// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.oa.action;

import java.util.List;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.youthen.framework.common.exception.DuplicateKeyException;
import com.youthen.framework.common.exception.ObjectNotFoundException;
import com.youthen.framework.presentation.action.BaseAction;
import com.youthen.framework.util.CommonUtils;
import com.youthen.master.persistence.entity.LoginUser;
import com.youthen.master.service.DepartmentService;
import com.youthen.master.service.KbnService;
import com.youthen.master.service.LoginUserService;
import com.youthen.master.service.OsAudittrailService;
import com.youthen.master.service.dto.DepartmentDto;
import com.youthen.master.service.dto.KbnDto;
import com.youthen.oa.service.OaAimService;
import com.youthen.oa.service.dto.OaAimDto;

/**
 * 。
 * 
 * @author Administrator
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
@Namespace("/mst-aim")
@Results({

        @Result(name = "entityInit", location = "/WEB-INF/jsp/oa/admin/aim/entity.jsp"),
        @Result(name = "view", location = "/WEB-INF/jsp/oa/admin/aim/view.jsp"),
        @Result(name = "toList", location = "/mst-aim/list.action", type = "redirect"),
        @Result(name = "list", location = "/WEB-INF/jsp/oa/admin/aim/list.jsp")})
@Controller
// @ExecAuthority(functioncd = "MST-ADMIN-001")
public class OaAimAction extends BaseAction {

    private static final long serialVersionUID = -4372884128344672292L;

    OaAimDto dto = new OaAimDto();

    private List<OaAimDto> aimList;

    // 首页大栏位
    private List<KbnDto> bigColumList;

    // 部门集合
    private List<DepartmentDto> departmentList;

    List<LoginUser> userList;

    private String namespace;

    private int tabId = 5;

    @Autowired
    private KbnService kbnService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private LoginUserService loginUserService;

    @Autowired
    private OaAimService aimService;

    @Autowired
    private OsAudittrailService audittrailService;

    /**
     * 显示所有的目标卡管理
     * 。
     * 
     * @return
     */
    @Action("list")
    public String list() {
        try {

            this.userList =
                    this.departmentService
                            .getByHql("from LoginUser where status.code !='DELETED' and departmentId="
                                    + this.dto.getExecutorDeptId());
            this.namespace = "mst-aim";
            this.bigColumList = this.kbnService.getBigColum();
            this.departmentList = this.departmentService.getByCompanyId(super.getSessionUser().getCompanyId());
            this.aimList = this.aimService.getAimList(this.dto);

            final int listSize = this.aimService.getAimCount(this.dto);
            final int apages = CommonUtils.countPages(listSize, this.dto.getPageSize());
            this.dto.setPages(apages);
            this.dto.setListSize(listSize);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return "list";
    }

    /**
     * 跳到新增目标管理卡页面
     * 。
     * 
     * @return
     */
    @Action("entityInit")
    public String entityInit() {

        try {

            this.bigColumList = this.kbnService.getBigColum();

            if (this.dto != null && this.dto.getId() != null) {
                this.dto = this.aimService.getById(this.dto.getId());

                this.userList =
                        this.departmentService
                                .getByHql("from LoginUser where status.code !='DELETED' and departmentId="
                                        + this.dto.getExecutorDeptId());

            }
            this.departmentList = this.departmentService.getByCompanyId(super.getSessionUser().getCompanyId());
        } catch (final ObjectNotFoundException e) {
            e.printStackTrace();
        }

        return "entityInit";
    }

    @Action("view")
    public String view() {

        this.bigColumList = this.kbnService.getBigColum();
        if (this.dto != null && this.dto.getId() != null) {
            this.dto = this.aimService.getById(this.dto.getId());
        }
        return "view";
    }

    @Action("saveAim")
    public String saveAim() {

        try {
            if (this.dto != null && this.dto.getId() != null) {
                this.aimService.update(this.dto);
            } else {
                this.dto.setCreater(this.aimService.getUserById(this.getSessionUser().getUserId()));
                this.aimService.insert(this.dto);
            }
        } catch (final DuplicateKeyException e) {
            e.printStackTrace();
        }

        return "toList";
    }

    @Action("deleteAim")
    public String deleteAim() {
        this.dto.setStatus(0);
        this.aimService.update(this.dto);
        return "toList";

    }

    public String editAimInit() {
        this.dto = this.aimService.getById(this.dto.getId());
        return "editAimInit";
    }

    public String editAim() {
        return "editAim";
    }

    /**
     * getter for dto.
     * 
     * @return dto
     */
    public OaAimDto getDto() {
        return this.dto;
    }

    /**
     * setter for dto.
     * 
     * @param aDto dto
     */
    public void setDto(final OaAimDto aDto) {
        this.dto = aDto;
    }

    /**
     * getter for aimManageList.
     * 
     * @return aimManageList
     */
    public List<OaAimDto> getAimList() {
        return this.aimList;
    }

    /**
     * setter for aimManageList.
     * 
     * @param aAimmanageList aimManageList
     */
    public void setAimeList(final List<OaAimDto> aAimList) {
        this.aimList = aAimList;
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
     * getter for departmentList.
     * 
     * @return departmentList
     */
    public List<DepartmentDto> getDepartmentList() {
        return this.departmentList;
    }

    /**
     * setter for departmentList.
     * 
     * @param aDepartmentList departmentList
     */
    public void setDepartmentList(final List<DepartmentDto> aDepartmentList) {
        this.departmentList = aDepartmentList;
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

}
