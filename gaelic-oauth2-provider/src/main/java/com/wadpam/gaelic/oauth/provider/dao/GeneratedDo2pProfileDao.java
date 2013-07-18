package com.wadpam.gaelic.oauth.provider.dao;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import net.sf.mardao.core.CursorPage;
import net.sf.mardao.core.dao.Dao;
import com.wadpam.gaelic.oauth.provider.domain.Do2pProfile;
import net.sf.mardao.core.geo.DLocation;

/**
 * DAO interface with finder methods for Do2pProfile entities.
 *
 * Generated on 2013-07-18T12:02:44.205+0700.
 * @author mardao DAO generator (net.sf.mardao.plugin.ProcessDomainMojo)
 */
public interface GeneratedDo2pProfileDao extends Dao<Do2pProfile, java.lang.Long> {

	/** Column name for primary key attribute is "id" */
	static final String COLUMN_NAME_ID = "id";


	/** Column name for field createdBy is "createdBy" */
	static final String COLUMN_NAME_CREATEDBY = "createdBy";
	/** Column name for field createdDate is "createdDate" */
	static final String COLUMN_NAME_CREATEDDATE = "createdDate";
	/** Column name for field email is "email" */
	static final String COLUMN_NAME_EMAIL = "email";
	/** Column name for field secret is "secret" */
	static final String COLUMN_NAME_SECRET = "secret";
	/** Column name for field state is "state" */
	static final String COLUMN_NAME_STATE = "state";
	/** Column name for field updatedBy is "updatedBy" */
	static final String COLUMN_NAME_UPDATEDBY = "updatedBy";
	/** Column name for field updatedDate is "updatedDate" */
	static final String COLUMN_NAME_UPDATEDDATE = "updatedDate";
	/** Column name for field username is "username" */
	static final String COLUMN_NAME_USERNAME = "username";

	/** The list of attribute names */
	static final List<String> COLUMN_NAMES = Arrays.asList(		COLUMN_NAME_CREATEDBY,
		COLUMN_NAME_CREATEDDATE,
		COLUMN_NAME_EMAIL,
		COLUMN_NAME_SECRET,
		COLUMN_NAME_STATE,
		COLUMN_NAME_UPDATEDBY,
		COLUMN_NAME_UPDATEDDATE,
		COLUMN_NAME_USERNAME);
	/** The list of Basic attribute names */
	static final List<String> BASIC_NAMES = Arrays.asList(		COLUMN_NAME_CREATEDBY,
		COLUMN_NAME_CREATEDDATE,
		COLUMN_NAME_EMAIL,
		COLUMN_NAME_SECRET,
		COLUMN_NAME_STATE,
		COLUMN_NAME_UPDATEDBY,
		COLUMN_NAME_UPDATEDDATE,
		COLUMN_NAME_USERNAME);
	/** The list of attribute names */
	static final List<String> MANY_TO_ONE_NAMES = Arrays.asList();


	// ----------------------- field finders -------------------------------
	/**
	 * query-by method for field createdBy
	 * @param createdBy the specified attribute
	 * @return an Iterable of Do2pProfiles for the specified createdBy
	 */
	Iterable<Do2pProfile> queryByCreatedBy(java.lang.String createdBy);
		
	/**
	 * query-keys-by method for field createdBy
	 * @param createdBy the specified attribute
	 * @return an Iterable of Do2pProfiles for the specified createdBy
	 */
	Iterable<java.lang.Long> queryKeysByCreatedBy(java.lang.String createdBy);

	/**
	 * query-page-by method for field createdBy
	 * @param createdBy the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of Do2pProfiles for the specified createdBy
	 */
	CursorPage<Do2pProfile> queryPageByCreatedBy(java.lang.String createdBy,
            int pageSize, String cursorString);


	/**
	 * query-by method for field createdDate
	 * @param createdDate the specified attribute
	 * @return an Iterable of Do2pProfiles for the specified createdDate
	 */
	Iterable<Do2pProfile> queryByCreatedDate(java.util.Date createdDate);
		
	/**
	 * query-keys-by method for field createdDate
	 * @param createdDate the specified attribute
	 * @return an Iterable of Do2pProfiles for the specified createdDate
	 */
	Iterable<java.lang.Long> queryKeysByCreatedDate(java.util.Date createdDate);

	/**
	 * query-page-by method for field createdDate
	 * @param createdDate the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of Do2pProfiles for the specified createdDate
	 */
	CursorPage<Do2pProfile> queryPageByCreatedDate(java.util.Date createdDate,
            int pageSize, String cursorString);


	/**
	 * query-by method for field email
	 * @param email the specified attribute
	 * @return an Iterable of Do2pProfiles for the specified email
	 */
	Iterable<Do2pProfile> queryByEmail(java.lang.String email);
		
