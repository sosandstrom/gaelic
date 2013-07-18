package com.wadpam.gaelic.oauth.provider.dao;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import net.sf.mardao.core.CursorPage;
import net.sf.mardao.core.dao.Dao;
import com.wadpam.gaelic.oauth.provider.domain.Do2pClient;
import net.sf.mardao.core.geo.DLocation;

/**
 * DAO interface with finder methods for Do2pClient entities.
 *
 * Generated on 2013-07-18T12:02:44.205+0700.
 * @author mardao DAO generator (net.sf.mardao.plugin.ProcessDomainMojo)
 */
public interface GeneratedDo2pClientDao extends Dao<Do2pClient, java.lang.Long> {

	/** Column name for primary key attribute is "id" */
	static final String COLUMN_NAME_ID = "id";


	/** Column name for field createdBy is "createdBy" */
	static final String COLUMN_NAME_CREATEDBY = "createdBy";
	/** Column name for field createdDate is "createdDate" */
	static final String COLUMN_NAME_CREATEDDATE = "createdDate";
	/** Column name for field description is "description" */
	static final String COLUMN_NAME_DESCRIPTION = "description";
	/** Column name for field name is "name" */
	static final String COLUMN_NAME_NAME = "name";
	/** Column name for field redirectUri is "redirectUri" */
	static final String COLUMN_NAME_REDIRECTURI = "redirectUri";
	/** Column name for field secret is "secret" */
	static final String COLUMN_NAME_SECRET = "secret";
	/** Column name for field updatedBy is "updatedBy" */
	static final String COLUMN_NAME_UPDATEDBY = "updatedBy";
	/** Column name for field updatedDate is "updatedDate" */
	static final String COLUMN_NAME_UPDATEDDATE = "updatedDate";

	/** The list of attribute names */
	static final List<String> COLUMN_NAMES = Arrays.asList(		COLUMN_NAME_CREATEDBY,
		COLUMN_NAME_CREATEDDATE,
		COLUMN_NAME_DESCRIPTION,
		COLUMN_NAME_NAME,
		COLUMN_NAME_REDIRECTURI,
		COLUMN_NAME_SECRET,
		COLUMN_NAME_UPDATEDBY,
		COLUMN_NAME_UPDATEDDATE);
	/** The list of Basic attribute names */
	static final List<String> BASIC_NAMES = Arrays.asList(		COLUMN_NAME_CREATEDBY,
		COLUMN_NAME_CREATEDDATE,
		COLUMN_NAME_DESCRIPTION,
		COLUMN_NAME_NAME,
		COLUMN_NAME_REDIRECTURI,
		COLUMN_NAME_SECRET,
		COLUMN_NAME_UPDATEDBY,
		COLUMN_NAME_UPDATEDDATE);
	/** The list of attribute names */
	static final List<String> MANY_TO_ONE_NAMES = Arrays.asList();


	// ----------------------- field finders -------------------------------
	/**
	 * query-by method for field createdBy
	 * @param createdBy the specified attribute
	 * @return an Iterable of Do2pClients for the specified createdBy
	 */
	Iterable<Do2pClient> queryByCreatedBy(java.lang.String createdBy);
		
	/**
	 * query-keys-by method for field createdBy
	 * @param createdBy the specified attribute
	 * @return an Iterable of Do2pClients for the specified createdBy
	 */
	Iterable<java.lang.Long> queryKeysByCreatedBy(java.lang.String createdBy);

	/**
	 * query-page-by method for field createdBy
	 * @param createdBy the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of Do2pClients for the specified createdBy
	 */
	CursorPage<Do2pClient> queryPageByCreatedBy(java.lang.String createdBy,
            int pageSize, String cursorString);


	/**
	 * query-by method for field createdDate
	 * @param createdDate the specified attribute
	 * @return an Iterable of Do2pClients for the specified createdDate
	 */
	Iterable<Do2pClient> queryByCreatedDate(java.util.Date createdDate);
		
	/**
	 * query-keys-by method for field createdDate
	 * @param createdDate the specified attribute
	 * @return an Iterable of Do2pClients for the specified createdDate
	 */
	Iterable<java.lang.Long> queryKeysByCreatedDate(java.util.Date createdDate);

	/**
	 * query-page-by method for field createdDate
	 * @param createdDate the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of Do2pClients for the specified createdDate
	 */
	CursorPage<Do2pClient> queryPageByCreatedDate(java.util.Date createdDate,
            int pageSize, String cursorString);


	/**
	 * query-by method for field description
	 * @param description the specified attribute
	 * @return an Iterable of Do2pClients for the specified description
	 */
	Iterable<Do2pClient> queryByDescription(java.lang.String description);
		
