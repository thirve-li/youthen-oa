// ============================================================
// Copyright(c) Soltoris Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.oa.service;

import java.util.List;
import com.youthen.oa.service.dto.OaRoomDto;

/**
 * 。
 * 
 * @author Lixin
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
public interface OaRoomService {

    void add(OaRoomDto dto);

    void edit(OaRoomDto dto);

    OaRoomDto getById(Long id);

    List<OaRoomDto> list(OaRoomDto dto);

}
