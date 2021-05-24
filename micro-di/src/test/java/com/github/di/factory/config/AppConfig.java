package com.github.di.factory.config;

import com.github.di.annotations.CustomBean;
import com.github.di.annotations.CustomConfiguration;
import com.github.di.annotations.InjectValue;
import com.github.di.factory.forTests.MyCustomBeanRepository;
import com.github.di.factory.libs.HibernateClass;

@CustomConfiguration
public class AppConfig {

    private final MyCustomBeanRepository myCustomBeanRepository;

    @InjectValue(name = "new.injected.value")
    private String myCustomValue;

    public AppConfig(MyCustomBeanRepository myCustomBeanRepository) {
        this.myCustomBeanRepository = myCustomBeanRepository;
    }

    @CustomBean
    public HibernateClass unconfiguratedClass() {
        System.out.println(myCustomBeanRepository);
        return new HibernateClass(myCustomValue);
    }
}
