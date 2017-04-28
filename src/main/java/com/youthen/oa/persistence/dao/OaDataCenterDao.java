// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.oa.persistence.dao;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.youthen.framework.common.StringUtils;
import com.youthen.framework.persistence.dao.impl.EntityDaoImpl;
import com.youthen.oa.persistence.entity.OaDataCenter;

/**
 * 。
 * 
 * @author Dbl
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
@Repository("oaDataCenterDao")
public class OaDataCenterDao extends EntityDaoImpl<OaDataCenter> {

    @Override
    @PostConstruct
    public void injectType() {
        this.setType(OaDataCenter.class);
    }

    public List<OaDataCenter> getDataList(final String ids) {
        if (StringUtils.isEmpty(ids) || "N.A.".equals(ids)) {
            return null;
        }

        final List<Long> lst = new ArrayList<Long>();
        for (final String id : ids.split(",")) {
            if (!"N.A.".equals(id)) {
                lst.add(Long.parseLong(id));
            }
        }

        final String hql = "from OaDataCenter where id in (:ids)";
        final Query query = this.getSession().createQuery(hql);
        query.setParameterList("ids", lst);
        return query.list();
    }

    public List<OaDataCenter> AllList() {

        final String hql = "from OaDataCenter";
        final Query query = this.getSession().createQuery(hql);
        return query.list();
    }
}
