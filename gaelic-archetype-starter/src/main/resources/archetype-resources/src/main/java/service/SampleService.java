#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*
 * INSERT COPYRIGHT HERE
 */

package ${package}.service;

import ${package}.dao.DSampleDaoBean;
import ${package}.domain.DSample;
import com.wadpam.gaelic.crud.MardaoCrudService;
import net.sf.mardao.core.dao.Dao;

/**
 *
 * @author sosandstrom
 */
public class SampleService extends MardaoCrudService<DSample, Long, Dao<DSample, Long>> {

    public SampleService() {
        super(DSample.class, Long.class, DSampleDaoBean.class);
    }

}
