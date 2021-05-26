- Spring Cache

- CaffeineCache

- Redis Cache

- EHCACHE cache

测试 localhost:8001/api/张三

可以切换profile 来切换cache实现

## EHCACHE

参考：
> https://howtodoinjava.com/spring-boot2/ehcache3-config-example/

通过xml配置ehcache (官网文件：https://www.ehcache.org/documentation/3.8/getting-started.html)
```yaml
<config
    xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'
    xmlns='http://www.ehcache.org/v3'
    xsi:schemaLocation="http://www.ehcache.org/v3 http://www.ehcache.org/schema/ehcache-core.xsd">

  <!-- cache alis -->
  <cache alias="foo"> 
    <!-- cache key type and value type -->
    <key-type>java.lang.String</key-type> 
    <value-type>java.lang.String</value-type> 
    <resources>
      <!-- foo is declared to hold up to 2,000 entries on heap -->
      <heap unit="entries">20</heap> 
      <!-- as well as up to 10 MB of off-heap memory before it starts evicting -->
      <offheap unit="MB">10</offheap> 
    </resources>
  </cache>

  <!-- abstract configuration  -->
  <cache-template name="myDefaults"> 
    <key-type>java.lang.Long</key-type>
    <value-type>java.lang.String</value-type>
    <heap unit="entries">200</heap>
  </cache-template>

  <cache alias="bar" uses-template="myDefaults"> 
    <!-- override key type -->
    <key-type>java.lang.Number</key-type>
  </cache>

  <!-- use myDefaults-->
  <cache alias="simpleCache" uses-template="myDefaults" /> 

</config>
```
application.yml指定配置
```yaml
  cache:
    jcache:
      config: classpath:ehcache.xml
```