	/**
	 * query-keys-by method for field email
	 * @param email the specified attribute
	 * @return an Iterable of Do2pProfiles for the specified email
	 */
	Iterable<java.lang.Long> queryKeysByEmail(java.lang.String email);

	/**
	 * query-page-by method for field email
	 * @param email the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of Do2pProfiles for the specified email
	 */
	CursorPage<Do2pProfile> queryPageByEmail(java.lang.String email,
            int pageSize, String cursorString);


	/**
	 * query-by method for field secret
	 * @param secret the specified attribute
	 * @return an Iterable of Do2pProfiles for the specified secret
	 */
	Iterable<Do2pProfile> queryBySecret(java.lang.String secret);
		
	/**
	 * query-keys-by method for field secret
	 * @param secret the specified attribute
	 * @return an Iterable of Do2pProfiles for the specified secret
	 */
	Iterable<java.lang.Long> queryKeysBySecret(java.lang.String secret);

	/**
	 * query-page-by method for field secret
	 * @param secret the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of Do2pProfiles for the specified secret
	 */
	CursorPage<Do2pProfile> queryPageBySecret(java.lang.String secret,
            int pageSize, String cursorString);


	/**
	 * query-by method for field state
	 * @param state the specified attribute
	 * @return an Iterable of Do2pProfiles for the specified state
	 */
	Iterable<Do2pProfile> queryByState(java.lang.Long state);
		
	/**
	 * query-keys-by method for field state
	 * @param state the specified attribute
	 * @return an Iterable of Do2pProfiles for the specified state
	 */
	Iterable<java.lang.Long> queryKeysByState(java.lang.Long state);

	/**
	 * query-page-by method for field state
	 * @param state the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of Do2pProfiles for the specified state
	 */
	CursorPage<Do2pProfile> queryPageByState(java.lang.Long state,
            int pageSize, String cursorString);


	/**
	 * query-by method for field updatedBy
	 * @param updatedBy the specified attribute
	 * @return an Iterable of Do2pProfiles for the specified updatedBy
	 */
	Iterable<Do2pProfile> queryByUpdatedBy(java.lang.String updatedBy);
		
	/**
	 * query-keys-by method for field updatedBy
	 * @param updatedBy the specified attribute
	 * @return an Iterable of Do2pProfiles for the specified updatedBy
	 */
	Iterable<java.lang.Long> queryKeysByUpdatedBy(java.lang.String updatedBy);

	/**
	 * query-page-by method for field updatedBy
	 * @param updatedBy the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of Do2pProfiles for the specified updatedBy
	 */
	CursorPage<Do2pProfile> queryPageByUpdatedBy(java.lang.String updatedBy,
            int pageSize, String cursorString);


	/**
	 * query-by method for field updatedDate
	 * @param updatedDate the specified attribute
	 * @return an Iterable of Do2pProfiles for the specified updatedDate
	 */
	Iterable<Do2pProfile> queryByUpdatedDate(java.util.Date updatedDate);
		
	/**
	 * query-keys-by method for field updatedDate
	 * @param updatedDate the specified attribute
	 * @return an Iterable of Do2pProfiles for the specified updatedDate
	 */
	Iterable<java.lang.Long> queryKeysByUpdatedDate(java.util.Date updatedDate);

	/**
	 * query-page-by method for field updatedDate
	 * @param updatedDate the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of Do2pProfiles for the specified updatedDate
	 */
	CursorPage<Do2pProfile> queryPageByUpdatedDate(java.util.Date updatedDate,
            int pageSize, String cursorString);


	/**
	 * find-by method for unique field username
	 * @param username the unique attribute
	 * @return the unique Do2pProfile for the specified username
	 */
	Do2pProfile findByUsername(java.lang.String username);

        /**
	 * find-key-by method for unique attribute field username
	 * @param username the unique attribute
	 * @return the unique Do2pProfile for the specified attribute
	 */
	java.lang.Long findKeyByUsername(java.lang.String username);

		  
	// ----------------------- one-to-one finders -------------------------

	// ----------------------- many-to-one finders -------------------------
	
	// ----------------------- many-to-many finders -------------------------

	// ----------------------- uniqueFields finders -------------------------
	
	
	// ----------------------- populate / persist method -------------------------

	/**
	 * Persist an entity given all attributes
	 */
	Do2pProfile persist(		java.lang.Long id, 
		java.lang.String email, 
		java.lang.String secret, 
		java.lang.Long state, 
		java.lang.String username);	

	/**
	 * Persists an entity unless it already exists
	 */
	 Do2pProfile persist(java.lang.String username, 
                java.lang.String email, 
                java.lang.String secret, 
                java.lang.Long state);

}
