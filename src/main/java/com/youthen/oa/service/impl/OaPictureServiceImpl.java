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
import com.youthen.framework.common.exception.DuplicateKeyException;
import com.youthen.framework.common.exception.ObjectNotFoundException;
import com.youthen.framework.common.exception.OptimisticLockStolenException;
import com.youthen.framework.util.BeanUtils;
import com.youthen.master.persistence.dao.LoginUserDao;
import com.youthen.master.persistence.entity.LoginUser;
import com.youthen.oa.persistence.dao.OaPictureDao;
import com.youthen.oa.persistence.entity.OaPicture;
import com.youthen.oa.service.OaPictureService;
import com.youthen.oa.service.dto.OaPictureDto;

/**
 * 。
 * 
 * @author Dbl
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
@Service(value = "pictureService")
public class OaPictureServiceImpl implements OaPictureService {

    @Autowired
    OaPictureDao dao;

    @Autowired
    LoginUserDao loginUserDao;

    @Override
    public List<OaPictureDto> getPictureList(final OaPictureDto aDto) {

        final String hql = "from OaPicture";

        final List<OaPicture> list =
                this.dao.getByPage(hql, aDto.getGotoPage(), aDto.getPageSize());
        final ArrayList<OaPictureDto> result = new ArrayList<OaPictureDto>();

        if (CollectionUtils.isNotEmpty(list)) {
            for (final OaPicture entity : list) {
                final OaPictureDto dto = new OaPictureDto();
                BeanUtils.copyAllNullableProperties(entity, dto);
                result.add(dto);
            }
        }
        return result;
    }

    @Override
    public Long insert(final OaPictureDto aDto) throws DuplicateKeyException {
        final OaPicture picture = new OaPicture();
        BeanUtils.copyAllNullableProperties(aDto, picture);
        return (Long) this.dao.insert(picture);
    }

    @Override
    public OaPictureDto getById(final Long aId) {
        final OaPictureDto dto = new OaPictureDto();
        BeanUtils.copyAllProperties(this.dao.getById(aId), dto);
        BeanUtils.setNAProperty(dto);
        return dto;
    }

    @Override
    public void delete(final OaPictureDto aDto) {
        final OaPicture e = this.dao.getById(aDto.getId());
        BeanUtils.copyNullableProperties(aDto, e);
        try {
            this.dao.delete(e);
        } catch (final ObjectNotFoundException e1) {
            e1.printStackTrace();
        } catch (final OptimisticLockStolenException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public LoginUser getUserById(final String aUserId) {
        return this.loginUserDao.getById(aUserId);
    }

}
