package com.wadpam.gaelic.oauth.dao;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import net.sf.mardao.core.CursorPage;
import net.sf.mardao.core.dao.Dao;
import com.wadpam.gaelic.oauth.domain.DConnection;
import net.sf.mardao.core.geo.DLocation;

/**
 * DAO interface with finder methods for DConnection entities.
 *
 * Generated on 2013-05-09T05:56:26.129+0700.
 * @author mardao DAO generator (net.sf.mardao.plugin.ProcessDomainMojo)
 */
public interface GeneratedDConnectionDao extends Dao<DConnection, java.lang.String> {

	/** Column name for primary key attribute is "id" */
	static final String COLUMN_NAME_ID = "id";

	/** Column name for parent userKey is "userKey" */
	static final String COLUMN_NAME_USERKEY = "userKey";


	/** Column name for field appArg0 is "appArg0" */
	static final String COLUMN_NAME_APPARG0 = "appArg0";
	/** Column name for field createdBy is "createdBy" */
	static final String COLUMN_NAME_CREATEDBY = "createdBy";
	/** Column name for field createdDate is "createdDate" */
	static final String COLUMN_NAME_CREATEDDATE = "createdDate";
	/** Column name for field displayName is "displayName" */
	static final String COLUMN_NAME_DISPLAYNAME = "displayName";
	/** Column name for field expireTime is "expireTime" */
	static final String COLUMN_NAME_EXPIRETIME = "expireTime";
	/** Column name for field imageUrl is "imageUrl" */
	static final String COLUMN_NAME_IMAGEURL = "imageUrl";
	/** Column name for field profileUrl is "profileUrl" */
	static final String COLUMN_NAME_PROFILEURL = "profileUrl";
	/** Column name for field providerId is "providerId" */
	static final String COLUMN_NAME_PROVIDERID = "providerId";
	/** Column name for field providerUserId is "providerUserId" */
	static final String COLUMN_NAME_PROVIDERUSERID = "providerUserId";
	/** Column name for field refreshToken is "refreshToken" */
	static final String COLUMN_NAME_REFRESHTOKEN = "refreshToken";
	/** Column name for field secret is "secret" */
	static final String COLUMN_NAME_SECRET = "secret";
	/** Column name for field updatedBy is "updatedBy" */
	static final String COLUMN_NAME_UPDATEDBY = "updatedBy";
	/** Column name for field updatedDate is "updatedDate" */
	static final String COLUMN_NAME_UPDATEDDATE = "updatedDate";
	/** Column name for field userRoles is "userRoles" */
	static final String COLUMN_NAME_USERROLES = "userRoles";

	/** The list of attribute names */
	static final List<String> COLUMN_NAMES = Arrays.asList(		COLUMN_NAME_APPARG0,
		COLUMN_NAME_CREATEDBY,
		COLUMN_NAME_CREATEDDATE,
		COLUMN_NAME_DISPLAYNAME,
		COLUMN_NAME_EXPIRETIME,
		COLUMN_NAME_IMAGEURL,
		COLUMN_NAME_PROFILEURL,
		COLUMN_NAME_PROVIDERID,
		COLUMN_NAME_PROVIDERUSERID,
		COLUMN_NAME_REFRESHTOKEN,
		COLUMN_NAME_SECRET,
		COLUMN_NAME_UPDATEDBY,
		COLUMN_NAME_UPDATEDDATE,
		COLUMN_NAME_USERROLES);
	/** The list of Basic attribute names */
	static final List<String> BASIC_NAMES = Arrays.asList(		COLUMN_NAME_APPARG0,
		COLUMN_NAME_CREATEDBY,
		COLUMN_NAME_CREATEDDATE,
		COLUMN_NAME_DISPLAYNAME,
		COLUMN_NAME_EXPIRETIME,
		COLUMN_NAME_IMAGEURL,
		COLUMN_NAME_PROFILEURL,
		COLUMN_NAME_PROVIDERID,
		COLUMN_NAME_PROVIDERUSERID,
		COLUMN_NAME_REFRESHTOKEN,
		COLUMN_NAME_SECRET,
		COLUMN_NAME_UPDATEDBY,
		COLUMN_NAME_UPDATEDDATE,
		COLUMN_NAME_USERROLES);
	/** The list of attribute names */
	static final List<String> MANY_TO_ONE_NAMES = Arrays.asList();


