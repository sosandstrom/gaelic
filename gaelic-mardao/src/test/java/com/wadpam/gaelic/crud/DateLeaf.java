/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.crud;

import com.wadpam.gaelic.domain.DDate;
import com.wadpam.gaelic.json.JDate;
import com.wadpam.gaelic.tree.CrudLeaf;

/**
 *
 * @author sosandstrom
 */
public class DateLeaf extends CrudLeaf<JDate, DDate, Long, DateService> {

    public DateLeaf() {
        super(DDate.class, Long.class, JDate.class);
    }

}
