#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*
 * INSERT COPYRIGHT HERE
 */

package ${package}.config;

import ${package}.service.SampleService;
import ${package}.web.SampleLeaf;
import com.wadpam.gaelic.GaelicConfig;
import com.wadpam.gaelic.GaelicServlet;
import com.wadpam.gaelic.Node;
import com.wadpam.gaelic.oauth.dao.DConnectionDao;
import com.wadpam.gaelic.oauth.dao.DOAuth2UserDao;
import com.wadpam.gaelic.oauth.service.ConnectionServiceImpl;
import com.wadpam.gaelic.oauth.service.OAuth2ServiceImpl;
import com.wadpam.gaelic.oauth.service.OAuth2UserServiceImpl;
import com.wadpam.gaelic.oauth.web.ConnectionConverter;
import com.wadpam.gaelic.oauth.web.OAuth2Interceptor;
import com.wadpam.gaelic.oauth.web.OAuth2Leaf;
import com.wadpam.gaelic.oauth.web.UserLeaf;
import com.wadpam.gaelic.security.SecurityConfig;
import com.wadpam.gaelic.tree.Interceptor;
import com.wadpam.gaelic.tree.InterceptorAdapter;
import java.util.Collection;
import java.util.Map;
import javax.servlet.ServletConfig;

/**
 *
 * @author sosandstrom
 */
public class AppConfig implements GaelicConfig, SecurityConfig {

    @Override
    public Node init(GaelicServlet gaelicServlet, ServletConfig servletConfig) {
        // DAO beans
        
        // services
        ConnectionServiceImpl connectionService = new ConnectionServiceImpl();
        DConnectionDao connectionDao = connectionService.getDao();
        
        OAuth2UserServiceImpl userService = new OAuth2UserServiceImpl();
        DOAuth2UserDao userDao = userService.getDao();
        
        OAuth2ServiceImpl oauth2Service = new OAuth2ServiceImpl();
        oauth2Service.setConnectionDao(connectionDao);
        oauth2Service.setOauth2UserService(userService);
        
        // Converters
        ConnectionConverter connectionConverter = new ConnectionConverter(userDao);
        
        // Resources
        UserLeaf userLeaf = new UserLeaf();
        userLeaf.setService(userService);
        
        SampleService sampleService = new SampleService();
        SampleLeaf sampleLeaf = new SampleLeaf();
        sampleLeaf.setService(sampleService);
        
        OAuth2Leaf oauth2Leaf = new OAuth2Leaf();
        oauth2Leaf.setService(oauth2Service);
        
        // Interceptors
        Interceptor basicInterceptor = new InterceptorAdapter();
//                DomainSecurityInterceptor basicInterceptor = new DomainSecurityInterceptor();
        OAuth2Interceptor oauth2Interceptor = new OAuth2Interceptor();
        oauth2Interceptor.setConnectionService(connectionService);
        oauth2Interceptor.setOauth2Service(oauth2Service);
        
        initSecurity(basicInterceptor, oauth2Interceptor);
        
        BUILDER.root()
                .interceptor("api", basicInterceptor)
                .interceptedPath("api", oauth2Interceptor)
                    .path(Node.PATH_DOMAIN)
                        .path("_admin")
                            .path("user")
                                .crud("v10", userLeaf, userService)
                        .from("_admin")
                            .path("sample")
                                .crud("v10", sampleLeaf, sampleService)
                    .from(Node.PATH_DOMAIN)
                        .path("federated")
                            .add("v11", oauth2Leaf);
        
        
        return BUILDER.build();
    }

    private void initSecurity(Interceptor basicInterceptor, OAuth2Interceptor oauth2Interceptor) {
        Collection<Map.Entry<String, Collection<String>>> oauth2Whitelist = 
                WHITELIST_BUILDER.with("${symbol_escape}${symbol_escape}A/api/[^/]+/federated/v1.${symbol_escape}${symbol_escape}z", GET, POST)
                    .build();
        
        oauth2Interceptor.setWhitelistedMethods(oauth2Whitelist);
    }

}
