Gaelic OAuth2 Provider
======================

Lightweight implementation of an OAuth2 Provider, based on Gaelic

Supported Features
==================
* REST API to create and manage user profiles
* REST API to create and manage OAuth2 clients
* Obtaining Implicit Grant access_token
* Obtaining Code Grant
* Exchanging code for token (access and refresh)
* Refresh access_token using refresh_token

Application Configuration
-------------------------
The `GaelicServlet` requires an implementation of the `GaelicConfig` to be specified
as an init-param in web.xml:
    
    <servlet>
        <servlet-name>GaelicServlet</servlet-name>
        <servlet-class>com.wadpam.gaelic.GaelicServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
        <init-param> 
            <param-name>com.wadpam.gaelic.Config</param-name> 
            <param-value>com.example.config.AppConfig</param-value> 
        </init-param> 
    </servlet>
    <servlet-mapping>
        <servlet-name>GaelicServlet</servlet-name>
        <url-pattern>/accounts/*</url-pattern>
    </servlet-mapping>
    
The Configuration is instantiated and the 
    
        Node init(GaelicServlet gaelicServlet, ServletConfig servletConfig);
        
method is invoked. It should return your one root `Node` (subclass) instance.

Here's a simple Config example:
    
    public class UnitTestConfig implements GaelicConfig, SecurityConfig {

        @Override
        public Node init(GaelicServlet gaelicServlet, ServletConfig servletConfig) {

            // Create Leaves and services for OAuth2 Provider:
            final Map<Class, Object> LEAF_MAP = ProviderConfig.createLeaves();

            DomainNamespaceInterceptor domainNamespaceInterceptor = new DomainNamespaceInterceptor();

            SecurityInterceptor basicClientInterceptor = (SecurityInterceptor) LEAF_MAP.get(SecurityInterceptor.class);
            basicClientInterceptor.setWhitelistedMethods(createBasicWhitelist());

            OAuth2Interceptor oauth2ProviderInterceptor = (OAuth2Interceptor) LEAF_MAP.get(OAuth2Interceptor.class);
            oauth2ProviderInterceptor.setWhitelistedMethods(createOAuth2ProviderWhitelist());

            BUILDER.root()
                // your back-end part of the URL tree:
                    .path("api")
                        .interceptedPath("{domain}", domainNamespaceInterceptor)
                            .add("leaf", MethodUriLeaf.class).named("getPage()");

                // the OAuth2 Provider part of the URL tree:
                BUILDER.from("root")
                    .interceptor("accounts", domainNamespaceInterceptor)
                    .interceptedPath("accounts", basicClientInterceptor)
                        .interceptedPath("{tenant}", oauth2ProviderInterceptor)
                            .interceptedPath("oauth", new MardaoPrincipalInterceptor())
                                .add("authorize", (Node) LEAF_MAP.get(AuthorizeEndpointLeaf.class)).named("AuthorizeEndpoint");
                            BUILDER.from("oauth")
                                .add("token", (Node) LEAF_MAP.get(TokenEndpointLeaf.class)).named("TokenEndpoint");
                            BUILDER.from("oauth")
                                .path("profile")
                                    .add("v10", (Node) LEAF_MAP.get(ProfileLeaf.class)).named("Resource Owner")
                            .from("oauth")
                                .path("client")
                                    .add("v10", (Node) LEAF_MAP.get(ClientLeaf.class)).named("Consumer Client")
                            .from("oauth")
                                .add("login.html", (Node) LEAF_MAP.get(ForwardLeaf.class)).named("Sign in");

            initDevServer(LEAF_MAP);

            return BUILDER.build();
        }

        protected Collection<Map.Entry<String, Collection<String>>> createBasicWhitelist() {
            return WHITELIST_BUILDER.with("\\A/accounts/[^/]+/oauth/profile/", GET, POST, DELETE)
                    .add("\\A/accounts/[^/]+/oauth/client/v", DELETE, GET, POST)
                    .add("\\A/accounts/[^/]+/oauth/login.html", DELETE, GET, POST)
                    .add("\\A/accounts/[^/]+/oauth/authorize", DELETE, GET, POST)
                    .build();
        }

        protected Collection<Map.Entry<String, Collection<String>>> createOAuth2Whitelist() {
            return WHITELIST_BUILDER.with("\\A/accounts/[^/]+/oauth/profile/v10\\z", POST)
                    .add("\\A/accounts/[^/]+/oauth/client/v", DELETE, GET, POST)
                    .add("\\A/accounts/[^/]+/oauth/login.html", DELETE, GET, POST)
                    .add("\\A/accounts/[^/]+/oauth/authorize", DELETE, GET, POST)
                    .add("\\A/accounts/[^/]+/oauth/token\\z", POST)
                    .build();
        }

        private void initDevServer(Map<Class, Object> LEAF_MAP) {
            if (SystemProperty.Environment.Value.Development == SystemProperty.environment.value()) {

                // create a client id 4004
                ClientLeaf clientLeaf = (ClientLeaf) LEAF_MAP.get(ClientLeaf.class);
                ClientService clientService = clientLeaf.getService();
                Do2pClientDao clientDao = clientService.getDao();
                clientDao.persist(4004L, "Localhost client", "localDev", 
                        "http://localhost/RestTest/oauth/gaelic.html", "topsecret");

                // create a profile to allow test sign in
                ProfileLeaf profileLeaf = (ProfileLeaf) LEAF_MAP.get(ProfileLeaf.class);
                ProfileService profileService = profileLeaf.getService();
                Do2pProfileDao profileDao = profileService.getDao();
                final String secret = ProviderService.encryptPassword("john.doe@acme.com", 7007L);
                profileDao.persist(7007L, "john.doe@acme.com", secret, 1L, "john.doe@acme.com");
            }
        }

    }
    
For reference, the ProviderConfig method looks like this:
    
    public static Map<Class, Object> createLeaves() {
        final Map<Class, Object> LEAF_MAP= new HashMap<Class, Object>();
        final Map<Class, DaoImpl> DAO_MAP = DaoConfig.createDaos();
        
        Do2pClientDao clientDao = (Do2pClientDaoBean) DAO_MAP.get(Do2pClient.class);
        ClientService clientService = new ClientService();
        clientService.setDao(clientDao);
        
        Do2pProfileDao profileDao = (Do2pProfileDaoBean) DAO_MAP.get(Do2pProfile.class);
        ProfileService profileService = new ProfileService();
        profileService.setDao(profileDao);
        
        Do2pTokenDao tokenDao = (Do2pTokenDaoBean) DAO_MAP.get(Do2pToken.class);
        
        ProviderService providerService = new ProviderService();
        providerService.setClientDao(clientDao);
        providerService.setProfileDao(profileDao);
        providerService.setTokenDao(tokenDao);
        LEAF_MAP.put(ProviderService.class, providerService);
        
        ProfileLeaf profileLeaf = new ProfileLeaf();
        profileLeaf.setService(profileService);
        LEAF_MAP.put(ProfileLeaf.class, profileLeaf);
        
        ClientLeaf clientLeaf = new ClientLeaf();
        clientLeaf.setService(clientService);
        LEAF_MAP.put(ClientLeaf.class, clientLeaf);
        
        AuthorizeEndpointLeaf authorizeEndpointLeaf = new AuthorizeEndpointLeaf();
        authorizeEndpointLeaf.setProviderService(providerService);
        LEAF_MAP.put(AuthorizeEndpointLeaf.class, authorizeEndpointLeaf);
        
        TokenEndpointLeaf tokenEndpointLeaf = new TokenEndpointLeaf();
        tokenEndpointLeaf.setProviderService(providerService);
        LEAF_MAP.put(TokenEndpointLeaf.class, tokenEndpointLeaf);
        
        // Basic Authentication interceptor protecting /oauth/token
        SecurityInterceptor basicInterceptor = new SecurityInterceptor();
        basicInterceptor.setSecurityDetailsService(clientService);
        LEAF_MAP.put(SecurityInterceptor.class, basicInterceptor);
        
        // OAuth2 interceptor protecting /oauth/profile
        OAuth2Interceptor oauth2Interceptor = new OAuth2Interceptor();
        oauth2Interceptor.setSecurityDetailsService(providerService);
        LEAF_MAP.put(OAuth2Interceptor.class, oauth2Interceptor);
        
        // forwards to login.html
        ForwardLeaf forwardLeaf = new ForwardLeaf("/internal/login.html");
        LEAF_MAP.put(ForwardLeaf.class, forwardLeaf);
        
        return LEAF_MAP;
    }
    
