// ============================================================
// Copyright(c) Soltoris Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.oa.action;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.youthen.framework.common.annotation.ExecAuthority;
import com.youthen.framework.presentation.action.AbstractAjaxAction;
import com.youthen.master.service.MasterDataMantanceService;
import com.youthen.oa.persistence.entity.OaRoom;

/**
 * 。
 * 
 * @author Administrator
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
@Namespace("/oa-room")
@Controller
@ExecAuthority(functioncd = "1")
public class OaRoomAjaxAction extends AbstractAjaxAction {

    /**
     * 。
     */
    private static final long serialVersionUID = 7953704643097224804L;
    @Autowired
    private MasterDataMantanceService masterDataMantanceService;

    /**
     * @see com.soltoris.sisqp.framework.presentation.action.AbstractAjaxAction#doExecute(java.lang.Object)
     */

    @SuppressWarnings("unchecked")
    @Override
    protected Object doExecute(final Object aArgs) throws Exception {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final String deptId = request.getParameter("deptId");
        this.masterDataMantanceService.setType(OaRoom.class);
        final List<OaRoom> roomList = this.masterDataMantanceService.queryByCondition(" departmentId = " + deptId);
        final List<OaRoom> result = new ArrayList<OaRoom>();
        for (final OaRoom room : roomList) {
            room.setDepartment(null);
            result.add(room);
        }
        return result;
    }

}
