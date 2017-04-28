// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.oa.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.youthen.framework.common.StringUtils;
import com.youthen.framework.common.context.SessionContext;
import com.youthen.framework.common.exception.DuplicateKeyException;
import com.youthen.framework.util.BeanUtils;
import com.youthen.master.persistence.dao.LoginUserDao;
import com.youthen.master.persistence.entity.LoginUser;
import com.youthen.master.service.LoginUserService;
import com.youthen.oa.persistence.dao.OaAimDao;
import com.youthen.oa.persistence.entity.OaAim;
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
@Service(value = "aimService")
public class OaAimServiceImpl implements OaAimService {

    @Autowired
    OaAimDao aimDao;

    @Autowired
    LoginUserDao loginUserDao;

    @Autowired
    LoginUserService loginUserService;

    /**
     * @see com.youthen.master.service.OaAimService#getAimmanageList(com.youthen.master.service.dto.OaAimDto, boolean)
     */
    @Override
    public List<OaAimDto> getAimList(final OaAimDto aAimDto) {
        final LoginUser loginUser = this.loginUserService.getUserByUserId(SessionContext.getUser().getUserId());

        String hql = "from OaAim where 1=1 ";

        if (!loginUser.hasRole("ADMIN")) {
            hql += " and status = 1";
        }

        final String orderByString = " order by createTime desc,status asc";
        if (aAimDto.getExecutorDeptId() != null) {
            hql += " and executorDeptId = '" + aAimDto.getExecutorDeptId() + "'";
        }

        if (!StringUtils.isEmpty(aAimDto.getExecutorId())) {
            hql += " and executorId = '" + aAimDto.getExecutorId() + "'";
        }

        if (aAimDto.getStatus() != null) {
            hql += " and status = '" + aAimDto.getStatus() + "'";
        }

        hql += orderByString;

        final List<OaAim> list =
                this.aimDao.getByPage(hql, aAimDto.getGotoPage(), aAimDto.getPageSize());
        final ArrayList<OaAimDto> result = new ArrayList<OaAimDto>();

        if (CollectionUtils.isNotEmpty(list)) {
            for (final OaAim entity : list) {
                final OaAimDto dto = new OaAimDto();
                BeanUtils.copyAllNullableProperties(entity, dto);
                result.add(dto);
            }
        }
        return result;
    }

    /**
     * @see com.youthen.master.service.OaAimService#getAimanageCount(com.youthen.master.service.dto.OaAimDto, boolean)
     */

    @Override
    public int getAimCount(final OaAimDto aAimDto) {
        final LoginUser loginUser = this.loginUserService.getUserByUserId(SessionContext.getUser().getUserId());

        String hql = "from OaAim where 1=1 ";
        if (!loginUser.hasRole("ADMIN")) {
            hql += " and status=1 ";
        }

        final String orderByString = " order by status asc, createTime desc";

        if (aAimDto.getStatus() != null) {
            hql += " and status = '" + aAimDto.getStatus() + "'";
        }

        hql += orderByString;
        return this.aimDao.getCount(hql);
    }

    /**
     * @see com.youthen.master.service.OaAimService#insert(com.youthen.master.service.dto.OaAimDto)
     */

    @Override
    @Transactional
    public Long insert(final OaAimDto aAimmanageDto) throws DuplicateKeyException {
        final OaAim aimmanage = new OaAim();
        BeanUtils.copyAllNullableProperties(aAimmanageDto, aimmanage);
        aimmanage.setStatus(1);
        return (Long) this.aimDao.insert(aimmanage);
    }

    /**
     * @see com.youthen.master.service.OaAimService#getById(java.lang.Long)
     */

    @Override
    public OaAimDto getById(final Long aId) {
        final OaAimDto dto = new OaAimDto();
        BeanUtils.copyAllProperties(this.aimDao.getById(aId), dto);
        return dto;
    }

    /**
     * @see com.youthen.master.service.OaAimService#update(com.youthen.master.service.dto.OaAimDto)
     */

    @Override
    @Transactional
    public OaAimDto update(final OaAimDto aAimmanageDto) {
        final OaAim aim = this.aimDao.getById(aAimmanageDto.getId());
        BeanUtils.copyNullableProperties(aAimmanageDto, aim);
        this.aimDao.update(aim);
        return aAimmanageDto;
    }

    /**
     * @see com.youthen.master.service.OaAimService#getUserById(java.lang.String)
     */

    @Override
    public LoginUser getUserById(final String aUserId) {
        return this.loginUserDao.getById(aUserId);
    }

}
