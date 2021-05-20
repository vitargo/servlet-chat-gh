package com.github.di.factory;

import com.github.di.factory.forTests.MyCustomBeanRepository;
import com.github.di.factory.forTests.SecondBeanService;
import com.github.di.factory.libs.HibernateClass;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class CustomBeanFactoryTest {

    @Test
    public void registration(){
        Map<String, Object> properties = new HashMap<>();
        properties.put("my.value", "hello world");
        CustomBeanFactory factory = CustomBeanFactory.getInstance();
        factory.setProperties(properties);
        factory.registration(FirstBean.class);
        factory.wiredObjects();
        MyCustomBeanRepository repo = factory.instance(MyCustomBeanRepository.class);
        System.out.println(repo.getMyValue());
    }

    @Test
    public void registration2(){
        Map<String, Object> properties = new HashMap<>();
        properties.put("my.value", "hello world");
        properties.put("new.injected.value", "value");
        CustomBeanFactory factory = CustomBeanFactory.getInstance();
        factory.setProperties(properties);
        factory.registration(FirstBean.class);
        factory.wiredObjects();
        MyCustomBeanRepository repo = factory.instance(MyCustomBeanRepository.class);
        Assert.assertEquals("hello world", repo.getMyValue());
        SecondBeanService secondBeanService = factory.instance(SecondBeanService.class);
        Assert.assertEquals("hello world 1", secondBeanService.getHello());
        FirstBean firstBean = factory.instance(FirstBean.class);
        Assert.assertEquals("hello world 1", firstBean.getSecondBeanService().getHello());
        HibernateClass unconf = factory.instance(HibernateClass.class);
        System.out.println(unconf.getStr());
    }

    @Test
    public void registration3(){
        Map<String, Object> properties = new HashMap<>();
        properties.put("my.value", "hello world");
        properties.put("new.injected.value", "value");
        CustomBeanFactory factory = CustomBeanFactory.getInstance();
        factory.setProperties(properties);
        factory.registration(FirstBean.class);
        factory.wiredObjects();
        MyCustomBeanRepository repo = factory.instance(MyCustomBeanRepository.class);
        Assert.assertEquals("hello world", repo.getMyValue());
        SecondBeanService secondBeanService = factory.instance(SecondBeanService.class);
        Assert.assertEquals("hello world 1", secondBeanService.getHello());
        FirstBean firstBean = factory.instance(FirstBean.class);
        Assert.assertEquals("hello world 1", firstBean.getSecondBeanService().getHello());
        Assert.assertEquals("value", firstBean.getHibernateClass().getStr());
    }


}