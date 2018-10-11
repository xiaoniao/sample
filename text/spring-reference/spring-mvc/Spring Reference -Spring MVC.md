## Spring Web MVC

- DispatcherServlet
- Filters 过滤器
- Annotated Controllers 注解
- URI Links
- Asynchronous Requests
- CORS
- Web Security
- HTTP Caching
- View Technologies
- MVC Config
- HTTP/2



本章讲解的是基于Servlet栈的Web应用程序（使用Servlet API并且部署在Servlet 容器中），对于基于事件的Web应用程序请阅读 Web on Reactive Stack。



Web on Servlet Stack

Web on Reactive Stack



##### DispatcherServlet

- 使用代码注册和初始化 *DispatcherServlet*
- 使用web.xml注册和初始化 *DispatcherServlet*

##### Context Hierarchy



##### 重要的Bean

- HandlerMapping
  - RequestMappingHandlerMapping
  - SimpleUrlHandlerMapping
- HandlerAdapter
- HandlerExceptionResolver
- ViewResolver
- LocalResolver LocalContextResolver
- ThemeResolver
- MultipartResolver
- FlashMapManager

#### Web MVC配置

#### Servlet 配置

- #### Filter

#### 处理流程

1. 绑定WebApplicationContext到HttpServletRequest(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE )
2. Locale resolver 语言配置，可以不配置
3. Theme resolver 主题配置(CSS)，可以不配置
4. Multipart file resolver 多区块【TODO】
   - https://www.cnblogs.com/jmcui/p/8179174.html
5. Search Handler
   - preprocessors, postprocessors, and controllers
6. Model and Renderer View



HandlerExceptionResolver 绑定在WebApplicationContext中，可以在处理请求的过程中处理异常

自定义DispatcherServlet，在web.xml中 <init-param> elements

#### 拦截器【TODO】

- preHandle(..)
- postHandle(..)
- afterCompletion(..)

#### 异常【TODO】

- HandlerExceptionResolver
- 异常处理链
- 错误页

#### 视图处理器

#### 日志

​	enableLoggingRequestDetails





#### Filters

- Form Data

  - ```java
    HttpPutFormContentFilter
    ```

- Forwarded Headers

  - ```java
    ForwardedHeaderFilter
    ```

- Shallow ETag

  - ```java
    ShallowEtagHeaderFilter
    ```

- CORS

  - ```java
    CorsFilter
    ```







========================================================================

#### Model

​	@ModelAttribute 【TODO】

#### DataBinder

#### Exception

​		REST API exceptions

​			ResponseEntityExceptionHandler 【TODO】







