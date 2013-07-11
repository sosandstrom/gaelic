package com.wadpam.gaelic.dao;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import net.sf.mardao.core.CursorPage;
import net.sf.mardao.core.dao.Dao;
import com.wadpam.gaelic.domain.DAppDomain;
import net.sf.mardao.core.geo.DLocation;

/**
 * DAO interface with finder methods for DAppDomain entities.
 *
 * Generated on 2013-07-11T17:23:21.353+0700.
 * @author mardao DAO generator (net.sf.mardao.plugin.ProcessDomainMojo)
 */
public interface GeneratedDAppDomainDao extends Dao<DAppDomain, java.lang.String> {

	/** Column name for primary key attribute is "id" */
	static final String COLUMN_NAME_ID = "id";


	/** Column name for field analyticsTrackingCode is "analyticsTrackingCode" */
	static final String COLUMN_NAME_ANALYTICSTRACKINGCODE = "analyticsTrackingCode";
	/** Column name for field appArg1 is "appArg1" */
	static final String COLUMN_NAME_APPARG1 = "appArg1";
	/** Column name for field appArg2 is "appArg2" */
	static final String COLUMN_NAME_APPARG2 = "appArg2";
	/** Column name for field createdBy is "createdBy" */
	static final String COLUMN_NAME_CREATEDBY = "createdBy";
	/** Column name for field createdDate is "createdDate" */
	static final String COLUMN_NAME_CREATEDDATE = "createdDate";
	/** Column name for field description is "description" */
	static final String COLUMN_NAME_DESCRIPTION = "description";
	/** Column name for field email is "email" */
	static final String COLUMN_NAME_EMAIL = "email";
	/** Column name for field password is "password" */
	static final String COLUMN_NAME_PASSWORD = "password";
	/** Column name for field updatedBy is "updatedBy" */
	static final String COLUMN_NAME_UPDATEDBY = "updatedBy";
	/** Column name for field updatedDate is "updatedDate" */
	static final String COLUMN_NAME_UPDATEDDATE = "updatedDate";
	/** Column name for field username is "username" */
	static final String COLUMN_NAME_USERNAME = "username";

	/** The list of attribute names */
	static final List<String> COLUMN_NAMES = Arrays.asList(		COLUMN_NAME_ANALYTICSTRACKINGCODE,
		COLUMN_NAME_APPARG1,
		COLUMN_NAME_APPARG2,
		COLUMN_NAME_CREATEDBY,
		COLUMN_NAME_CREATEDDATE,
		COLUMN_NAME_DESCRIPTION,
		COLUMN_NAME_EMAIL,
		COLUMN_NAME_PASSWORD,
		COLUMN_NAME_UPDATEDBY,
		COLUMN_NAME_UPDATEDDATE,
		COLUMN_NAME_USERNAME);
	/** The list of Basic attribute names */
	static final List<String> BASIC_NAMES = Arrays.asList(		COLUMN_NAME_ANALYTICSTRACKINGCODE,
		COLUMN_NAME_APPARG1,
		COLUMN_NAME_APPARG2,
		COLUMN_NAME_CREATEDBY,
		COLUMN_NAME_CREATEDDATE,
		COLUMN_NAME_DESCRIPTION,
		COLUMN_NAME_EMAIL,
		COLUMN_NAME_PASSWORD,
		COLUMN_NAME_UPDATEDBY,
		COLUMN_NAME_UPDATEDDATE,
		COLUMN_NAME_USERNAME);
	/** The list of attribute names */
	static final List<String> MANY_TO_ONE_NAMES = Arrays.asList();


	// ----------------------- field finders -------------------------------
	/**
	 * query-by method for field analyticsTrackingCode
	 * @param analyticsTrackingCode the specified attribute
	 * @return an Iterable of DAppDomains for the specified analyticsTrackingCode
	 */
	Iterable<DAppDomain> queryByAnalyticsTrackingCode(java.lang.String analyticsTrackingCode);
		
	/**
	 * query-keys-by method for field analyticsTrackingCode
	 * @param analyticsTrackingCode the specified attribute
	 * @return an Iterable of DAppDomains for the specified analyticsTrackingCode
	 */
	Iterable<java.lang.String> queryKeysByAnalyticsTrackingCode(java.lang.String analyticsTrackingCode);

