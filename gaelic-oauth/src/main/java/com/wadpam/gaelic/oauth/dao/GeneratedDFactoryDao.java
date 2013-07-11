package com.wadpam.gaelic.oauth.dao;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import net.sf.mardao.core.CursorPage;
import net.sf.mardao.core.dao.Dao;
import com.wadpam.gaelic.oauth.domain.DFactory;
import net.sf.mardao.core.geo.DLocation;

/**
 * DAO interface with finder methods for DFactory entities.
 *
 * Generated on 2013-07-11T18:39:40.213+0700.
 * @author mardao DAO generator (net.sf.mardao.plugin.ProcessDomainMojo)
 */
public interface GeneratedDFactoryDao extends Dao<DFactory, java.lang.String> {

	/** Column name for primary key attribute is "id" */
	static final String COLUMN_NAME_ID = "id";


	/** Column name for field baseUrl is "baseUrl" */
	static final String COLUMN_NAME_BASEURL = "baseUrl";
	/** Column name for field clientId is "clientId" */
	static final String COLUMN_NAME_CLIENTID = "clientId";
	/** Column name for field clientSecret is "clientSecret" */
	static final String COLUMN_NAME_CLIENTSECRET = "clientSecret";
	/** Column name for field createdBy is "createdBy" */
	static final String COLUMN_NAME_CREATEDBY = "createdBy";
	/** Column name for field createdDate is "createdDate" */
	static final String COLUMN_NAME_CREATEDDATE = "createdDate";
	/** Column name for field updatedBy is "updatedBy" */
	static final String COLUMN_NAME_UPDATEDBY = "updatedBy";
	/** Column name for field updatedDate is "updatedDate" */
	static final String COLUMN_NAME_UPDATEDDATE = "updatedDate";

	/** The list of attribute names */
	static final List<String> COLUMN_NAMES = Arrays.asList(		COLUMN_NAME_BASEURL,
		COLUMN_NAME_CLIENTID,
		COLUMN_NAME_CLIENTSECRET,
		COLUMN_NAME_CREATEDBY,
		COLUMN_NAME_CREATEDDATE,
		COLUMN_NAME_UPDATEDBY,
		COLUMN_NAME_UPDATEDDATE);
	/** The list of Basic attribute names */
	static final List<String> BASIC_NAMES = Arrays.asList(		COLUMN_NAME_BASEURL,
		COLUMN_NAME_CLIENTID,
		COLUMN_NAME_CLIENTSECRET,
		COLUMN_NAME_CREATEDBY,
		COLUMN_NAME_CREATEDDATE,
		COLUMN_NAME_UPDATEDBY,
		COLUMN_NAME_UPDATEDDATE);
	/** The list of attribute names */
	static final List<String> MANY_TO_ONE_NAMES = Arrays.asList();


	// ----------------------- field finders -------------------------------
	/**
	 * query-by method for field baseUrl
	 * @param baseUrl the specified attribute
	 * @return an Iterable of DFactorys for the specified baseUrl
	 */
	Iterable<DFactory> queryByBaseUrl(java.lang.String baseUrl);
		
	/**
	 * query-keys-by method for field baseUrl
	 * @param baseUrl the specified attribute
	 * @return an Iterable of DFactorys for the specified baseUrl
	 */
	Iterable<java.lang.String> queryKeysByBaseUrl(java.lang.String baseUrl);

	/**
	 * query-page-by method for field baseUrl
	 * @param baseUrl the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DFactorys for the specified baseUrl
	 */
	CursorPage<DFactory> queryPageByBaseUrl(java.lang.String baseUrl,
            int pageSize, String cursorString);


	/**
	 * query-by method for field clientId
	 * @param clientId the specified attribute
	 * @return an Iterable of DFactorys for the specified clientId
	 */
	Iterable<DFactory> queryByClientId(java.lang.String clientId);
		
	/**
	 * query-keys-by method for field clientId
	 * @param clientId the specified attribute
	 * @return an Iterable of DFactorys for the specified clientId
	 */
	Iterable<java.lang.String> queryKeysByClientId(java.lang.String clientId);

	/**
	 * query-page-by method for field clientId
	 * @param clientId the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DFactorys for the specified clientId
	 */
	CursorPage<DFactory> queryPageByClientId(java.lang.String clientId,
            int pageSize, String cursorString);


