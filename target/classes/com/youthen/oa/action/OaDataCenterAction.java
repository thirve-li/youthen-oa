// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.oa.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import com.youthen.framework.common.exception.DuplicateKeyException;
import com.youthen.framework.presentation.action.BaseAction;
import com.youthen.framework.util.CommonUtils;
import com.youthen.master.persistence.entity.LoginUser;
import com.youthen.master.persistence.entity.Role;
import com.youthen.master.service.KbnService;
import com.youthen.master.service.LoginUserService;
import com.youthen.master.service.OsAudittrailService;
import com.youthen.master.service.dto.KbnDto;
import com.youthen.oa.service.OaDataCenterService;
import com.youthen.oa.service.dto.OaDataCenterDto;

/**
 * 。
 * 
 * @author Dbl
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
@Namespace("/oa-data")
@Results({
        @Result(name = "list", location = "/WEB-INF/jsp/oa/admin/data/list.jsp"),
        @Result(name = "addDataInit", location = "/WEB-INF/jsp/oa/admin/data/add.jsp"),
        @Result(name = "tolist", location = "/oa-data/list.action", type = "redirect")})
@Controller
public class OaDataCenterAction extends BaseAction {

    private static final long serialVersionUID = -4372884128344672292L;

    @Autowired
    private OaDataCenterService ds;

    private OaDataCenterDto dto = new OaDataCenterDto();

    // 首页大栏位
    private List<KbnDto> bigColumList;

    private String namespace;

    private int tabId = 6;

    // 新增页面的小栏位
    private List<KbnDto> smallColumList;

    @Autowired
    private KbnService kbnService;

    @Autowired
    private LoginUserService loginUserService;

    @Autowired
    private OsAudittrailService audittrailService;

    private List<OaDataCenterDto> DataDtoList;

    private File upload;

    private String uploadContentType;

    private String uploadFileName;

    @Action("list")
    public String list() {
        final LoginUser loginUser = this.loginUserService.getUserByUserId(this.getSessionUser().getUserId());
        boolean isAdmin = false;
        for (final Role role : loginUser.getRoles()) { // 此角色,用户已经拥有,不做校验
            if ("ADMIN".equals(role.getCode())) {
                isAdmin = true;
            }
        }
        this.namespace = "oa-data";
        this.bigColumList = this.kbnService.getBigColum();
        this.dto.setColumnId(this.dto.getColumnId());
        this.DataDtoList = this.ds.getDataList(this.dto, isAdmin);
        final int listSize = this.ds.getDataCount(this.dto, isAdmin);
        final int pages = CommonUtils.countPages(listSize, this.dto.getPageSize());
        this.dto.setPages(pages);
        this.dto.setListSize(listSize);

        // 获得资料中心小栏位List
        final KbnDto condition = new KbnDto();
        condition.setCode("ZLZX");
        final List<KbnDto> kbnList = this.kbnService.getKbnListBy(condition);

        if (!CollectionUtils.isEmpty(kbnList)) {
            final KbnDto dataCenter = kbnList.get(0);
            final KbnDto kbnDto = new KbnDto();
            kbnDto.setParentTypeId(dataCenter.getId());
            this.smallColumList = this.kbnService.getKbnListBy(kbnDto);
        }

        return "list";
    }

    @Action("addDataInit")
    public String addDataInit() {
        this.namespace = "oa-data";
        final LoginUser loginUser = this.loginUserService.getUserByUserId(this.getSessionUser().getUserId());
        this.bigColumList = this.kbnService.getBigColum();
        final KbnDto condition = new KbnDto();
        condition.setCode("ZLZX");
        final List<KbnDto> kbnList = this.kbnService.getKbnListBy(condition);

        if (!CollectionUtils.isEmpty(kbnList)) {
            final KbnDto dataCenter = kbnList.get(0);
            final KbnDto kbnDto = new KbnDto();
            kbnDto.setParentTypeId(dataCenter.getId());
            this.smallColumList = this.kbnService.getKbnListBy(kbnDto);
        }

        return "addDataInit";
    }

    /*
     * 资料上传
     */
    @Action("addData")
    public String addData() {
        try {
            final HttpServletRequest request = ServletActionContext.getRequest();
            final HttpSession session = request.getSession();

            final String fileName = System.currentTimeMillis()
                    + this.uploadFileName.substring(this.uploadFileName.lastIndexOf("."));
            session.setAttribute("fileName", fileName);

            final long fileSize = this.upload.length();
            session.setAttribute("fileSize", fileSize);

            final String path =
                    ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/paper");

            final File fileDir = new File(path);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }

            final String filePath = path + File.separator + getUploadFileName();
            final File fileTmp = new File(filePath);

            float barPercent = 0F;
            long byteSum = 0;
            int byteRead = 0;

            final FileInputStream fis = new FileInputStream(this.upload);
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

            // 更新数据库中的URL
            this.dto.setFilePath(filePath);
            this.dto.setCreater(this.ds.getUserById(this.getSessionUser().getUserId()));
            this.dto.setCreateTime(new Date());
            this.dto.setStatus(1);
            this.ds.insert(this.dto);

        } catch (final DuplicateKeyException e) {
            e.printStackTrace();
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        } catch (final NumberFormatException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        }

        return "tolist";
    }

    @Action("downData")
    public String downData() {
        OutputStream out = null;
        InputStream in = null;
        try {
            this.bigColumList = this.kbnService.getBigColum();
            final HttpServletResponse response = ServletActionContext.getResponse();
            this.dto = this.ds.getById(this.dto.getId());

            final String tempPath = this.dto.getFilePath();
            final String extName = this.dto.getFilePath().substring(tempPath.lastIndexOf("."), tempPath.length());
            final String filename = this.dto.getFileName() + extName;
            final String filepath = this.dto.getFilePath();
            out = response.getOutputStream();
            final byte b[] = new byte[2048];
            final File fileload = new File(filepath);
            response.setContentType("application/*");
            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
            in = new FileInputStream(fileload);
            int bytesRead;
            while (-1 != (bytesRead = in.read(b, 0, b.length))) {
                out.write(b, 0, bytesRead);
            }
            out.flush();
        } catch (final Exception e) {

        } finally {
            if (in != null) {
                IOUtils.closeQuietly(in);
                in = null;
            }
            if (out != null) {
                IOUtils.closeQuietly(out);
                out = null;
            }
        }
        return null;
    }

    @Action("deleteData")
    public String deleteData() {

        this.ds.delete(this.dto);

        return list();
    }

    /**
     * getter for dataDtoList.
     * 
     * @return dataDtoList
     */
    public List<OaDataCenterDto> getDataDtoList() {
        return this.DataDtoList;
    }

    /**
     * setter for dataDtoList.
     * 
     * @param aDataDtoList dataDtoList
     */
    public void setDataDtoList(final List<OaDataCenterDto> aDataDtoList) {
        this.DataDtoList = aDataDtoList;
    }

    /**
     * getter for upload.
     * 
     * @return upload
     */
    public File getUpload() {
        return this.upload;
    }

    /**
     * setter for upload.
     * 
     * @param aUpload upload
     */
    public void setUpload(final File aUpload) {
        this.upload = aUpload;
    }

    /**
     * getter for uploadContentType.
     * 
     * @return uploadContentType
     */
    public String getUploadContentType() {
        return this.uploadContentType;
    }

    /**
     * setter for uploadContentType.
     * 
     * @param aUploadContentType uploadContentType
     */
    public void setUploadContentType(final String aUploadContentType) {
        this.uploadContentType = aUploadContentType;
    }

    /**
     * getter for uploadFileName.
     * 
     * @return uploadFileName
     */
    public String getUploadFileName() {
        return this.uploadFileName;
    }

    /**
     * setter for uploadFileName.
     * 
     * @param aUploadFileName uploadFileName
     */
    public void setUploadFileName(final String aUploadFileName) {
        this.uploadFileName = aUploadFileName;
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
     * getter for dto.
     * 
     * @return dto
     */
    public OaDataCenterDto getDto() {
        return this.dto;
    }

    /**
     * setter for dto.
     * 
     * @param aDto dto
     */
    public void setDto(final OaDataCenterDto aDto) {
        this.dto = aDto;
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
