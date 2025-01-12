package com.ecommerce.productservicedecember2024.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

//This application config is a configuration class in springboot application.
//It is annotated with @configuration, indicating that this provides bean definition and configuration for the application context(generally for bean management)
//Purpose of this is, it marks this class as source of spring bean or spring object definition
//Effect is, when application starts, spring process everything in this class and register the bean defines within it into the spring application context and we will define the bean for rest template right now
//In general, it will create and manage the life cycle of bean. When application shut down, the beans are destroyed and everything manage by spring

/* Application context/spring container/IOC container - It is a container and putting all objects and use it for multiple times.
/*@Configuration - now spring knows, that this class is responsible for providing beans, here we use rest template beans  */
@Configuration
public class ApplicationConfig{

    //Tell to spring that this is bean, so use @Bean annotation here
    @Bean //@Bean denotes that getRestTemplate method is a bean producer not bean. If it is bean producer, then take this bean(object (i.e) restTemplate here) and put it into the application context or IOC container, so that classes, services use this
          //here, bean producer produces a new object of rest template
    public RestTemplate getRestTemplate(){//we are returning the RestTemplate here

        return new RestTemplate();
    }

    /*
    //here, the following method is not registered as bean, so whatever return here will not register as beans and not put it into the application context
    public int getValue(){
        return 0;
    }*/
}
