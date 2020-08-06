# ysoseiral-anycode
cb1 cc2 cc3 cc4 cc10 …… any gadget chain that used Gadgets.createTemplatesImpl()
## building
Requires Java 1.7+ and Maven 3.x+

```mvn clean package -DskipTests```

## Usage

```shell
$  java -jar ysoserial.jar
Y SO SERIAL?
Usage: java -jar ysoserial.jar [payload] '[command]'
`[command]`:    normal,  code:$javacode,  codefile:xxx.java
  Available payload types:
     Payload              Authors                     Dependencies
     -------              -------                     ------------
     BeanShell1           @pwntester, @cschneider4711 bsh:2.0b5
     C3P0                 @mbechler                   c3p0:0.9.5.2, mchange-commons-java:0.2.11
     Clojure              @JackOfMostTrades           clojure:1.8.0
     CommonsBeanutils1    @frohoff                    commons-beanutils:1.9.2, commons-collections:3.1, commons-logging:1.2
     CommonsCollections1  @frohoff                    commons-collections:3.1
     CommonsCollections2  @frohoff                    commons-collections4:4.0
     CommonsCollections3  @frohoff                    commons-collections:3.1
     CommonsCollections4  @frohoff                    commons-collections4:4.0
     CommonsCollections5  @matthias_kaiser, @jasinner commons-collections:3.1
     CommonsCollections6  @matthias_kaiser            commons-collections:3.1
     CommonsCollections7  @SCRISTALLI                 commons-collections:3.1
                          @HANYRAX
                          @EDOARDOVIGNATI    
     CommonsCollections10 @unknown                    commons-collections:3.1
     FileUpload1          @mbechler                   commons-fileupload:1.3.1, commons-io:2.4
     Groovy1              @frohoff                    groovy:2.3.9
     Hibernate1           @mbechler
     Hibernate2           @mbechler
     JBossInterceptors1   @matthias_kaiser            javassist:3.12.1.GA, jboss-interceptor-core:2.0.0.Final, cdi-api:1.0-SP1, javax.interceptor-api:3.1, jboss-interceptor-spi:2.0.0.Final, slf4j-api:1.7.21
     JRMPClient           @mbechler
     JRMPListener         @mbechler
     JSON1                @mbechler                   json-lib:jar:jdk15:2.4, spring-aop:4.1.4.RELEASE, aopalliance:1.0, commons-logging:1.2, commons-lang:2.6, ezmorph:1.0.6, commons-beanutils:1.9.2, spring-core:4.1.4.RELEASE, commons-collections:3.1
     JavassistWeld1       @matthias_kaiser            javassist:3.12.1.GA, weld-core:1.1.33.Final, cdi-api:1.0-SP1, javax.interceptor-api:3.1, jboss-interceptor-spi:2.0.0.Final, slf4j-api:1.7.21
     Jdk7u21              @frohoff
     Jython1              @pwntester, @cschneider4711 jython-standalone:2.5.2
     MozillaRhino1        @matthias_kaiser            js:1.7R2
     Myfaces1             @mbechler
     Myfaces2             @mbechler
     ROME                 @mbechler                   rome:1.0
     Spring1              @frohoff                    spring-core:4.1.4.RELEASE, spring-beans:4.1.4.RELEASE
     Spring2              @mbechler                   spring-core:4.1.4.RELEASE, spring-aop:4.1.4.RELEASE, aopalliance:1.0, commons-logging:1.2
     URLDNS               @gebl
     Wicket1              @jacob-baines               wicket-util:6.23.0, slf4j-api:1.6.4
```
