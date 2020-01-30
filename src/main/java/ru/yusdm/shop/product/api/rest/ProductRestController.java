package ru.yusdm.shop.product.api.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yusdm.shop.common.utils.UuidConverter;
import ru.yusdm.shop.product.domain.Product;
import ru.yusdm.shop.product.dto.ProductDomainDtoMapper;
import ru.yusdm.shop.product.dto.ProductDto;
import ru.yusdm.shop.product.service.ProductService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = ProductRestController.PRODUCT_BASE_REST_CONTROLLER_URL)
@AllArgsConstructor
public class ProductRestController {
    public static final String PRODUCT_BASE_REST_CONTROLLER_URL = "/api/rest/product";

    private final ProductService productService;
    private final ProductDomainDtoMapper productDomainDtoMapper;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable("id") String id) {
        return productService.findById(UuidConverter.to(id))
                .map(domain -> ResponseEntity.ok(productDomainDtoMapper.fromDomainToDto(domain)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAll() {
        Collection<Product> all = productService.getAll();
        List<ProductDto> dtos = productDomainDtoMapper.fromDomainsToDtos(all);
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<ProductDto> save(@RequestBody ProductDto productDto) {
        Product product = productDomainDtoMapper.fromDtoToDomain(productDto);
        productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDomainDtoMapper.fromDomainToDto(product));
    }

    @PutMapping
    public ResponseEntity<ProductDto> update(@RequestBody ProductDto productDto) {
        Product product = productDomainDtoMapper.fromDtoToDomain(productDto);
        productService.merge(product);
        return ResponseEntity.ok(productDomainDtoMapper.fromDomainToDto(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        productService.delete(UuidConverter.to(id));
        return ResponseEntity.ok().build();
    }
}
