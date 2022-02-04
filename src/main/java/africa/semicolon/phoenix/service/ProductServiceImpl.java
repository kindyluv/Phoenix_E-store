package africa.semicolon.phoenix.service;

import africa.semicolon.phoenix.data.dto.ProductDto;
import africa.semicolon.phoenix.data.models.Product;
import africa.semicolon.phoenix.data.repository.ProductRepository;
import africa.semicolon.phoenix.web.Exceptions.BusinessLogicException;
import africa.semicolon.phoenix.web.Exceptions.ProductDoesNotExistException;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findProductById(Long productId) throws ProductDoesNotExistException {
        if(productId == null){
            throw new IllegalArgumentException("Id cannot be null");
        }
        Optional<Product> queryResult = productRepository.findById(productId);
        if(queryResult.isPresent()){
            return queryResult.get();
        }
        throw new ProductDoesNotExistException("Product with iD: "+ productId +""+"does not exist");
    }

    @Override
    public Product createProduct(ProductDto productDto) throws BusinessLogicException {
        if(productDto == null){
            throw new IllegalArgumentException("Argument cannot be null.");
        }

        Optional<Product> query = productRepository.findByName(productDto.getName());
        if(query.isPresent()) throw new BusinessLogicException("Products with name"+""+ productDto.getName()+""+" already exists");

        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        return productRepository.save(product);
    }

    @Override
    public Product updateProductDetails(Long productId, JsonPatch patch) throws BusinessLogicException {
        Optional<Product> query = productRepository.findById(productId);
        if(query.isEmpty()) throw new BusinessLogicException("Products with name"+""+ productId+""+" does not exists");

        return null;
    }

//    @Override
//    public Product updateProduct(Long productId, ProductDto productDto) throws ProductDoesNotExistException {
//        if(productId == null) throw new IllegalArgumentException("Product id is null");
//
//        Optional<Product> foundProduct = productRepository.findById(productId);
//        if(foundProduct.isEmpty()) throw new ProductDoesNotExistException("Product with product id" + " " + productId +" "+" doesnt exist");
//        foundProduct.get().setName(productDto.getName());
//        foundProduct.get().setDescription(productDto.getDescription());
//        foundProduct.get().setPrice(productDto.getPrice());
//        foundProduct.get().setQuantity(productDto.getQuantity());
//        return productRepository.save(foundProduct.get());
//    }
}