	/**
	 * query-page-by method for field analyticsTrackingCode
	 * @param analyticsTrackingCode the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DAppDomains for the specified analyticsTrackingCode
	 */
	CursorPage<DAppDomain> queryPageByAnalyticsTrackingCode(java.lang.String analyticsTrackingCode,
            int pageSize, String cursorString);


	/**
	 * query-by method for field appArg1
	 * @param appArg1 the specified attribute
	 * @return an Iterable of DAppDomains for the specified appArg1
	 */
	Iterable<DAppDomain> queryByAppArg1(java.lang.String appArg1);
		
	/**
	 * query-keys-by method for field appArg1
	 * @param appArg1 the specified attribute
	 * @return an Iterable of DAppDomains for the specified appArg1
	 */
	Iterable<java.lang.String> queryKeysByAppArg1(java.lang.String appArg1);

	/**
	 * query-page-by method for field appArg1
	 * @param appArg1 the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DAppDomains for the specified appArg1
	 */
	CursorPage<DAppDomain> queryPageByAppArg1(java.lang.String appArg1,
            int pageSize, String cursorString);


	/**
	 * query-by method for field appArg2
	 * @param appArg2 the specified attribute
	 * @return an Iterable of DAppDomains for the specified appArg2
	 */
	Iterable<DAppDomain> queryByAppArg2(java.lang.String appArg2);
		
	/**
	 * query-keys-by method for field appArg2
	 * @param appArg2 the specified attribute
	 * @return an Iterable of DAppDomains for the specified appArg2
	 */
	Iterable<java.lang.String> queryKeysByAppArg2(java.lang.String appArg2);

	/**
	 * query-page-by method for field appArg2
	 * @param appArg2 the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DAppDomains for the specified appArg2
	 */
	CursorPage<DAppDomain> queryPageByAppArg2(java.lang.String appArg2,
            int pageSize, String cursorString);


	/**
	 * query-by method for field createdBy
	 * @param createdBy the specified attribute
	 * @return an Iterable of DAppDomains for the specified createdBy
	 */
	Iterable<DAppDomain> queryByCreatedBy(java.lang.String createdBy);
		
	/**
	 * query-keys-by method for field createdBy
	 * @param createdBy the specified attribute
	 * @return an Iterable of DAppDomains for the specified createdBy
	 */
	Iterable<java.lang.String> queryKeysByCreatedBy(java.lang.String createdBy);

	/**
	 * query-page-by method for field createdBy
	 * @param createdBy the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DAppDomains for the specified createdBy
	 */
	CursorPage<DAppDomain> queryPageByCreatedBy(java.lang.String createdBy,
            int pageSize, String cursorString);


	/**
	 * query-by method for field createdDate
	 * @param createdDate the specified attribute
	 * @return an Iterable of DAppDomains for the specified createdDate
	 */
	Iterable<DAppDomain> queryByCreatedDate(java.util.Date createdDate);
		
	/**
	 * query-keys-by method for field createdDate
	 * @param createdDate the specified attribute
	 * @return an Iterable of DAppDomains for the specified createdDate
	 */
	Iterable<java.lang.String> queryKeysByCreatedDate(java.util.Date createdDate);

	/**
	 * query-page-by method for field createdDate
	 * @param createdDate the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DAppDomains for the specified createdDate
	 */
	CursorPage<DAppDomain> queryPageByCreatedDate(java.util.Date createdDate,
            int pageSize, String cursorString);


	/**
	 * query-by method for field description
	 * @param description the specified attribute
	 * @return an Iterable of DAppDomains for the specified description
	 */
	Iterable<DAppDomain> queryByDescription(java.lang.String description);
		
	/**
	 * query-keys-by method for field description
	 * @param description the specified attribute
	 * @return an Iterable of DAppDomains for the specified description
	 */
	Iterable<java.lang.String> queryKeysByDescription(java.lang.String description);

	/**
	 * query-page-by method for field description
	 * @param description the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DAppDomains for the specified description
	 */
	CursorPage<DAppDomain> queryPageByDescription(java.lang.String description,
            int pageSize, String cursorString);


