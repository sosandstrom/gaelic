package com.wadpam.gaelic.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import net.sf.mardao.core.CursorPage;
import net.sf.mardao.core.Filter;
import net.sf.mardao.core.dao.DaoImpl;
import net.sf.mardao.core.dao.TypeDaoImpl;
import net.sf.mardao.core.geo.DLocation;
import com.wadpam.gaelic.domain.DAppDomain;

/**
 * The DAppDomain domain-object specific finders and methods go in this POJO.
 * 
 * Generated on 2013-07-11T17:23:21.353+0700.
 * @author mardao DAO generator (net.sf.mardao.plugin.ProcessDomainMojo)
 */
public class GeneratedDAppDomainDaoImpl extends TypeDaoImpl<DAppDomain, java.lang.String> 
	implements GeneratedDAppDomainDao {


    /** to list the property names for ManyToOne relations */
    @Override
    protected List<String> getBasicColumnNames() {
        return BASIC_NAMES;
    }

    /** to list the property names for ManyToOne relations */
    @Override
    protected List<String> getManyToOneColumnNames() {
        return MANY_TO_ONE_NAMES;
    }

    private final Map<String, DaoImpl> MANY_TO_ONE_DAOS = new TreeMap<String, DaoImpl>();

    /** Default constructor */
   public GeneratedDAppDomainDaoImpl() {
      super(DAppDomain.class, java.lang.String.class);
   }

   // ------ BEGIN DaoImpl overrides -----------------------------
   
   public String getPrimaryKeyColumnName() {
   		return COLUMN_NAME_ID;
   }
   
   public List<String> getColumnNames() {
        return COLUMN_NAMES;
   }

   @Override
   protected DaoImpl getManyToOneDao(String columnName) {
       return MANY_TO_ONE_DAOS.get(columnName);
   }

    @Override
    protected Object getDomainProperty(DAppDomain domain, String name) {
        Object value;
        // simple key?
        if (COLUMN_NAME_ID.equals(name)) {
            value = domain.getId();
        }
        // fields
        else if (COLUMN_NAME_ANALYTICSTRACKINGCODE.equals(name)) {
            value = domain.getAnalyticsTrackingCode();
        }
        else if (COLUMN_NAME_APPARG1.equals(name)) {
            value = domain.getAppArg1();
        }
        else if (COLUMN_NAME_APPARG2.equals(name)) {
            value = domain.getAppArg2();
        }
        else if (COLUMN_NAME_CREATEDBY.equals(name)) {
            value = domain.getCreatedBy();
        }
        else if (COLUMN_NAME_CREATEDDATE.equals(name)) {
            value = domain.getCreatedDate();
        }
        else if (COLUMN_NAME_DESCRIPTION.equals(name)) {
            value = domain.getDescription();
        }
        else if (COLUMN_NAME_EMAIL.equals(name)) {
            value = domain.getEmail();
        }
        else if (COLUMN_NAME_PASSWORD.equals(name)) {
            value = domain.getPassword();
        }
        else if (COLUMN_NAME_UPDATEDBY.equals(name)) {
            value = domain.getUpdatedBy();
        }
        else if (COLUMN_NAME_UPDATEDDATE.equals(name)) {
            value = domain.getUpdatedDate();
        }
        else if (COLUMN_NAME_USERNAME.equals(name)) {
            value = domain.getUsername();
        }
        // one-to-ones
        // many-to-ones
        // many-to-manys
        else {
            value = super.getDomainProperty(domain, name);
        }

        return value;
    }

    /**
     * Returns the class of the domain property for specified column
     * @param name
     * @return the class of the domain property
     */
    public Class getColumnClass(String name) {
        Class clazz;
        // simple key?
        if (COLUMN_NAME_ID.equals(name)) {
            clazz = java.lang.String.class;
        }
        // fields
        else if (COLUMN_NAME_ANALYTICSTRACKINGCODE.equals(name)) {
            clazz = java.lang.String.class;
        }
        else if (COLUMN_NAME_APPARG1.equals(name)) {
            clazz = java.lang.String.class;
        }
        else if (COLUMN_NAME_APPARG2.equals(name)) {
            clazz = java.lang.String.class;
        }
        else if (COLUMN_NAME_CREATEDBY.equals(name)) {
            clazz = java.lang.String.class;
        }
        else if (COLUMN_NAME_CREATEDDATE.equals(name)) {
            clazz = java.util.Date.class;
        }
        else if (COLUMN_NAME_DESCRIPTION.equals(name)) {
            clazz = java.lang.String.class;
        }
        else if (COLUMN_NAME_EMAIL.equals(name)) {
            clazz = java.lang.String.class;
        }
        else if (COLUMN_NAME_PASSWORD.equals(name)) {
            clazz = java.lang.String.class;
        }
        else if (COLUMN_NAME_UPDATEDBY.equals(name)) {
            clazz = java.lang.String.class;
        }
        else if (COLUMN_NAME_UPDATEDDATE.equals(name)) {
            clazz = java.util.Date.class;
        }
        else if (COLUMN_NAME_USERNAME.equals(name)) {
            clazz = java.lang.String.class;
        }
        // one-to-ones
        // many-to-ones
        // many-to-manys
        else {
            throw new IllegalArgumentException("No such column " + name);
        }

        return clazz;
    }
      
    @Override
    protected void setDomainProperty(final DAppDomain domain, final String name, final Object value) {
        // simple key?
        if (COLUMN_NAME_ID.equals(name)) {
            domain.setId((java.lang.String) value);
        }
        // fields
        else if (COLUMN_NAME_ANALYTICSTRACKINGCODE.equals(name)) {
            domain.setAnalyticsTrackingCode((java.lang.String) value);
        }
        else if (COLUMN_NAME_APPARG1.equals(name)) {
            domain.setAppArg1((java.lang.String) value);
        }
        else if (COLUMN_NAME_APPARG2.equals(name)) {
            domain.setAppArg2((java.lang.String) value);
        }
        else if (COLUMN_NAME_CREATEDBY.equals(name)) {
            domain.setCreatedBy((java.lang.String) value);
        }
        else if (COLUMN_NAME_CREATEDDATE.equals(name)) {
            domain.setCreatedDate((java.util.Date) value);
        }
        else if (COLUMN_NAME_DESCRIPTION.equals(name)) {
            domain.setDescription((java.lang.String) value);
        }
        else if (COLUMN_NAME_EMAIL.equals(name)) {
            domain.setEmail((java.lang.String) value);
        }
        else if (COLUMN_NAME_PASSWORD.equals(name)) {
            domain.setPassword((java.lang.String) value);
        }
        else if (COLUMN_NAME_UPDATEDBY.equals(name)) {
            domain.setUpdatedBy((java.lang.String) value);
        }
        else if (COLUMN_NAME_UPDATEDDATE.equals(name)) {
            domain.setUpdatedDate((java.util.Date) value);
        }
        else if (COLUMN_NAME_USERNAME.equals(name)) {
            domain.setUsername((java.lang.String) value);
        }
        // one-to-ones
        // many-to-ones
        // many-to-manys
        else {
            super.setDomainProperty(domain, name, value);
        }
    }

    @Override
    protected void setDomainStringProperty(final DAppDomain domain, final String name, final Map<String, String> properties) {
        final String value = properties.get(name);
        Class clazz = getColumnClass(name);
        // many-to-ones
        setDomainProperty(domain, name, parseProperty(value, clazz));
    }

    /**
     * Overrides to substitute Entity properties with foreign keys
     */
    @Override
    protected void setCoreProperty(Object core, String name, Object value) {
        if (null == core || null == name) {
            return;
        }
        else if (null == value) {
            // do nothing in particular, will call super at end
        }
        super.setCoreProperty(core, name, value);
    }

   // ------ END DaoImpl overrides -----------------------------

        // DAppDomain has no parent

        /**
         * @return the simple key for specified DAppDomain domain object
         */
        public String getSimpleKey(DAppDomain domain) {
            if (null == domain) {
                return null;
            }
            return domain.getId();
        }

        /**
         * @return the simple key for specified DAppDomain domain object
         */
        public void setSimpleKey(DAppDomain domain, String id) {
            domain.setId(id);
        }

        public String getCreatedByColumnName() {
            return COLUMN_NAME_CREATEDBY;
        }

        public String getCreatedBy(DAppDomain domain) {
            if (null == domain) {
                return null;
            }
            return domain.getCreatedBy();
        }

        public void _setCreatedBy(DAppDomain domain, String creator) {
            domain.setCreatedBy(creator);
        }

        public String getUpdatedByColumnName() {
            return COLUMN_NAME_UPDATEDBY;
        }

        public String getUpdatedBy(DAppDomain domain) {
            if (null == domain) {
                return null;
            }
            return domain.getUpdatedBy();
        }

        public void _setUpdatedBy(DAppDomain domain, String updator) {
            domain.setUpdatedBy(updator);
        }

        public String getCreatedDateColumnName() {
            return COLUMN_NAME_CREATEDDATE;
        }

        public Date getCreatedDate(DAppDomain domain) {
            if (null == domain) {
                return null;
            }
            return domain.getCreatedDate();
        }

        public void _setCreatedDate(DAppDomain domain, Date date) {
            domain.setCreatedDate(date);
        }

        public String getUpdatedDateColumnName() {
            return COLUMN_NAME_UPDATEDDATE;
        }

        public Date getUpdatedDate(DAppDomain domain) {
            if (null == domain) {
                return null;
            }
            return domain.getUpdatedDate();
        }

        public void _setUpdatedDate(DAppDomain domain, Date date) {
            domain.setUpdatedDate(date);
        }

	// ----------------------- field finders -------------------------------
	/**
         * {@inheritDoc}
	 */
	public final Iterable<DAppDomain> queryByAnalyticsTrackingCode(java.lang.String analyticsTrackingCode) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_ANALYTICSTRACKINGCODE, analyticsTrackingCode);
            return queryIterable(false, 0, -1, null, null, null, false, null, false, filter);
	}
	
	/**
	 * query-key-by method for attribute field analyticsTrackingCode
	 * @param analyticsTrackingCode the specified attribute
	 * @return an Iterable of keys to the DAppDomains with the specified attribute
	 */
	public final Iterable<java.lang.String> queryKeysByAnalyticsTrackingCode(java.lang.String analyticsTrackingCode) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_ANALYTICSTRACKINGCODE, analyticsTrackingCode);
            return queryIterableKeys(0, -1, null, null, null, false, null, false, filter);
	}

	/**
	 * query-page-by method for field analyticsTrackingCode
	 * @param analyticsTrackingCode the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DAppDomains for the specified analyticsTrackingCode
	 */
	public final CursorPage<DAppDomain> queryPageByAnalyticsTrackingCode(java.lang.String analyticsTrackingCode,
            int pageSize, String cursorString) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_ANALYTICSTRACKINGCODE, analyticsTrackingCode);
            return queryPage(false, pageSize, null, null, null, false, null, false, cursorString, filter);
        }

	 
	/**
         * {@inheritDoc}
	 */
	public final Iterable<DAppDomain> queryByAppArg1(java.lang.String appArg1) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_APPARG1, appArg1);
            return queryIterable(false, 0, -1, null, null, null, false, null, false, filter);
	}
	
	/**
	 * query-key-by method for attribute field appArg1
	 * @param appArg1 the specified attribute
	 * @return an Iterable of keys to the DAppDomains with the specified attribute
	 */
	public final Iterable<java.lang.String> queryKeysByAppArg1(java.lang.String appArg1) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_APPARG1, appArg1);
            return queryIterableKeys(0, -1, null, null, null, false, null, false, filter);
	}

	/**
	 * query-page-by method for field appArg1
	 * @param appArg1 the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DAppDomains for the specified appArg1
	 */
	public final CursorPage<DAppDomain> queryPageByAppArg1(java.lang.String appArg1,
            int pageSize, String cursorString) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_APPARG1, appArg1);
            return queryPage(false, pageSize, null, null, null, false, null, false, cursorString, filter);
        }

	 
	/**
         * {@inheritDoc}
	 */
	public final Iterable<DAppDomain> queryByAppArg2(java.lang.String appArg2) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_APPARG2, appArg2);
            return queryIterable(false, 0, -1, null, null, null, false, null, false, filter);
	}
	
	/**
	 * query-key-by method for attribute field appArg2
	 * @param appArg2 the specified attribute
	 * @return an Iterable of keys to the DAppDomains with the specified attribute
	 */
	public final Iterable<java.lang.String> queryKeysByAppArg2(java.lang.String appArg2) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_APPARG2, appArg2);
            return queryIterableKeys(0, -1, null, null, null, false, null, false, filter);
	}

	/**
	 * query-page-by method for field appArg2
	 * @param appArg2 the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DAppDomains for the specified appArg2
	 */
	public final CursorPage<DAppDomain> queryPageByAppArg2(java.lang.String appArg2,
            int pageSize, String cursorString) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_APPARG2, appArg2);
            return queryPage(false, pageSize, null, null, null, false, null, false, cursorString, filter);
        }

	 
	/**
         * {@inheritDoc}
	 */
	public final Iterable<DAppDomain> queryByCreatedBy(java.lang.String createdBy) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_CREATEDBY, createdBy);
            return queryIterable(false, 0, -1, null, null, null, false, null, false, filter);
	}
	
	/**
	 * query-key-by method for attribute field createdBy
	 * @param createdBy the specified attribute
	 * @return an Iterable of keys to the DAppDomains with the specified attribute
	 */
	public final Iterable<java.lang.String> queryKeysByCreatedBy(java.lang.String createdBy) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_CREATEDBY, createdBy);
            return queryIterableKeys(0, -1, null, null, null, false, null, false, filter);
	}

	/**
	 * query-page-by method for field createdBy
	 * @param createdBy the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DAppDomains for the specified createdBy
	 */
	public final CursorPage<DAppDomain> queryPageByCreatedBy(java.lang.String createdBy,
            int pageSize, String cursorString) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_CREATEDBY, createdBy);
            return queryPage(false, pageSize, null, null, null, false, null, false, cursorString, filter);
        }

	 
	/**
         * {@inheritDoc}
	 */
	public final Iterable<DAppDomain> queryByCreatedDate(java.util.Date createdDate) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_CREATEDDATE, createdDate);
            return queryIterable(false, 0, -1, null, null, null, false, null, false, filter);
	}
	
	/**
	 * query-key-by method for attribute field createdDate
	 * @param createdDate the specified attribute
	 * @return an Iterable of keys to the DAppDomains with the specified attribute
	 */
	public final Iterable<java.lang.String> queryKeysByCreatedDate(java.util.Date createdDate) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_CREATEDDATE, createdDate);
            return queryIterableKeys(0, -1, null, null, null, false, null, false, filter);
	}

	/**
	 * query-page-by method for field createdDate
	 * @param createdDate the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DAppDomains for the specified createdDate
	 */
	public final CursorPage<DAppDomain> queryPageByCreatedDate(java.util.Date createdDate,
            int pageSize, String cursorString) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_CREATEDDATE, createdDate);
            return queryPage(false, pageSize, null, null, null, false, null, false, cursorString, filter);
        }

	 
	/**
         * {@inheritDoc}
	 */
	public final Iterable<DAppDomain> queryByDescription(java.lang.String description) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_DESCRIPTION, description);
            return queryIterable(false, 0, -1, null, null, null, false, null, false, filter);
	}
	
	/**
	 * query-key-by method for attribute field description
	 * @param description the specified attribute
	 * @return an Iterable of keys to the DAppDomains with the specified attribute
	 */
	public final Iterable<java.lang.String> queryKeysByDescription(java.lang.String description) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_DESCRIPTION, description);
            return queryIterableKeys(0, -1, null, null, null, false, null, false, filter);
	}

	/**
	 * query-page-by method for field description
	 * @param description the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DAppDomains for the specified description
	 */
	public final CursorPage<DAppDomain> queryPageByDescription(java.lang.String description,
            int pageSize, String cursorString) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_DESCRIPTION, description);
            return queryPage(false, pageSize, null, null, null, false, null, false, cursorString, filter);
        }

	 
	/**
         * {@inheritDoc}
	 */
	public final Iterable<DAppDomain> queryByEmail(java.lang.String email) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_EMAIL, email);
            return queryIterable(false, 0, -1, null, null, null, false, null, false, filter);
	}
	
	/**
	 * query-key-by method for attribute field email
	 * @param email the specified attribute
	 * @return an Iterable of keys to the DAppDomains with the specified attribute
	 */
	public final Iterable<java.lang.String> queryKeysByEmail(java.lang.String email) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_EMAIL, email);
            return queryIterableKeys(0, -1, null, null, null, false, null, false, filter);
	}

	/**
	 * query-page-by method for field email
	 * @param email the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DAppDomains for the specified email
	 */
	public final CursorPage<DAppDomain> queryPageByEmail(java.lang.String email,
            int pageSize, String cursorString) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_EMAIL, email);
            return queryPage(false, pageSize, null, null, null, false, null, false, cursorString, filter);
        }

	 
	/**
         * {@inheritDoc}
	 */
	public final Iterable<DAppDomain> queryByPassword(java.lang.String password) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_PASSWORD, password);
            return queryIterable(false, 0, -1, null, null, null, false, null, false, filter);
	}
	
	/**
	 * query-key-by method for attribute field password
	 * @param password the specified attribute
	 * @return an Iterable of keys to the DAppDomains with the specified attribute
	 */
	public final Iterable<java.lang.String> queryKeysByPassword(java.lang.String password) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_PASSWORD, password);
            return queryIterableKeys(0, -1, null, null, null, false, null, false, filter);
	}

	/**
	 * query-page-by method for field password
	 * @param password the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DAppDomains for the specified password
	 */
	public final CursorPage<DAppDomain> queryPageByPassword(java.lang.String password,
            int pageSize, String cursorString) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_PASSWORD, password);
            return queryPage(false, pageSize, null, null, null, false, null, false, cursorString, filter);
        }

	 
	/**
         * {@inheritDoc}
	 */
	public final Iterable<DAppDomain> queryByUpdatedBy(java.lang.String updatedBy) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_UPDATEDBY, updatedBy);
            return queryIterable(false, 0, -1, null, null, null, false, null, false, filter);
	}
	
	/**
	 * query-key-by method for attribute field updatedBy
	 * @param updatedBy the specified attribute
	 * @return an Iterable of keys to the DAppDomains with the specified attribute
	 */
	public final Iterable<java.lang.String> queryKeysByUpdatedBy(java.lang.String updatedBy) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_UPDATEDBY, updatedBy);
            return queryIterableKeys(0, -1, null, null, null, false, null, false, filter);
	}

	/**
	 * query-page-by method for field updatedBy
	 * @param updatedBy the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DAppDomains for the specified updatedBy
	 */
	public final CursorPage<DAppDomain> queryPageByUpdatedBy(java.lang.String updatedBy,
            int pageSize, String cursorString) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_UPDATEDBY, updatedBy);
            return queryPage(false, pageSize, null, null, null, false, null, false, cursorString, filter);
        }

	 
	/**
         * {@inheritDoc}
	 */
	public final Iterable<DAppDomain> queryByUpdatedDate(java.util.Date updatedDate) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_UPDATEDDATE, updatedDate);
            return queryIterable(false, 0, -1, null, null, null, false, null, false, filter);
	}
	
	/**
	 * query-key-by method for attribute field updatedDate
	 * @param updatedDate the specified attribute
	 * @return an Iterable of keys to the DAppDomains with the specified attribute
	 */
	public final Iterable<java.lang.String> queryKeysByUpdatedDate(java.util.Date updatedDate) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_UPDATEDDATE, updatedDate);
            return queryIterableKeys(0, -1, null, null, null, false, null, false, filter);
	}

	/**
	 * query-page-by method for field updatedDate
	 * @param updatedDate the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DAppDomains for the specified updatedDate
	 */
	public final CursorPage<DAppDomain> queryPageByUpdatedDate(java.util.Date updatedDate,
            int pageSize, String cursorString) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_UPDATEDDATE, updatedDate);
            return queryPage(false, pageSize, null, null, null, false, null, false, cursorString, filter);
        }

	 
	/**
	 * find-by method for unique attribute field username
	 * @param username the unique attribute
	 * @return the unique DAppDomain for the specified attribute
	 */
	public final DAppDomain findByUsername(java.lang.String username) {
                Filter filter = createEqualsFilter(COLUMN_NAME_USERNAME, username);
		return findUniqueBy(filter);
	}

	/**
	 * find-key-by method for unique attribute field username
	 * @param username the unique attribute
	 * @return the unique DAppDomain for the specified attribute
	 */
	public final java.lang.String findKeyByUsername(java.lang.String username) {
                Filter filter = createEqualsFilter(COLUMN_NAME_USERNAME, username);
		return findUniqueKeyBy(filter);
	}
	 
	// ----------------------- one-to-one finders -------------------------

	// ----------------------- many-to-one finders -------------------------

	// ----------------------- many-to-many finders -------------------------

	// ----------------------- uniqueFields finders -------------------------

	// ----------------------- populate / persist method -------------------------

	/**
	 * Persist an entity given all attributes
	 */
	public DAppDomain persist(		java.lang.String id, 
		java.lang.String analyticsTrackingCode, 
		java.lang.String appArg1, 
		java.lang.String appArg2, 
		java.lang.String description, 
		java.lang.String email, 
		java.lang.String password, 
		java.lang.String username) {

            DAppDomain domain = null;
            // if primaryKey specified, use it
            if (null != id) {
                    domain = findByPrimaryKey(id);
            }
		
            // create new?
            if (null == domain) {
                    domain = new DAppDomain();
                    // generate Id?
                    if (null != id) {
                            domain.setId(id);
                    }
                    // fields
                    domain.setAnalyticsTrackingCode(analyticsTrackingCode);
                    domain.setAppArg1(appArg1);
                    domain.setAppArg2(appArg2);
                    domain.setDescription(description);
                    domain.setEmail(email);
                    domain.setPassword(password);
                    domain.setUsername(username);
                    // one-to-ones
                    // many-to-ones
			
                    persist(domain);
            }
            return domain;
	}


	/**
	 * Persists an entity unless it already exists
	 */
	public DAppDomain persist(java.lang.String username, 
                java.lang.String analyticsTrackingCode, 
                java.lang.String appArg1, 
                java.lang.String appArg2, 
                java.lang.String description, 
                java.lang.String email, 
                java.lang.String password) {
            DAppDomain domain = findByUsername(username);
            if (null == domain) {
                domain = new DAppDomain();
                domain.setAnalyticsTrackingCode(analyticsTrackingCode);
                domain.setAppArg1(appArg1);
                domain.setAppArg2(appArg2);
                domain.setDescription(description);
                domain.setEmail(email);
                domain.setPassword(password);
                domain.setUsername(username);
                persist(domain);
            }
            return domain;
	}


}
