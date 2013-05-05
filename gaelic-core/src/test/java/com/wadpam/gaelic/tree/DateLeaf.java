/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.tree;

import com.wadpam.gaelic.crud.DateService;
import com.wadpam.gaelic.domain.DDate;
import com.wadpam.gaelic.json.JDate;

/**
 *
 * @author sosandstrom
 */
public class DateLeaf extends CrudLeaf<JDate, DDate, Long, DateService> {

    public DateLeaf() {
        super(DDate.class, Long.class, JDate.class);
    }

}
