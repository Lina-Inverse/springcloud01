package com.feiyu.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalanced {

    ServiceInstance getServiceInstance(List<ServiceInstance> serviceInstances);
}
