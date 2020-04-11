package com.github.wangcanfeng01.freedom.ragnaros.configuration;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.UriTemplate;
import com.github.wangcanfeng01.freedom.ragnaros.annotations.CustomPath;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * controller自定义路径映射
 * Created in 14:56-2020/4/7
 *
 * @author wangcanfeng
 * @since 1.0.0
 */
public class ControllerMappingHandler extends RequestMappingHandlerMapping {
    private final Map<String, HandlerMethod> handlerMethods = new LinkedHashMap<String, HandlerMethod>();
    private final Environment environment;
    private final Object handler;

    ControllerMappingHandler(
            Environment environment,
            Object handler) {
        this.environment = environment;
        this.handler = handler;
    }

    @Override
    protected void initHandlerMethods() {
        logger.debug("initialising the handler methods");
        setOrder(Ordered.HIGHEST_PRECEDENCE + 1000);
        Class<?> clazz = handler.getClass();
        if (isHandler(clazz)) {
            for (Method method : clazz.getMethods()) {
                CustomPath pathMapper = method.getAnnotation(CustomPath.class);
                if (pathMapper != null) {
                    RequestMappingInfo mapping = getMappingForMethod(method, clazz);
                    HandlerMethod handlerMethod = createHandlerMethod(handler, method);
                    String mappingPath = mappingPath(pathMapper);
                    // 这里注入自定义的路径
                    if (!ObjectUtils.isEmpty(mappingPath)) {
                        handlerMethods.put(mappingPath, handlerMethod);
                    } else {
                        for (String path : mapping.getPatternsCondition().getPatterns()) {
                            handlerMethods.put(path, handlerMethod);
                        }
                    }
                }
            }
        }
    }

    private String mappingPath(final CustomPath path) {
        final String key = path.pathKey();
        return environment.getProperty(key);
    }

    @Override
    protected boolean isHandler(Class<?> beanType) {
        return ((AnnotationUtils.findAnnotation(beanType, Controller.class) != null) ||
                (AnnotationUtils.findAnnotation(beanType, RequestMapping.class) != null));
    }

    /**
     * The lookup handler method, maps the SEOMapper method to the request URL.
     * <p>If no mapping is found, or if the URL is disabled, it will simply drop through
     * to the standard 404 handling.</p>
     *
     * @param urlPath the path to match.
     * @param request the http servlet request.
     * @return The HandlerMethod if one was found.
     */
    @Override
    protected HandlerMethod lookupHandlerMethod(String urlPath, HttpServletRequest request) {
        logger.debug("looking up handler for path: " + urlPath);
        HandlerMethod handlerMethod = handlerMethods.get(urlPath);
        if (handlerMethod != null) {
            return handlerMethod;
        }
        for (String path : handlerMethods.keySet()) {
            UriTemplate template = new UriTemplate(path);
            if (template.matches(urlPath)) {
                request.setAttribute(
                        HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE,
                        template.match(urlPath));
                return handlerMethods.get(path);
            }
        }
        return null;
    }
}
