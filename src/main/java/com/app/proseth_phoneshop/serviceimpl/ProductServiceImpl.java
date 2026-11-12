package com.app.proseth_phoneshop.serviceimpl;

import com.app.proseth_phoneshop.dto.ProductDTO;
import com.app.proseth_phoneshop.entity.Color;
import com.app.proseth_phoneshop.entity.Model;
import com.app.proseth_phoneshop.entity.Product;
import com.app.proseth_phoneshop.exception.ApiExceptions;
import com.app.proseth_phoneshop.exception.ProductAlreadyExistsException;
import com.app.proseth_phoneshop.exception.ResourceNotFoundExceptions;
import com.app.proseth_phoneshop.mapper.ProductMapstructMapper;
import com.app.proseth_phoneshop.repository.ColorRepository;
import com.app.proseth_phoneshop.repository.ModelRepository;
import com.app.proseth_phoneshop.repository.ProductRepository;
import com.app.proseth_phoneshop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapstructMapper productMapstrucMapper;
    private final ModelRepository modelRepository;
    private final ColorRepository colorRepository;
//    @Override
//    public ProductDTO create(ProductDTO productDTO) {
//        Product product = productMapstrucMapper.toProductEntity(productDTO);
//        if(productDTO.getModelId() !=null && productDTO.getModelId()> 0){
//            Model model = modelRepository.findById(productDTO.getModelId())
//                    .orElseThrow(() -> new ResourceNotFoundExceptions("Model:",+ productDTO.getModelId()));
//            product.setModel(model);
//        }
//        if(productDTO.getColorId() !=null && productDTO.getColorId()> 0){
//            Color color = colorRepository.findById(productDTO.getColorId())
//                    .orElseThrow(() -> new ResourceNotFoundExceptions("Color:", +productDTO.getColorId()));
//            product.setColor(color);
//        }
//        String name ="%s %s"
//                .formatted(product.getModel().getName(),product.getColor().getName());
//        product.setName(name);
//        Product productEntity = productRepository.save(product);
//      return productMapstrucMapper.toProductDTO(productEntity);
//    }

    @Override
    public Product create(ProductDTO productDTO) {
                Product product = productMapstrucMapper.toProductEntity(productDTO);
        if(productDTO.getModelId() !=null && productDTO.getModelId()> 0){
            Model model = modelRepository.findById(productDTO.getModelId())
                    .orElseThrow(() -> new ResourceNotFoundExceptions("Model:",+ productDTO.getModelId()));
            product.setModel(model);
        }
        if(productDTO.getColorId() !=null && productDTO.getColorId()> 0){
            Color color = colorRepository.findById(productDTO.getColorId())
                    .orElseThrow(() -> new ResourceNotFoundExceptions("Color:", +productDTO.getColorId()));
            product.setColor(color);
        }
        boolean exits = productRepository.existsByModelIdAndColorIdAndStorage(
                productDTO.getModelId(),
                productDTO.getColorId(),
                productDTO.getStorage()
        );
        if(exits){
            throw new ProductAlreadyExistsException(
                    productDTO.getModelId(), productDTO.getModelId(), product.getStorage()
            );
        }
        String name ="%s %s %s"
                .formatted(product.getModel().getName(),
                        product.getColor().getName(),product.getStorage());
        product.setName(name);
      return productRepository.save(product);
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product productEntity = productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundExceptions("Product",id));
        return productMapstrucMapper.toProductDTO(productEntity);
    }

    @Override
    public List<ProductDTO> getAllProduct() {
        List<Product> productList = productRepository.findAll();
        return productList.stream().
                map(productMapstrucMapper::toProductDTO).toList();
    }
    @Override
    public void setSellPrice(Long id, BigDecimal price) {
//       ProductDTO productDTO = getProductById(id);
//       productDTO.setSellingPrice(price);
//       Product productEntity = productMapstrucMapper.toProductEntity(productDTO);
//        productRepository.save(productEntity);
        Product product = productRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundExceptions("Product",id));
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ApiExceptions(
                    HttpStatus.BAD_REQUEST,
                    "Price must be greater than 0"
            );
        }
        product.setSellingPrice(price);
        productRepository.save(product);

    }

    @Override
    public void validateStock(Long productId, Integer numberOfUnit) {

    }
}

