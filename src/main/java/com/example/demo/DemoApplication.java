package com.example.demo;

import com.ulisesbocchio.jasyptspringboot.EncryptablePropertyDetector;
import com.ulisesbocchio.jasyptspringboot.EncryptablePropertySourceConverter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;

@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Bean
  public static BeanFactoryPostProcessor encryptValuePropertySourcePostProcessor(
      ConfigurableEnvironment environment,
      EncryptablePropertySourceConverter converter,
      EncryptablePropertyDetector detector) {
    return new EncryptValuePropertySourcePostProcessor(environment, converter, detector);
  }

  public record EncryptValuePropertySourcePostProcessor(
      ConfigurableEnvironment environment,
      EncryptablePropertySourceConverter converter,
      EncryptablePropertyDetector detector)
      implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
        throws BeansException {
      environment
          .getPropertySources()
          .addLast(converter.makeEncryptable(new EncryptValuePropertySource(detector)));
    }
  }

  public static class EncryptValuePropertySource
      extends PropertySource<EncryptablePropertyDetector> {

    public static final String ENCRYPT_PROPERTY_SOURCE_NAME = "encrypt";

    public EncryptValuePropertySource(EncryptablePropertyDetector detector) {
      this(ENCRYPT_PROPERTY_SOURCE_NAME, detector);
    }

    public EncryptValuePropertySource(String name, EncryptablePropertyDetector detector) {
      super(name, detector);
    }

    @Override
    public Object getProperty(String name) {
      return getSource().isEncrypted(name) ? name : null;
    }
  }
}
