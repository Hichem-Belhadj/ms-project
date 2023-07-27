package com.hbtheme.inventoryservice;

import com.hbtheme.inventoryservice.model.Inventory;
import com.hbtheme.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


/**
 *  "@EnableEurekaClient" is deprecated, no need to annotate the main class.
 *  It is enough to add the spring-cloud-starter-netflix-eureka-client
 *  dependency to pom.xml and if we have the application name in yml
 *  or properties file it will be registered to Eureka Server.
 */
@SpringBootApplication
public class InventoryServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
        return args -> {
            Inventory iphone14 = Inventory.builder()
                    .skuCode("iphone_14")
                    .quantity(100)
                    .build();

            Inventory iphone14Red = Inventory.builder()
                    .skuCode("iphone_14_red")
                    .quantity(0)
                    .build();

            inventoryRepository.save(iphone14);
            inventoryRepository.save(iphone14Red);
        };
    }
}
