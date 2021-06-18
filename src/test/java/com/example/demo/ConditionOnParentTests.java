package com.example.demo;

import org.junit.jupiter.api.Test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.mock.env.MockEnvironment;

import static org.assertj.core.api.Assertions.assertThat;

class ConditionOnParentTests {

	@Test
	void conditionOnParentShouldBeIgnored() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		MockEnvironment environment = new MockEnvironment().withProperty("test.condition.concrete", "true");
		context.setEnvironment(environment);
		context.register(ConcreteConfiguration.class);
		context.refresh();
		assertThat(context.getBeanDefinitionNames()).contains("concreteConfiguration", "concreteBean", "baseBean", "simpleBean");
	}

	@Test
	void conditionOnParentIsEvaluatedForComponentScan() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		MockEnvironment environment = new MockEnvironment().withProperty("test.condition.concrete", "true")
				.withProperty("VCAP_APPLICATION", "{}");
		context.setEnvironment(environment);
		context.register(ConcreteConfiguration.class);
		context.refresh();
		assertThat(context.getBeanDefinitionNames()).contains("concreteConfiguration", "concreteBean", "baseBean", "simpleBean");
	}

}
