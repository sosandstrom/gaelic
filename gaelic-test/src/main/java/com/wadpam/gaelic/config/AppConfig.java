/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.config;

import com.wadpam.gaelic.GaelicConfig;
import com.wadpam.gaelic.GaelicServlet;
import com.wadpam.gaelic.Node;
import com.wadpam.gaelic.oauth.provider.dao.DaoConfig;
import com.wadpam.gaelic.oauth.provider.dao.Do2pProfileDao;
import com.wadpam.gaelic.oauth.provider.dao.Do2pProfileDaoBean;
import com.wadpam.gaelic.oauth.provider.domain.Do2pProfile;
import com.wadpam.gaelic.oauth.provider.service.ProfileService;
import com.wadpam.gaelic.oauth.provider.service.ProviderService;
import com.wadpam.gaelic.oauth.provider.tree.AuthorizeLeaf;
import com.wadpam.gaelic.oauth.provider.tree.ProfileLeaf;
import com.wadpam.gaelic.tree.InterceptorAdapter;
import com.wadpam.gaelic.tree.MethodUriLeaf;
import java.util.Map;
import javax.servlet.ServletConfig;
import net.sf.mardao.core.dao.DaoImpl;

/**
 *
 * @author sosandstrom
 */
public class AppConfig implements GaelicConfig {

    @Override
    public Node init(GaelicServlet gaelicServlet, ServletConfig servletConfig) {
        final Map<Class, DaoImpl> DAO_MAP = DaoConfig.createDaos();
        
        Do2pProfileDao profileDao = (Do2pProfileDaoBean) DAO_MAP.get(Do2pProfile.class);
        ProfileService profileService = new ProfileService();
        profileService.setDao(profileDao);
        ProviderService providerService = new ProviderService();
        providerService.setProfileDao(profileDao);
        
        ProfileLeaf profileLeaf = new ProfileLeaf();
        
        AuthorizeLeaf authorizeLeaf = new AuthorizeLeaf();
        authorizeLeaf.setProviderService(providerService);
        
        BUILDER.root()
                .path("api")
                    .interceptedPath("{domain}", new InterceptorAdapter())
                        .add("leaf", MethodUriLeaf.class).named("getPage()")
                    .from(Node.PATH_DOMAIN)
                        .path("profile")
                            .crud("v10", profileLeaf, profileService)
            .from("root")
                .path("oauth")
                    .add("authorize", authorizeLeaf).named("AuthorizeEndpoint");
        
        return BUILDER.build();
    }

}
