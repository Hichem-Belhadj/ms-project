package com.hbtheme.inventoryservice;

import com.hbtheme.inventoryservice.model.Inventory;
import com.hbtheme.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
