// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.oa.service;

import java.util.List;
import com.youthen.framework.common.exception.DuplicateKeyException;
import com.youthen.master.persistence.entity.LoginUser;
import com.youthen.oa.service.dto.OaMeetingDto;

/**
 * 。
 * 
 * @author Dbl
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
public interface OaMeetingService {

    List<OaMeetingDto> getMeetingList(OaMeetingDto aDto);

    List<OaMeetingDto> getWeekMeetingList(OaMeetingDto aDto);

    int getMeetingCount(OaMeetingDto aDto);

    public Long insert(OaMeetingDto aDto)
            throws DuplicateKeyException;

    OaMeetingDto getById(Long id);

    OaMeetingDto update(OaMeetingDto aDto) throws DuplicateKeyException;

    LoginUser getUserById(final String userId);

    void delete(OaMeetingDto aDto);

}
