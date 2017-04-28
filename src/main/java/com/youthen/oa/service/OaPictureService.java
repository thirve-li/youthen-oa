// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.oa.service;

import java.util.List;
import com.youthen.framework.common.exception.DuplicateKeyException;
import com.youthen.master.persistence.entity.LoginUser;
import com.youthen.oa.service.dto.OaPictureDto;

/**
 * 。
 * 
 * @author Dbl
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
public interface OaPictureService {

    List<OaPictureDto> getPictureList(OaPictureDto aDto);

    public Long insert(OaPictureDto aDto) throws DuplicateKeyException;

    OaPictureDto getById(Long id);

    void delete(OaPictureDto aDto);

    LoginUser getUserById(final String userId);

}
