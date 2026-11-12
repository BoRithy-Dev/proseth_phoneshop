package com.app.proseth_phoneshop.serviceimpl;

import com.app.proseth_phoneshop.dto.ProductDTO;
import com.app.proseth_phoneshop.entity.Color;
import com.app.proseth_phoneshop.entity.Model;
import com.app.proseth_phoneshop.entity.Product;
import com.app.proseth_phoneshop.entity.ProductImportHistory;
import com.app.proseth_phoneshop.exception.ApiExceptions;
import com.app.proseth_phoneshop.exception.ProductAlreadyExistsException;
import com.app.proseth_phoneshop.exception.ResourceNotFoundExceptions;
import com.app.proseth_phoneshop.mapper.ProductMapstructMapper;
import com.app.proseth_phoneshop.repository.ColorRepository;
import com.app.proseth_phoneshop.repository.ModelRepository;
import com.app.proseth_phoneshop.repository.ProductImportHistoryRepository;
import com.app.proseth_phoneshop.repository.ProductRepository;
import com.app.proseth_phoneshop.service.ProductService;
import com.app.proseth_phoneshop.service.ProductServiceImportHistory;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapstructMapper productMapstrucMapper;
    private final ModelRepository modelRepository;
    private final ColorRepository colorRepository;
    private final ProductImportHistoryRepository productImportHistoryRepository;
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
    @Override
    public Map<Integer,String> uploadProduct(MultipartFile file) {
        Map<Integer,String> map = new HashMap<>();
        try {
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheet("product");
            Iterator<Row> rowIterator = sheet.iterator();
// Skip header row
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

//            rowIterator.next();  //@todo improve
            List<Product> productsToUpdate = new ArrayList<>();
            List<ProductImportHistory> historiesToSave = new ArrayList<>();

            while (rowIterator.hasNext()){
                Integer rowNumber = 0;
              try {
                  Row row = rowIterator.next();
                  int cellIndex = 0;
                  Cell cellNo = row.getCell(cellIndex++);
                  if (cellNo ==null||cellNo.getCellType()==CellType.BLANK ){
                      continue;
                  }
                   rowNumber = (int) cellNo.getNumericCellValue();
                  Cell cellModelId = row.getCell(cellIndex++);
                  if (cellModelId == null ||
                          cellModelId.getCellType() == CellType.BLANK) {
                      continue;
                  }
                  Long modelId = (long) cellModelId.getNumericCellValue();
                  Cell cellColorId = row.getCell(cellIndex++);
                  Long colorId = (long) cellColorId.getNumericCellValue();
                  Cell cellStorage = row.getCell(cellIndex++);
                  String storageCell = cellStorage.getStringCellValue();
                  Cell cellImportPrice = row.getCell(cellIndex++);
                  BigDecimal importPrice = BigDecimal.valueOf(cellImportPrice.getNumericCellValue());
                  Cell cellImportUnit = row.getCell(cellIndex++);
                  Integer importUnit = (int) cellImportUnit.getNumericCellValue();
                  if(importUnit<1){
                      throw new ApiExceptions(HttpStatus.BAD_REQUEST,"Unit must be greater then 0");
                  }
                  Cell cellImportDate = row.getCell(cellIndex++);
                  LocalDateTime importDate = cellImportDate.getLocalDateTimeCellValue();
                  Product product = getByModeIdAndColorIdAndStorage(modelId,colorId,storageCell);
                  product.setStock(product.getStock() + importUnit);
                  productsToUpdate.add(product);
                  ProductImportHistory productImportHistory = new ProductImportHistory();
                  productImportHistory.setDateImport(importDate);
                  productImportHistory.setImportUnit(importUnit);
                  productImportHistory.setPricePerUnit(importPrice);
                  productImportHistory.setProduct(product);
                  historiesToSave.add(productImportHistory);
              }catch (Exception e){
                map.put(rowNumber, e.getMessage());
              }
            }
            productRepository.saveAll(productsToUpdate);
            productImportHistoryRepository.saveAll(historiesToSave);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
    @Override
    public Product getByModeIdAndColorIdAndStorage(Long modelId, Long colorId,String storage) {
        String text = "Product with model id =%d and color id =%d and storage =%s was not found";
        return productRepository.findByModelIdAndColorIdAndStorage(modelId,colorId,storage)
                .orElseThrow(()->new ApiExceptions(HttpStatus.BAD_REQUEST,text.formatted(modelId,colorId,storage)));
    }
}

