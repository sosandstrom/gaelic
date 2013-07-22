Gaelic
======

REST framework for Google App Engine

Generate starter project using Maven Archetype
----------------------------------------------
    
    mvn archetype:generate -DarchetypeGroupId=com.wadpam.gaelic -DarchetypeArtifactId=gaelic-archetype-starter -DarchetypeVersion=1.0.6
    
If this is your first time using this App Engine SDK of the version, download and unpack the SDK by typing
    
    mvn gae:unpack
    
in the generated projects directory. Then, build your project and start your development server with
    
    mvn clean gae:run
    
Introduction
------------

Gaelic was created when we wanted to replace Spring framework with something
more suitable for Google App Engine. We had suffered bad from (too) long initialization
times (loading requests), and needed something that initializes _very_ quickly.

We tried Jersey, but decided to give up the following:
* Annotation-based mapping of resources / controllers
* Compatibility with non-GAE environments

and push hard for
* Convention over Configuration
* Very few dependencies

Supported Features
==================
* [Tree-style mapping of REST URLs](#application-configuration) to resources in Configuration
* [Spring-style Interceptors](#interceptors) with flexible "point-cuts"
* [Security](#security) out-of-the-box with Basic Authentication and OAuth2
* [CRUD resources](#crud-resources) with Leaf, Service and mardao Dao
* [App Domain](#app-domain) management mapping to GAE Namespace
* [OAuth2 Provider](#oauth2-provider) implementation

and non-functional requirements:

* Core dependency only to Jackson and Slf4j
* Security adds dependency to commons-codec

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
* the Security interceptor is mapped to /api/* requests
* endpoints is a protected resource
* GET public has public access
* task should be Container-based secured via web.xml

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
        
CRUD Resources
---------------
There is a CrudLeaf you can extend or map in the tree, to get out-of-the-box
methods for the basic CRUD operations:
* `GET /` a page of entities
* `GET /{id}` a specific entity
* `POST /` to create a new entity
* `POST /{id}` to update a specific entity
* `DELETE /{id}` to delete a specific entity

There is also a CrudService implementation with connects your Resource with the
Datastore using Mardao.

App Domain
---------------
With the concept of App Domains, you can host for multiple, separated customers
from the same App Engine application. Their data is separated (multi-tenancy) by
using Namespaces. Convention is to select domain via the REST path, e.g.

    http://example.appspot.com/api/bmw/user
    
where the App Domain would be named bmw. This App Domain entity also has properties
for username and password, so you can protect some API methods within /api/bmw/ with
Basic Authentication.
An App Domain also has a tracking code property, allowing to send tracking for /api/bmw
to a bmw-dedicated Analytics Account (e.g. Google Analytics).

OAuth2 Provider
---------------
Look at the sub-module `gaelic-oauth2-provider` [README](https://github.com/sosandstrom/gaelic/tree/master/gaelic-oauth2-provider) page for more information on OAuth2 Provider implementation.