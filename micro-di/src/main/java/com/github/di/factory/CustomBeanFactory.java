package com.github.di.factory;

import com.github.di.annotations.*;
import com.github.di.utils.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Rules:
 * - inject only by final fields;
 * - inject only by constructor;
 * - add config value by annotation @InjectValue;
 * - config classes with custom beans marked as @CustomBean
 * methods always return value;
 */

public class CustomBeanFactory {

    private static final CustomBeanFactory instance = new CustomBeanFactory();

    private final Map<Class<?>, Object> container = new HashMap<>();

    private Map<String, Object> properties = new HashMap<>();

    private Map<Class<?>, List<Class<?>>> adjacency = new HashMap<>();

    public static CustomBeanFactory getInstance(){
        return instance;
    }

    public void setProperties(Map<String, Object> properties){
        this.properties = properties;
    }

    public void registration(Class<?> mainClz){
        List<Class<?>> classes = ReflectionUtils.findAllClasses(mainClz.getPackageName());
        this.adjacency = classes.stream().filter(clz->
                clz.isAnnotationPresent(CustomService.class) ||
                clz.isAnnotationPresent(CustomComponent.class) ||
                clz.isAnnotationPresent(CustomRepository.class) ||
                clz.isAnnotationPresent(CustomConfiguration.class) ||
                clz.isAnnotationPresent(CustomController.class)).
                collect(Collectors.toMap(Function.identity(), v -> new ArrayList<>()));
    }

    private void dependency(){
        Set<Class<?>> keys = this.adjacency.keySet();
        for(Class<?> source : keys) {
            List<Class<?>> sourceClasses = ReflectionUtils.findFinalFields(source);
            if(this.adjacency.containsKey(source)){
                this.adjacency.put(source,sourceClasses);
            }
            for (Class<?> destination : sourceClasses) {
                List<Class<?>> destinationClasses = ReflectionUtils.findFinalFields(destination);
                if(this.adjacency.containsKey(destination));
                this.adjacency.put(destination, destinationClasses);
            }
        }
    }

    public void wiredObjects() {
        dependency();
        List<Class<?>> unconnected = findAllUnconnected();
        for (Class<?> clz : unconnected) {
            Object obj = unconnectedObj(clz);
            this.container.put(clz, obj);
            if (clz.isAnnotationPresent(CustomConfiguration.class)) {
                createBeanMethods(clz, obj);
            }
        }
        for (Class<?> clz : this.adjacency.keySet()) {
            List<Class<?>> classes = this.adjacency.get(clz);
            if (unconnected.containsAll(classes)) {
                Object obj = connectedObj(clz, classes);
                this.container.put(clz, obj);
                if (clz.isAnnotationPresent(CustomConfiguration.class)) {
                    createBeanMethods(clz, obj);
                }
            }
        }
        for (Class<?> clz : this.adjacency.keySet()) {
            if (!this.container.containsKey(clz)) {
                Object obj = connectedObj(clz, this.adjacency.get(clz));
                this.container.put(clz, obj);
                if (clz.isAnnotationPresent(CustomConfiguration.class)) {
                    createBeanMethods(clz, obj);
                }
            }
        }
    }

    private List<Class<?>> findAllUnconnected() {
        return this.adjacency.keySet().stream()
                .filter(clz->this.adjacency.get(clz).isEmpty())
                .collect(Collectors.toList());
    }

    private Object unconnectedObj (Class<?> clz){
        Object obj = ReflectionUtils.newInstanceWithoutParams(clz);
        if (ReflectionUtils.isHasProps(clz)) {
            ReflectionUtils.injectProps(obj, clz, this.properties);
        }
        return obj;
    }

    private Object connectedObj (Class<?> clz, List<Class<?>> classes){
        List<Object> objects = classes.stream().map(c -> this.container.get(c))
                .collect(Collectors.toList());
        Object obj = ReflectionUtils.newInstanceWithParams(clz, objects);
        if (ReflectionUtils.isHasProps(clz)) {
            ReflectionUtils.injectProps(obj, clz, this.properties);
        }
        return obj;
    }

    private void createBeanMethods(Class<?> clz, Object obj) {
        List<Method> methods = ReflectionUtils.findDeclaredBeanMethods(clz);
        for (Method m : methods) {
            this.container.put(m.getReturnType(), ReflectionUtils.invokeBeanMethod(m, obj));
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T instance(Class<?> clz) {
        return (T) this.container.get(clz);
    }
}
