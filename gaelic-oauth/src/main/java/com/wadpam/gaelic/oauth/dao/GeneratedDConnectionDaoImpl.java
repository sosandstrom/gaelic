package com.wadpam.gaelic.oauth.dao;

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
import com.wadpam.gaelic.oauth.domain.DConnection;

/**
 * The DConnection domain-object specific finders and methods go in this POJO.
 * 
 * Generated on 2013-07-11T18:39:40.213+0700.
 * @author mardao DAO generator (net.sf.mardao.plugin.ProcessDomainMojo)
 */
public class GeneratedDConnectionDaoImpl extends TypeDaoImpl<DConnection, java.lang.Long> 
	implements GeneratedDConnectionDao {


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
   public GeneratedDConnectionDaoImpl() {
      super(DConnection.class, java.lang.Long.class);
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
    protected Object getDomainProperty(DConnection domain, String name) {
        Object value;
        // simple key?
        if (COLUMN_NAME_ID.equals(name)) {
            value = domain.getId();
        }
        // parent key?
        else if (COLUMN_NAME_USERKEY.equals(name)) {
            value = domain.getUserKey();
        }
        // fields
        else if (COLUMN_NAME_ACCESSTOKEN.equals(name)) {
            value = domain.getAccessToken();
        }
        else if (COLUMN_NAME_APPARG0.equals(name)) {
            value = domain.getAppArg0();
        }
        else if (COLUMN_NAME_CREATEDBY.equals(name)) {
            value = domain.getCreatedBy();
        }
        else if (COLUMN_NAME_CREATEDDATE.equals(name)) {
            value = domain.getCreatedDate();
        }
        else if (COLUMN_NAME_DISPLAYNAME.equals(name)) {
            value = domain.getDisplayName();
        }
        else if (COLUMN_NAME_EXPIRETIME.equals(name)) {
            value = domain.getExpireTime();
        }
        else if (COLUMN_NAME_IMAGEURL.equals(name)) {
            value = domain.getImageUrl();
        }
        else if (COLUMN_NAME_PROFILEURL.equals(name)) {
            value = domain.getProfileUrl();
        }
        else if (COLUMN_NAME_PROVIDERID.equals(name)) {
            value = domain.getProviderId();
        }
        else if (COLUMN_NAME_PROVIDERUSERID.equals(name)) {
            value = domain.getProviderUserId();
        }
        else if (COLUMN_NAME_REFRESHTOKEN.equals(name)) {
            value = domain.getRefreshToken();
        }
        else if (COLUMN_NAME_SECRET.equals(name)) {
            value = domain.getSecret();
        }
        else if (COLUMN_NAME_UPDATEDBY.equals(name)) {
            value = domain.getUpdatedBy();
        }
        else if (COLUMN_NAME_UPDATEDDATE.equals(name)) {
            value = domain.getUpdatedDate();
        }
        else if (COLUMN_NAME_USERROLES.equals(name)) {
            value = domain.getUserRoles();
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
        // parent key?
        else if (COLUMN_NAME_USERKEY.equals(name)) {
            clazz = java.lang.Object.class;
        }
        // fields
        else if (COLUMN_NAME_ACCESSTOKEN.equals(name)) {
            clazz = java.lang.String.class;
        }
        else if (COLUMN_NAME_APPARG0.equals(name)) {
            clazz = java.lang.String.class;
        }
        else if (COLUMN_NAME_CREATEDBY.equals(name)) {
            clazz = java.lang.String.class;
        }
        else if (COLUMN_NAME_CREATEDDATE.equals(name)) {
            clazz = java.util.Date.class;
        }
        else if (COLUMN_NAME_DISPLAYNAME.equals(name)) {
            clazz = java.lang.String.class;
        }
        else if (COLUMN_NAME_EXPIRETIME.equals(name)) {
            clazz = java.util.Date.class;
        }
        else if (COLUMN_NAME_IMAGEURL.equals(name)) {
            clazz = java.lang.String.class;
        }
        else if (COLUMN_NAME_PROFILEURL.equals(name)) {
            clazz = java.lang.String.class;
        }
        else if (COLUMN_NAME_PROVIDERID.equals(name)) {
            clazz = java.lang.String.class;
        }
        else if (COLUMN_NAME_PROVIDERUSERID.equals(name)) {
            clazz = java.lang.String.class;
        }
        else if (COLUMN_NAME_REFRESHTOKEN.equals(name)) {
            clazz = java.lang.String.class;
        }
        else if (COLUMN_NAME_SECRET.equals(name)) {
            clazz = java.lang.String.class;
        }
        else if (COLUMN_NAME_UPDATEDBY.equals(name)) {
            clazz = java.lang.String.class;
        }
        else if (COLUMN_NAME_UPDATEDDATE.equals(name)) {
            clazz = java.util.Date.class;
        }
        else if (COLUMN_NAME_USERROLES.equals(name)) {
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
    protected void setDomainProperty(final DConnection domain, final String name, final Object value) {
        // simple key?
        if (COLUMN_NAME_ID.equals(name)) {
            domain.setId((java.lang.Long) value);
        }
        // parent key?
        else if (COLUMN_NAME_USERKEY.equals(name)) {
            domain.setUserKey((java.lang.Object) value);
        }
        // fields
        else if (COLUMN_NAME_ACCESSTOKEN.equals(name)) {
            domain.setAccessToken((java.lang.String) value);
        }
        else if (COLUMN_NAME_APPARG0.equals(name)) {
            domain.setAppArg0((java.lang.String) value);
        }
        else if (COLUMN_NAME_CREATEDBY.equals(name)) {
            domain.setCreatedBy((java.lang.String) value);
        }
        else if (COLUMN_NAME_CREATEDDATE.equals(name)) {
            domain.setCreatedDate((java.util.Date) value);
        }
        else if (COLUMN_NAME_DISPLAYNAME.equals(name)) {
            domain.setDisplayName((java.lang.String) value);
        }
        else if (COLUMN_NAME_EXPIRETIME.equals(name)) {
            domain.setExpireTime((java.util.Date) value);
        }
        else if (COLUMN_NAME_IMAGEURL.equals(name)) {
            domain.setImageUrl((java.lang.String) value);
        }
        else if (COLUMN_NAME_PROFILEURL.equals(name)) {
            domain.setProfileUrl((java.lang.String) value);
        }
        else if (COLUMN_NAME_PROVIDERID.equals(name)) {
            domain.setProviderId((java.lang.String) value);
        }
        else if (COLUMN_NAME_PROVIDERUSERID.equals(name)) {
            domain.setProviderUserId((java.lang.String) value);
        }
        else if (COLUMN_NAME_REFRESHTOKEN.equals(name)) {
            domain.setRefreshToken((java.lang.String) value);
        }
        else if (COLUMN_NAME_SECRET.equals(name)) {
            domain.setSecret((java.lang.String) value);
        }
        else if (COLUMN_NAME_UPDATEDBY.equals(name)) {
            domain.setUpdatedBy((java.lang.String) value);
        }
        else if (COLUMN_NAME_UPDATEDDATE.equals(name)) {
            domain.setUpdatedDate((java.util.Date) value);
        }
        else if (COLUMN_NAME_USERROLES.equals(name)) {
            domain.setUserRoles((java.lang.String) value);
        }
        // one-to-ones
        // many-to-ones
        // many-to-manys
        else {
            super.setDomainProperty(domain, name, value);
        }
    }

    @Override
    protected void setDomainStringProperty(final DConnection domain, final String name, final Map<String, String> properties) {
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

    /** Default implementation returns null, overrides for userKey parent */
    public String getParentKeyColumnName() {
        return COLUMN_NAME_USERKEY;
    }

   // ------ END DaoImpl overrides -----------------------------

        public Object getParentKey(DConnection domain) {
            return domain.getUserKey();
        }

        public void setParentKey(DConnection domain, Object userKey) {
            domain.setUserKey((Serializable) userKey);
        }

	// ----------------------- parent finders -------------------------------

	/**
	 * query-by method for parent field userKey
	 * @param userKey the specified attribute
	 * @return an Iterable of DConnections for the specified parent
	 */
	public final Iterable<DConnection> queryByUserKey(Object userKey) {
            return queryIterable(false, 0, -1, userKey, null, null, false, null, false);
	}
	
	/**
	 * query-key-by method for parent field userKey
	 * @param userKey the parent
	 * @return an Iterable of keys to the DConnections with the specified parent
	 */
	public final Iterable<java.lang.Long> queryKeysByUserKey(Object userKey) {
            return queryIterableKeys(0, -1, userKey, null, null, false, null, false);
	}

	/**
	 * query-page-by method for parent field userKey
	 * @param userKey the specified parent
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DConnections for the specified userKey
	 */
	public final CursorPage<DConnection> queryPageByUserKey(java.lang.Object userKey,
            int pageSize, String cursorString) {
            return queryPage(false, pageSize, userKey, null, null, false, null, false, cursorString);
        }


        /**
         * @return the simple key for specified DConnection domain object
         */
        public Long getSimpleKey(DConnection domain) {
            if (null == domain) {
                return null;
            }
            return domain.getId();
        }

        /**
         * @return the simple key for specified DConnection domain object
         */
        public void setSimpleKey(DConnection domain, Long id) {
            domain.setId(id);
        }

        public String getCreatedByColumnName() {
            return COLUMN_NAME_CREATEDBY;
        }

        public String getCreatedBy(DConnection domain) {
            if (null == domain) {
                return null;
            }
            return domain.getCreatedBy();
        }

        public void _setCreatedBy(DConnection domain, String creator) {
            domain.setCreatedBy(creator);
        }

        public String getUpdatedByColumnName() {
            return COLUMN_NAME_UPDATEDBY;
        }

        public String getUpdatedBy(DConnection domain) {
            if (null == domain) {
                return null;
            }
            return domain.getUpdatedBy();
        }

        public void _setUpdatedBy(DConnection domain, String updator) {
            domain.setUpdatedBy(updator);
        }

        public String getCreatedDateColumnName() {
            return COLUMN_NAME_CREATEDDATE;
        }

        public Date getCreatedDate(DConnection domain) {
            if (null == domain) {
                return null;
            }
            return domain.getCreatedDate();
        }

        public void _setCreatedDate(DConnection domain, Date date) {
            domain.setCreatedDate(date);
        }

        public String getUpdatedDateColumnName() {
            return COLUMN_NAME_UPDATEDDATE;
        }

        public Date getUpdatedDate(DConnection domain) {
            if (null == domain) {
                return null;
            }
            return domain.getUpdatedDate();
        }

        public void _setUpdatedDate(DConnection domain, Date date) {
            domain.setUpdatedDate(date);
        }

	// ----------------------- field finders -------------------------------
	/**
	 * find-by method for unique attribute field accessToken
	 * @param accessToken the unique attribute
	 * @return the unique DConnection for the specified attribute
	 */
	public final DConnection findByAccessToken(java.lang.String accessToken) {
                Filter filter = createEqualsFilter(COLUMN_NAME_ACCESSTOKEN, accessToken);
		return findUniqueBy(filter);
	}

	/**
	 * find-key-by method for unique attribute field accessToken
	 * @param accessToken the unique attribute
	 * @return the unique DConnection for the specified attribute
	 */
	public final java.lang.Long findKeyByAccessToken(java.lang.String accessToken) {
                Filter filter = createEqualsFilter(COLUMN_NAME_ACCESSTOKEN, accessToken);
		return findUniqueKeyBy(filter);
	}
	 
	/**
         * {@inheritDoc}
	 */
	public final Iterable<DConnection> queryByAppArg0(java.lang.String appArg0) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_APPARG0, appArg0);
            return queryIterable(false, 0, -1, null, null, null, false, null, false, filter);
	}
	
	/**
	 * query-key-by method for attribute field appArg0
	 * @param appArg0 the specified attribute
	 * @return an Iterable of keys to the DConnections with the specified attribute
	 */
	public final Iterable<java.lang.Long> queryKeysByAppArg0(java.lang.String appArg0) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_APPARG0, appArg0);
            return queryIterableKeys(0, -1, null, null, null, false, null, false, filter);
	}

	/**
	 * query-page-by method for field appArg0
	 * @param appArg0 the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DConnections for the specified appArg0
	 */
	public final CursorPage<DConnection> queryPageByAppArg0(java.lang.String appArg0,
            int pageSize, String cursorString) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_APPARG0, appArg0);
            return queryPage(false, pageSize, null, null, null, false, null, false, cursorString, filter);
        }

	 
	/**
         * {@inheritDoc}
	 */
	public final Iterable<DConnection> queryByCreatedBy(java.lang.String createdBy) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_CREATEDBY, createdBy);
            return queryIterable(false, 0, -1, null, null, null, false, null, false, filter);
	}
	
	/**
	 * query-key-by method for attribute field createdBy
	 * @param createdBy the specified attribute
	 * @return an Iterable of keys to the DConnections with the specified attribute
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
	 * @return a Page of DConnections for the specified createdBy
	 */
	public final CursorPage<DConnection> queryPageByCreatedBy(java.lang.String createdBy,
            int pageSize, String cursorString) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_CREATEDBY, createdBy);
            return queryPage(false, pageSize, null, null, null, false, null, false, cursorString, filter);
        }

	 
	/**
         * {@inheritDoc}
	 */
	public final Iterable<DConnection> queryByCreatedDate(java.util.Date createdDate) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_CREATEDDATE, createdDate);
            return queryIterable(false, 0, -1, null, null, null, false, null, false, filter);
	}
	
	/**
	 * query-key-by method for attribute field createdDate
	 * @param createdDate the specified attribute
	 * @return an Iterable of keys to the DConnections with the specified attribute
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
	 * @return a Page of DConnections for the specified createdDate
	 */
	public final CursorPage<DConnection> queryPageByCreatedDate(java.util.Date createdDate,
            int pageSize, String cursorString) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_CREATEDDATE, createdDate);
            return queryPage(false, pageSize, null, null, null, false, null, false, cursorString, filter);
        }

	 
	/**
         * {@inheritDoc}
	 */
	public final Iterable<DConnection> queryByDisplayName(java.lang.String displayName) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_DISPLAYNAME, displayName);
            return queryIterable(false, 0, -1, null, null, null, false, null, false, filter);
	}
	
	/**
	 * query-key-by method for attribute field displayName
	 * @param displayName the specified attribute
	 * @return an Iterable of keys to the DConnections with the specified attribute
	 */
	public final Iterable<java.lang.Long> queryKeysByDisplayName(java.lang.String displayName) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_DISPLAYNAME, displayName);
            return queryIterableKeys(0, -1, null, null, null, false, null, false, filter);
	}

	/**
	 * query-page-by method for field displayName
	 * @param displayName the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DConnections for the specified displayName
	 */
	public final CursorPage<DConnection> queryPageByDisplayName(java.lang.String displayName,
            int pageSize, String cursorString) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_DISPLAYNAME, displayName);
            return queryPage(false, pageSize, null, null, null, false, null, false, cursorString, filter);
        }

	 
	/**
         * {@inheritDoc}
	 */
	public final Iterable<DConnection> queryByExpireTime(java.util.Date expireTime) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_EXPIRETIME, expireTime);
            return queryIterable(false, 0, -1, null, null, null, false, null, false, filter);
	}
	
	/**
	 * query-key-by method for attribute field expireTime
	 * @param expireTime the specified attribute
	 * @return an Iterable of keys to the DConnections with the specified attribute
	 */
	public final Iterable<java.lang.Long> queryKeysByExpireTime(java.util.Date expireTime) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_EXPIRETIME, expireTime);
            return queryIterableKeys(0, -1, null, null, null, false, null, false, filter);
	}

	/**
	 * query-page-by method for field expireTime
	 * @param expireTime the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DConnections for the specified expireTime
	 */
	public final CursorPage<DConnection> queryPageByExpireTime(java.util.Date expireTime,
            int pageSize, String cursorString) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_EXPIRETIME, expireTime);
            return queryPage(false, pageSize, null, null, null, false, null, false, cursorString, filter);
        }

	 
	/**
         * {@inheritDoc}
	 */
	public final Iterable<DConnection> queryByImageUrl(java.lang.String imageUrl) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_IMAGEURL, imageUrl);
            return queryIterable(false, 0, -1, null, null, null, false, null, false, filter);
	}
	
	/**
	 * query-key-by method for attribute field imageUrl
	 * @param imageUrl the specified attribute
	 * @return an Iterable of keys to the DConnections with the specified attribute
	 */
	public final Iterable<java.lang.Long> queryKeysByImageUrl(java.lang.String imageUrl) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_IMAGEURL, imageUrl);
            return queryIterableKeys(0, -1, null, null, null, false, null, false, filter);
	}

	/**
	 * query-page-by method for field imageUrl
	 * @param imageUrl the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DConnections for the specified imageUrl
	 */
	public final CursorPage<DConnection> queryPageByImageUrl(java.lang.String imageUrl,
            int pageSize, String cursorString) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_IMAGEURL, imageUrl);
            return queryPage(false, pageSize, null, null, null, false, null, false, cursorString, filter);
        }

	 
	/**
         * {@inheritDoc}
	 */
	public final Iterable<DConnection> queryByProfileUrl(java.lang.String profileUrl) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_PROFILEURL, profileUrl);
            return queryIterable(false, 0, -1, null, null, null, false, null, false, filter);
	}
	
	/**
	 * query-key-by method for attribute field profileUrl
	 * @param profileUrl the specified attribute
	 * @return an Iterable of keys to the DConnections with the specified attribute
	 */
	public final Iterable<java.lang.Long> queryKeysByProfileUrl(java.lang.String profileUrl) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_PROFILEURL, profileUrl);
            return queryIterableKeys(0, -1, null, null, null, false, null, false, filter);
	}

	/**
	 * query-page-by method for field profileUrl
	 * @param profileUrl the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DConnections for the specified profileUrl
	 */
	public final CursorPage<DConnection> queryPageByProfileUrl(java.lang.String profileUrl,
            int pageSize, String cursorString) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_PROFILEURL, profileUrl);
            return queryPage(false, pageSize, null, null, null, false, null, false, cursorString, filter);
        }

	 
	/**
         * {@inheritDoc}
	 */
	public final Iterable<DConnection> queryByProviderId(java.lang.String providerId) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_PROVIDERID, providerId);
            return queryIterable(false, 0, -1, null, null, null, false, null, false, filter);
	}
	
	/**
	 * query-key-by method for attribute field providerId
	 * @param providerId the specified attribute
	 * @return an Iterable of keys to the DConnections with the specified attribute
	 */
	public final Iterable<java.lang.Long> queryKeysByProviderId(java.lang.String providerId) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_PROVIDERID, providerId);
            return queryIterableKeys(0, -1, null, null, null, false, null, false, filter);
	}

	/**
	 * query-page-by method for field providerId
	 * @param providerId the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DConnections for the specified providerId
	 */
	public final CursorPage<DConnection> queryPageByProviderId(java.lang.String providerId,
            int pageSize, String cursorString) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_PROVIDERID, providerId);
            return queryPage(false, pageSize, null, null, null, false, null, false, cursorString, filter);
        }

	 
	/**
         * {@inheritDoc}
	 */
	public final Iterable<DConnection> queryByProviderUserId(java.lang.String providerUserId) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_PROVIDERUSERID, providerUserId);
            return queryIterable(false, 0, -1, null, null, null, false, null, false, filter);
	}
	
	/**
	 * query-key-by method for attribute field providerUserId
	 * @param providerUserId the specified attribute
	 * @return an Iterable of keys to the DConnections with the specified attribute
	 */
	public final Iterable<java.lang.Long> queryKeysByProviderUserId(java.lang.String providerUserId) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_PROVIDERUSERID, providerUserId);
            return queryIterableKeys(0, -1, null, null, null, false, null, false, filter);
	}

	/**
	 * query-page-by method for field providerUserId
	 * @param providerUserId the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DConnections for the specified providerUserId
	 */
	public final CursorPage<DConnection> queryPageByProviderUserId(java.lang.String providerUserId,
            int pageSize, String cursorString) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_PROVIDERUSERID, providerUserId);
            return queryPage(false, pageSize, null, null, null, false, null, false, cursorString, filter);
        }

	 
	/**
	 * find-by method for unique attribute field refreshToken
	 * @param refreshToken the unique attribute
	 * @return the unique DConnection for the specified attribute
	 */
	public final DConnection findByRefreshToken(java.lang.String refreshToken) {
                Filter filter = createEqualsFilter(COLUMN_NAME_REFRESHTOKEN, refreshToken);
		return findUniqueBy(filter);
	}

	/**
	 * find-key-by method for unique attribute field refreshToken
	 * @param refreshToken the unique attribute
	 * @return the unique DConnection for the specified attribute
	 */
	public final java.lang.Long findKeyByRefreshToken(java.lang.String refreshToken) {
                Filter filter = createEqualsFilter(COLUMN_NAME_REFRESHTOKEN, refreshToken);
		return findUniqueKeyBy(filter);
	}
	 
	/**
         * {@inheritDoc}
	 */
	public final Iterable<DConnection> queryBySecret(java.lang.String secret) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_SECRET, secret);
            return queryIterable(false, 0, -1, null, null, null, false, null, false, filter);
	}
	
	/**
	 * query-key-by method for attribute field secret
	 * @param secret the specified attribute
	 * @return an Iterable of keys to the DConnections with the specified attribute
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
	 * @return a Page of DConnections for the specified secret
	 */
	public final CursorPage<DConnection> queryPageBySecret(java.lang.String secret,
            int pageSize, String cursorString) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_SECRET, secret);
            return queryPage(false, pageSize, null, null, null, false, null, false, cursorString, filter);
        }

	 
	/**
         * {@inheritDoc}
	 */
	public final Iterable<DConnection> queryByUpdatedBy(java.lang.String updatedBy) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_UPDATEDBY, updatedBy);
            return queryIterable(false, 0, -1, null, null, null, false, null, false, filter);
	}
	
	/**
	 * query-key-by method for attribute field updatedBy
	 * @param updatedBy the specified attribute
	 * @return an Iterable of keys to the DConnections with the specified attribute
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
	 * @return a Page of DConnections for the specified updatedBy
	 */
	public final CursorPage<DConnection> queryPageByUpdatedBy(java.lang.String updatedBy,
            int pageSize, String cursorString) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_UPDATEDBY, updatedBy);
            return queryPage(false, pageSize, null, null, null, false, null, false, cursorString, filter);
        }

	 
	/**
         * {@inheritDoc}
	 */
	public final Iterable<DConnection> queryByUpdatedDate(java.util.Date updatedDate) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_UPDATEDDATE, updatedDate);
            return queryIterable(false, 0, -1, null, null, null, false, null, false, filter);
	}
	
	/**
	 * query-key-by method for attribute field updatedDate
	 * @param updatedDate the specified attribute
	 * @return an Iterable of keys to the DConnections with the specified attribute
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
	 * @return a Page of DConnections for the specified updatedDate
	 */
	public final CursorPage<DConnection> queryPageByUpdatedDate(java.util.Date updatedDate,
            int pageSize, String cursorString) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_UPDATEDDATE, updatedDate);
            return queryPage(false, pageSize, null, null, null, false, null, false, cursorString, filter);
        }

	 
	/**
         * {@inheritDoc}
	 */
	public final Iterable<DConnection> queryByUserRoles(java.lang.String userRoles) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_USERROLES, userRoles);
            return queryIterable(false, 0, -1, null, null, null, false, null, false, filter);
	}
	
	/**
	 * query-key-by method for attribute field userRoles
	 * @param userRoles the specified attribute
	 * @return an Iterable of keys to the DConnections with the specified attribute
	 */
	public final Iterable<java.lang.Long> queryKeysByUserRoles(java.lang.String userRoles) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_USERROLES, userRoles);
            return queryIterableKeys(0, -1, null, null, null, false, null, false, filter);
	}

	/**
	 * query-page-by method for field userRoles
	 * @param userRoles the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DConnections for the specified userRoles
	 */
	public final CursorPage<DConnection> queryPageByUserRoles(java.lang.String userRoles,
            int pageSize, String cursorString) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_USERROLES, userRoles);
            return queryPage(false, pageSize, null, null, null, false, null, false, cursorString, filter);
        }

	 
	// ----------------------- one-to-one finders -------------------------

	// ----------------------- many-to-one finders -------------------------

	// ----------------------- many-to-many finders -------------------------

	// ----------------------- uniqueFields finders -------------------------

	// ----------------------- populate / persist method -------------------------

	/**
	 * Persist an entity given all attributes
	 */
	public DConnection persist(Object userKey,  	
		java.lang.Long id, 
		java.lang.String accessToken, 
		java.lang.String appArg0, 
		java.lang.String displayName, 
		java.util.Date expireTime, 
		java.lang.String imageUrl, 
		java.lang.String profileUrl, 
		java.lang.String providerId, 
		java.lang.String providerUserId, 
		java.lang.String refreshToken, 
		java.lang.String secret, 
		java.lang.String userRoles) {

            DConnection domain = null;
            // if primaryKey specified, use it
            if (null != id) {
                    domain = findByPrimaryKey(userKey, id);
            }
		
            // create new?
            if (null == domain) {
                    domain = new DConnection();
                    // set parent
                    domain.setUserKey((java.lang.Object) userKey);
                    // generate Id?
                    if (null != id) {
                            domain.setId(id);
                    }
                    // fields
                    domain.setAccessToken(accessToken);
                    domain.setAppArg0(appArg0);
                    domain.setDisplayName(displayName);
                    domain.setExpireTime(expireTime);
                    domain.setImageUrl(imageUrl);
                    domain.setProfileUrl(profileUrl);
                    domain.setProviderId(providerId);
                    domain.setProviderUserId(providerUserId);
                    domain.setRefreshToken(refreshToken);
                    domain.setSecret(secret);
                    domain.setUserRoles(userRoles);
                    // one-to-ones
                    // many-to-ones
			
                    persist(domain);
            }
            return domain;
	}


	/**
	 * Persists an entity unless it already exists
	 */
	public DConnection persist(java.lang.String accessToken, 
                java.lang.String appArg0, 
                java.lang.String displayName, 
                java.util.Date expireTime, 
                java.lang.String imageUrl, 
                java.lang.String profileUrl, 
                java.lang.String providerId, 
                java.lang.String providerUserId, 
                java.lang.String refreshToken, 
                java.lang.String secret, 
                java.lang.String userRoles) {
            DConnection domain = findByAccessToken(accessToken);
            if (null == domain) {
                domain = new DConnection();
                domain.setAccessToken(accessToken);
                domain.setAppArg0(appArg0);
                domain.setDisplayName(displayName);
                domain.setExpireTime(expireTime);
                domain.setImageUrl(imageUrl);
                domain.setProfileUrl(profileUrl);
                domain.setProviderId(providerId);
                domain.setProviderUserId(providerUserId);
                domain.setRefreshToken(refreshToken);
                domain.setSecret(secret);
                domain.setUserRoles(userRoles);
                persist(domain);
            }
            return domain;
	}


}
