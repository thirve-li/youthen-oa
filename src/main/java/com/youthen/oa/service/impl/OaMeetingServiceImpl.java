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
import com.youthen.master.util.CommonUtil;
import com.youthen.oa.persistence.dao.OaMeetingDao;
import com.youthen.oa.persistence.entity.OaMeeting;
import com.youthen.oa.service.OaMeetingService;
import com.youthen.oa.service.dto.OaMeetingDto;

/**
 * 。
 * 
 * @author Dbl
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
@Service(value = "oaMeetingService")
public class OaMeetingServiceImpl implements OaMeetingService {

    @Autowired
    OaMeetingDao dao;

    @Autowired
    LoginUserDao loginUserDao;

    @Override
    public List<OaMeetingDto> getMeetingList(final OaMeetingDto aDto) {
        final CommonUtil cu = new CommonUtil();
        String hql = "from OaMeeting where 1=1 ";
        final String orderBy = "order by startTime desc";
        if (aDto.getRoomId() != null && aDto.getRoomId() != 0) {
            hql += " and roomId='" + aDto.getRoomId() + "'";
        }
        if (aDto.getPurposeId() != null && aDto.getPurposeId() != 0) {
            hql += " and purposeId='" + aDto.getPurposeId() + "'";
        }

        if (aDto.getType() != null) {
            hql += " and type='" + aDto.getType() + "'";
        }

        if (aDto.getStartTime() != null && aDto.getEndTime() != null) {
            hql +=
                    " and startTime between '" + cu.dateToStr(aDto.getStartTime()) + " 00:00:00" + "' and"
                            + " '" + cu.dateToStr(aDto.getEndTime()) + " 23:59:59" + "' and endTime between "
                            + " '" + cu.dateToStr(aDto.getStartTime()) + " 00:00:00" + "' and '"
                            + cu.dateToStr(aDto.getEndTime()) + " 23:59:59" + "'";
        }
        hql += orderBy;

        final List<OaMeeting> list =
                this.dao.getByPage(hql, aDto.getGotoPage(), aDto.getPageSize());
        final ArrayList<OaMeetingDto> result = new ArrayList<OaMeetingDto>();

        if (CollectionUtils.isNotEmpty(list)) {
            for (final OaMeeting entity : list) {
                final OaMeetingDto dto = new OaMeetingDto();
                BeanUtils.copyAllNullableProperties(entity, dto);
                result.add(dto);
            }
        }
        return result;
    }

    @Override
    public List<OaMeetingDto> getWeekMeetingList(final OaMeetingDto aDto) {
        final CommonUtil cu = new CommonUtil();
        String hql = "from OaMeeting where 1=1 ";
        final String orderBy = "order by startTime asc";
        if (aDto.getRoomId() != null && aDto.getRoomId() != 0) {
            hql += " and roomId='" + aDto.getRoomId() + "'";
        }
        if (aDto.getPurposeId() != null && aDto.getPurposeId() != 0) {
            hql += " and purposeId='" + aDto.getPurposeId() + "'";
        }

        if (aDto.getType() != null) {
            hql += " and type='" + aDto.getType() + "'";
        }

        if (aDto.getStartTime() != null && aDto.getEndTime() != null) {
            hql +=
                    " and startTime between '" + cu.dateToStrLong(aDto.getStartTime()) + "' and"
                            + " '" + cu.dateToStrLong(aDto.getEndTime()) + "' or endTime between "
                            + " '" + cu.dateToStrLong(aDto.getStartTime()) + "' and '"
                            + cu.dateToStrLong(aDto.getEndTime()) + "' or (startTime<='"
                            + cu.dateToStrLong(aDto.getStartTime()) + "'"
                            + " and endTime>='" + cu.dateToStrLong(aDto.getEndTime()) + "' ) ";
        }
        hql += orderBy;

        final List<OaMeeting> list =
                this.dao.getByPage(hql, aDto.getGotoPage(), aDto.getPageSize());
        final ArrayList<OaMeetingDto> result = new ArrayList<OaMeetingDto>();

        if (CollectionUtils.isNotEmpty(list)) {
            for (final OaMeeting entity : list) {
                final OaMeetingDto dto = new OaMeetingDto();
                BeanUtils.copyAllNullableProperties(entity, dto);
                result.add(dto);
            }
        }
        return result;
    }

    @Override
    public int getMeetingCount(final OaMeetingDto aDto) {
        String hql = "from OaMeeting where 1=1 ";
        final String orderBy = "order by  startTime desc";
        if (aDto.getRoomId() != null && aDto.getRoomId() != 0) {
            hql += " and roomId='" + aDto.getRoomId() + "'";
        }
        if (aDto.getPurposeId() != null && aDto.getPurposeId() != 0) {
            hql += " and purposeId='" + aDto.getPurposeId() + "'";
        }
        hql += orderBy;

        return this.dao.getCount(hql);
    }

    @Override
    @Transactional
    public Long insert(final OaMeetingDto adto) throws DuplicateKeyException {

        final OaMeeting meeting = new OaMeeting();
        BeanUtils.copyAllNullableProperties(adto, meeting);
        return (Long) this.dao.insert(meeting);
    }

    @Override
    public OaMeetingDto getById(final Long id) {
        final OaMeetingDto dto = new OaMeetingDto();
        BeanUtils.copyAllProperties(this.dao.getById(id), dto);
        return dto;
    }

    @Override
    @Transactional
    public OaMeetingDto update(final OaMeetingDto aDto) throws DuplicateKeyException {
        final OaMeeting e = this.dao.getById(aDto.getId());
        BeanUtils.copyNullableProperties(aDto, e);
        this.dao.update(e);
        BeanUtils.copyProperties(e, aDto);
        return aDto;
    }

    @Override
    public LoginUser getUserById(final String userId) {

        return this.loginUserDao.getById(userId);
    }

    @Override
    public void delete(final OaMeetingDto aDto) {
        final OaMeeting e = this.dao.getById(aDto.getId());
        BeanUtils.copyNullableProperties(aDto, e);
        try {
            this.dao.delete(e);
        } catch (final ObjectNotFoundException e1) {
            e1.printStackTrace();
        } catch (final OptimisticLockStolenException e1) {
            e1.printStackTrace();
        }

    }
}
