package org.csu.mypetstore;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.csu.mypetstore.dao")
public class MypetstoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(MypetstoreApplication.class, args);
    }
}
