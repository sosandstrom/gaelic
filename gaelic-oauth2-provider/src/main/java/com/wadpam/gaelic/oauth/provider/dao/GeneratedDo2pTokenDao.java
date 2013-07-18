package com.wadpam.gaelic.oauth.provider.dao;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import net.sf.mardao.core.CursorPage;
import net.sf.mardao.core.dao.Dao;
import com.wadpam.gaelic.oauth.provider.domain.Do2pToken;
import net.sf.mardao.core.geo.DLocation;

/**
 * DAO interface with finder methods for Do2pToken entities.
 *
 * Generated on 2013-07-18T12:02:44.205+0700.
 * @author mardao DAO generator (net.sf.mardao.plugin.ProcessDomainMojo)
 */
public interface GeneratedDo2pTokenDao extends Dao<Do2pToken, java.lang.Long> {

	/** Column name for primary key attribute is "id" */
	static final String COLUMN_NAME_ID = "id";

	/** Column name for parent profileKey is "profileKey" */
	static final String COLUMN_NAME_PROFILEKEY = "profileKey";


	/** Column name for field accessToken is "accessToken" */
	static final String COLUMN_NAME_ACCESSTOKEN = "accessToken";
	/** Column name for field clientId is "clientId" */
	static final String COLUMN_NAME_CLIENTID = "clientId";
	/** Column name for field createdBy is "createdBy" */
	static final String COLUMN_NAME_CREATEDBY = "createdBy";
	/** Column name for field createdDate is "createdDate" */
	static final String COLUMN_NAME_CREATEDDATE = "createdDate";
	/** Column name for field expiryDate is "expiryDate" */
	static final String COLUMN_NAME_EXPIRYDATE = "expiryDate";
	/** Column name for field refreshToken is "refreshToken" */
	static final String COLUMN_NAME_REFRESHTOKEN = "refreshToken";
	/** Column name for field scope is "scope" */
	static final String COLUMN_NAME_SCOPE = "scope";
	/** Column name for field updatedBy is "updatedBy" */
	static final String COLUMN_NAME_UPDATEDBY = "updatedBy";
	/** Column name for field updatedDate is "updatedDate" */
	static final String COLUMN_NAME_UPDATEDDATE = "updatedDate";

	/** The list of attribute names */
	static final List<String> COLUMN_NAMES = Arrays.asList(		COLUMN_NAME_ACCESSTOKEN,
		COLUMN_NAME_CLIENTID,
		COLUMN_NAME_CREATEDBY,
		COLUMN_NAME_CREATEDDATE,
		COLUMN_NAME_EXPIRYDATE,
		COLUMN_NAME_REFRESHTOKEN,
		COLUMN_NAME_SCOPE,
		COLUMN_NAME_UPDATEDBY,
		COLUMN_NAME_UPDATEDDATE);
	/** The list of Basic attribute names */
	static final List<String> BASIC_NAMES = Arrays.asList(		COLUMN_NAME_ACCESSTOKEN,
		COLUMN_NAME_CLIENTID,
		COLUMN_NAME_CREATEDBY,
		COLUMN_NAME_CREATEDDATE,
		COLUMN_NAME_EXPIRYDATE,
		COLUMN_NAME_REFRESHTOKEN,
		COLUMN_NAME_SCOPE,
		COLUMN_NAME_UPDATEDBY,
		COLUMN_NAME_UPDATEDDATE);
	/** The list of attribute names */
	static final List<String> MANY_TO_ONE_NAMES = Arrays.asList();


	// ----------------------- parent finder -------------------------------
	/**
	 * query-by method for parent field profileKey
	 * @param profileKey the specified attribute
	 * @return an Iterable of Do2pTokens with the specified parent
	 */
	Iterable<Do2pToken> queryByProfileKey(Object profileKey);
		
