package africa.semicolon.phoenix.service;

import africa.semicolon.phoenix.data.dto.ProductDto;
import africa.semicolon.phoenix.data.models.Product;
import africa.semicolon.phoenix.web.Exceptions.BusinessLogicException;
import africa.semicolon.phoenix.web.Exceptions.ProductDoesNotExistException;
import com.github.fge.jsonpatch.JsonPatch;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product findProductById(Long productId) throws ProductDoesNotExistException;
    Product createProduct(ProductDto productDto) throws BusinessLogicException;
    Product updateProductDetails(Long productId, JsonPatch patch) throws BusinessLogicException;
//    Product updateProduct(Long productId, ProductDto productDto) throws ProductDoesNotExistException;

}
