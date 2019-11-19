package ru.yusdm.shop.product.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yusdm.shop.common.domain.Currency;
import ru.yusdm.shop.common.exceptions.ExceptionCode;
import ru.yusdm.shop.common.exceptions.ShopException;
import ru.yusdm.shop.integration.fixer.cache.DailyCurrencyByEuroRatesCache;
import ru.yusdm.shop.product.domain.Product;
import ru.yusdm.shop.product.repo.ProductRepo;
import ru.yusdm.shop.product.service.ProductService;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class ProductDefaultService implements ProductService {

    private final ProductRepo productRepo;
    private DailyCurrencyByEuroRatesCache byEuroRatesCache;

    @Override
    public Optional<Product> findById(String id) {
        return productRepo.findById(id);
    }

    @Override
    public Collection<Product> getAll() {
        return productRepo.findAll();
    }

    @Override
    public String save(Product product) {
        String id = product.getId();

        if (id == null) {
            id = UUID.randomUUID().toString();
            product.setId(id);
        }
        setEuroPriceToProduct(product);
        return productRepo.save(product).getId();
    }


    private void setEuroPriceToProduct(Product product) {
        if (!Currency.EUR.equals(product.getCurrency())) {
            product.setPrice(convertPriceToEuro(product.getCurrency(), product.getPrice()));
            product.setCurrency(Currency.EUR);
        }
    }

    private double convertPriceToEuro(Currency currency, double price) {
        return Optional.ofNullable(byEuroRatesCache.getRates().get(currency)).map(rate -> price * (double) 1 / rate)
                .orElseThrow(() -> new ShopException(ExceptionCode.NO_INFORMATION_ABOUT_CURRENCY));
    }

    @Override
    public void delete(String id) {
        productRepo.deleteById(id);
    }

    @Override
    public void merge(Product product) {
        setEuroPriceToProduct(product);
        productRepo.save(product);
    }
}