	/**
	 * query-keys-by method for parent field profileKey
	 * @param profileKey the specified attribute
	 * @return an Iterable of Do2pTokens with the specified parent
	 */
	Iterable<java.lang.Long> queryKeysByProfileKey(Object profileKey);

	/**
	 * query-page-by method for parent field profileKey
	 * @param profileKey the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of Do2pTokens for the specified profileKey (parent)
	 */
	CursorPage<Do2pToken> queryPageByProfileKey(java.lang.Object profileKey,
            int pageSize, String cursorString);

	// ----------------------- field finders -------------------------------
	/**
	 * find-by method for unique field accessToken
	 * @param accessToken the unique attribute
	 * @return the unique Do2pToken for the specified accessToken
	 */
	Do2pToken findByAccessToken(java.lang.String accessToken);

        /**
	 * find-key-by method for unique attribute field accessToken
	 * @param accessToken the unique attribute
	 * @return the unique Do2pToken for the specified attribute
	 */
	java.lang.Long findKeyByAccessToken(java.lang.String accessToken);

	/**
	 * query-by method for field clientId
	 * @param clientId the specified attribute
	 * @return an Iterable of Do2pTokens for the specified clientId
	 */
	Iterable<Do2pToken> queryByClientId(java.lang.Long clientId);
		
	/**
	 * query-keys-by method for field clientId
	 * @param clientId the specified attribute
	 * @return an Iterable of Do2pTokens for the specified clientId
	 */
	Iterable<java.lang.Long> queryKeysByClientId(java.lang.Long clientId);

	/**
	 * query-page-by method for field clientId
	 * @param clientId the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of Do2pTokens for the specified clientId
	 */
	CursorPage<Do2pToken> queryPageByClientId(java.lang.Long clientId,
            int pageSize, String cursorString);


	/**
	 * query-by method for field createdBy
	 * @param createdBy the specified attribute
	 * @return an Iterable of Do2pTokens for the specified createdBy
	 */
	Iterable<Do2pToken> queryByCreatedBy(java.lang.String createdBy);
		
	/**
	 * query-keys-by method for field createdBy
	 * @param createdBy the specified attribute
	 * @return an Iterable of Do2pTokens for the specified createdBy
	 */
	Iterable<java.lang.Long> queryKeysByCreatedBy(java.lang.String createdBy);

	/**
	 * query-page-by method for field createdBy
	 * @param createdBy the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of Do2pTokens for the specified createdBy
	 */
	CursorPage<Do2pToken> queryPageByCreatedBy(java.lang.String createdBy,
            int pageSize, String cursorString);


	/**
	 * query-by method for field createdDate
	 * @param createdDate the specified attribute
	 * @return an Iterable of Do2pTokens for the specified createdDate
	 */
	Iterable<Do2pToken> queryByCreatedDate(java.util.Date createdDate);
		
	/**
	 * query-keys-by method for field createdDate
	 * @param createdDate the specified attribute
	 * @return an Iterable of Do2pTokens for the specified createdDate
	 */
	Iterable<java.lang.Long> queryKeysByCreatedDate(java.util.Date createdDate);

	/**
	 * query-page-by method for field createdDate
	 * @param createdDate the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of Do2pTokens for the specified createdDate
	 */
	CursorPage<Do2pToken> queryPageByCreatedDate(java.util.Date createdDate,
            int pageSize, String cursorString);


	/**
	 * query-by method for field expiryDate
	 * @param expiryDate the specified attribute
	 * @return an Iterable of Do2pTokens for the specified expiryDate
	 */
	Iterable<Do2pToken> queryByExpiryDate(java.util.Date expiryDate);
		
	/**
	 * query-keys-by method for field expiryDate
	 * @param expiryDate the specified attribute
	 * @return an Iterable of Do2pTokens for the specified expiryDate
	 */
	Iterable<java.lang.Long> queryKeysByExpiryDate(java.util.Date expiryDate);