	/**
	 * query-keys-by method for field description
	 * @param description the specified attribute
	 * @return an Iterable of Do2pClients for the specified description
	 */
	Iterable<java.lang.Long> queryKeysByDescription(java.lang.String description);

	/**
	 * query-page-by method for field description
	 * @param description the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of Do2pClients for the specified description
	 */
	CursorPage<Do2pClient> queryPageByDescription(java.lang.String description,
            int pageSize, String cursorString);


	/**
	 * query-by method for field name
	 * @param name the specified attribute
	 * @return an Iterable of Do2pClients for the specified name
	 */
	Iterable<Do2pClient> queryByName(java.lang.String name);
		
	/**
	 * query-keys-by method for field name
	 * @param name the specified attribute
	 * @return an Iterable of Do2pClients for the specified name
	 */
	Iterable<java.lang.Long> queryKeysByName(java.lang.String name);

	/**
	 * query-page-by method for field name
	 * @param name the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of Do2pClients for the specified name
	 */
	CursorPage<Do2pClient> queryPageByName(java.lang.String name,
            int pageSize, String cursorString);


	/**
	 * query-by method for field redirectUri
	 * @param redirectUri the specified attribute
	 * @return an Iterable of Do2pClients for the specified redirectUri
	 */
	Iterable<Do2pClient> queryByRedirectUri(java.lang.String redirectUri);
		
	/**
	 * query-keys-by method for field redirectUri
	 * @param redirectUri the specified attribute
	 * @return an Iterable of Do2pClients for the specified redirectUri
	 */
	Iterable<java.lang.Long> queryKeysByRedirectUri(java.lang.String redirectUri);

	/**
	 * query-page-by method for field redirectUri
	 * @param redirectUri the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of Do2pClients for the specified redirectUri
	 */
	CursorPage<Do2pClient> queryPageByRedirectUri(java.lang.String redirectUri,
            int pageSize, String cursorString);


	/**
	 * query-by method for field secret
	 * @param secret the specified attribute
	 * @return an Iterable of Do2pClients for the specified secret
	 */
	Iterable<Do2pClient> queryBySecret(java.lang.String secret);
		
	/**
	 * query-keys-by method for field secret
	 * @param secret the specified attribute
	 * @return an Iterable of Do2pClients for the specified secret
	 */
	Iterable<java.lang.Long> queryKeysBySecret(java.lang.String secret);

	/**
	 * query-page-by method for field secret
	 * @param secret the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of Do2pClients for the specified secret
	 */
	CursorPage<Do2pClient> queryPageBySecret(java.lang.String secret,
            int pageSize, String cursorString);


	/**
	 * query-by method for field updatedBy
	 * @param updatedBy the specified attribute
	 * @return an Iterable of Do2pClients for the specified updatedBy
	 */
	Iterable<Do2pClient> queryByUpdatedBy(java.lang.String updatedBy);
		
	/**
	 * query-keys-by method for field updatedBy
	 * @param updatedBy the specified attribute
	 * @return an Iterable of Do2pClients for the specified updatedBy
	 */
	Iterable<java.lang.Long> queryKeysByUpdatedBy(java.lang.String updatedBy);

	/**
	 * query-page-by method for field updatedBy
	 * @param updatedBy the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of Do2pClients for the specified updatedBy
	 */
	CursorPage<Do2pClient> queryPageByUpdatedBy(java.lang.String updatedBy,
            int pageSize, String cursorString);


	/**
	 * query-by method for field updatedDate
	 * @param updatedDate the specified attribute
	 * @return an Iterable of Do2pClients for the specified updatedDate
	 */
	Iterable<Do2pClient> queryByUpdatedDate(java.util.Date updatedDate);
		
	/**
	 * query-keys-by method for field updatedDate
	 * @param updatedDate the specified attribute
	 * @return an Iterable of Do2pClients for the specified updatedDate
	 */
	Iterable<java.lang.Long> queryKeysByUpdatedDate(java.util.Date updatedDate);

	/**
	 * query-page-by method for field updatedDate
	 * @param updatedDate the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of Do2pClients for the specified updatedDate
	 */
	CursorPage<Do2pClient> queryPageByUpdatedDate(java.util.Date updatedDate,
            int pageSize, String cursorString);


		  
	// ----------------------- one-to-one finders -------------------------

	// ----------------------- many-to-one finders -------------------------
	
	// ----------------------- many-to-many finders -------------------------

	// ----------------------- uniqueFields finders -------------------------
	
	
	// ----------------------- populate / persist method -------------------------

	/**
	 * Persist an entity given all attributes
	 */
	Do2pClient persist(		java.lang.Long id, 
		java.lang.String description, 
		java.lang.String name, 
		java.lang.String redirectUri, 
		java.lang.String secret);	

}
