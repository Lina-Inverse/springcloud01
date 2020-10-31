package com.feiyu.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLb implements LoadBalanced {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    private final int  getNext() {

        int current ;
        int next;

        do {
            current = this.atomicInteger.get();
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;
        }while (!this.atomicInteger.compareAndSet(current, next));

        return next;
    }

    @Override
    public ServiceInstance getServiceInstance(List<ServiceInstance> serviceInstances) {

        if (serviceInstances == null || serviceInstances.size() == 0)
            return null;

        int index = getNext() % serviceInstances.size();

        return serviceInstances.get(index);
    }
}
