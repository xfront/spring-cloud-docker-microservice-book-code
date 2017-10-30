package cn.eshop.cloud.config;

import com.itmuch.cloud.config.RibbonConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

/**
 * 使用RibbonClient，为特定name的Ribbon Client自定义配置.
 * 使用@RibbonClient的configuration属性，指定Ribbon的配置类.
 * 可参考的示例：
 * http://spring.io/guides/gs/client-side-load-balancing/
 *
 * @author 周立
 */
@Configuration
@RibbonClient(name = "ms-user", configuration = RibbonConfiguration.class)
public class TestConfiguration {
}