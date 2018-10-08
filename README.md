# helloworld-opencontrib-jaxrs

1. Download wildfly 13
2. copy war to `standalone/deployments`
3. run server
4. `curl -iv  http://localhost:8080/app005ws/helloworld/v1/helloworld`

Span with a custom operation name (by defaults it's `/helloworld`) should be present in logs:
```
14:38:44,870 INFO  [io.jaegertracing.internal.reporters.LoggingReporter] (default task-1) Span reported: 57882e81ecdfbbb1:57882e81ecdfbbb1:0:1 - GET:be.wyfrel.helloworld.application.controller.HelloWorldController.getHelloWorld
```
