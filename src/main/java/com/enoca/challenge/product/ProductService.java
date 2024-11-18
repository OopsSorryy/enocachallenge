package com.enoca.challenge.product;


import com.enoca.challenge.constant.Constant;
import com.enoca.challenge.exception.AlreadyExistException;
import com.enoca.challenge.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    @CacheEvict(value = Constant.REDIS_PRODUCT_CLASS, allEntries = true, condition = "#createProductRequest != null")
    public void createProduct(CreateProductRequest createProductRequest){

        if(productRepository.findProductByName(createProductRequest.name()).isPresent()){
            throw new AlreadyExistException(Constant.PRODUCT_ALREADY_EXIST);
        }

        Product product = new Product
                (
                        createProductRequest.name(),
                        createProductRequest.price(),
                        createProductRequest.unitInStock()
                );

        productRepository.save(product);

    }

    @Cacheable(Constant.REDIS_PRODUCT_CLASS)
    public List<ProductResponse> getAllProducts() {
        System.out.println("Data is fetched from db");
       return productConverter.getAllConvert(productRepository.findAll());
    }

    @CacheEvict(value = Constant.REDIS_PRODUCT_CLASS, allEntries = true, condition = "#updateProductRequest != null")
    public void updateProduct(UpdateProductRequest updateProductRequest){

        Product product = findById(updateProductRequest.id());

        if(productRepository.findProductByName(updateProductRequest.name()).isPresent()){
            throw new AlreadyExistException(Constant.PRODUCT_ALREADY_EXIST);
        }

        product.setName(updateProductRequest.name() == null || updateProductRequest.name().trim().isEmpty() ? product.getName() : updateProductRequest.name());
        product.setPrice(updateProductRequest.price() == null ? product.getPrice() : updateProductRequest.price());
        product.setUnitInStock(updateProductRequest.unitInStock() == null ? product.getUnitInStock() : updateProductRequest.unitInStock());

        productRepository.save(product);
    }

    @Cacheable(value = Constant.REDIS_PRODUCT_CLASS,key = "#id")
    public ProductResponse getProduct(String id){
        return productConverter.convert(findById(id));
    }

    @CacheEvict(value = Constant.REDIS_PRODUCT_CLASS, allEntries = true, condition = "#id != null")
    public void deleteProduct(String id){
        productRepository.delete(findById(id));
    }

    @CacheEvict(value = Constant.REDIS_PRODUCT_CLASS, allEntries = true, condition = "#id != null")
    public Product findProductByProductIdAndSetUnitInStockForAddProductToCart(String id){
        Product product = findById(id);

        if(product.getUnitInStock() == 0){
            throw new NotFoundException(Constant.PRODUCT_NOT_ENOUGH);
        }
        Integer newUnitStock = product.getUnitInStock() - 1;
        product.setUnitInStock(newUnitStock);
        productRepository.save(product);
        return product;
    }
    @CacheEvict(value = Constant.REDIS_PRODUCT_CLASS, allEntries = true, condition = "#id != null")
    public void findProductByProductIdAndSetUnitInStockForDeleteProductFromCart(String id){
        Product product = findById(id);
        Integer newUnitStock = product.getUnitInStock() + 1;
        product.setUnitInStock(newUnitStock);
        productRepository.save(product);
    }

    protected Product findById(String id){
       return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Constant.PRODUCT_NOT_FOUND));
    }
}
