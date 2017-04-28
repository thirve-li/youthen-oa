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
import com.youthen.oa.persistence.entity.OaMeeting;

/**
 * 。
 * 
 * @author Dbl
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */

@Repository("oaMeetingDao")
public class OaMeetingDao extends EntityDaoImpl<OaMeeting> {

    /**
     * @see com.youthen.framework.persistence.dao.impl.EntityDaoImpl#injectType()
     */

    @Override
    @PostConstruct
    public void injectType() {
        this.setType(OaMeeting.class);

    }

    public List<OaMeeting> getMeetingList(final String ids) {
        if (StringUtils.isEmpty(ids) || "N.A.".equals(ids)) {
            return null;
        }

        final List<Long> lst = new ArrayList<Long>();
        for (final String id : ids.split(",")) {
            if (!"N.A.".equals(id)) {
                lst.add(Long.parseLong(id));
            }
        }

        final String hql = "from OA_USER_MEETING_ROOM where id in (:ids)";
        final Query query = this.getSession().createQuery(hql);
        query.setParameterList("ids", lst);
        return query.list();
    }

    public List<OaMeeting> AllList() {

        final String hql = "from OA_USER_MEETING_ROOM";
        final Query query = this.getSession().createQuery(hql);
        return query.list();
    }

}
