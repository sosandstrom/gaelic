/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.tree;

import com.wadpam.gaelic.converter.BaseConverter;
import com.wadpam.gaelic.crud.DateService;
import com.wadpam.gaelic.domain.DDate;
import com.wadpam.gaelic.json.JDate;

/**
 *
 * @author sosandstrom
 */
public class DateLeaf extends CrudLeaf<JDate, DDate, DateService> {
    
    protected static final DateConverter CONVERTER = new DateConverter();

    public DateLeaf() {
        super(DDate.class, Long.class, JDate.class);
        setConverter(CONVERTER);
    }

    protected static class DateConverter extends BaseConverter<JDate, DDate> {

        protected DateConverter() {
            super(JDate.class, DDate.class);
        }

        @Override
        public void convertDomain(DDate from, JDate to) {
            to.setId(toString(from.getId()));
            to.setStartDate(toLong(from.getStartDate()));
        }

        @Override
        public void convertJson(JDate from, DDate to) {
            to.setId(toLong(from.getId()));
            to.setStartDate(toDate(from.getStartDate()));
        }
        
    }
}