	// ----------------------- parent finder -------------------------------
	/**
	 * query-by method for parent field userKey
	 * @param userKey the specified attribute
	 * @return an Iterable of DConnections with the specified parent
	 */
	Iterable<DConnection> queryByUserKey(Object userKey);
		
	/**
	 * query-keys-by method for parent field userKey
	 * @param userKey the specified attribute
	 * @return an Iterable of DConnections with the specified parent
	 */
	Iterable<java.lang.String> queryKeysByUserKey(Object userKey);

	/**
	 * query-page-by method for parent field userKey
	 * @param userKey the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DConnections for the specified userKey (parent)
	 */
	CursorPage<DConnection, java.lang.String> queryPageByUserKey(java.lang.Object userKey,
            int pageSize, String cursorString);

	// ----------------------- field finders -------------------------------
	/**
	 * query-by method for field appArg0
	 * @param appArg0 the specified attribute
	 * @return an Iterable of DConnections for the specified appArg0
	 */
	Iterable<DConnection> queryByAppArg0(java.lang.String appArg0);
		
	/**
	 * query-keys-by method for field appArg0
	 * @param appArg0 the specified attribute
	 * @return an Iterable of DConnections for the specified appArg0
	 */
	Iterable<java.lang.String> queryKeysByAppArg0(java.lang.String appArg0);

	/**
	 * query-page-by method for field appArg0
	 * @param appArg0 the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DConnections for the specified appArg0
	 */
	CursorPage<DConnection, java.lang.String> queryPageByAppArg0(java.lang.String appArg0,
            int pageSize, String cursorString);


	/**
	 * query-by method for field createdBy
	 * @param createdBy the specified attribute
	 * @return an Iterable of DConnections for the specified createdBy
	 */
	Iterable<DConnection> queryByCreatedBy(java.lang.String createdBy);
		
	/**
	 * query-keys-by method for field createdBy
	 * @param createdBy the specified attribute
	 * @return an Iterable of DConnections for the specified createdBy
	 */
	Iterable<java.lang.String> queryKeysByCreatedBy(java.lang.String createdBy);

	/**
	 * query-page-by method for field createdBy
	 * @param createdBy the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DConnections for the specified createdBy
	 */
	CursorPage<DConnection, java.lang.String> queryPageByCreatedBy(java.lang.String createdBy,
            int pageSize, String cursorString);


	/**
	 * query-by method for field createdDate
	 * @param createdDate the specified attribute
	 * @return an Iterable of DConnections for the specified createdDate
	 */
	Iterable<DConnection> queryByCreatedDate(java.util.Date createdDate);
		
	/**
	 * query-keys-by method for field createdDate
	 * @param createdDate the specified attribute
	 * @return an Iterable of DConnections for the specified createdDate
	 */
	Iterable<java.lang.String> queryKeysByCreatedDate(java.util.Date createdDate);

	/**
	 * query-page-by method for field createdDate
	 * @param createdDate the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DConnections for the specified createdDate
	 */
	CursorPage<DConnection, java.lang.String> queryPageByCreatedDate(java.util.Date createdDate,
            int pageSize, String cursorString);


	/**
	 * query-by method for field displayName
	 * @param displayName the specified attribute
	 * @return an Iterable of DConnections for the specified displayName
	 */
	Iterable<DConnection> queryByDisplayName(java.lang.String displayName);
		
	/**
	 * query-keys-by method for field displayName
	 * @param displayName the specified attribute
	 * @return an Iterable of DConnections for the specified displayName
	 */
	Iterable<java.lang.String> queryKeysByDisplayName(java.lang.String displayName);

	/**
	 * query-page-by method for field displayName
	 * @param displayName the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DConnections for the specified displayName
	 */
	CursorPage<DConnection, java.lang.String> queryPageByDisplayName(java.lang.String displayName,
            int pageSize, String cursorString);


	/**
	 * query-by method for field expireTime
	 * @param expireTime the specified attribute
	 * @return an Iterable of DConnections for the specified expireTime
	 */
	Iterable<DConnection> queryByExpireTime(java.util.Date expireTime);
		
