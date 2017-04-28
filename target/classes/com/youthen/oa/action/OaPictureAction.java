// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.oa.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.ActionContext;
import com.youthen.framework.presentation.action.BaseAction;
import com.youthen.master.service.KbnService;
import com.youthen.master.service.LoginUserService;
import com.youthen.master.service.dto.KbnDto;
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

@Namespace("/oa-picture")
@Results({
        @Result(name = "toList", location = "/oa-picture/list.action", type = "redirect"),
        @Result(name = "view", location = "/WEB-INF/jsp/oa/admin/picture/view.jsp"),
        @Result(name = "PictureInit", location = "/WEB-INF/jsp/oa/admin/picture/addPicture.jsp"),
        @Result(name = "list", location = "/WEB-INF/jsp/oa/admin/picture/list.jsp")
})
@Controller
public class OaPictureAction extends BaseAction {

    private static final long serialVersionUID = -4372884128344672292L;

    // 首页大栏位
    private List<KbnDto> bigColumList;

    @Autowired
    private LoginUserService loginUserService;

    private String namespace;

    private final int tabId = 10;

    private List<OaPictureDto> pictureList;

    private String pictureName;

    private OaPictureDto dto;

    private File img;

    private String imgContentType;

    private String imgFileName;

    @Autowired
    private OaPictureService pictureService;

    @Autowired
    private KbnService kbnService;

    @Action("pictureInit")
    public String PictureInit() {
        this.namespace = "oa-picture";
        this.bigColumList = this.kbnService.getBigColum();
        return "PictureInit";
    }

    @Action("addPicture")
    public String addPicture() {
        try {
            if (this.img != null) {
                final HttpServletRequest request = ServletActionContext.getRequest();
                final HttpSession session = request.getSession();

                final String fileName = System.currentTimeMillis()
                        + this.imgFileName.substring(this.imgFileName.lastIndexOf("."));
                session.setAttribute("fileName", fileName);

                final long fileSize = this.img.length();
                session.setAttribute("fileSize", fileSize);

                final String path =
                        ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/images");

                final File fileDir = new File(path);
                if (!fileDir.exists()) {
                    fileDir.mkdirs();
                }

                final String filePath = path + File.separator + fileName;
                final File fileTmp = new File(filePath);

                float barPercent = 0F;
                long byteSum = 0;
                int byteRead = 0;

                final FileInputStream fis = new FileInputStream(this.img);
                final FileOutputStream fos = new FileOutputStream(fileTmp);
                final byte[] buffer = new byte[1024];
                while ((byteRead = fis.read(buffer)) != -1) {
                    byteSum += byteRead;
                    session.setAttribute("byteSum", byteSum);
                    barPercent = Float.parseFloat(byteSum + "") / Float.parseFloat(fileSize + "");
                    session.setAttribute("barPercent", barPercent);
                    fos.write(buffer, 0, byteRead);
                }
                IOUtils.closeQuietly(fis);
                IOUtils.closeQuietly(fos);
                this.dto.setPictureName(fileName);
                this.dto.setCreater(this.pictureService.getUserById(this.getSessionUser().getUserId()));
                this.pictureService.insert(this.dto);
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return list();
    }

    @Action("list")
    public String list() {
        this.namespace = "oa-picture";
        this.bigColumList = this.kbnService.getBigColum();
        this.pictureList = this.pictureService.getPictureList(this.dto);
        this.pictureName = this.dto.getPictureName();
        ActionContext.getContext().getSession().put("pictureName", this.pictureName);
        return "list";
    }

    @Action("deletePicture")
    public String deletePicture() {
        this.pictureService.delete(this.dto);
        return list();
    }

    @Action("view")
    public String view() {
        this.namespace = "oa-picture";
        this.bigColumList = this.kbnService.getBigColum();
        this.dto = this.pictureService.getById(this.dto.getId());
        return "view";
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
     * getter for dto.
     * 
     * @return dto
     */
    public OaPictureDto getDto() {
        return this.dto;
    }

    /**
     * setter for dto.
     * 
     * @param aDto dto
     */
    public void setDto(final OaPictureDto aDto) {
        this.dto = aDto;
    }

    /**
     * getter for img.
     * 
     * @return img
     */
    public File getImg() {
        return this.img;
    }

    /**
     * setter for img.
     * 
     * @param aImg img
     */
    public void setImg(final File aImg) {
        this.img = aImg;
    }

    /**
     * getter for imgContentType.
     * 
     * @return imgContentType
     */
    public String getImgContentType() {
        return this.imgContentType;
    }

    /**
     * setter for imgContentType.
     * 
     * @param aImgContentType imgContentType
     */
    public void setImgContentType(final String aImgContentType) {
        this.imgContentType = aImgContentType;
    }

    /**
     * getter for imgFileName.
     * 
     * @return imgFileName
     */
    public String getImgFileName() {
        return this.imgFileName;
    }

    /**
     * setter for imgFileName.
     * 
     * @param aImgFileName imgFileName
     */
    public void setImgFileName(final String aImgFileName) {
        this.imgFileName = aImgFileName;
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

}
