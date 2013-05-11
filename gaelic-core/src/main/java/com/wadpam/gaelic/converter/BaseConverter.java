/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.converter;

import com.wadpam.gaelic.json.JBaseObject;
import com.wadpam.gaelic.json.JCursorPage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author sosandstrom
 */
public abstract class BaseConverter<J extends Serializable, T> {
    
    protected final Class<J> jsonClass;
    protected final Class<T> domainClass;

    public BaseConverter(Class<J> jsonClass, Class<T> domainClass) {
        this.jsonClass = jsonClass;
        this.domainClass = domainClass;
    }

    // --------------- Converter methods --------------------------
    
    public J convertDomain(T from) {
        if (null == from) {
            return null;
        }
        J to = createJson();
        convertDomain(from, to);
        return to;
    }
    
    public T convertJson(J from) {
        if (null == from) {
            return null;
        }
        T to = createDomain();
        convertJson(from, to);
        return to;
    }

    public J convertMap(Map<String,Object> from) {
        if (null == from) {
            return null;
        }
        J to = createJson();
        convertJMap(from, to);
        return to;
    }

    public abstract void convertDomain(T from, J to);
    public abstract void convertJson(J from, T to);
    
    /**
     * Override to get upsertBatch to work!
     * @param from is a LinkedHashMap created by Jackson
     */
    public void convertJMap(Map<String,Object> from, J to) {
        throw new UnsupportedOperationException("Override in subclass!"); 
    }
    
    public J createJson() {
        try {
            return (J) jsonClass.newInstance();
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        }
        return null;
    }
    
    public T createDomain() {
        try {
            return (T) domainClass.newInstance();
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        }
        return null;
    }
    
    public static void convertJCreatedUpdated(Map<String, Object> from, JBaseObject to) {
        if (null == from || null == to) {
            return;
        }

        to.setCreatedBy((String)from.get("createdBy"));
        to.setCreatedDate((Long)from.get("createdDate"));
        to.setUpdatedBy((String)from.get("updatedBy"));
        to.setUpdatedDate((Long)from.get("updatedDate"));
    }
    
    // Convert iterable
    public Collection<J> convertDomains(Iterable<T> from) {
        if (null == from)
            return new ArrayList<J>();

        final Collection<J> returnValue = new ArrayList<J>();

        J to;
        for (T o : from) {
            to = convertDomain(o);
            returnValue.add(to);
        }
        
        return returnValue;
    }

    public ArrayList<T> convertJSONs(Iterable<J> from) {
        final ArrayList<T> returnValue = new ArrayList<T>();
        if (null == from) {
            return returnValue;
        }

        T to;
        for (J o : from) {
            to = convertJson(o);
            returnValue.add(to);
        }
        
        return returnValue;
    }

    public JCursorPage<J> convertDomainPage(JCursorPage<T> from) {
        final JCursorPage<J> to = new JCursorPage<J>();
        
        to.setPageSize(from.getItems().size());
        to.setCursorKey(from.getCursorKey());
        to.setTotalSize(from.getTotalSize());
        to.setItems(convertDomains(from.getItems()));
        
        return to;
    }

    public JCursorPage<T> convertJsonPage(JCursorPage<J> from) {
        final JCursorPage<T> to = new JCursorPage<T>();
        
        to.setPageSize(from.getItems().size());
        to.setCursorKey(from.getCursorKey());
        to.setTotalSize(from.getTotalSize());
        to.setItems(convertJSONs(from.getItems()));
        
        return to;
    }

    protected Collection<String> getInnerParameterNames() {
        return Collections.EMPTY_LIST;
    }

    public static Long toLong(Date from) {
        if (null == from) {
            return null;
        }
        return from.getTime();
    }

    public static Long toLong(String from) {
        if (null == from) {
            return null;
        }
        return Long.parseLong(from);
    }

    public static Date toDate(Long from) {
        if (null == from) {
            return null;
        }
        return new Date(from);
    }

    public static String toString(Long from) {
        if (null == from) {
            return null;
        }
        return Long.toString(from);
    }

    public static Collection<Long> toLongs(Collection<String> from) {
        if (null == from) {
            return null;
        }

        final Collection<Long> to = new ArrayList<Long>();

        for(String s : from) {
            try {
                to.add(Long.parseLong(s));
            }
            catch (NumberFormatException sometimes) {
                to.add(null);
            }
        }

        return to;
    }

    public static Collection<String> toString(Collection<Long> from) {
        if (null == from) {
            return null;
        }

        final Collection<String> to = new ArrayList<String>(from.size());

        for(Long l : from) {
            to.add(l.toString());
        }

        return to;
    }
    
    
    
}
