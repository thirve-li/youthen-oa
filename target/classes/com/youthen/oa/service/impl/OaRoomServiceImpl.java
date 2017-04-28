// ============================================================
// Copyright(c) Soltoris Incorporated All Right Reserved.
// File: $Id$
// ============================================================
package com.youthen.oa.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.youthen.framework.persistence.entity.CommonEntity;
import com.youthen.framework.util.BeanUtils;
import com.youthen.master.service.MasterDataMantanceService;
import com.youthen.master.service.OsAudittrailService;
import com.youthen.oa.persistence.entity.OaRoom;
import com.youthen.oa.service.OaRoomService;
import com.youthen.oa.service.dto.OaRoomDto;

/**
 * 。
 * 
 * @author Lixin
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
@Service(value = "mstRoomService")
@Transactional(rollbackFor = Throwable.class)
public class OaRoomServiceImpl<T extends CommonEntity> implements OaRoomService {

    @Autowired
    MasterDataMantanceService masterDataMantanceService;

    @Autowired
    private OsAudittrailService audittrailService;

    /**
     * @see com.soltoris.sisqp.MstMstRoomService.workflow.service.CmsMstMstRoomService#addMstRoom(com.OaRoomDto.sisqp.MstRoomDto.service.dto.CmsMstMstRoomDto)
     */

    @Override
    @Transactional
    public void add(final OaRoomDto aDto) {
        final OaRoom entity = new OaRoom();
        aDto.setObjectName(aDto.getRoomName());
        BeanUtils.copyAllNullableProperties(aDto, entity);
        entity.setStatus(1L);
        this.masterDataMantanceService.setType(OaRoom.class);
        this.masterDataMantanceService.insert(entity);
    }

    /**
     * @see com.soltoris.sisqp.MstMstRoomService.workflow.service.CmsMstMstRoomService#editMstRoom(com.OaRoomDto.sisqp.MstRoomDto.service.dto.CmsMstMstRoomDto)
     */

    @Override
    @Transactional
    public void edit(final OaRoomDto aDto) {

        this.masterDataMantanceService.setType(OaRoom.class);
        aDto.setObjectName(aDto.getRoomName());
        final OaRoom room = (OaRoom) this.masterDataMantanceService.getById(aDto.getId());
        BeanUtils.copyAllNullableProperties(aDto, room);
        this.masterDataMantanceService.update(room);

    }

    /**
     * @see com.soltoris.sisqp.MstMstRoomService.workflow.service.CmsMstMstRoomService#getMstRoomById(java.lang.String,
     *      java.lang.String)
     */

    @Override
    public OaRoomDto getById(final Long id) {
        this.masterDataMantanceService.setType(OaRoom.class);
        final OaRoom kbn = (OaRoom) this.masterDataMantanceService.getById(id);
        final OaRoomDto kbnDto = new OaRoomDto();
        BeanUtils.copyAllNullableProperties(kbn, kbnDto);
        return kbnDto;
    }

    /**
     * @see com.soltoris.sisqp.MstMstRoomService.workflow.service.CmsMstMstRoomService#list(com.OaRoomDto.sisqp.MstRoomDto.service.dto.CmsMstMstRoomDto)
     */

    @Override
    public List<OaRoomDto> list(OaRoomDto aDto) {
        this.masterDataMantanceService.setType(OaRoom.class);

        if (aDto == null) {
            aDto = new OaRoomDto();
        }
        final String condition = "1=1";
        final List<OaRoom> list = this.masterDataMantanceService.queryByCondition(condition);
        final List<OaRoomDto> dtolist = new ArrayList<OaRoomDto>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (final OaRoom kbn : list) {
                final OaRoomDto kbnDto = new OaRoomDto();
                BeanUtils.copyAllNullableProperties(kbn, kbnDto);
                dtolist.add(kbnDto);
            }
        }

        return dtolist;
    }

}
