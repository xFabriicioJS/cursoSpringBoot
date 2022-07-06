package com.example.demo.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.demo.entities.Category;
import com.example.demo.entities.Client;
import com.example.demo.entities.Order;
import com.example.demo.entities.enums.OrderStatus;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.ClientRepository;
import com.example.demo.repositories.OrderRepository;

@Configuration

public class ConfigTest implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void run(String... args) throws Exception {

        Category cat1 = new Category(null, "Electronics");
        Category cat2 = new Category(null, "Books");
        Category cat3 = new Category(null, "Computers");

        categoryRepository.saveAll(Arrays.asList(cat1,cat2,cat3));

        Client u1 = new Client(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        Client u2 = new Client(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

        Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), u1, OrderStatus.PAID);
        Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), u2, OrderStatus.SHIPPED);
        Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), u1, OrderStatus.WAITING_PAYMENT);

        // Salvará no banco de dados]
        clientRepository.saveAll(Arrays.asList(u1, u2));
        orderRepository.saveAll(Arrays.asList(o1, o2, o3));

    }

}
