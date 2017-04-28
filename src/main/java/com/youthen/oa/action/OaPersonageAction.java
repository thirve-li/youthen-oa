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
import com.youthen.framework.presentation.action.BaseAction;
import com.youthen.master.persistence.entity.LoginUser;
import com.youthen.master.service.KbnService;
import com.youthen.master.service.LoginUserService;
import com.youthen.master.service.dto.KbnDto;

/**
 * 。
 * 
 * @author Administrator
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */

@Namespace("/oa-personage")
@Results({

        @Result(name = "target_manage", location = "/WEB-INF/jsp/oa/admin/personage/target_manage.jsp"),
        @Result(name = "comment", location = "/WEB-INF/jsp/oa/admin/personage/comment.jsp"),
        @Result(name = "innerletter", location = "/WEB-INF/jsp/oa/admin/personage/innerletter.jsp"),
        @Result(name = "meet_list", location = "/WEB-INF/jsp/oa/admin/personage/meet_list.jsp"),
        @Result(name = "memberIndex", location = "/WEB-INF/jsp/oa/admin/personage/memberIndex.jsp"),
        @Result(name = "activity", location = "/WEB-INF/jsp/oa/admin/personage/activity.jsp"),
        @Result(name = "showcase_index", location = "/WEB-INF/jsp/oa/admin/personage/showcase_index.jsp")
})
@Controller
public class OaPersonageAction extends BaseAction {

    private LoginUser loginUser;

    @Autowired
    private LoginUserService loginUserService;

    // 首页大栏位
    private List<KbnDto> bigColumList;

    private final int tabId = 11;

    @Autowired
    private KbnService kbnService;

    @Action("target_manage")
    public String target_manage() {
        this.bigColumList = this.kbnService.getBigColum();
        return "target_manage";
    }

    @Action("comment")
    public String comment() {
        this.bigColumList = this.kbnService.getBigColum();
        return "comment";
    }

    @Action("innerletter")
    public String innerletter() {
        this.bigColumList = this.kbnService.getBigColum();
        return "innerletter";
    }

    @Action("memberIndex")
    public String memberIndex() {
        this.loginUser = this.loginUserService.getUserByUserId(this.getSessionUser().getUserId());
        this.bigColumList = this.kbnService.getBigColum();
        return "memberIndex";
    }

    @Action("showcase_index")
    public String showcase_index() {
        this.bigColumList = this.kbnService.getBigColum();
        return "showcase_index";
    }

    @Action("meet_list")
    public String meet_list() {
        this.bigColumList = this.kbnService.getBigColum();
        return "meet_list";
    }

    @Action("activity")
    public String activity() {
        this.bigColumList = this.kbnService.getBigColum();
        return "activity";
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
     * getter for loginUser.
     * 
     * @return loginUser
     */
    public LoginUser getLoginUser() {
        return this.loginUser;
    }

    /**
     * setter for loginUser.
     * 
     * @param aLoginUser loginUser
     */
    public void setLoginUser(final LoginUser aLoginUser) {
        this.loginUser = aLoginUser;
    }

    /**
     * getter for tabId.
     * 
     * @return tabId
     */
    public int getTabId() {
        return this.tabId;
    }

}
