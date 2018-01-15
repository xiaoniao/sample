disconf-standalone-demo
=======

使用disconf的standalone demo程序(基于spring)

## Quick Start

### 基于注解式的分布式配置

#### 第一步：撰写配置类

目录 com.example.disconf.demo.config 下

- code.properties: 分布式配置文件
- coefficients.properties: 分布式配置文件
- redis.properties: 分布式配置文件
- remote.properties: 分布式配置文件
- empty.properties:  空的分布式配置文件
- static.properties: 静态配置文件示例
- testXml.xml: xml配置文件示例

以 `code.properties` 举例：

    @Service
    @DisconfFile(filename = "code.properties", copy2TargetDirPath = "disconf")
    public class CodeConfig {
    
        private String codeError = "";
    
        @DisconfFileItem(name = "syserror.paramtype", associateField = "codeError")
        public String getCodeError() {
            return codeError;
        }
    
        public void setCodeError(String codeError) {
            this.codeError = codeError;
        }
    }
  
#### 第一步附： 配置项示例：

com.example.disconf.demo.config.Coefficients.discount

    /**
     * 金融系数文件
     */
    @Service
    @DisconfFile(filename = "coefficients.properties")
    public class Coefficients {
    
        public static final String key = "discountRate";
    
        @Value(value = "2.0d")
        private Double discount;
    }

#### 第二步：撰写回调类

- com.example.disconf.demo.config.JedisConfig: 配置类与回调类 是同一个类的示例
- com.example.disconf.demo.service.callbacks.*: 配置类与配置项 的回调类示例
    - RemoteServiceUpdateCallback:
    - SimpleRedisServiceUpdateCallback
    - TestXmlConfigCallback: XML的回调函数

示例：

    /**
     * 更新Redis配置时的回调函数
     *
     * @author liaoqiqi
     * @version 2014-6-17
     */
    @Service
    @Scope("singleton")
    @DisconfUpdateService(classes = {JedisConfig.class}, itemKeys = {Coefficients.key})
    public class SimpleRedisServiceUpdateCallback implements IDisconfUpdate {
    
        protected static final Logger LOGGER = LoggerFactory.getLogger(SimpleRedisServiceUpdateCallback.class);
    
        @Autowired
        private SimpleRedisService simpleRedisService;
    
        /**
         *
         */
        public void reload() throws Exception {
    
            simpleRedisService.changeJedis();
        }
    
    }


#### 第三步：使用起来

看这里 com.example.disconf.demo.task.DisconfDemoTask

- 支持spring注解式(bean)
- 支持静态类(非bean)

### XML配置方式

#### 第一步：增加XML配置

需要自动reload的

- autoconfig.properties
- autoconfig2.properties
- myserver_slave.properties
- testJson.json
- testXml2.xml

不需要自动reload的：

- myserver.properties

    <!-- 使用托管方式的disconf配置(无代码侵入, 配置更改会自动reload)-->
    <bean id="configproperties_disconf"
          class="com.baidu.disconf.client.addons.properties.ReloadablePropertiesFactoryBean">
        <property name="locations">
            <list>
                <value>classpath:/autoconfig.properties</value>
                <value>classpath:/autoconfig2.properties</value>
                <value>classpath:/myserver_slave.properties</value>
                <value>classpath:/testJson.json</value>
                <value>testXml2.xml</value>
            </list>
        </property>
    </bean>

    <bean id="propertyConfigurer"
          class="com.baidu.disconf.client.addons.properties.ReloadingPropertyPlaceholderConfigurer">
        <property name="ignoreResourceNotFound" value="true"/>
        <property name="ignoreUnresolvablePlaceholders" value="true"/>
        <property name="propertiesArray">
            <list>
                <ref bean="configproperties_disconf"/>
            </list>
        </property>
    </bean>

    <!-- 使用托管方式的disconf配置(无代码侵入, 配置更改不会自动reload)-->
    <bean id="configproperties_no_reloadable_disconf"
          class="com.baidu.disconf.client.addons.properties.ReloadablePropertiesFactoryBean">
        <property name="locations">
            <list>
                <value>myserver.properties</value>
            </list>
        </property>
    </bean>

    <bean id="propertyConfigurerForProject1"
          class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
        <property name="ignoreResourceNotFound" value="true"/>
        <property name="ignoreUnresolvablePlaceholders" value="true"/>
        <property name="propertiesArray">
            <list>
                <ref bean="configproperties_no_reloadable_disconf"/>
            </list>
        </property>
    </bean>
    
    <bean id="autoService" class="com.example.disconf.demo.service.AutoService">
        <property name="auto" value="${auto=100}"/>
    </bean>

    <bean id="autoService2" class="com.example.disconf.demo.service.AutoService2">
        <property name="auto2" value="${auto2}"/>
    </bean>

#### 第二步：撰写相应的bean类

- com.example.disconf.demo.service.AutoService
- com.example.disconf.demo.service.AutoService2

#### 第三步：回调类

- com.example.disconf.demo.service.callbacks.*: 
    - AutoServiceCallback: 
    - TestJsonConfigCallback
    
示例：

    /**
     * 这是 autoconfig.properties 的回调函数类
     * <p/>
     * Created by knightliao on 15/3/21.
     */
    @Service
    @DisconfUpdateService(confFileKeys = {"autoconfig.properties", "autoconfig2.properties"})
    public class AutoServiceCallback implements IDisconfUpdate {
    
        protected static final Logger LOGGER = LoggerFactory.getLogger(AutoServiceCallback.class);
    
        @Autowired
        private AutoService autoService;
    
        @Override
        public void reload() throws Exception {
    
            LOGGER.info("reload callback " + "autoconfig.properties or autoconfig2.properties" + autoService.getAuto());
    
        }
    }

## Run 打包、部署、运行

### 打包

mvn clean package

### 运行

    ➜  disconf-standalone-demo git:(dev) ✗ cd target/starter-run
    ➜  starter-run git:(dev) ✗ ll
    total 42680
    -rw-r--r--  1 knightliao  staff  21831639  6  8 14:36 disconf-standalone-demo.jar
    -rw-r--r--  1 knightliao  staff      1431  6  8 14:36 disconf.properties
    -rw-r--r--  1 knightliao  staff       417  6  8 14:36 env
    -rw-r--r--  1 knightliao  staff      1493  6  8 14:36 logback.xml
    -rw-r--r--  1 knightliao  staff      1037  6  8 14:36 start.sh
    -rw-r--r--  1 knightliao  staff       532  6  8 14:36 stop.sh

    ➜  starter-run git:(dev) ✗ sh start.sh
    nohup java  -server -Xms1024m -Xmx1024m -Xmn448m -Xss256K -XX:MaxPermSize=128m -XX:ReservedCodeCacheSize=64m -XX:+UseParallelGC -XX:+UseParallelOldGC -XX:ParallelGCThreads=2 -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Dlogback.configurationFile=file:logback.xml -jar disconf-standalone-demo.jar  >> log_1465367799.log 2>&1 &
    ➜  starter-run git:(dev) ✗ tail -f log_1465367799.log
        disConfCommonModel=DisConfCommonModel [app=disconf_demo, version=1_0_0_0, env=rd]
        disconfCommonCallbackModel=DisconfCommonCallbackModel{disconfConfUpdates=[com.example.disconf.demo.service.callbacks.SimpleRedisServiceUpdateCallback@50b7ae59], disconfUpdatesActiveBackups=[]}]]
            
