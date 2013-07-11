/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.crud;

import com.wadpam.gaelic.GaelicServlet;
import com.wadpam.gaelic.domain.DDate;
import com.wadpam.gaelic.exception.BadRequestException;
import com.wadpam.gaelic.json.JCursorPage;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 *
 * @author sosandstrom
 */
public class DateService extends CrudServiceAdapter<DDate> {
    
    private final TreeMap<Long, DDate> DATES = new TreeMap<Long, DDate>();

    @Override
    public String create(DDate domain) {
        final long millis = null != domain.getStartDate() ?
                domain.getStartDate().getTime() : System.currentTimeMillis();
        domain.setId(millis);
        DATES.put(millis, domain);
        return Long.toString(millis);
    }

    @Override
    public void delete(Object parentKey, String id) {
        if (DATES.containsKey(Long.parseLong(id))) {
            DATES.remove(Long.parseLong(id));
        }
    }
    
    @Override
    public DDate get(Object parentKey, String id) {
        if (null == id) {
            return null;
        }
        
        try {
            return DATES.get(Long.parseLong(id));
        }
        catch (NumberFormatException notLong) {
            throw new BadRequestException(GaelicServlet.ERROR_CODE_ID_LONG, id, null);
        }
    }

    @Override
    public JCursorPage<DDate> getPage(Object parentKey, int pageSize, String cursorKey) {
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
    public String update(DDate domain) {
        DATES.put(domain.getId(), domain);
        return Long.toString(domain.getId());
    }

}
