package springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

/*

(1)
@SpringBootApplication
* 스프링 부트의 자동 설정,
* 스프링 Bean의 읽기와 생성이 모두 자동으로 설정


(2)
SpringApplication.run(Application.class, args);
* 내장 WAS를 실행

*/

