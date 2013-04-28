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
            final AbstractPath root = new AbstractPath();
            root.setName("root");

            final AbstractPath api = new AbstractPath();
            api.setName("api");
            root.addChild("api", api);

            final InterceptorAdapter domain = new InterceptorAdapter();
            domain.setName("{domain}");
            api.addChild("{domain}", domain);

            final MethodUriLeaf endpoints = new MethodUriLeaf();
            endpoints.setName("getEndpoints()");
            domain.addChild("endpoints", endpoints);

            final UnitTestInterceptor interceptor = new UnitTestInterceptor();
            interceptor.setName("appendURI");
            domain.addChild("interceptor", interceptor);
            final MethodUriLeaf bool = new MethodUriLeaf("bool");
            interceptor.addChild("true", bool);
            interceptor.addChild("false", bool);

            return root;
        }

    }
    
It creates and maps the following resource tree:

* /api
* * /{domain}
* * * /endpoints
* * * /interceptor
* * * * /true
* * * * /false
