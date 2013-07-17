package com.wadpam.gaelic.oauth.provider.dao;

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
import com.wadpam.gaelic.oauth.provider.domain.Do2pProfile;

/**
 * The Do2pProfile domain-object specific finders and methods go in this POJO.
 * 
 * Generated on 2013-07-17T19:10:35.416+0700.
 * @author mardao DAO generator (net.sf.mardao.plugin.ProcessDomainMojo)
 */
public class GeneratedDo2pProfileDaoImpl extends TypeDaoImpl<Do2pProfile, java.lang.Long> 
	implements GeneratedDo2pProfileDao {


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
   public GeneratedDo2pProfileDaoImpl() {
      super(Do2pProfile.class, java.lang.Long.class);
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
    protected Object getDomainProperty(Do2pProfile domain, String name) {
        Object value;
        // simple key?
        if (COLUMN_NAME_ID.equals(name)) {
            value = domain.getId();
        }
        // fields
        else if (COLUMN_NAME_CREATEDBY.equals(name)) {
            value = domain.getCreatedBy();
        }
        else if (COLUMN_NAME_CREATEDDATE.equals(name)) {
            value = domain.getCreatedDate();
        }
        else if (COLUMN_NAME_EMAIL.equals(name)) {
            value = domain.getEmail();
        }
        else if (COLUMN_NAME_SECRET.equals(name)) {
            value = domain.getSecret();
        }
        else if (COLUMN_NAME_STATE.equals(name)) {
            value = domain.getState();
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
            clazz = java.lang.Long.class;
        }
        // fields
        else if (COLUMN_NAME_CREATEDBY.equals(name)) {
            clazz = java.lang.String.class;
        }
        else if (COLUMN_NAME_CREATEDDATE.equals(name)) {
            clazz = java.util.Date.class;
        }
        else if (COLUMN_NAME_EMAIL.equals(name)) {
            clazz = java.lang.String.class;
        }
        else if (COLUMN_NAME_SECRET.equals(name)) {
            clazz = java.lang.String.class;
        }
        else if (COLUMN_NAME_STATE.equals(name)) {
            clazz = java.lang.Long.class;
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
    protected void setDomainProperty(final Do2pProfile domain, final String name, final Object value) {
        // simple key?
        if (COLUMN_NAME_ID.equals(name)) {
            domain.setId((java.lang.Long) value);
        }
        // fields
        else if (COLUMN_NAME_CREATEDBY.equals(name)) {
            domain.setCreatedBy((java.lang.String) value);
        }
        else if (COLUMN_NAME_CREATEDDATE.equals(name)) {
            domain.setCreatedDate((java.util.Date) value);
        }
        else if (COLUMN_NAME_EMAIL.equals(name)) {
            domain.setEmail((java.lang.String) value);
        }
        else if (COLUMN_NAME_SECRET.equals(name)) {
            domain.setSecret((java.lang.String) value);
        }
        else if (COLUMN_NAME_STATE.equals(name)) {
            domain.setState((java.lang.Long) value);
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
    protected void setDomainStringProperty(final Do2pProfile domain, final String name, final Map<String, String> properties) {
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

        // Do2pProfile has no parent

        /**
         * @return the simple key for specified Do2pProfile domain object
         */
        public Long getSimpleKey(Do2pProfile domain) {
            if (null == domain) {
                return null;
            }
            return domain.getId();
        }

        /**
         * @return the simple key for specified Do2pProfile domain object
         */
        public void setSimpleKey(Do2pProfile domain, Long id) {
            domain.setId(id);
        }

        public String getCreatedByColumnName() {
            return COLUMN_NAME_CREATEDBY;
        }

        public String getCreatedBy(Do2pProfile domain) {
            if (null == domain) {
                return null;
            }
            return domain.getCreatedBy();
        }

        public void _setCreatedBy(Do2pProfile domain, String creator) {
            domain.setCreatedBy(creator);
        }

        public String getUpdatedByColumnName() {
            return COLUMN_NAME_UPDATEDBY;
        }

        public String getUpdatedBy(Do2pProfile domain) {
            if (null == domain) {
                return null;
            }
            return domain.getUpdatedBy();
        }

        public void _setUpdatedBy(Do2pProfile domain, String updator) {
            domain.setUpdatedBy(updator);
        }

        public String getCreatedDateColumnName() {
            return COLUMN_NAME_CREATEDDATE;
        }

        public Date getCreatedDate(Do2pProfile domain) {
            if (null == domain) {
                return null;
            }
            return domain.getCreatedDate();
        }

        public void _setCreatedDate(Do2pProfile domain, Date date) {
            domain.setCreatedDate(date);
        }

        public String getUpdatedDateColumnName() {
            return COLUMN_NAME_UPDATEDDATE;
        }

        public Date getUpdatedDate(Do2pProfile domain) {
            if (null == domain) {
                return null;
            }
            return domain.getUpdatedDate();
        }

        public void _setUpdatedDate(Do2pProfile domain, Date date) {
            domain.setUpdatedDate(date);
        }

	// ----------------------- field finders -------------------------------
	/**
         * {@inheritDoc}
	 */
	public final Iterable<Do2pProfile> queryByCreatedBy(java.lang.String createdBy) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_CREATEDBY, createdBy);
            return queryIterable(false, 0, -1, null, null, null, false, null, false, filter);
	}
	
	/**
	 * query-key-by method for attribute field createdBy
	 * @param createdBy the specified attribute
	 * @return an Iterable of keys to the Do2pProfiles with the specified attribute
	 */
	public final Iterable<java.lang.Long> queryKeysByCreatedBy(java.lang.String createdBy) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_CREATEDBY, createdBy);
            return queryIterableKeys(0, -1, null, null, null, false, null, false, filter);
	}

	/**
	 * query-page-by method for field createdBy
	 * @param createdBy the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of Do2pProfiles for the specified createdBy
	 */
	public final CursorPage<Do2pProfile> queryPageByCreatedBy(java.lang.String createdBy,
            int pageSize, String cursorString) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_CREATEDBY, createdBy);
            return queryPage(false, pageSize, null, null, null, false, null, false, cursorString, filter);
        }

	 
	/**
         * {@inheritDoc}
	 */
	public final Iterable<Do2pProfile> queryByCreatedDate(java.util.Date createdDate) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_CREATEDDATE, createdDate);
            return queryIterable(false, 0, -1, null, null, null, false, null, false, filter);
	}
	
	/**
	 * query-key-by method for attribute field createdDate
	 * @param createdDate the specified attribute
	 * @return an Iterable of keys to the Do2pProfiles with the specified attribute
	 */
	public final Iterable<java.lang.Long> queryKeysByCreatedDate(java.util.Date createdDate) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_CREATEDDATE, createdDate);
            return queryIterableKeys(0, -1, null, null, null, false, null, false, filter);
	}

	/**
	 * query-page-by method for field createdDate
	 * @param createdDate the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of Do2pProfiles for the specified createdDate
	 */
	public final CursorPage<Do2pProfile> queryPageByCreatedDate(java.util.Date createdDate,
            int pageSize, String cursorString) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_CREATEDDATE, createdDate);
            return queryPage(false, pageSize, null, null, null, false, null, false, cursorString, filter);
        }

	 
	/**
         * {@inheritDoc}
	 */
	public final Iterable<Do2pProfile> queryByEmail(java.lang.String email) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_EMAIL, email);
            return queryIterable(false, 0, -1, null, null, null, false, null, false, filter);
	}
	
	/**
	 * query-key-by method for attribute field email
	 * @param email the specified attribute
	 * @return an Iterable of keys to the Do2pProfiles with the specified attribute
	 */
	public final Iterable<java.lang.Long> queryKeysByEmail(java.lang.String email) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_EMAIL, email);
            return queryIterableKeys(0, -1, null, null, null, false, null, false, filter);
	}

	/**
	 * query-page-by method for field email
	 * @param email the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of Do2pProfiles for the specified email
	 */
	public final CursorPage<Do2pProfile> queryPageByEmail(java.lang.String email,
            int pageSize, String cursorString) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_EMAIL, email);
            return queryPage(false, pageSize, null, null, null, false, null, false, cursorString, filter);
        }

	 
	/**
         * {@inheritDoc}
	 */
	public final Iterable<Do2pProfile> queryBySecret(java.lang.String secret) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_SECRET, secret);
            return queryIterable(false, 0, -1, null, null, null, false, null, false, filter);
	}
	
	/**
	 * query-key-by method for attribute field secret
	 * @param secret the specified attribute
	 * @return an Iterable of keys to the Do2pProfiles with the specified attribute
	 */
	public final Iterable<java.lang.Long> queryKeysBySecret(java.lang.String secret) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_SECRET, secret);
            return queryIterableKeys(0, -1, null, null, null, false, null, false, filter);
	}

	/**
	 * query-page-by method for field secret
	 * @param secret the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of Do2pProfiles for the specified secret
	 */
	public final CursorPage<Do2pProfile> queryPageBySecret(java.lang.String secret,
            int pageSize, String cursorString) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_SECRET, secret);
            return queryPage(false, pageSize, null, null, null, false, null, false, cursorString, filter);
        }

	 
	/**
         * {@inheritDoc}
	 */
	public final Iterable<Do2pProfile> queryByState(java.lang.Long state) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_STATE, state);
            return queryIterable(false, 0, -1, null, null, null, false, null, false, filter);
	}
	
	/**
	 * query-key-by method for attribute field state
	 * @param state the specified attribute
	 * @return an Iterable of keys to the Do2pProfiles with the specified attribute
	 */
	public final Iterable<java.lang.Long> queryKeysByState(java.lang.Long state) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_STATE, state);
            return queryIterableKeys(0, -1, null, null, null, false, null, false, filter);
	}

	/**
	 * query-page-by method for field state
	 * @param state the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of Do2pProfiles for the specified state
	 */
	public final CursorPage<Do2pProfile> queryPageByState(java.lang.Long state,
            int pageSize, String cursorString) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_STATE, state);
            return queryPage(false, pageSize, null, null, null, false, null, false, cursorString, filter);
        }

	 
	/**
         * {@inheritDoc}
	 */
	public final Iterable<Do2pProfile> queryByUpdatedBy(java.lang.String updatedBy) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_UPDATEDBY, updatedBy);
            return queryIterable(false, 0, -1, null, null, null, false, null, false, filter);
	}
	
	/**
	 * query-key-by method for attribute field updatedBy
	 * @param updatedBy the specified attribute
	 * @return an Iterable of keys to the Do2pProfiles with the specified attribute
	 */
	public final Iterable<java.lang.Long> queryKeysByUpdatedBy(java.lang.String updatedBy) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_UPDATEDBY, updatedBy);
            return queryIterableKeys(0, -1, null, null, null, false, null, false, filter);
	}

	/**
	 * query-page-by method for field updatedBy
	 * @param updatedBy the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of Do2pProfiles for the specified updatedBy
	 */
	public final CursorPage<Do2pProfile> queryPageByUpdatedBy(java.lang.String updatedBy,
            int pageSize, String cursorString) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_UPDATEDBY, updatedBy);
            return queryPage(false, pageSize, null, null, null, false, null, false, cursorString, filter);
        }

	 
	/**
         * {@inheritDoc}
	 */
	public final Iterable<Do2pProfile> queryByUpdatedDate(java.util.Date updatedDate) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_UPDATEDDATE, updatedDate);
            return queryIterable(false, 0, -1, null, null, null, false, null, false, filter);
	}
	
	/**
	 * query-key-by method for attribute field updatedDate
	 * @param updatedDate the specified attribute
	 * @return an Iterable of keys to the Do2pProfiles with the specified attribute
	 */
	public final Iterable<java.lang.Long> queryKeysByUpdatedDate(java.util.Date updatedDate) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_UPDATEDDATE, updatedDate);
            return queryIterableKeys(0, -1, null, null, null, false, null, false, filter);
	}

	/**
	 * query-page-by method for field updatedDate
	 * @param updatedDate the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of Do2pProfiles for the specified updatedDate
	 */
	public final CursorPage<Do2pProfile> queryPageByUpdatedDate(java.util.Date updatedDate,
            int pageSize, String cursorString) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_UPDATEDDATE, updatedDate);
            return queryPage(false, pageSize, null, null, null, false, null, false, cursorString, filter);
        }

	 
	/**
	 * find-by method for unique attribute field username
	 * @param username the unique attribute
	 * @return the unique Do2pProfile for the specified attribute
	 */
	public final Do2pProfile findByUsername(java.lang.String username) {
                Filter filter = createEqualsFilter(COLUMN_NAME_USERNAME, username);
		return findUniqueBy(filter);
	}

	/**
	 * find-key-by method for unique attribute field username
	 * @param username the unique attribute
	 * @return the unique Do2pProfile for the specified attribute
	 */
	public final java.lang.Long findKeyByUsername(java.lang.String username) {
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
	public Do2pProfile persist(		java.lang.Long id, 
		java.lang.String email, 
		java.lang.String secret, 
		java.lang.Long state, 
		java.lang.String username) {

            Do2pProfile domain = null;
            // if primaryKey specified, use it
            if (null != id) {
                    domain = findByPrimaryKey(id);
            }
		
            // create new?
            if (null == domain) {
                    domain = new Do2pProfile();
                    // generate Id?
                    if (null != id) {
                            domain.setId(id);
                    }
                    // fields
                    domain.setEmail(email);
                    domain.setSecret(secret);
                    domain.setState(state);
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
	public Do2pProfile persist(java.lang.String username, 
                java.lang.String email, 
                java.lang.String secret, 
                java.lang.Long state) {
            Do2pProfile domain = findByUsername(username);
            if (null == domain) {
                domain = new Do2pProfile();
                domain.setEmail(email);
                domain.setSecret(secret);
                domain.setState(state);
                domain.setUsername(username);
                persist(domain);
            }
            return domain;
	}


}
