#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*
 * INSERT COPYRIGHT HERE
 */

package ${package}.web;

import ${package}.domain.DSample;
import ${package}.json.JSample;
import ${package}.service.SampleService;
import com.wadpam.gaelic.converter.LongConverter;
import com.wadpam.gaelic.tree.CrudLeaf;

/**
 *
 * @author sosandstrom
 */
public class SampleLeaf extends CrudLeaf<JSample, DSample, Long, SampleService> {
    
    static final SampleConverter CONVERTER = new SampleConverter();

    public SampleLeaf() {
        super(DSample.class, Long.class, JSample.class);
        setConverter(CONVERTER);
    }
    
    static class SampleConverter extends LongConverter<JSample, DSample> {

        public SampleConverter() {
            super(JSample.class, DSample.class);
        }

        @Override
        public void convertDomain(DSample from, JSample to) {
            super.convertDomain(from, to);
            to.setPhoneNumber(from.getPhoneNumber());
        }

        @Override
        public void convertJson(JSample from, DSample to) {
            super.convertJson(from, to);
            to.setPhoneNumber(from.getPhoneNumber());
        }
        
    }
}
