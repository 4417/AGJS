package agjs.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringMvcConfig {

	public SpringMvcConfig() {
		System.out.println("SpringMvcServiceImpl init");
	}

	@Bean
	public void beanDo() {
		
		System.out.println("beanDo");

	}
}
