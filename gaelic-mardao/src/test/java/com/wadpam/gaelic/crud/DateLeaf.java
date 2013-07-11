/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.crud;

import com.wadpam.gaelic.converter.MardaoConverter;
import com.wadpam.gaelic.domain.DDate;
import com.wadpam.gaelic.json.JDate;
import com.wadpam.gaelic.tree.CrudLeaf;

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

    protected static class DateConverter extends MardaoConverter<JDate, DDate> {

        protected DateConverter() {
            super(JDate.class, DDate.class);
        }

        @Override
        public void convertDomain(DDate from, JDate to) {
            convertLongEntity(from, to);
            to.setStartDate(toLong(from.getStartDate()));
        }

        @Override
        public void convertJson(JDate from, DDate to) {
            convertJLong(from, to);
            to.setStartDate(toDate(from.getStartDate()));
        }
        
    }
}
