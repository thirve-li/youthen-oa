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
import com.opensymphony.xwork2.ActionContext;
import com.youthen.framework.presentation.action.BaseAction;
import com.youthen.master.persistence.entity.LoginUser;
import com.youthen.master.service.ArticleService;
import com.youthen.master.service.KbnService;
import com.youthen.master.service.LoginUserService;
import com.youthen.master.service.NoticeService;
import com.youthen.master.service.dto.ArticleDto;
import com.youthen.master.service.dto.KbnDto;
import com.youthen.master.service.dto.NoticeDto;
import com.youthen.oa.service.OaPictureService;
import com.youthen.oa.service.dto.OaPictureDto;

/**
 * 。
 * 
 * @author Administrator
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
@Namespace("/oa-front")
@Results({
        @Result(name = "index", location = "/WEB-INF/jsp/oa/index.jsp"),

})
@Controller
public class OaFrontAction extends BaseAction {

    private LoginUser loginUser;

    private List<OaPictureDto> pictureList;

    private String image;

    private int columnIds;

    private List<KbnDto> bigColumList;

    // 公司新闻
    private List<ArticleDto> comList;

    // 八面来风
    private List<ArticleDto> eightList;

    // 公告集合
    private List<NoticeDto> noticedtoList;

    @Autowired
    private OaPictureService pictureService;

    @Autowired
    private LoginUserService loginUserService;

    @Autowired
    private KbnService kbnService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private NoticeService noticeService;

    /*
     * 主页显示
     */
    @Action("index")
    public String index() {
        this.columnIds = 1;

        // 头像
        this.loginUser = this.loginUserService.getUserByUserId(this.getSessionUser().getUserId());
        this.image = this.loginUser.getImageName();
        ActionContext.getContext().getSession().put("image", this.image);

        // 首页焦点图
        this.pictureList = this.pictureService.getPictureList(new OaPictureDto());
        ActionContext.getContext().getSession().put("pictureList", this.pictureList);

        // 菜单栏
        this.bigColumList = this.kbnService.getBigColum();
        final ArticleDto adto = new ArticleDto();
        adto.setBigColumnId(144L);
        adto.setPageSize(10);
        adto.setStatus(1);
        this.comList = this.articleService.getArticleList(adto);

        // 八面来风
        adto.setPageSize(5);
        adto.setBigColumnId(145L);
        adto.setStatus(1);
        this.eightList = this.articleService.getArticleList(adto);

        // 公告栏
        final NoticeDto ndto = new NoticeDto();
        this.noticedtoList = this.noticeService.getNoticeList(ndto, false);
        return "index";

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
     * getter for pictureList.
     * 
     * @return pictureList
     */
    public List<OaPictureDto> getPictureList() {
        return this.pictureList;
    }

    /**
     * setter for pictureList.
     * 
     * @param aPictureList pictureList
     */
    public void setPictureList(final List<OaPictureDto> aPictureList) {
        this.pictureList = aPictureList;
    }

    /**
     * getter for image.
     * 
     * @return image
     */
    public String getImage() {
        return this.image;
    }

    /**
     * setter for image.
     * 
     * @param aImage image
     */
    public void setImage(final String aImage) {
        this.image = aImage;
    }

    /**
     * getter for columnIds.
     * 
     * @return columnIds
     */
    public int getColumnIds() {
        return this.columnIds;
    }

    /**
     * setter for columnIds.
     * 
     * @param aColumnIds columnIds
     */
    public void setColumnIds(final int aColumnIds) {
        this.columnIds = aColumnIds;
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
     * getter for comList.
     * 
     * @return comList
     */
    public List<ArticleDto> getComList() {
        return this.comList;
    }

    /**
     * setter for comList.
     * 
     * @param aComList comList
     */
    public void setComList(final List<ArticleDto> aComList) {
        this.comList = aComList;
    }

    /**
     * getter for eightList.
     * 
     * @return eightList
     */
    public List<ArticleDto> getEightList() {
        return this.eightList;
    }

    /**
     * setter for eightList.
     * 
     * @param aEightList eightList
     */
    public void setEightList(final List<ArticleDto> aEightList) {
        this.eightList = aEightList;
    }

    /**
     * getter for noticedtoList.
     * 
     * @return noticedtoList
     */
    public List<NoticeDto> getNoticedtoList() {
        return this.noticedtoList;
    }

    /**
     * setter for noticedtoList.
     * 
     * @param aNoticedtoList noticedtoList
     */
    public void setNoticedtoList(final List<NoticeDto> aNoticedtoList) {
        this.noticedtoList = aNoticedtoList;
    }

}