	/**
	 * query-page-by method for field expiryDate
	 * @param expiryDate the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of Do2pTokens for the specified expiryDate
	 */
	CursorPage<Do2pToken> queryPageByExpiryDate(java.util.Date expiryDate,
            int pageSize, String cursorString);


	/**
	 * find-by method for unique field refreshToken
	 * @param refreshToken the unique attribute
	 * @return the unique Do2pToken for the specified refreshToken
	 */
	Do2pToken findByRefreshToken(java.lang.String refreshToken);

        /**
	 * find-key-by method for unique attribute field refreshToken
	 * @param refreshToken the unique attribute
	 * @return the unique Do2pToken for the specified attribute
	 */
	java.lang.Long findKeyByRefreshToken(java.lang.String refreshToken);

	/**
	 * query-by method for field scope
	 * @param scope the specified attribute
	 * @return an Iterable of Do2pTokens for the specified scope
	 */
	Iterable<Do2pToken> queryByScope(java.lang.String scope);
		
	/**
	 * query-keys-by method for field scope
	 * @param scope the specified attribute
	 * @return an Iterable of Do2pTokens for the specified scope
	 */
	Iterable<java.lang.Long> queryKeysByScope(java.lang.String scope);

	/**
	 * query-page-by method for field scope
	 * @param scope the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of Do2pTokens for the specified scope
	 */
	CursorPage<Do2pToken> queryPageByScope(java.lang.String scope,
            int pageSize, String cursorString);


	/**
	 * query-by method for field updatedBy
	 * @param updatedBy the specified attribute
	 * @return an Iterable of Do2pTokens for the specified updatedBy
	 */
	Iterable<Do2pToken> queryByUpdatedBy(java.lang.String updatedBy);
		
	/**
	 * query-keys-by method for field updatedBy
	 * @param updatedBy the specified attribute
	 * @return an Iterable of Do2pTokens for the specified updatedBy
	 */
	Iterable<java.lang.Long> queryKeysByUpdatedBy(java.lang.String updatedBy);

	/**
	 * query-page-by method for field updatedBy
	 * @param updatedBy the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of Do2pTokens for the specified updatedBy
	 */
	CursorPage<Do2pToken> queryPageByUpdatedBy(java.lang.String updatedBy,
            int pageSize, String cursorString);


	/**
	 * query-by method for field updatedDate
	 * @param updatedDate the specified attribute
	 * @return an Iterable of Do2pTokens for the specified updatedDate
	 */
	Iterable<Do2pToken> queryByUpdatedDate(java.util.Date updatedDate);
		
	/**
	 * query-keys-by method for field updatedDate
	 * @param updatedDate the specified attribute
	 * @return an Iterable of Do2pTokens for the specified updatedDate
	 */
	Iterable<java.lang.Long> queryKeysByUpdatedDate(java.util.Date updatedDate);

	/**
	 * query-page-by method for field updatedDate
	 * @param updatedDate the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of Do2pTokens for the specified updatedDate
	 */
	CursorPage<Do2pToken> queryPageByUpdatedDate(java.util.Date updatedDate,
            int pageSize, String cursorString);


		  
	// ----------------------- one-to-one finders -------------------------

	// ----------------------- many-to-one finders -------------------------
	
	// ----------------------- many-to-many finders -------------------------

	// ----------------------- uniqueFields finders -------------------------
	
	
	// ----------------------- populate / persist method -------------------------

	/**
	 * Persist an entity given all attributes
	 */
	Do2pToken persist(Object profileKey,  	
		java.lang.Long id, 
		java.lang.String accessToken, 
		java.lang.Long clientId, 
		java.util.Date expiryDate, 
		java.lang.String refreshToken, 
		java.lang.String scope);	

	/**
	 * Persists an entity unless it already exists
	 */
	 Do2pToken persist(java.lang.String accessToken, 
                java.lang.Long clientId, 
                java.util.Date expiryDate, 
                java.lang.String refreshToken, 
                java.lang.String scope);

}
