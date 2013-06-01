#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dao;

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
import ${package}.domain.DProfile;

/**
 * The DProfile domain-object specific finders and methods go in this POJO.
 * 
 * Generated on 2013-06-01T09:47:45.776+0700.
 * @author mardao DAO generator (net.sf.mardao.plugin.ProcessDomainMojo)
 */
public class GeneratedDProfileDaoImpl extends TypeDaoImpl<DProfile, java.lang.Long> 
	implements GeneratedDProfileDao {


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
   public GeneratedDProfileDaoImpl() {
      super(DProfile.class, java.lang.Long.class);
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
    protected Object getDomainProperty(DProfile domain, String name) {
        Object value;
        // simple key?
        if (COLUMN_NAME_ID.equals(name)) {
            value = domain.getId();
        }
        // fields
        else if (COLUMN_NAME_AB0.equals(name)) {
            value = domain.getAb0();
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
        else if (COLUMN_NAME_EMAIL.equals(name)) {
            value = domain.getEmail();
        }
        else if (COLUMN_NAME_PHONENUMBER.equals(name)) {
            value = domain.getPhoneNumber();
        }
        else if (COLUMN_NAME_PROFILELINK.equals(name)) {
            value = domain.getProfileLink();
        }
        else if (COLUMN_NAME_ROLES.equals(name)) {
            value = domain.getRoles();
        }
        else if (COLUMN_NAME_THUMBNAILURL.equals(name)) {
            value = domain.getThumbnailUrl();
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
        else if (COLUMN_NAME_AB0.equals(name)) {
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
        else if (COLUMN_NAME_EMAIL.equals(name)) {
            clazz = java.lang.String.class;
        }
        else if (COLUMN_NAME_PHONENUMBER.equals(name)) {
            clazz = java.lang.String.class;
        }
        else if (COLUMN_NAME_PROFILELINK.equals(name)) {
            clazz = java.lang.String.class;
        }
        else if (COLUMN_NAME_ROLES.equals(name)) {
            clazz = java.util.Collection.class;
        }
        else if (COLUMN_NAME_THUMBNAILURL.equals(name)) {
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
    protected void setDomainProperty(final DProfile domain, final String name, final Object value) {
        // simple key?
        if (COLUMN_NAME_ID.equals(name)) {
            domain.setId((java.lang.Long) value);
        }
        // fields
        else if (COLUMN_NAME_AB0.equals(name)) {
            domain.setAb0((java.lang.String) value);
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
        else if (COLUMN_NAME_EMAIL.equals(name)) {
            domain.setEmail((java.lang.String) value);
        }
        else if (COLUMN_NAME_PHONENUMBER.equals(name)) {
            domain.setPhoneNumber((java.lang.String) value);
        }
        else if (COLUMN_NAME_PROFILELINK.equals(name)) {
            domain.setProfileLink((java.lang.String) value);
        }
        else if (COLUMN_NAME_ROLES.equals(name)) {
            domain.setRoles((java.util.Collection) value);
        }
        else if (COLUMN_NAME_THUMBNAILURL.equals(name)) {
            domain.setThumbnailUrl((java.lang.String) value);
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
    protected void setDomainStringProperty(final DProfile domain, final String name, final Map<String, String> properties) {
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

        // DProfile has no parent

        /**
         * @return the simple key for specified DProfile domain object
         */
        public Long getSimpleKey(DProfile domain) {
            if (null == domain) {
                return null;
            }
            return domain.getId();
        }

        /**
         * @return the simple key for specified DProfile domain object
         */
        public void setSimpleKey(DProfile domain, Long id) {
            domain.setId(id);
        }

        public String getCreatedByColumnName() {
            return COLUMN_NAME_CREATEDBY;
        }

        public String getCreatedBy(DProfile domain) {
            if (null == domain) {
                return null;
            }
            return domain.getCreatedBy();
        }

        public void _setCreatedBy(DProfile domain, String creator) {
            domain.setCreatedBy(creator);
        }

        public String getUpdatedByColumnName() {
            return COLUMN_NAME_UPDATEDBY;
        }

        public String getUpdatedBy(DProfile domain) {
            if (null == domain) {
                return null;
            }
            return domain.getUpdatedBy();
        }

        public void _setUpdatedBy(DProfile domain, String updator) {
            domain.setUpdatedBy(updator);
        }

        public String getCreatedDateColumnName() {
            return COLUMN_NAME_CREATEDDATE;
        }

        public Date getCreatedDate(DProfile domain) {
            if (null == domain) {
                return null;
            }
            return domain.getCreatedDate();
        }

        public void _setCreatedDate(DProfile domain, Date date) {
            domain.setCreatedDate(date);
        }

        public String getUpdatedDateColumnName() {
            return COLUMN_NAME_UPDATEDDATE;
        }

        public Date getUpdatedDate(DProfile domain) {
            if (null == domain) {
                return null;
            }
            return domain.getUpdatedDate();
        }

        public void _setUpdatedDate(DProfile domain, Date date) {
            domain.setUpdatedDate(date);
        }

	// ----------------------- field finders -------------------------------
	/**
         * {@inheritDoc}
	 */
	public final Iterable<DProfile> queryByAb0(java.lang.String ab0) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_AB0, ab0);
            return queryIterable(false, 0, -1, null, null, null, false, null, false, filter);
	}
	
	/**
	 * query-key-by method for attribute field ab0
	 * @param ab0 the specified attribute
	 * @return an Iterable of keys to the DProfiles with the specified attribute
	 */
	public final Iterable<java.lang.Long> queryKeysByAb0(java.lang.String ab0) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_AB0, ab0);
            return queryIterableKeys(0, -1, null, null, null, false, null, false, filter);
	}

	/**
	 * query-page-by method for field ab0
	 * @param ab0 the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DProfiles for the specified ab0
	 */
	public final CursorPage<DProfile, java.lang.Long> queryPageByAb0(java.lang.String ab0,
            int pageSize, String cursorString) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_AB0, ab0);
            return queryPage(false, pageSize, null, null, null, false, null, false, cursorString, filter);
        }

	 
	/**
         * {@inheritDoc}
	 */
	public final Iterable<DProfile> queryByCreatedBy(java.lang.String createdBy) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_CREATEDBY, createdBy);
            return queryIterable(false, 0, -1, null, null, null, false, null, false, filter);
	}
	
	/**
	 * query-key-by method for attribute field createdBy
	 * @param createdBy the specified attribute
	 * @return an Iterable of keys to the DProfiles with the specified attribute
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
	 * @return a Page of DProfiles for the specified createdBy
	 */
	public final CursorPage<DProfile, java.lang.Long> queryPageByCreatedBy(java.lang.String createdBy,
            int pageSize, String cursorString) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_CREATEDBY, createdBy);
            return queryPage(false, pageSize, null, null, null, false, null, false, cursorString, filter);
        }

	 
	/**
         * {@inheritDoc}
	 */
	public final Iterable<DProfile> queryByCreatedDate(java.util.Date createdDate) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_CREATEDDATE, createdDate);
            return queryIterable(false, 0, -1, null, null, null, false, null, false, filter);
	}
	
	/**
	 * query-key-by method for attribute field createdDate
	 * @param createdDate the specified attribute
	 * @return an Iterable of keys to the DProfiles with the specified attribute
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
	 * @return a Page of DProfiles for the specified createdDate
	 */
	public final CursorPage<DProfile, java.lang.Long> queryPageByCreatedDate(java.util.Date createdDate,
            int pageSize, String cursorString) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_CREATEDDATE, createdDate);
            return queryPage(false, pageSize, null, null, null, false, null, false, cursorString, filter);
        }

	 
	/**
         * {@inheritDoc}
	 */
	public final Iterable<DProfile> queryByDisplayName(java.lang.String displayName) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_DISPLAYNAME, displayName);
            return queryIterable(false, 0, -1, null, null, null, false, null, false, filter);
	}
	
	/**
	 * query-key-by method for attribute field displayName
	 * @param displayName the specified attribute
	 * @return an Iterable of keys to the DProfiles with the specified attribute
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
	 * @return a Page of DProfiles for the specified displayName
	 */
	public final CursorPage<DProfile, java.lang.Long> queryPageByDisplayName(java.lang.String displayName,
            int pageSize, String cursorString) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_DISPLAYNAME, displayName);
            return queryPage(false, pageSize, null, null, null, false, null, false, cursorString, filter);
        }

	 
	/**
	 * find-by method for unique attribute field email
	 * @param email the unique attribute
	 * @return the unique DProfile for the specified attribute
	 */
	public final DProfile findByEmail(java.lang.String email) {
                Filter filter = createEqualsFilter(COLUMN_NAME_EMAIL, email);
		return findUniqueBy(filter);
	}

	/**
	 * find-key-by method for unique attribute field email
	 * @param email the unique attribute
	 * @return the unique DProfile for the specified attribute
	 */
	public final java.lang.Long findKeyByEmail(java.lang.String email) {
                Filter filter = createEqualsFilter(COLUMN_NAME_EMAIL, email);
		return findUniqueKeyBy(filter);
	}
	 
	/**
         * {@inheritDoc}
	 */
	public final Iterable<DProfile> queryByPhoneNumber(java.lang.String phoneNumber) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_PHONENUMBER, phoneNumber);
            return queryIterable(false, 0, -1, null, null, null, false, null, false, filter);
	}
	
	/**
	 * query-key-by method for attribute field phoneNumber
	 * @param phoneNumber the specified attribute
	 * @return an Iterable of keys to the DProfiles with the specified attribute
	 */
	public final Iterable<java.lang.Long> queryKeysByPhoneNumber(java.lang.String phoneNumber) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_PHONENUMBER, phoneNumber);
            return queryIterableKeys(0, -1, null, null, null, false, null, false, filter);
	}

	/**
	 * query-page-by method for field phoneNumber
	 * @param phoneNumber the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DProfiles for the specified phoneNumber
	 */
	public final CursorPage<DProfile, java.lang.Long> queryPageByPhoneNumber(java.lang.String phoneNumber,
            int pageSize, String cursorString) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_PHONENUMBER, phoneNumber);
            return queryPage(false, pageSize, null, null, null, false, null, false, cursorString, filter);
        }

	 
	/**
         * {@inheritDoc}
	 */
	public final Iterable<DProfile> queryByProfileLink(java.lang.String profileLink) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_PROFILELINK, profileLink);
            return queryIterable(false, 0, -1, null, null, null, false, null, false, filter);
	}
	
	/**
	 * query-key-by method for attribute field profileLink
	 * @param profileLink the specified attribute
	 * @return an Iterable of keys to the DProfiles with the specified attribute
	 */
	public final Iterable<java.lang.Long> queryKeysByProfileLink(java.lang.String profileLink) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_PROFILELINK, profileLink);
            return queryIterableKeys(0, -1, null, null, null, false, null, false, filter);
	}

	/**
	 * query-page-by method for field profileLink
	 * @param profileLink the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DProfiles for the specified profileLink
	 */
	public final CursorPage<DProfile, java.lang.Long> queryPageByProfileLink(java.lang.String profileLink,
            int pageSize, String cursorString) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_PROFILELINK, profileLink);
            return queryPage(false, pageSize, null, null, null, false, null, false, cursorString, filter);
        }

	 
	/**
         * {@inheritDoc}
	 */
	public final Iterable<DProfile> queryByRoles(java.lang.Object roles) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_ROLES, roles);
            return queryIterable(false, 0, -1, null, null, null, false, null, false, filter);
	}
	
	/**
	 * query-key-by method for attribute field roles
	 * @param roles the specified attribute
	 * @return an Iterable of keys to the DProfiles with the specified attribute
	 */
	public final Iterable<java.lang.Long> queryKeysByRoles(java.lang.Object roles) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_ROLES, roles);
            return queryIterableKeys(0, -1, null, null, null, false, null, false, filter);
	}

	/**
	 * query-page-by method for field roles
	 * @param roles the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DProfiles for the specified roles
	 */
	public final CursorPage<DProfile, java.lang.Long> queryPageByRoles(java.lang.Object roles,
            int pageSize, String cursorString) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_ROLES, roles);
            return queryPage(false, pageSize, null, null, null, false, null, false, cursorString, filter);
        }

	 
	/**
         * {@inheritDoc}
	 */
	public final Iterable<DProfile> queryByThumbnailUrl(java.lang.String thumbnailUrl) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_THUMBNAILURL, thumbnailUrl);
            return queryIterable(false, 0, -1, null, null, null, false, null, false, filter);
	}
	
	/**
	 * query-key-by method for attribute field thumbnailUrl
	 * @param thumbnailUrl the specified attribute
	 * @return an Iterable of keys to the DProfiles with the specified attribute
	 */
	public final Iterable<java.lang.Long> queryKeysByThumbnailUrl(java.lang.String thumbnailUrl) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_THUMBNAILURL, thumbnailUrl);
            return queryIterableKeys(0, -1, null, null, null, false, null, false, filter);
	}

	/**
	 * query-page-by method for field thumbnailUrl
	 * @param thumbnailUrl the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DProfiles for the specified thumbnailUrl
	 */
	public final CursorPage<DProfile, java.lang.Long> queryPageByThumbnailUrl(java.lang.String thumbnailUrl,
            int pageSize, String cursorString) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_THUMBNAILURL, thumbnailUrl);
            return queryPage(false, pageSize, null, null, null, false, null, false, cursorString, filter);
        }

	 
	/**
         * {@inheritDoc}
	 */
	public final Iterable<DProfile> queryByUpdatedBy(java.lang.String updatedBy) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_UPDATEDBY, updatedBy);
            return queryIterable(false, 0, -1, null, null, null, false, null, false, filter);
	}
	
	/**
	 * query-key-by method for attribute field updatedBy
	 * @param updatedBy the specified attribute
	 * @return an Iterable of keys to the DProfiles with the specified attribute
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
	 * @return a Page of DProfiles for the specified updatedBy
	 */
	public final CursorPage<DProfile, java.lang.Long> queryPageByUpdatedBy(java.lang.String updatedBy,
            int pageSize, String cursorString) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_UPDATEDBY, updatedBy);
            return queryPage(false, pageSize, null, null, null, false, null, false, cursorString, filter);
        }

	 
	/**
         * {@inheritDoc}
	 */
	public final Iterable<DProfile> queryByUpdatedDate(java.util.Date updatedDate) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_UPDATEDDATE, updatedDate);
            return queryIterable(false, 0, -1, null, null, null, false, null, false, filter);
	}
	
	/**
	 * query-key-by method for attribute field updatedDate
	 * @param updatedDate the specified attribute
	 * @return an Iterable of keys to the DProfiles with the specified attribute
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
	 * @return a Page of DProfiles for the specified updatedDate
	 */
	public final CursorPage<DProfile, java.lang.Long> queryPageByUpdatedDate(java.util.Date updatedDate,
            int pageSize, String cursorString) {
            final Filter filter = createEqualsFilter(COLUMN_NAME_UPDATEDDATE, updatedDate);
            return queryPage(false, pageSize, null, null, null, false, null, false, cursorString, filter);
        }

	 
	/**
	 * find-by method for unique attribute field username
	 * @param username the unique attribute
	 * @return the unique DProfile for the specified attribute
	 */
	public final DProfile findByUsername(java.lang.String username) {
                Filter filter = createEqualsFilter(COLUMN_NAME_USERNAME, username);
		return findUniqueBy(filter);
	}

	/**
	 * find-key-by method for unique attribute field username
	 * @param username the unique attribute
	 * @return the unique DProfile for the specified attribute
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
	public DProfile persist(		java.lang.Long id, 
		java.lang.String ab0, 
		java.lang.String displayName, 
		java.lang.String email, 
		java.lang.String phoneNumber, 
		java.lang.String profileLink, 
		java.util.Collection roles, 
		java.lang.String thumbnailUrl, 
		java.lang.String username) {

            DProfile domain = null;
            // if primaryKey specified, use it
            if (null != id) {
                    domain = findByPrimaryKey(id);
            }
		
            // create new?
            if (null == domain) {
                    domain = new DProfile();
                    // generate Id?
                    if (null != id) {
                            domain.setId(id);
                    }
                    // fields
                    domain.setAb0(ab0);
                    domain.setDisplayName(displayName);
                    domain.setEmail(email);
                    domain.setPhoneNumber(phoneNumber);
                    domain.setProfileLink(profileLink);
                    domain.setRoles(roles);
                    domain.setThumbnailUrl(thumbnailUrl);
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
	public DProfile persist(java.lang.String email, 
                java.lang.String ab0, 
                java.lang.String displayName, 
                java.lang.String phoneNumber, 
                java.lang.String profileLink, 
                java.util.Collection roles, 
                java.lang.String thumbnailUrl, 
                java.lang.String username) {
            DProfile domain = findByEmail(email);
            if (null == domain) {
                domain = new DProfile();
                domain.setAb0(ab0);
                domain.setDisplayName(displayName);
                domain.setEmail(email);
                domain.setPhoneNumber(phoneNumber);
                domain.setProfileLink(profileLink);
                domain.setRoles(roles);
                domain.setThumbnailUrl(thumbnailUrl);
                domain.setUsername(username);
                persist(domain);
            }
            return domain;
	}


}
