/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.converter;

import static com.wadpam.gaelic.converter.BaseConverter.toDate;
import static com.wadpam.gaelic.converter.BaseConverter.toLong;
import static com.wadpam.gaelic.converter.BaseConverter.toString;
import com.wadpam.gaelic.json.JBaseObject;
import com.wadpam.gaelic.json.JCursorPage;
import com.wadpam.gaelic.json.JLocation;
import java.io.Serializable;
import java.util.Map;
import net.sf.mardao.core.CursorPage;
import net.sf.mardao.core.domain.AbstractCreatedUpdatedEntity;
import net.sf.mardao.core.domain.AbstractLongEntity;
import net.sf.mardao.core.domain.AbstractStringEntity;
import net.sf.mardao.core.geo.DLocation;

/**
 *
 * @author sosandstrom
 */
public abstract class MardaoConverter<J extends Serializable, T> 
    extends BaseConverter<J, T> {

    public MardaoConverter(Class<J> jsonClass, Class<T> domainClass) {
        super(jsonClass, domainClass);
    }

    public static void convertCreatedUpdatedEntity(AbstractCreatedUpdatedEntity from, JBaseObject to) {
        if (null == from || null == to) {
            return;
        }

        to.setCreatedBy(from.getCreatedBy());
        to.setCreatedDate(toLong(from.getCreatedDate()));
        to.setUpdatedBy(from.getUpdatedBy());
        to.setUpdatedDate(toLong(from.getUpdatedDate()));
    }
    
    public static void convertLongEntity(AbstractLongEntity from, JBaseObject to) {
        if (null == from || null == to) {
            return;
        }

        convertCreatedUpdatedEntity((AbstractCreatedUpdatedEntity) from, to);
        
        to.setId(toString(from.getId()));
    }

    public static void convertStringEntity(AbstractStringEntity from, JBaseObject to) {
        if (null == from || null == to) {
            return;
        }

        convertCreatedUpdatedEntity((AbstractCreatedUpdatedEntity) from, to);
        
        to.setId(from.getId());
    }

    public static void convertJCreatedUpdated(JBaseObject from, AbstractCreatedUpdatedEntity to) {
        if (null == from || null == to) {
            return;
        }

        to.setCreatedBy(from.getCreatedBy());
        to.setCreatedDate(toDate(from.getCreatedDate()));
        to.setUpdatedBy(from.getUpdatedBy());
        to.setUpdatedDate(toDate(from.getUpdatedDate()));
    }
    
    public static void convertJLong(JBaseObject from, AbstractLongEntity to) {
        if (null == from || null == to) {
            return;
        }
        convertJCreatedUpdated(from, to);

        to.setId(toLong(from.getId()));
    }
    
    public static void convertJLong(Map<String, Object> from, JBaseObject to) {
        if (null == from || null == to) {
            return;
        }
        convertJCreatedUpdated(from, to);

        Object id = from.get("id");
        to.setId(null != id ? id.toString() : null);
    }

    public static void convertJString(JBaseObject from, AbstractStringEntity to) {
        if (null == from || null == to) {
            return;
        }
        convertJCreatedUpdated(from, to);

        to.setId(from.getId());
    }

    public static JCursorPage convertMardaoPage(CursorPage from) {
        if (null == from) {
            return null;
        }
        
        final JCursorPage to = new JCursorPage();
        
        to.setCursorKey(from.getCursorKey());
        to.setItems(from.getItems());
        to.setTotalSize(from.getTotalSize());
        
        return to;
    }
    
    /**
     * Converts from a Mardao CursorPage of domain objects
     * @param from the Mardao page of domains
     * @return a JCursorPage of JSON objects
     */
    public JCursorPage<J> convertMardaoDomainPage(CursorPage from) {
        final JCursorPage<J> to = convertMardaoPage(from);
        to.setItems(convertDomains(from.getItems()));
        return to;
    }

//    public JCursorPage<J> convertPageWithInner(HttpServletRequest request, HttpServletResponse response,
//            String domain, CursorPage<T, ID> from) {
//        final JCursorPage<J> to = new JCursorPage<J>();
//        
//        to.setPageSize(from.getItems().size());
//        to.setCursorKey(from.getCursorKey());
//        to.setTotalSize(from.getTotalSize());
//        to.setItems(convertWithInner(request, response, domain, 
//                from.getItems()));
//        
//        return to;
//    }
//    
    // Convert Mardao DLocation
    public static JLocation convertDLocation(DLocation from) {
        if (null == from) {
            return null;
        }

        return new JLocation(from.getLatitude(), from.getLongitude());
    }
    
    
}