	/**
	 * query-keys-by method for field expireTime
	 * @param expireTime the specified attribute
	 * @return an Iterable of DConnections for the specified expireTime
	 */
	Iterable<java.lang.String> queryKeysByExpireTime(java.util.Date expireTime);

	/**
	 * query-page-by method for field expireTime
	 * @param expireTime the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DConnections for the specified expireTime
	 */
	CursorPage<DConnection, java.lang.String> queryPageByExpireTime(java.util.Date expireTime,
            int pageSize, String cursorString);


	/**
	 * query-by method for field imageUrl
	 * @param imageUrl the specified attribute
	 * @return an Iterable of DConnections for the specified imageUrl
	 */
	Iterable<DConnection> queryByImageUrl(java.lang.String imageUrl);
		
	/**
	 * query-keys-by method for field imageUrl
	 * @param imageUrl the specified attribute
	 * @return an Iterable of DConnections for the specified imageUrl
	 */
	Iterable<java.lang.String> queryKeysByImageUrl(java.lang.String imageUrl);

	/**
	 * query-page-by method for field imageUrl
	 * @param imageUrl the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DConnections for the specified imageUrl
	 */
	CursorPage<DConnection, java.lang.String> queryPageByImageUrl(java.lang.String imageUrl,
            int pageSize, String cursorString);


	/**
	 * query-by method for field profileUrl
	 * @param profileUrl the specified attribute
	 * @return an Iterable of DConnections for the specified profileUrl
	 */
	Iterable<DConnection> queryByProfileUrl(java.lang.String profileUrl);
		
	/**
	 * query-keys-by method for field profileUrl
	 * @param profileUrl the specified attribute
	 * @return an Iterable of DConnections for the specified profileUrl
	 */
	Iterable<java.lang.String> queryKeysByProfileUrl(java.lang.String profileUrl);

	/**
	 * query-page-by method for field profileUrl
	 * @param profileUrl the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DConnections for the specified profileUrl
	 */
	CursorPage<DConnection, java.lang.String> queryPageByProfileUrl(java.lang.String profileUrl,
            int pageSize, String cursorString);


	/**
	 * query-by method for field providerId
	 * @param providerId the specified attribute
	 * @return an Iterable of DConnections for the specified providerId
	 */
	Iterable<DConnection> queryByProviderId(java.lang.String providerId);
		
	/**
	 * query-keys-by method for field providerId
	 * @param providerId the specified attribute
	 * @return an Iterable of DConnections for the specified providerId
	 */
	Iterable<java.lang.String> queryKeysByProviderId(java.lang.String providerId);

	/**
	 * query-page-by method for field providerId
	 * @param providerId the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DConnections for the specified providerId
	 */
	CursorPage<DConnection, java.lang.String> queryPageByProviderId(java.lang.String providerId,
            int pageSize, String cursorString);


	/**
	 * query-by method for field providerUserId
	 * @param providerUserId the specified attribute
	 * @return an Iterable of DConnections for the specified providerUserId
	 */
	Iterable<DConnection> queryByProviderUserId(java.lang.String providerUserId);
		
	/**
	 * query-keys-by method for field providerUserId
	 * @param providerUserId the specified attribute
	 * @return an Iterable of DConnections for the specified providerUserId
	 */
	Iterable<java.lang.String> queryKeysByProviderUserId(java.lang.String providerUserId);

	/**
	 * query-page-by method for field providerUserId
	 * @param providerUserId the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DConnections for the specified providerUserId
	 */
	CursorPage<DConnection, java.lang.String> queryPageByProviderUserId(java.lang.String providerUserId,
            int pageSize, String cursorString);


	/**
	 * find-by method for unique field refreshToken
	 * @param refreshToken the unique attribute
	 * @return the unique DConnection for the specified refreshToken
	 */
	DConnection findByRefreshToken(java.lang.String refreshToken);

        /**
	 * find-key-by method for unique attribute field refreshToken
	 * @param refreshToken the unique attribute
	 * @return the unique DConnection for the specified attribute
	 */
	java.lang.String findKeyByRefreshToken(java.lang.String refreshToken);

	/**
	 * query-by method for field secret
	 * @param secret the specified attribute
	 * @return an Iterable of DConnections for the specified secret
	 */
	Iterable<DConnection> queryBySecret(java.lang.String secret);
		
