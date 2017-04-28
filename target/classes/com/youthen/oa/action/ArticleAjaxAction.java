// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.oa.action;

import java.util.List;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.youthen.framework.presentation.action.AbstractAjaxAction;
import com.youthen.master.service.KbnService;
import com.youthen.master.service.dto.KbnDto;

@SuppressWarnings("serial")
@Namespace("/oa-article")
@Controller
public class ArticleAjaxAction extends AbstractAjaxAction {

    Long bigColumnId;

    @Autowired
    KbnService kbnService;

    /**
     * @see com.youthen.framework.presentation.action.AbstractAjaxAction#doExecute(java.lang.Object)
     */

    @Override
    protected Object doExecute(final Object aArgs) throws Exception {
        return null;
    }

    public List<KbnDto> getSamllColumnList() {
        final KbnDto kbn = new KbnDto();
        kbn.setParentTypeId(this.bigColumnId);
        return this.kbnService.getKbnListBy(kbn);
    }

    /**
     * getter for bigColumnId.
     * 
     * @return bigColumnId
     */
    public Long getBigColumnId() {
        return this.bigColumnId;
    }

    /**
     * setter for bigColumnId.
     * 
     * @param aBigColumnId bigColumnId
     */
    public void setBigColumnId(final Long aBigColumnId) {
        this.bigColumnId = aBigColumnId;
    }

}
