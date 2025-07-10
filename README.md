# jasypt-spring-boot-3.5.x-systemenv-workaround

> Workaround for reading encrypted system environment variables in Spring Boot 3.5.x when using Jasypt.

- Issue: [ulisesbocchio/jasypt-spring-boot#409](https://github.com/ulisesbocchio/jasypt-spring-boot/issues/409#issuecomment-3052754908)
- `DemoApplication.java` contains the workaround for the issue. 
- `DemoApplicationTests.java` contains a test case that verifies the workaround can decrypt the system environment variable successfully.
