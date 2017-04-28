// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.oa.service;

import java.util.List;
import com.youthen.framework.common.exception.DuplicateKeyException;
import com.youthen.master.persistence.entity.LoginUser;
import com.youthen.oa.service.dto.OaAimDto;

/**
 * 。
 * 
 * @author Administrator
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
public interface OaAimService {

    List<OaAimDto> getAimList(OaAimDto aimDto);

    int getAimCount(OaAimDto aimDto);

    public Long insert(OaAimDto aAimDto) throws DuplicateKeyException;

    OaAimDto getById(Long id);

    OaAimDto update(OaAimDto aimDto);

    LoginUser getUserById(final String userId);

}