	/**
	 * query-keys-by method for field secret
	 * @param secret the specified attribute
	 * @return an Iterable of DConnections for the specified secret
	 */
	Iterable<java.lang.String> queryKeysBySecret(java.lang.String secret);

	/**
	 * query-page-by method for field secret
	 * @param secret the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DConnections for the specified secret
	 */
	CursorPage<DConnection, java.lang.String> queryPageBySecret(java.lang.String secret,
            int pageSize, String cursorString);


	/**
	 * query-by method for field updatedBy
	 * @param updatedBy the specified attribute
	 * @return an Iterable of DConnections for the specified updatedBy
	 */
	Iterable<DConnection> queryByUpdatedBy(java.lang.String updatedBy);
		
	/**
	 * query-keys-by method for field updatedBy
	 * @param updatedBy the specified attribute
	 * @return an Iterable of DConnections for the specified updatedBy
	 */
	Iterable<java.lang.String> queryKeysByUpdatedBy(java.lang.String updatedBy);

	/**
	 * query-page-by method for field updatedBy
	 * @param updatedBy the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DConnections for the specified updatedBy
	 */
	CursorPage<DConnection, java.lang.String> queryPageByUpdatedBy(java.lang.String updatedBy,
            int pageSize, String cursorString);


	/**
	 * query-by method for field updatedDate
	 * @param updatedDate the specified attribute
	 * @return an Iterable of DConnections for the specified updatedDate
	 */
	Iterable<DConnection> queryByUpdatedDate(java.util.Date updatedDate);
		
	/**
	 * query-keys-by method for field updatedDate
	 * @param updatedDate the specified attribute
	 * @return an Iterable of DConnections for the specified updatedDate
	 */
	Iterable<java.lang.String> queryKeysByUpdatedDate(java.util.Date updatedDate);

	/**
	 * query-page-by method for field updatedDate
	 * @param updatedDate the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DConnections for the specified updatedDate
	 */
	CursorPage<DConnection, java.lang.String> queryPageByUpdatedDate(java.util.Date updatedDate,
            int pageSize, String cursorString);


	/**
	 * query-by method for field userRoles
	 * @param userRoles the specified attribute
	 * @return an Iterable of DConnections for the specified userRoles
	 */
	Iterable<DConnection> queryByUserRoles(java.lang.String userRoles);
		
	/**
	 * query-keys-by method for field userRoles
	 * @param userRoles the specified attribute
	 * @return an Iterable of DConnections for the specified userRoles
	 */
	Iterable<java.lang.String> queryKeysByUserRoles(java.lang.String userRoles);

	/**
	 * query-page-by method for field userRoles
	 * @param userRoles the specified attribute
         * @param pageSize the number of domain entities in the page
         * @param cursorString non-null if get next page
	 * @return a Page of DConnections for the specified userRoles
	 */
	CursorPage<DConnection, java.lang.String> queryPageByUserRoles(java.lang.String userRoles,
            int pageSize, String cursorString);


		  
	// ----------------------- one-to-one finders -------------------------

	// ----------------------- many-to-one finders -------------------------
	
	// ----------------------- many-to-many finders -------------------------

	// ----------------------- uniqueFields finders -------------------------
	
	
	// ----------------------- populate / persist method -------------------------

	/**
	 * Persist an entity given all attributes
	 */
	DConnection persist(Object userKey,  	
		java.lang.String id, 
		java.lang.String appArg0, 
		java.lang.String displayName, 
		java.util.Date expireTime, 
		java.lang.String imageUrl, 
		java.lang.String profileUrl, 
		java.lang.String providerId, 
		java.lang.String providerUserId, 
		java.lang.String refreshToken, 
		java.lang.String secret, 
		java.lang.String userRoles);	

	/**
	 * Persists an entity unless it already exists
	 */
	 DConnection persist(java.lang.String refreshToken, 
                java.lang.String appArg0, 
                java.lang.String displayName, 
                java.util.Date expireTime, 
                java.lang.String imageUrl, 
                java.lang.String profileUrl, 
                java.lang.String providerId, 
                java.lang.String providerUserId, 
                java.lang.String secret, 
                java.lang.String userRoles);

}
