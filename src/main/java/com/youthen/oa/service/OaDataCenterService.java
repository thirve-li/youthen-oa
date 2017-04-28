// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.oa.service;

import java.util.List;
import com.youthen.framework.common.exception.DuplicateKeyException;
import com.youthen.master.persistence.entity.LoginUser;
import com.youthen.oa.service.dto.OaDataCenterDto;

/**
 * 。
 * 
 * @author Dbl
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
public interface OaDataCenterService {

    List<OaDataCenterDto> getDataList(OaDataCenterDto aDto, final boolean choose);

    int getDataCount(OaDataCenterDto aDto, final boolean choose);

    public Long insert(OaDataCenterDto DataDto)
            throws DuplicateKeyException;

    OaDataCenterDto getById(Long id);

    OaDataCenterDto update(OaDataCenterDto aDto) throws DuplicateKeyException;

    LoginUser getUserById(final String userId);

    void delete(OaDataCenterDto aDto);

    /**
     * 。
     * 
     * @param aDto
     * @param aChoose
     * @return
     */

}
