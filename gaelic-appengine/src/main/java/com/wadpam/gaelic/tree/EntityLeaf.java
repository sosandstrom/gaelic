/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.tree;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.EmbeddedEntity;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.PropertyContainer;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Text;
import com.wadpam.gaelic.GaelicServlet;
import com.wadpam.gaelic.Node;
import static com.wadpam.gaelic.Node.METHOD_DELETE;
import static com.wadpam.gaelic.Node.METHOD_GET;
import static com.wadpam.gaelic.Node.METHOD_POST;
import static com.wadpam.gaelic.Node.getPathVariable;
import com.wadpam.gaelic.exception.BadRequestException;
import com.wadpam.gaelic.exception.ConflictException;
import com.wadpam.gaelic.exception.NotFoundException;
import com.wadpam.gaelic.json.JEntity;
import com.wadpam.gaelic.json.JKey;
import static com.wadpam.gaelic.tree.CrudLeaf.REQUEST_ATTR_FILENAME;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sosandstrom
 */
public class EntityLeaf extends Node {
    
    public static final String PATH_KIND = "{kind}";
    
    public static final int ERROR_BASE = GaelicServlet.ERROR_CODE_ENTITY_BASE;
    public static final int ERROR_DELETE_BAD_REQUEST = ERROR_BASE + 0;
    public static final int ERROR_GET_NOT_FOUND = ERROR_BASE + 4;
    public static final int ERROR_GET_FILTER_TYPE = ERROR_BASE + 5;
    public static final int ERROR_KIND_CONFLICT = ERROR_BASE + 9;
    public static final int ERROR_ID_CONFLICT = ERROR_BASE + 19;
    
    public static final TreeSet<String> SUPPORTED_METHODS = 
            new TreeSet<String>(Arrays.asList(METHOD_DELETE, METHOD_GET, METHOD_POST));
    
    public static final char FILTER_EQUALS_STRING = 's';
    public static final char FILTER_EQUALS_INTEGER = 'i';
    public static final char FILTER_EQUALS_LONG = 'l';
    public static final char FILTER_EQUALS_DATE = 'd';
    /** String Filter Greater Than or Equal */
    public static final char FILTER_GTE_STRING = 'S';
    /** Integer Filter Greater Than or Equal */
    public static final char FILTER_GTE_INTEGER = 'I';
    /** Long Filter Greater Than or Equal */
    public static final char FILTER_GTE_LONG = 'L';
    /** Date Filter Greater Than or Equal */
    public static final char FILTER_GTE_DATE = 'D';
    
    public static final String FILTER_NULL_STRING = "NULL";
    
    public static final String REQUEST_ATTR_JKEY = "com.wadpam.gaelic.jKey";
    
    protected static JEntity convertEntity(Entity from) {
        JEntity to = new JEntity();
        convertKey(from.getKey(), to);
        convertProperties(from.getProperties(), to);
        
        return to;
    }

    protected static Entity convertJEntity(JEntity from) {
        final Key key = convertJKey(from);
        Entity to;
        if (null == key) {
            // with parent?
            final Key parent = convertJKey(from.getParentKey());
            to = null != parent ? new Entity(from.getKind(), parent) : new Entity(from.getKind());
        }
        else {
             to = new Entity(key); 
        }
        
        to.setPropertiesFrom(convertJProperties(from.getProperties(), from.getPropertyTypes()));
        
        return to;
    }
    
    protected static Key convertJKey(JKey from) {
        if (null == from) {
            return null;
        }
        
        if (null != from.getId() || null != from.getName()) {
            LOG.debug("parent:{}, kind:{}, id:{}, name:{}", new Object[] {
                from.getParentKey(), from.getKind(), from.getId(), from.getName()
            });

            final Key parent = convertJKey(from.getParentKey());

            if (null != from.getName()) {
                return KeyFactory.createKey(parent, from.getKind(), from.getName());
            }

            return KeyFactory.createKey(parent, from.getKind(), from.getId());
        }
        return null;
    }

