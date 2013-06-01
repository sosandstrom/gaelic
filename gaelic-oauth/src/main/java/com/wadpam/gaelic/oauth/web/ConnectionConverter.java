/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.oauth.web;

import static com.wadpam.gaelic.converter.BaseConverter.toLong;
import static com.wadpam.gaelic.converter.BaseConverter.toString;
import com.wadpam.gaelic.converter.MardaoConverter;
import static com.wadpam.gaelic.converter.MardaoConverter.convertLongEntity;
import com.wadpam.gaelic.oauth.domain.DConnection;
import com.wadpam.gaelic.oauth.json.JConnection;
import net.sf.mardao.core.dao.Dao;
import net.sf.mardao.core.domain.AbstractLongEntity;

/**
 *
 * @author sosandstrom
 */
public class ConnectionConverter extends MardaoConverter<JConnection, DConnection> {
    
    private static Dao<AbstractLongEntity, Long> userDao;

    public ConnectionConverter(Dao userDao) {
        super(JConnection.class, DConnection.class);
        ConnectionConverter.userDao = userDao;
    }

    @Override
    public void convertDomain(DConnection from, JConnection to) {
        convertDConnection(from, to);
    }
    
    public static void convertDConnection(DConnection from, JConnection to) {
        convertLongEntity(from, to);
        to.setDisplayName(from.getDisplayName());
        to.setExpireTime(toLong(from.getExpireTime()));
        to.setImageUrl(from.getImageUrl());
        to.setProfileUrl(from.getProfileUrl());
        to.setProviderId(from.getProviderId());
        to.setProviderUserId(from.getProviderUserId());
        to.setRefreshToken(from.getRefreshToken());
        to.setSecret(from.getSecret());
        to.setUserId(toString(userDao.getSimpleKeyByPrimaryKey(from.getUserKey())));
        to.setUserRoles(from.getUserRoles());
    }

    @Override
    public void convertJson(JConnection from, DConnection to) {
        convertJConnection(from, to);
    }

    public static void convertJConnection(JConnection from, DConnection to) {
        convertJLong(from, to);
        to.setDisplayName(from.getDisplayName());
        to.setExpireTime(toDate(from.getExpireTime()));
        to.setImageUrl(from.getImageUrl());
        to.setProfileUrl(from.getProfileUrl());
        to.setProviderId(from.getProviderId());
        to.setProviderUserId(from.getProviderUserId());
        to.setRefreshToken(from.getRefreshToken());
        to.setSecret(from.getSecret());
        to.setUserKey(userDao.getPrimaryKey(null, toLong(from.getUserId())));
        to.setUserRoles(from.getUserRoles());
    }
}
