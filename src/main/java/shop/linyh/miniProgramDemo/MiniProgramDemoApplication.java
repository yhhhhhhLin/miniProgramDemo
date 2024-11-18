package shop.linyh.miniProgramDemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
*
* @author linzz
*/
@SpringBootApplication
@MapperScan("shop.linyh.miniProgramDemo.mapper")
public class MiniProgramDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiniProgramDemoApplication.class, args);
    }

}