	/**
	 * query-by method for field email
	 * @param email the specified attribute
	 * @return an Iterable of DAppDomains for the specified email
	 */
	Iterable<DAppDomain> queryByEmail(java.lang.String email);
		
	/**
	 * query-keys-by method for field email
	 * @param email the specified attribute
	 * @return an Iterable of DAppDomains for the specified email
	 */
	Iterable<java.lang.String> queryKeysByEmail(java.lang.String email);

	/**
	 * query-page-by method for field email
	 * @param email the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DAppDomains for the specified email
	 */
	CursorPage<DAppDomain> queryPageByEmail(java.lang.String email,
            int pageSize, String cursorString);


	/**
	 * query-by method for field password
	 * @param password the specified attribute
	 * @return an Iterable of DAppDomains for the specified password
	 */
	Iterable<DAppDomain> queryByPassword(java.lang.String password);
		
	/**
	 * query-keys-by method for field password
	 * @param password the specified attribute
	 * @return an Iterable of DAppDomains for the specified password
	 */
	Iterable<java.lang.String> queryKeysByPassword(java.lang.String password);

	/**
	 * query-page-by method for field password
	 * @param password the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DAppDomains for the specified password
	 */
	CursorPage<DAppDomain> queryPageByPassword(java.lang.String password,
            int pageSize, String cursorString);


	/**
	 * query-by method for field updatedBy
	 * @param updatedBy the specified attribute
	 * @return an Iterable of DAppDomains for the specified updatedBy
	 */
	Iterable<DAppDomain> queryByUpdatedBy(java.lang.String updatedBy);
		
	/**
	 * query-keys-by method for field updatedBy
	 * @param updatedBy the specified attribute
	 * @return an Iterable of DAppDomains for the specified updatedBy
	 */
	Iterable<java.lang.String> queryKeysByUpdatedBy(java.lang.String updatedBy);

	/**
	 * query-page-by method for field updatedBy
	 * @param updatedBy the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DAppDomains for the specified updatedBy
	 */
	CursorPage<DAppDomain> queryPageByUpdatedBy(java.lang.String updatedBy,
            int pageSize, String cursorString);


	/**
	 * query-by method for field updatedDate
	 * @param updatedDate the specified attribute
	 * @return an Iterable of DAppDomains for the specified updatedDate
	 */
	Iterable<DAppDomain> queryByUpdatedDate(java.util.Date updatedDate);
		
	/**
	 * query-keys-by method for field updatedDate
	 * @param updatedDate the specified attribute
	 * @return an Iterable of DAppDomains for the specified updatedDate
	 */
	Iterable<java.lang.String> queryKeysByUpdatedDate(java.util.Date updatedDate);

	/**
	 * query-page-by method for field updatedDate
	 * @param updatedDate the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DAppDomains for the specified updatedDate
	 */
	CursorPage<DAppDomain> queryPageByUpdatedDate(java.util.Date updatedDate,
            int pageSize, String cursorString);


	/**
	 * find-by method for unique field username
	 * @param username the unique attribute
	 * @return the unique DAppDomain for the specified username
	 */
	DAppDomain findByUsername(java.lang.String username);

        /**
	 * find-key-by method for unique attribute field username
	 * @param username the unique attribute
	 * @return the unique DAppDomain for the specified attribute
	 */
	java.lang.String findKeyByUsername(java.lang.String username);

		  
	// ----------------------- one-to-one finders -------------------------

	// ----------------------- many-to-one finders -------------------------
	
	// ----------------------- many-to-many finders -------------------------

	// ----------------------- uniqueFields finders -------------------------
	
	
	// ----------------------- populate / persist method -------------------------

	/**
	 * Persist an entity given all attributes
	 */
	DAppDomain persist(		java.lang.String id, 
		java.lang.String analyticsTrackingCode, 
		java.lang.String appArg1, 
		java.lang.String appArg2, 
		java.lang.String description, 
		java.lang.String email, 
		java.lang.String password, 
		java.lang.String username);	

	/**
	 * Persists an entity unless it already exists
	 */
	 DAppDomain persist(java.lang.String username, 
                java.lang.String analyticsTrackingCode, 
                java.lang.String appArg1, 
                java.lang.String appArg2, 
                java.lang.String description, 
                java.lang.String email, 
                java.lang.String password);

}
