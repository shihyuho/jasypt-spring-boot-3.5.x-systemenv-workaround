package com.example.demo;

import com.ulisesbocchio.jasyptspringboot.EncryptablePropertySourceConverter;
import com.ulisesbocchio.jasyptspringboot.properties.JasyptEncryptorConfigurationProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;

import java.util.function.Predicate;

import static com.example.demo.DemoApplication.EncryptValuePropertySource.ENCRYPT_PROPERTY_SOURCE_NAME;
import static com.ulisesbocchio.jasyptspringboot.properties.JasyptEncryptorConfigurationProperties.bindConfigProps;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public static BeanFactoryPostProcessor encryptValuePropertySourcePostProcessor(ConfigurableEnvironment environment,
			EncryptablePropertySourceConverter converter) {
		return new EncryptValuePropertySourcePostProcessor(environment, converter);
	}

	public record EncryptValuePropertySourcePostProcessor(ConfigurableEnvironment environment,
			EncryptablePropertySourceConverter converter) implements BeanFactoryPostProcessor {

		@Override
		public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
			var properties = bindConfigProps(environment);
			environment.getPropertySources()
				.addLast(converter.makeEncryptable(new EncryptValuePropertySource(ENCRYPT_PROPERTY_SOURCE_NAME,
						properties.getProperty().getPrefix(), properties.getProperty().getSuffix())));
		}
	}

	public static class EncryptValuePropertySource extends PropertySource<Predicate<String>> {

		public static final String ENCRYPT_PROPERTY_SOURCE_NAME = "encrypt";

		public EncryptValuePropertySource(String name, String prefix, String suffix) {
			this(name, propertyName -> propertyName.startsWith(prefix) && propertyName.endsWith(suffix));
		}

		public EncryptValuePropertySource(String name, Predicate<String> matcher) {
			super(name, matcher);
		}

		@Override
		public Object getProperty(String name) {
			return getSource().test(name) ? name : null;
		}

	}

}
