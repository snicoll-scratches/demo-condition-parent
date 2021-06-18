package com.example.demo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty("test.condition.concrete")
public class ConcreteConfiguration extends BaseConfiguration {

	@Bean
	public String concreteBean() {
		return "concrete";
	}

}
