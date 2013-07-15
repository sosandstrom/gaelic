package com.wadpam.gaelic.crud;

import com.wadpam.gaelic.GaelicServlet;
import com.wadpam.gaelic.converter.MardaoConverter;
import com.wadpam.gaelic.exception.BadRequestException;
import com.wadpam.gaelic.exception.RestException;
import com.wadpam.gaelic.json.JCursorPage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Future;
import net.sf.mardao.core.CursorPage;
import net.sf.mardao.core.Filter;
import net.sf.mardao.core.dao.Dao;
import net.sf.mardao.core.dao.DaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author os
 */
public abstract class MardaoCrudService<T extends Object,
        ID extends Serializable, D extends Dao<T, ID>> 
    extends BasicCrudObservable<Serializable, T>
    implements CrudService<T> {
    
    protected static final Logger LOG = LoggerFactory.getLogger(MardaoCrudService.class);
    
    protected final Class<T> domainClass;
    protected final Class<ID> idClass;
    protected final Class<Dao<T, ID>> daoClass;
    protected D dao;

    public MardaoCrudService(Class domainClass, Class idClass, Class daoClass) {
        super(null, null);
        setService(this);
        this.domainClass = domainClass;
        this.idClass = idClass;
        this.daoClass = daoClass;
        try {
            this.dao = (D) daoClass.newInstance();
        } catch (InstantiationException ex) {
            throw new RuntimeException("instantiating", ex);
        } catch (IllegalAccessException ex) {
            throw new RuntimeException("access", ex);
        }
    }
    
    /**
     * Converts from String id to domain-specific ID class
     * @param stringId String id, as used in API
     * @return domain-specific ID class
     */
    protected ID getID(final String stringId) {
        return getID(stringId, this.idClass);
    }
    
    /**
     * Converts from String id to specified ID class
     * @param stringId String id, as used in API
     * @param idClass class for the ID
     * @return domain-specific ID class
     */
    public static <I extends Serializable> I getID(final String stringId, Class<I> idClass) {
        I id = null;
        
        if (null != stringId) {
            // if ID is Long, parse filename
            if (Long.class.equals(idClass)) {
                try {
                    Long l = Long.parseLong(stringId);
                    id = (I) l;
                }
                catch (NumberFormatException notLong) {
                    throw new BadRequestException(GaelicServlet.ERROR_CODE_ID_LONG, stringId, null);
                }
            }
            else {
                id = (I) stringId;
            }
        }
        
        return id;
    }
    
    /**
     * Converts from domain-specific ID class to String
     * @param id domain-specific ID
     * @return String id, as used in API
     */
    protected String getStringId(final ID id) {
        return getStringId(id, this.idClass);
    }
    
    /**
     * Converts from domain-specific ID class to String
     * @param id domain-specific ID
     * @return String id, as used in API
     */
    public static <I extends Serializable> String getStringId(final I id, Class<I> idClass) {
        if (null == id || String.class.equals(idClass)) {
            return (String) id;
        }
        
        return id.toString();
    }

    protected List<ID> getIDs(final Iterable<String> stringIds) {
        final ArrayList<ID> ids = new ArrayList<ID>();
        for (String id : stringIds) {
            ids.add(getID(id));
        }
        return ids;
    }
    
    protected List<String> getStringIds(final Iterable<ID> ids) {
        final ArrayList<String> stringIds = new ArrayList<String>();
        for (ID id : ids) {
            stringIds.add(getStringId(id));
        }
        return stringIds;
    }
    
    protected CursorPage<String> getCursorPage(CursorPage<ID> page) {
        final CursorPage p = page;
        
        // in-page conversion
        p.setItems(getStringIds(page.getItems()));
        
        return p;
    }
    
    @Override
    public T createDomain() {
        try {
            return (T) ((DaoImpl) dao).createDomain();
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        }
        return null;
    }
    
    protected void preDao() {
    }
    
    protected void postDao() {
    }
    
    protected Object beginTransaction() {
//        return dao.beginTransaction();
        return null;
    }
    
    protected void commitTransaction(Object transaction) {
//        dao.commitTransaction(transaction);
    }
    
    protected void rollbackActiveTransaction(Object transaction) {
//        dao.rollbackActiveTransaction(transaction);
    }
    
    @Override
    public String create(T domain) {
        if (null == domain) {
            return null;
        }
        
        final Object transaction = beginTransaction();
        preDao();
        prePersist(domain);
        preService(null, null, CrudListener.CREATE, null, domain, dao.getSimpleKey(domain));
        try {
            final ID id = dao.persist(domain);

            LOG.debug("Created {}", domain);

            commitTransaction(transaction);
            postService(null, null, CrudListener.CREATE, null, id, domain);
            return getStringId(id);
        }
        finally {
            postDao();
            rollbackActiveTransaction(transaction);
        }
    }
    
    @Override
    public void delete(Object parentKey, String id) {
        final Object transaction = beginTransaction();
        preDao();
        preService(null, null, CrudListener.DELETE, null, null, id);
        try {
            LOG.debug("deleting {} with ID {}/{}", new Object[] {dao.getTableName(), parentKey, id});
            dao.delete(parentKey, getID(id));
            commitTransaction(transaction);
            postService(null, null, CrudListener.DELETE, null, id, null);
        }
        finally {
            postDao();
            rollbackActiveTransaction(transaction);
        }
    }

    @Override
    public void delete(Object parentKey, String[] id) {
        final Object transaction = beginTransaction();
        preDao();
        preService(null, null, CrudListener.DELETE_BATCH, null, null, id);
        try {
            final ArrayList<ID> ids = new ArrayList<ID>();
            for (String i : id) {
                ids.add(getID(i));
            }
            dao.delete(parentKey, ids);
            commitTransaction(transaction);
            postService(null, null, CrudListener.DELETE_BATCH, null, id, null);
        }
        finally {
            postDao();
            rollbackActiveTransaction(transaction);
        }
    }

    @Override
    public T get(Object parentKey, String id) {
        if (null == id || "".equals(id)) {
            return null;
        }
        
        preDao();
        preService(null, null, CrudListener.GET, null, null, id);
        try {
            T domain = dao.findByPrimaryKey(parentKey, getID(id));
            LOG.debug("GET {}/{}/{} returns {}", new Object[] {
                dao.getTableName(), parentKey, id, domain});

            postService(null, null, CrudListener.GET, null, id, domain);
            return domain;
        }
        catch (RuntimeException toLog) {
            LOG.warn("in GET", toLog);
            throw toLog;
        }
        finally {
            postDao();
        }
    }
    
    @Override
    public Iterable<T> getByPrimaryKeys(Object parentKey, Collection<String> ids) {
        preDao();
        final Serializable idArray = ids.toArray(new Serializable[ids.size()]);
        preService(null, null, CrudListener.GET_BATCH, null, null, idArray);
        try {
            final Iterable<T> entities = dao.queryByPrimaryKeys(parentKey, getIDs(ids));

            postService(null, null, CrudListener.GET_BATCH, null, idArray, entities);
            return entities;
        }
        finally {
            postDao();
        }
    }
    
    protected String[] getExportColumns() {
        return new String[] {
            dao.getPrimaryKeyColumnName(),
            dao.getCreatedDateColumnName(),
            dao.getCreatedByColumnName(),
            dao.getUpdatedDateColumnName(),
            dao.getUpdatedByColumnName()
        };
    }
    
    public String getKeyString(Object key) {
        return dao.getKeyString(key);
    }

    @Override
    public JCursorPage<T> getPage(Object parentKey, int pageSize, String cursorKey) {
        preDao();
        preService(null, null, CrudListener.GET_PAGE, null, null, cursorKey);
        try {
            JCursorPage page = MardaoConverter.convertMardaoPage(
                               dao.queryPage(pageSize, cursorKey));
            postService(null, null, CrudListener.GET_PAGE, null, cursorKey, page);
            return page;
        }
        finally {
            postDao();
        }
    }
    
    @Override
    public String getParentKeyString(T domain) {
        final Object parentKey = dao.getParentKey(domain);
        return dao.getKeyString(parentKey);
    }
    
    public Object getPrimaryKey(T domain) {
        return dao.getPrimaryKey(domain);
    }

    @Override
    public String getPrimaryKeyColumnName() {
        return dao.getPrimaryKeyColumnName();
    }

    @Override
    public Class getPrimaryKeyColumnClass() {
        return dao.getColumnClass(dao.getPrimaryKeyColumnName());
    }

    @Override
    public String getSimpleKey(T domain) {
        return getStringId(dao.getSimpleKey(domain));
    }

    @Override
    public String getSimpleKeyString(Object primaryKey) {
        return getStringId(dao.getSimpleKeyByPrimaryKey(primaryKey));
    }

    @Override
    public String getTableName() {
        return dao.getTableName();
    }

    @Override
    public Map<String, Class> getTypeMap() {
        final TreeMap<String, Class> map = new TreeMap<String, Class>();
        
        for (String col : dao.getColumnNames()) {
            map.put(col, dao.getColumnClass(col));
        }
        
        return map;
    }
    
    /** Override to implement pre-persist validation */
    protected void prePersist(T domain) throws RestException {
    }
    
    @Override
    public String update(T domain) {
        final Object transaction = beginTransaction();
        preDao();
        try {
            LOG.debug("Update {}", domain);
            final String id = getSimpleKey(domain);
            if (null == domain || null == id) {
                throw new IllegalArgumentException("ID cannot be null updating " + dao.getTableName());
            }
            preService(null, null, CrudListener.UPDATE, null, domain, id);

            dao.update(domain);

            commitTransaction(transaction);
            postService(null, null, CrudListener.UPDATE, null, id, domain);
            return id;
        }
        finally {
            postDao();
            rollbackActiveTransaction(transaction);
        }
    }
    
    @Override
    public List<String> upsert(Iterable<T> dEntities) {
        final Object transaction = beginTransaction();
        
        // group entities by create or update:
        final ArrayList<T> toCreate = new ArrayList<T>();
        final ArrayList<T> toUpdate = new ArrayList<T>();
        ID id;
        for (T d : dEntities) {
            id = dao.getSimpleKey(d);
            if (null == id) {
                toCreate.add(d);
            }
            else {
                toUpdate.add(d);
            }
        }
        LOG.debug("Creating {}, Updating {}", toCreate.size(), toUpdate.size());
        LOG.debug("Creating {}, Updating {}", toCreate, toUpdate);
        
        preService(null, null, CrudListener.UPSERT_BATCH, null, dEntities, null);
        try {
            // create new entities using async API
            Future<List<?>> createFuture = null;
            if (!toCreate.isEmpty()) {
                createFuture = dao.persistForFuture(toCreate);
            }

            // update in parallel
            if (!toUpdate.isEmpty()) {
                dao.update(toUpdate);
            }

            // join future
            if (null != createFuture) {
                dao.getSimpleKeys(createFuture);
    //            Iterator<ID> i = ids.iterator();
    //            for (T t : toCreate) {
    //                dao.setSimpleKey(t, i.next());
    //            }
            }

            // collect the IDs
            final ArrayList<String> body = new ArrayList<String>();
            for (T d : dEntities) {
                body.add(getSimpleKey(d));
            }

            commitTransaction(transaction);
            postService(null, null, CrudListener.UPSERT_BATCH, null, body, dEntities);
            return body;
        }
        finally {
            rollbackActiveTransaction(transaction);
        }
    }
    
    @Override
    public JCursorPage<String> whatsChanged(Date since, int pageSize, String cursorKey) {
        preDao();
        preService(null, null, CrudListener.WHAT_CHANGED, null, null, cursorKey);
        try {
            // TODO: include deletes from Audit table
            JCursorPage page = MardaoConverter.convertMardaoPage(
                                dao.whatsChanged(since, pageSize, cursorKey));
            postService(null, null, CrudListener.WHAT_CHANGED, null, cursorKey, page);
            return page;
        }
        finally {
            postDao();
        }
    }
    
    public CursorPage<String> whatsChanged(Object parentKey, Date since, 
            int pageSize, String cursorKey, Filter... filters) {
        preDao();
        preService(null, null, CrudListener.WHAT_CHANGED, null, null, cursorKey);
        try {
            // TODO: include deletes from Audit table
            CursorPage<ID> page = dao.whatsChanged(parentKey, since, pageSize, cursorKey, filters);
            postService(null, null, CrudListener.WHAT_CHANGED, null, cursorKey, page);
            return getCursorPage(page);
        }
        finally {
            postDao();
        }
    }

    // -------------------- getters and setters --------------------------------
    
    @Override
    public Class getDaoClass() {
        return daoClass;
    }

    @Override
    public Class getDomainClass() {
        return domainClass;
    }

    @Override
    public Class getIdClass() {
        return idClass;
    }
    
    public void setDao(D dao) {
        this.dao = dao;
    }

    public D getDao() {
        return dao;
    }
    
}