	/**
	 * query-by method for field clientSecret
	 * @param clientSecret the specified attribute
	 * @return an Iterable of DFactorys for the specified clientSecret
	 */
	Iterable<DFactory> queryByClientSecret(java.lang.String clientSecret);
		
	/**
	 * query-keys-by method for field clientSecret
	 * @param clientSecret the specified attribute
	 * @return an Iterable of DFactorys for the specified clientSecret
	 */
	Iterable<java.lang.String> queryKeysByClientSecret(java.lang.String clientSecret);

	/**
	 * query-page-by method for field clientSecret
	 * @param clientSecret the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DFactorys for the specified clientSecret
	 */
	CursorPage<DFactory> queryPageByClientSecret(java.lang.String clientSecret,
            int pageSize, String cursorString);


	/**
	 * query-by method for field createdBy
	 * @param createdBy the specified attribute
	 * @return an Iterable of DFactorys for the specified createdBy
	 */
	Iterable<DFactory> queryByCreatedBy(java.lang.String createdBy);
		
	/**
	 * query-keys-by method for field createdBy
	 * @param createdBy the specified attribute
	 * @return an Iterable of DFactorys for the specified createdBy
	 */
	Iterable<java.lang.String> queryKeysByCreatedBy(java.lang.String createdBy);

	/**
	 * query-page-by method for field createdBy
	 * @param createdBy the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DFactorys for the specified createdBy
	 */
	CursorPage<DFactory> queryPageByCreatedBy(java.lang.String createdBy,
            int pageSize, String cursorString);


	/**
	 * query-by method for field createdDate
	 * @param createdDate the specified attribute
	 * @return an Iterable of DFactorys for the specified createdDate
	 */
	Iterable<DFactory> queryByCreatedDate(java.util.Date createdDate);
		
	/**
	 * query-keys-by method for field createdDate
	 * @param createdDate the specified attribute
	 * @return an Iterable of DFactorys for the specified createdDate
	 */
	Iterable<java.lang.String> queryKeysByCreatedDate(java.util.Date createdDate);

	/**
	 * query-page-by method for field createdDate
	 * @param createdDate the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DFactorys for the specified createdDate
	 */
	CursorPage<DFactory> queryPageByCreatedDate(java.util.Date createdDate,
            int pageSize, String cursorString);


	/**
	 * query-by method for field updatedBy
	 * @param updatedBy the specified attribute
	 * @return an Iterable of DFactorys for the specified updatedBy
	 */
	Iterable<DFactory> queryByUpdatedBy(java.lang.String updatedBy);
		
	/**
	 * query-keys-by method for field updatedBy
	 * @param updatedBy the specified attribute
	 * @return an Iterable of DFactorys for the specified updatedBy
	 */
	Iterable<java.lang.String> queryKeysByUpdatedBy(java.lang.String updatedBy);

	/**
	 * query-page-by method for field updatedBy
	 * @param updatedBy the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DFactorys for the specified updatedBy
	 */
	CursorPage<DFactory> queryPageByUpdatedBy(java.lang.String updatedBy,
            int pageSize, String cursorString);


	/**
	 * query-by method for field updatedDate
	 * @param updatedDate the specified attribute
	 * @return an Iterable of DFactorys for the specified updatedDate
	 */
	Iterable<DFactory> queryByUpdatedDate(java.util.Date updatedDate);
		
	/**
	 * query-keys-by method for field updatedDate
	 * @param updatedDate the specified attribute
	 * @return an Iterable of DFactorys for the specified updatedDate
	 */
	Iterable<java.lang.String> queryKeysByUpdatedDate(java.util.Date updatedDate);

	/**
	 * query-page-by method for field updatedDate
	 * @param updatedDate the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DFactorys for the specified updatedDate
	 */
	CursorPage<DFactory> queryPageByUpdatedDate(java.util.Date updatedDate,
            int pageSize, String cursorString);


		  
	// ----------------------- one-to-one finders -------------------------

	// ----------------------- many-to-one finders -------------------------
	
	// ----------------------- many-to-many finders -------------------------

	// ----------------------- uniqueFields finders -------------------------
	
	
	// ----------------------- populate / persist method -------------------------

	/**
	 * Persist an entity given all attributes
	 */
	DFactory persist(		java.lang.String id, 
		java.lang.String baseUrl, 
		java.lang.String clientId, 
		java.lang.String clientSecret);	

}
