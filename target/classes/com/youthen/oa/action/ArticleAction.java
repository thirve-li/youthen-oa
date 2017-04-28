// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.oa.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.youthen.framework.common.annotation.ExecAuthority;
import com.youthen.framework.common.exception.DuplicateKeyException;
import com.youthen.framework.presentation.action.BaseAction;
import com.youthen.framework.util.BeanUtils;
import com.youthen.framework.util.CommonUtils;
import com.youthen.master.service.ArticleService;
import com.youthen.master.service.KbnService;
import com.youthen.master.service.LoginUserService;
import com.youthen.master.service.MasterDataMantanceService;
import com.youthen.master.service.OsAudittrailService;
import com.youthen.master.service.dto.ArticleDto;
import com.youthen.master.service.dto.KbnDto;

@Namespace("/oa-article")
@Results({
        @Result(name = "main", location = "/WEB-INF/jsp/master/main.jsp"),
        @Result(name = "entityInit", location = "/WEB-INF/jsp/oa/admin/article/entity.jsp"),
        @Result(name = "view", location = "/WEB-INF/jsp/oa/admin/article/view.jsp"),
        @Result(name = "toList", location = "/oa-article/list.action", type = "redirect"),
        @Result(name = "list", location = "/WEB-INF/jsp/oa/admin/article/list.jsp"),
        @Result(name = "getTitle", location = "/WEB-INF/jsp/oa/admin/article/list.jsp")})
@Controller
@ExecAuthority(functioncd = "MST-ADMIN-001")
public class ArticleAction extends BaseAction {

    private static final long serialVersionUID = -4372884128344672292L;

    // 文章
    private ArticleDto dto = new ArticleDto();

    // 文章集合
    private List<ArticleDto> articleDtoList;

    // 首页大栏位
    private List<KbnDto> bigColumList;

    // 文章专用大栏位
    private List<KbnDto> bigArticleList;

    private List<KbnDto> smallColumList;

    // KBN
    private List<KbnDto> kbnList;

    private String namespace;

    private int tabId = 4;

    // 文章标题
    private String title;

    @Autowired
    private KbnService kbnService;

    @Autowired
    private LoginUserService loginUserService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private OsAudittrailService audittrailService;

    @Autowired
    MasterDataMantanceService masterDataMantanceService;

    /*
     * 文章信息区列表
     */
    @Action("list")
    public String list() {
        this.namespace = "oa-article";

        this.bigColumList = this.kbnService.getBigColum();

        this.bigArticleList = this.kbnService.getKbnListByType("OA_BIG_MENU");

        this.kbnList = this.kbnService.getKbnListByType("USER_STATUS");
        this.smallColumList = this.kbnService.getKbnListByType("OA_SMALL_MENU");
        if (this.dto.getBigColumnId() != null) {
            final KbnDto condition = new KbnDto();
            condition.setParentTypeId(this.dto.getBigColumnId());
            this.smallColumList = this.kbnService.getKbnListBy(condition);
        }

        this.articleDtoList = this.articleService.getArticleList(this.dto);

        final int listSize = this.articleService.getArticleCount(this.dto);
        final int pages = CommonUtils.countPages(listSize, this.dto.getPageSize());
        this.dto.setPages(pages);
        this.dto.setListSize(listSize);

        return "list";
    }