    protected static PropertyContainer convertJProperties(Map<String, Object> properties, Map<String, String> propertyTypes) {
        EmbeddedEntity to = new EmbeddedEntity();
        Object value;
        String type;
        for (Entry<String,Object> entry : properties.entrySet()) {
            value = entry.getValue();
            
            if (null != propertyTypes && !propertyTypes.isEmpty()) {
                type = propertyTypes.get(entry.getKey());
                if (null != type) {
                    if (Date.class.getName().equals(type)) {
                        Long date = (Long) value;
                        value = new Date(date);
                    }
                }
            }
            
            if (value instanceof String && 500 <= ((String) value).length() ) {
                value = new Text((String) value);
            }
            
            to.setProperty(entry.getKey(), value);
        }
        
        return to;
    }
    
    protected static JKey convertKey(Key from) {
        if (null == from) {
            return null;
        }
        
        final JKey to = new JKey();
        convertKey(from, to);
        return to;
    }
        
    protected static void convertKey(Key from, JKey to) {
        to.setParentKey(convertKey(from.getParent()));
        if (null != from.getName()) {
            to.setName(from.getName());
        }
        else {
            to.setId(from.getId());
        }
        to.setKind(from.getKind());
    }

    protected static void convertProperties(Map<String, Object> from, JEntity body) {
        Map<String, Object> to = body.getProperties();
        if (null == to) {
            to = new TreeMap<String, Object>();
            body.setProperties(to);
        }
        
        Map<String, String> types = body.getPropertyTypes();
        if (null == types) {
            types = new TreeMap<String, String>();
            body.setPropertyTypes(types);
        }
        
        Object value;
        for (Entry<String, Object> entry : from.entrySet()) {
            value = entry.getValue();
            
            if (value instanceof Date) {
                types.put(entry.getKey(), Date.class.getName());
                value = ((Date) value).getTime();
            }
            
            to.put(entry.getKey(), value);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final JKey jKey = (JKey) request.getAttribute(REQUEST_ATTR_JKEY);
        
        if (null != jKey.getId() || null != jKey.getName()) {
            final DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
            final Key key = convertJKey(jKey);
            datastore.delete(key);
        }
        else {
            throw new BadRequestException(ERROR_DELETE_BAD_REQUEST, "Must include a primary key", null);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final JKey jKey = (JKey) request.getAttribute(REQUEST_ATTR_JKEY);
        
        if (null != jKey.getId() || null != jKey.getName()) {
            getEntity(request, response, jKey);
        }
        else {
            getEntities(request, response, jKey);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String kind = getPathVariable(PATH_KIND);
        final String filename = (String) request.getAttribute(REQUEST_ATTR_FILENAME);
        final JEntity jBody = getRequestBody(request);
        
        // check kind
        if (null == jBody.getKind())
        {
            jBody.setKind(kind);
        }
        if (!kind.equals(jBody.getKind())) {
            throw new ConflictException(ERROR_KIND_CONFLICT, String.format("%s != %s", kind, jBody.getKind()), null);
        }
        
        if (null != filename) {
            // Update is only a check of ID for consistency
            if (null == jBody.getId()) {
                jBody.setId(Long.parseLong(filename));
            }
            if (!filename.equals(jBody.getId().toString())) {
                throw new ConflictException(ERROR_ID_CONFLICT, String.format("%s != %s", filename, jBody.getId()), null);
            }
        }        
        putEntity(request, response, jBody);
    }
    
    protected void getEntities(HttpServletRequest request, HttpServletResponse response, JKey jKey) {
        final DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        final Query query = new Query(jKey.getKind());
        
        Map<String,String[]> params = request.getParameterMap();
        for (Entry<String,String[]> entry : params.entrySet()) {
            
            // simple filter
            if (1 == entry.getValue().length) {
                FilterOperator op = getFilterOperator(entry.getValue()[0]);
                query.setFilter(new Query.FilterPredicate(entry.getKey(), op, getFilterValue(entry.getValue()[0])));
            }
            else if (1 < entry.getValue().length) {
                
                // range  min <= value < max?
                if (2 == entry.getValue().length && 
                        FilterOperator.GREATER_THAN_OR_EQUAL.equals(getFilterOperator(entry.getValue()[0]))) {
                    
                    final Filter gteFilter = new Query.FilterPredicate(entry.getKey(), 
                            FilterOperator.GREATER_THAN_OR_EQUAL, getFilterValue(entry.getValue()[0]));
                    final Filter ltFilter = new Query.FilterPredicate(entry.getKey(), 
                            FilterOperator.LESS_THAN, getFilterValue(entry.getValue()[1]));
                    query.setFilter(CompositeFilterOperator.and(gteFilter, ltFilter));
                }
                else {
                    
                    // multiple values gives IN
                    final ArrayList values = new ArrayList();
                    for (String v : entry.getValue()) {
                        values.add(getFilterValue(v));
                    }
                    query.setFilter(new Query.FilterPredicate(entry.getKey(), Query.FilterOperator.IN, values));
                }
            }
        }
        
        PreparedQuery pq = datastore.prepare(query);
        Iterable<Entity> i = pq.asIterable();
        
        ArrayList<JEntity> body = new ArrayList<JEntity>();
        for (Entity entity : i) {
            body.add(convertEntity(entity));
        }
        
        setResponseBody(request, HttpServletResponse.SC_OK, body);
    }
    
    protected void getEntity(HttpServletRequest request, HttpServletResponse response, JKey jKey) {
        final DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        final Key key = convertJKey(jKey);
        try {
            final Entity entity = datastore.get(key);
            final JEntity body = convertEntity(entity);
            setResponseBody(request, HttpServletResponse.SC_OK, body);
        } catch (EntityNotFoundException ex) {
            throw new NotFoundException(ERROR_GET_NOT_FOUND, jKey.toString(), null);
        }
    }
    
    protected static FilterOperator getFilterOperator(String stringValue) {
        final char type = stringValue.charAt(0);
        switch (type) {
            case FILTER_GTE_STRING:
            case FILTER_GTE_INTEGER:
            case FILTER_GTE_LONG:
            case FILTER_GTE_DATE:
                return FilterOperator.GREATER_THAN_OR_EQUAL;
        }
        
        return FilterOperator.EQUAL;
    }
        
    protected static Object getFilterValue(String stringValue) {
        final char type = stringValue.charAt(0);
        final String sub = stringValue.substring(1);
        if (FILTER_NULL_STRING.equals(sub)) {
            return null;
        }
        
        switch (type) {
            case FILTER_EQUALS_STRING:
                return  sub;
            case FILTER_EQUALS_INTEGER:
                return Integer.parseInt(sub);
            case FILTER_EQUALS_LONG:
                return Long.parseLong(sub);
            case FILTER_EQUALS_DATE:
                return new Date(Long.parseLong(sub));
        }
        
        throw new BadRequestException(ERROR_GET_FILTER_TYPE, String.format("No such filter value type {}", type), null);
    }

    protected JEntity getRequestBody(HttpServletRequest request) throws IOException {
        JEntity body = null;
        
        if (request.getContentType().startsWith(GaelicServlet.MEDIA_TYPE_JSON)) {
            ServletInputStream in = request.getInputStream();
            body = (JEntity) GaelicServlet.MAPPER.readValue(in, JEntity.class);
        }
        LOG.debug("Parsed request Content-Type: {} into {}", request.getContentType(), body);
        
        return body;
    }
    
    @Override
    public Node getServingNode(HttpServletRequest request, LinkedList<String> pathList, int pathIndex) {
        LOG.debug("path[{}]={}", pathIndex, pathList.get(pathIndex-1));
        
        JKey parentKey = null, key = null;
        for (int i = pathIndex-1; i < pathList.size(); i += 2) {
            final String kind = pathList.get(i);
            final String name = i+1 < pathList.size() ? pathList.get(i+1) : null;
            key = new JKey();
            key.setParentKey(parentKey);
            key.setKind(kind);
            if (null != name && 0 < name.length()) {
                try {
                    key.setId(Long.parseLong(name));
                }
                catch (NumberFormatException ifStringKey) {
                    key.setName(name);
                }
            }
            
            parentKey = key;
        }
        
        request.setAttribute(REQUEST_ATTR_JKEY, key);
        
        final String method = request.getMethod();
        return SUPPORTED_METHODS.contains(method) ? this : null;
    }

    protected void putEntity(HttpServletRequest request, HttpServletResponse response, JEntity jBody) {
        final DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
            
        final Entity entity = convertJEntity(jBody);
        datastore.put(entity);
        final JEntity body = convertEntity(entity);
        setResponseBody(request, 
                null != jBody.getId() ? HttpServletResponse.SC_OK : HttpServletResponse.SC_CREATED, 
                body);
    }

}
