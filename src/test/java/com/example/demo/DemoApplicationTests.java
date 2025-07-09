package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.SetEnvironmentVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@SpringBootTest
// mvn com.github.ulisesbocchio:jasypt-maven-plugin:encrypt-value -Djasypt.encryptor.password="demo" -Djasypt.plugin.value="Jasypt"
@SetEnvironmentVariable(key = "hello", value = "ENC(XpovJN5AbC5mFBWOZhyKI+2/6MNIcuM8I3WD2aTgtK/s4iuDx7irPX9ft9c0yDOH)")
class DemoApplicationTests {

  @Autowired
  Environment environment;

  @Test
  void contextLoads() {
    Assertions.assertThat(environment.getProperty("hello")).isEqualTo("Jasypt");
  }
}
