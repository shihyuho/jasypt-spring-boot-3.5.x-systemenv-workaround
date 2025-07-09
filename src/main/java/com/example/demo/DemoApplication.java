package com.example.demo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.StandardEnvironment;

@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Bean
  public static BeanFactoryPostProcessor wrappingSystemEnvironmentPropertySource(ConfigurableEnvironment environment) {
    return new WrappingSystemEnvironmentPropertySource(environment);
  }

  public record WrappingSystemEnvironmentPropertySource(
    ConfigurableEnvironment environment) implements BeanFactoryPostProcessor, Ordered {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
      var ps =
        environment
          .getPropertySources()
          .get(StandardEnvironment.SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME);
      var copy = new CompositePropertySource(ps.getName());
      copy.addPropertySource(ps);
      environment.getPropertySources().replace(copy.getName(), copy);
    }

    @Override
    public int getOrder() {
      // Ensure this runs after EnableEncryptablePropertiesBeanFactoryPostProcessor
      return Ordered.LOWEST_PRECEDENCE - 99;
    }
  }
}
