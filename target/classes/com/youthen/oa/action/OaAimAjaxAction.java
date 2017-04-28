// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.oa.action;

import java.util.List;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.youthen.framework.common.exception.ObjectNotFoundException;
import com.youthen.framework.presentation.action.AbstractAjaxAction;
import com.youthen.master.persistence.entity.Department;
import com.youthen.master.service.DepartmentService;
import com.youthen.master.service.dto.DepartmentDto;

@SuppressWarnings("serial")
@Namespace("/mst-aim")
@Controller
public class OaAimAjaxAction extends AbstractAjaxAction {

    Long executorDeptId;

    @Autowired
    private DepartmentService departmentService;

    /**
     * @see com.youthen.framework.presentation.action.AbstractAjaxAction#doExecute(java.lang.Object)
     */

    @Override
    protected Object doExecute(final Object aArgs) throws Exception {
        return null;
    }

    public List<Department> getDepartmentList() {
        final DepartmentDto dept = new DepartmentDto();
        dept.setCompanyId(this.executorDeptId);
        try {
            return this.departmentService.getByHql("from Department where status=1");
        } catch (final ObjectNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * getter for executorDeptId.
     * 
     * @return executorDeptId
     */
    public Long getExecutorDeptId() {
        return this.executorDeptId;
    }

    /**
     * setter for executorDeptId.
     * 
     * @param aExecutorDeptId executorDeptId
     */
    public void setExecutorDeptId(final Long aExecutorDeptId) {
        this.executorDeptId = aExecutorDeptId;
    }

}
