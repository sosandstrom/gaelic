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

Supported Features
==================
* [Tree-style mapping of resources in Configuration](#application-configuration)
* [Path parameters with {paramname} style mapping](#path-parameters)
* [Spring-style Interceptors](#interceptors)

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
    
    public class UnitTestConfig implements GaelicConfig {

        @Override
        public Node init(GaelicServlet gaelicServlet, ServletConfig servletConfig) {

            // add /api/{domain}
            BUILDER.root()
                .path("api")
                    .interceptedPath("{domain}", new InterceptorAdapter())
                        // add /endpoints
                        .add("endpoints", MethodUriLeaf.class).named("getEndpoints()");

                    // add /interceptor/{boolean}
                    BUILDER.from("{domain}")
                        .interceptedPath("interceptor", new UnitTestInterceptor()).named("appendURI")
                            .add("true", MethodUriLeaf.class).named("bool");
                        BUILDER.from("interceptor")
                            .add("false", "bool");

            return BUILDER.build();
        }

    }
    
It creates and maps the following resource tree:

* /api
* /api/{domain}
* /api/{domain}/endpoints
* /api/{domain}/interceptor
* /api/{domain}/interceptor/true
* /api/{domain}/interceptor/false

Path Parameters
---------------


Interceptors
------------

