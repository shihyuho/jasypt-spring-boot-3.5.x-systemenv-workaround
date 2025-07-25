package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.SetEnvironmentVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

// mvn com.github.ulisesbocchio:jasypt-maven-plugin:encrypt-value -Djasypt.encryptor.password="demo"
// -Djasypt.plugin.value="Jasypt"
@SetEnvironmentVariable(
    key = "SERVER",
    value = "${ENC(XpovJN5AbC5mFBWOZhyKI+2/6MNIcuM8I3WD2aTgtK/s4iuDx7irPX9ft9c0yDOH)}")
@SetEnvironmentVariable(
    key = "SERVER_PORT",
    value = "${ENC(XpovJN5AbC5mFBWOZhyKI+2/6MNIcuM8I3WD2aTgtK/s4iuDx7irPX9ft9c0yDOH)}")
@SetEnvironmentVariable(
    key = "HOST_0",
    value = "${ENC(XpovJN5AbC5mFBWOZhyKI+2/6MNIcuM8I3WD2aTgtK/s4iuDx7irPX9ft9c0yDOH)}")
@SetEnvironmentVariable(
    key = "HOST_0_1",
    value = "${ENC(XpovJN5AbC5mFBWOZhyKI+2/6MNIcuM8I3WD2aTgtK/s4iuDx7irPX9ft9c0yDOH)}")
@SetEnvironmentVariable(
    key = "HOST_0_NAME",
    value = "${ENC(XpovJN5AbC5mFBWOZhyKI+2/6MNIcuM8I3WD2aTgtK/s4iuDx7irPX9ft9c0yDOH)}")
@SetEnvironmentVariable(
    key = "HOST_F00_NAME",
    value = "${ENC(XpovJN5AbC5mFBWOZhyKI+2/6MNIcuM8I3WD2aTgtK/s4iuDx7irPX9ft9c0yDOH)}")
@SetEnvironmentVariable(
    key = "S-ERVER",
    value = "${ENC(XpovJN5AbC5mFBWOZhyKI+2/6MNIcuM8I3WD2aTgtK/s4iuDx7irPX9ft9c0yDOH)}")
@SetEnvironmentVariable(
    key = "S-ERVER",
    value = "${ENC(XpovJN5AbC5mFBWOZhyKI+2/6MNIcuM8I3WD2aTgtK/s4iuDx7irPX9ft9c0yDOH)}")
@SpringBootTest({
  "myserver=ENC(XpovJN5AbC5mFBWOZhyKI+2/6MNIcuM8I3WD2aTgtK/s4iuDx7irPX9ft9c0yDOH)",
  "myhost[0]=ENC(XpovJN5AbC5mFBWOZhyKI+2/6MNIcuM8I3WD2aTgtK/s4iuDx7irPX9ft9c0yDOH)",
  "myhost[0][1]=ENC(XpovJN5AbC5mFBWOZhyKI+2/6MNIcuM8I3WD2aTgtK/s4iuDx7irPX9ft9c0yDOH)",
  "my.kebab-case=ENC(XpovJN5AbC5mFBWOZhyKI+2/6MNIcuM8I3WD2aTgtK/s4iuDx7irPX9ft9c0yDOH)",
  "my.camelCase=ENC(XpovJN5AbC5mFBWOZhyKI+2/6MNIcuM8I3WD2aTgtK/s4iuDx7irPX9ft9c0yDOH)",
  "my.underscore_notation=ENC(XpovJN5AbC5mFBWOZhyKI+2/6MNIcuM8I3WD2aTgtK/s4iuDx7irPX9ft9c0yDOH)",
  "my.mixed-CASE=ENC(XpovJN5AbC5mFBWOZhyKI+2/6MNIcuM8I3WD2aTgtK/s4iuDx7irPX9ft9c0yDOH)"
})
class DemoApplicationTests {

  @Autowired private Environment environment;

  @Test
  void contextLoads() {
    // SystemEnvironmentPropertyMapper scenarios
    assertThat(environment.getProperty("server")).isEqualTo("Jasypt");
    assertThat(environment.getProperty("server.port")).isEqualTo("Jasypt");
    assertThat(environment.getProperty("host[0]")).isEqualTo("Jasypt");
    assertThat(environment.getProperty("host[0][1]")).isEqualTo("Jasypt");
    assertThat(environment.getProperty("host[0].name")).isEqualTo("Jasypt");
    assertThat(environment.getProperty("host.f00.name")).isEqualTo("Jasypt");
    assertThat(environment.getProperty("s-erver")).isEqualTo("Jasypt");

    // DefaultPropertyMapper scenarios
    assertThat(environment.getProperty("myserver")).isEqualTo("Jasypt");
    assertThat(environment.getProperty("myhost[0]")).isEqualTo("Jasypt");
    assertThat(environment.getProperty("myhost[0][1]")).isEqualTo("Jasypt");
    assertThat(environment.getProperty("my.kebab-case")).isEqualTo("Jasypt");
    assertThat(environment.getProperty("my.camelCase")).isEqualTo("Jasypt");
    assertThat(environment.getProperty("my.underscore_notation")).isEqualTo("Jasypt");
    assertThat(environment.getProperty("my.mixed-CASE")).isEqualTo("Jasypt");
  }
}
