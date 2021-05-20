package com.github.di.factory.forTests;

import com.github.di.annotations.CustomService;

@CustomService
public class SecondBeanService {

    private final MyCustomBeanRepository myCustomBeanRepository;

//    private final IMyCustomInterface firstRealiase;

    public SecondBeanService(MyCustomBeanRepository myCustomBeanRepository, IMyCustomInterface firstRealiase) {
        this.myCustomBeanRepository = myCustomBeanRepository;
//        this.firstRealiase = firstRealiase;
    }

    public String getHello() {
        return "hello world 1";
    }

    public MyCustomBeanRepository getMyCustomBeanRepository() {
        return myCustomBeanRepository;
    }
}

