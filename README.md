# jasypt-spring-boot-3.5.x-systemenv-workaround

> Workaround for reading encrypted system environment variables in Spring Boot 3.5.x when using Jasypt.

- Issue: [ulisesbocchio/jasypt-spring-boot#409](https://github.com/ulisesbocchio/jasypt-spring-boot/issues/409#issuecomment-3112995172)
- `DemoApplication.java` contains the workaround for the issue. 
- `DemoApplicationTests.java` contains a test case that verifies the workaround can decrypt the system environment variable successfully.

This workaround requires a small change to how system environment variables are defined:

Before:

```properties
MY_SECRET=ENC(.....)
```

After:

```properties
MY_SECRET=${ENC(.....)}
```

> If encrypted values aren’t coming from system environment variables, then no changes are needed.
