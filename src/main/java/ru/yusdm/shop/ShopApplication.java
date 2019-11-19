package ru.yusdm.shop;

import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.yusdm.shop.integration.fixer.api.rest.FixerRestClient;

@SpringBootApplication
@EnableScheduling
public class ShopApplication implements ApplicationRunner, ApplicationContextAware {

    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.fetchCurrencyData();
    }

    private void fetchCurrencyData() {
        FixerRestClient fixerRestClient = this.applicationContext.getBean(FixerRestClient.class);
        fixerRestClient.fetchCurrencyData();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
