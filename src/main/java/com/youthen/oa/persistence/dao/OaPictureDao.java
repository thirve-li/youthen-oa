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
import com.youthen.oa.persistence.entity.OaPicture;

/**
 * 。
 * 
 * @author Dbl
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */

@Repository("pictureDao")
public class OaPictureDao extends EntityDaoImpl<OaPicture> {

    /**
     * @see com.youthen.framework.persistence.dao.impl.EntityDaoImpl#injectType()
     */

    @Override
    @PostConstruct
    public void injectType() {
        this.setType(OaPicture.class);
    }

    public List<OaPicture> getPictureList(final String ids) {
        if (StringUtils.isEmpty(ids) || "N.A.".equals(ids)) {
            return null;
        }

        final List<Long> lst = new ArrayList<Long>();
        for (final String id : ids.split(",")) {
            if (!"N.A.".equals(id)) {
                lst.add(Long.parseLong(id));
            }
        }

        final String hql = "from OA_Picture_ROOM where id in (:ids)";
        final Query query = this.getSession().createQuery(hql);
        query.setParameterList("ids", lst);
        return query.list();
    }

    public List<OaPicture> AllList() {

        final String hql = "from OA_Picture";
        final Query query = this.getSession().createQuery(hql);
        return query.list();
    }

}
