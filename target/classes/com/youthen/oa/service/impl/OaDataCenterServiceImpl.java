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
import com.youthen.framework.common.exception.DuplicateKeyException;
import com.youthen.framework.common.exception.ObjectNotFoundException;
import com.youthen.framework.common.exception.OptimisticLockStolenException;
import com.youthen.framework.util.BeanUtils;
import com.youthen.master.persistence.dao.LoginUserDao;
import com.youthen.master.persistence.entity.LoginUser;
import com.youthen.oa.persistence.dao.OaDataCenterDao;
import com.youthen.oa.persistence.entity.OaDataCenter;
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
@Service(value = "oaDataCenterService")
public class OaDataCenterServiceImpl implements OaDataCenterService {

    @Autowired
    OaDataCenterDao dataDao;

    @Autowired
    LoginUserDao loginUserDao;

    @Override
    public List<OaDataCenterDto> getDataList(final OaDataCenterDto aDto, final boolean choose) {

        String hql = "from OaDataCenter where 1=1 ";
        final String orderBy = "order by status asc, createTime desc";
        if (aDto.getColumnId() != null && aDto.getColumnId() != 0) {
            hql += " and columnId='" + aDto.getColumnId() + "'";
        }

        if (!choose) {

            hql += " and status = 1 ";

        }

        hql += orderBy;

        final List<OaDataCenter> list =
                this.dataDao.getByPage(hql, aDto.getGotoPage(), aDto.getPageSize());
        final ArrayList<OaDataCenterDto> result = new ArrayList<OaDataCenterDto>();

        if (CollectionUtils.isNotEmpty(list)) {
            for (final OaDataCenter entity : list) {
                final OaDataCenterDto dto = new OaDataCenterDto();
                BeanUtils.copyAllNullableProperties(entity, dto);
                result.add(dto);
            }
        }
        return result;
    }

    /**
     * 
     */

    @Override
    public int getDataCount(final OaDataCenterDto aDto, final boolean choose) {
        String hql = "from OaDataCenter where 1=1 ";
        final String orderBy = "order by status asc, createTime desc";
        if (aDto.getColumnId() != null && aDto.getColumnId() != 0) {
            hql += " and columnId='" + aDto.getColumnId() + "'";
        }

        if (!choose) {
            hql += " and status = 1 ";
        }

        hql += orderBy;

        return this.dataDao.getCount(hql);
    }

    /**
     * 
     */

    @Override
    @Transactional
    public Long insert(final OaDataCenterDto aDto) throws DuplicateKeyException {
        final OaDataCenter data = new OaDataCenter();
        BeanUtils.copyAllNullableProperties(aDto, data);
        return (Long) this.dataDao.insert(data);
    }

    /**
     * 
     */

    @Override
    public OaDataCenterDto getById(final Long aId) {
        final OaDataCenterDto dto = new OaDataCenterDto();
        BeanUtils.copyAllProperties(this.dataDao.getById(aId), dto);
        BeanUtils.setNAProperty(dto);
        return dto;
    }

    /**
     * 
     */

    @Override
    @Transactional
    public OaDataCenterDto update(final OaDataCenterDto aDto) throws DuplicateKeyException {
        final OaDataCenter e = this.dataDao.getById(aDto.getId());
        BeanUtils.copyNullableProperties(aDto, e);
        this.dataDao.update(e);
        BeanUtils.copyProperties(e, aDto);
        return aDto;
    }

    @Override
    public void delete(final OaDataCenterDto aDto) {
        final OaDataCenter e = this.dataDao.getById(aDto.getId());
        BeanUtils.copyNullableProperties(aDto, e);
        try {
            this.dataDao.delete(e);
        } catch (final ObjectNotFoundException e1) {
            e1.printStackTrace();
        } catch (final OptimisticLockStolenException e1) {
            e1.printStackTrace();
        }

    }

    /**
     * 
     */

    @Override
    public LoginUser getUserById(final String aUserId) {
        return this.loginUserDao.getById(aUserId);
    }

}
