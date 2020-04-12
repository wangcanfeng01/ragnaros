# ragnaros
##character
### character1
 Monitor data throughput
### character2
Do not invade code logic
### character3
Have a friendly ui for easy viewing
## function
``` xml
<dependency>
      <groupId>com.github.wangcanfeng01</groupId>
      <artifactId>freedom-ragnaros</artifactId>
      <version>1.0.RELEASE</version>
</dependency>
```
### function1
We can customize the interface request address in application.properties
api.throughput.watch.all=/ui/throughput/watch
api.throughput.open.all=/ui/throughput/open
api.throughput.close.all=/ui/throughput/close
api.throughput.watch.single=/api/throughput/watch
api.throughput.open.single=/api/throughput/open
api.throughput.close.single=/api/throughput/close
### function2
Specific instructions for use
``` java
@Component
@ThroughputScan
public class ExampleClass {

    @Throughput(name = "wcf.test")
    public String test() {
        try {
            TimeUnit.MILLISECONDS.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "started";
    }
}
```
``` java
@UseRagnaros
@Component
public class RagnarosConfiguration  implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/ragnaros.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
```
# finally
![展示结果.JPG](https://upload-images.jianshu.io/upload_images/8350955-9229b76d3cdd254f.JPG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
