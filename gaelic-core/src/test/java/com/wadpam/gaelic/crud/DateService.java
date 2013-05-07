/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.crud;

import com.wadpam.gaelic.domain.DDate;
import com.wadpam.gaelic.json.JCursorPage;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 *
 * @author sosandstrom
 */
public class DateService extends CrudServiceAdapter<DDate, Long> {
    
    private final TreeMap<Long, DDate> DATES = new TreeMap<Long, DDate>();

    @Override
    public Long create(DDate domain) {
        final long millis = null != domain.getStartDate() ?
                domain.getStartDate().getTime() : System.currentTimeMillis();
        domain.setId(millis);
        DATES.put(millis, domain);
        return millis;
    }

    @Override
    public void delete(String parentKeyString, Long id) {
        if (DATES.containsKey(id)) {
            DATES.remove(id);
        }
    }
    
    @Override
    public DDate get(String parentKeyString, Long id) {
        if (null == id) {
            return null;
        }
        
        return DATES.get(id);
    }

    @Override
    public JCursorPage<DDate> getPage(int pageSize, String cursorKey) {
        JCursorPage<DDate> page = new JCursorPage<DDate>();
        if (null == cursorKey) {
            page.setTotalSize(DATES.size());
        }
        ArrayList<DDate> items = new ArrayList<DDate>(DATES.values());
        page.setItems(items);
        while (pageSize < items.size())
        {
            items.remove(pageSize);
            page.setCursorKey(Integer.toString(pageSize));
        }
        return page;
    }

    @Override
    public Long update(DDate domain) {
        DATES.put(domain.getId(), domain);
        return domain.getId();
    }

}