    @Action("getTitle")
    public String getTitle() {
        try {
            final HttpServletRequest request = ServletActionContext.getRequest();
            final HttpServletResponse response = ServletActionContext.getResponse();
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            this.namespace = "oa-article";

            this.bigColumList = this.kbnService.getBigColum();

            this.bigArticleList = this.kbnService.getKbnListByType("OA_BIG_MENU");

            this.kbnList = this.kbnService.getKbnListByType("USER_STATUS");
            this.smallColumList = this.kbnService.getKbnListByType("OA_SMALL_MENU");
            if (this.dto.getBigColumnId() != null) {
                final KbnDto condition = new KbnDto();
                condition.setParentTypeId(this.dto.getBigColumnId());
                this.smallColumList = this.kbnService.getKbnListBy(condition);
            }

            this.articleDtoList = this.articleService.getArticleTitle(this.title);
            this.dto.setTitle(this.title);
            final int listSize = this.articleService.getArticleCount(this.dto);
            final int pages = CommonUtils.countPages(listSize, this.dto.getPageSize());
            this.dto.setPages(pages);
            this.dto.setListSize(listSize);
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return "getTitle";
    }

    /*
     * 文章信息区新增界面
     */

    @Action("entityInit")
    public String entityInit() {

        this.bigColumList = this.kbnService.getBigColum();
        this.bigArticleList = this.kbnService.getKbnListByType("OA_BIG_MENU");
        this.bigColumList = this.kbnService.getBigColum();

        this.smallColumList = this.kbnService.getKbnListByType("OA_SMALL_MENU");
        try {
            if (this.dto != null && this.dto.getId() != null) {
                this.dto = this.articleService.getById(this.dto.getId());
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return "entityInit";
    }

    /*
     * 文章信息区新增
     */
    @Action("saveArticle")
    public String saveArticle() {
        try {
            if (this.dto != null && this.dto.getId() != null) {
                this.articleService.update(this.dto);
            } else {
                this.articleService.insert(this.dto);
            }

        } catch (final DuplicateKeyException e) {
            e.printStackTrace();
        }
        return "toList";
    }

    /**
     * 删除文章信息
     * 。
     * 
     * @return
     */
    @Action("deleteArticle")
    public String deleteArticle() {

        try {
            this.dto.setStatus(0);
            this.articleService.update(this.dto);

        } catch (final DuplicateKeyException e) {
            e.printStackTrace();

        }

        return "toList";
    }

    @Action("view")
    public String view() {

        this.bigArticleList = this.kbnService.getKbnListByType("OA_BIG_MENU");
        this.bigColumList = this.kbnService.getBigColum();
        this.smallColumList = this.kbnService.getKbnListByType("OA_SMALL_MENU");
        this.articleDtoList = this.articleService.getArticleList(this.dto);
        this.dto = this.articleService.getById(this.dto.getId());
        BeanUtils.setNAProperty(this.dto);
        final String sour = this.dto.getContent();
        this.dto.setContent(sour);
        return "view";
    }

    @Action("main")
    public String main() {

        this.namespace = "oa-article";
        this.bigColumList = this.kbnService.getBigColum();
        return "main";
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
     * getter for dto.
     * 
     * @return dto
     */
    public ArticleDto getDto() {
        return this.dto;
    }

    /**
     * setter for dto.
     * 
     * @param aDto dto
     */
    public void setDto(final ArticleDto aDto) {
        this.dto = aDto;
    }

    /**
     * getter for articleDtoList.
     * 
     * @return articleDtoList
     */
    public List<ArticleDto> getArticleDtoList() {
        return this.articleDtoList;
    }

    /**
     * setter for articleDtoList.
     * 
     * @param aArticleDtoList articleDtoList
     */
    public void setArticleDtoList(final List<ArticleDto> aArticleDtoList) {
        this.articleDtoList = aArticleDtoList;
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

    /**
     * getter for kbnList.
     * 
     * @return kbnList
     */
    public List<KbnDto> getKbnList() {
        return this.kbnList;
    }

    /**
     * setter for kbnList.
     * 
     * @param aKbnList kbnList
     */
    public void setKbnList(final List<KbnDto> aKbnList) {
        this.kbnList = aKbnList;
    }

    /**
     * getter for bigArticleList.
     * 
     * @return bigArticleList
     */
    public List<KbnDto> getBigArticleList() {
        return this.bigArticleList;
    }

    /**
     * setter for bigArticleList.
     * 
     * @param aBigArticleList bigArticleList
     */
    public void setBigArticleList(final List<KbnDto> aBigArticleList) {
        this.bigArticleList = aBigArticleList;
    }

    /**
     * setter for title.
     * 
     * @param aTitle title
     */
    public void setTitle(final String aTitle) {
        this.title = aTitle;
    }

}
