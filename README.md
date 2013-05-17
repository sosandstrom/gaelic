Gaelic
======

REST service framework for Google App Engine

Introduction
------------

Gaelic was created when we wanted to replace Spring framework with something
more suitable for Google App Engine. We had suffered bad from (too) long initialization
times (loading requests), and needed something that initializes _very_ quickly.

We tried Jersey, but decided to give up the following:
* Annotation-based mapping of resources / controllers
* Compatibility with non-GAE environments

Generate using Maven Archetype
------------------------------
    
    mvn archetype:generate -DarchetypeGroupId=com.wadpam.gaelic -DarchetypeArtifactId=gaelic-archetype-starter -DarchetypeVersion=1.0.4
    
If this is your first time using this App Engine SDK of the version, download and unpack the SDK by typing
    
    mvn gae:unpack
    
in the generated projects directory. Then, build your project and start your development server with
    
    mvn clean gae:run
    

Supported Features
==================
* [Tree-style mapping of resources in Configuration](#application-configuration)
* [Path parameters with {paramname} style mapping](#path-parameters)
* [Spring-style Interceptors](#interceptors)
* [Security](#security) out-of-the-box with Basic Authentication and OAuth2

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
    
The Configuration is instantiated and the 
    
        Node init(GaelicServlet gaelicServlet, ServletConfig servletConfig);
        
method is invoked. It should return your one root `Node` (subclass) instance.

Here's a simple Config example:
    
    public class UnitTestConfig implements GaelicConfig, SecurityConfig {

        @Override
        public Node init(GaelicServlet gaelicServlet, ServletConfig servletConfig) {

            DomainSecurityInterceptor securityInterceptor = new DomainSecurityInterceptor();

            SecurityDetailsService detailsService = new UnitTestDetailsService();
            securityInterceptor.setSecurityDetailsService(detailsService);

            Collection<Map.Entry<String, Collection<String>>> basicWhitelist = WHITELIST_BUILDER
                    .with("\\A/api/[^/]+/public\\z", GET)
                    .build();
            securityInterceptor.setWhitelistedMethods(basicWhitelist);

            // add /api/{domain}
            BUILDER.root()
                .path("api")
                    .interceptedPath("{domain}", securityInterceptor)
                        // add /endpoints
                        .add("endpoints", MethodUriLeaf.class).named("getEndpoints()");

            BUILDER.from("{domain}")
                        // add public whitelisted
                        .add("public", MethodUriLeaf.class).named("public");

            BUILDER.from("{domain}")
                        // add _admin
                        .path("_admin")
                            .add("task", MethodUriLeaf.class).named("AdminTask");

            return BUILDER.build();
        }

    }
    
It creates and maps the following resource tree:

* /api
* /api/{domain}
* /api/{domain}/endpoints
* /api/{domain}/public
* /api/{domain}/_admin/task

Where 
* endpoints is a protected resource
* GET public has public access
* task should be Container-based secured via web.xml

Path Parameters
---------------


Interceptors
------------
Interceptors are implemented either by implementing the `Interceptor` interface,
or by extending the `InterceptorAdapter` class.
The interceptors should be injected into either `InterceptorDelegate` node,
or into `InterceptedPath` path.

Security
------------
Three security interceptors are provided by the Gaelic framework:
    
    SecurityInterceptor extends InterceptorAdapter
    + - DomainSecurityInterceptor extends SecurityInterceptor
        + - OAuth2Interceptor extends DomainSecurityInterceptor
        
