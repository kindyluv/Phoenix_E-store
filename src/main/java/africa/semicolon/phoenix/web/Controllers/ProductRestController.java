package africa.semicolon.phoenix.web.Controllers;

import africa.semicolon.phoenix.data.dto.ProductDto;
import africa.semicolon.phoenix.data.models.Product;
import africa.semicolon.phoenix.service.ProductService;
import africa.semicolon.phoenix.web.Exceptions.BusinessLogicException;
import africa.semicolon.phoenix.web.Exceptions.ProductDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductRestController {

    @Autowired
    ProductService productService;

    @GetMapping()
    public ResponseEntity<?> findAllProducts(){
        List<Product> productList = productService.getAllProducts();
        return ResponseEntity.ok().body(productList);
    }

    @PostMapping()
    public ResponseEntity<?> createProduct(@RequestBody ProductDto request){
        try{
            Product product = productService.createProduct(request);
            return ResponseEntity.ok().body(product);
        }catch(IllegalArgumentException | BusinessLogicException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

//    @PutMapping()
//    public ResponseEntity<?> updateProduct(@PathVariable Long productId, @RequestBody ProductDto productDto){
//        try{
//            Product updateProduct = productService.updateProduct(productId, productDto);
//            return ResponseEntity.ok().body(updateProduct);
//        }catch(IllegalArgumentException | ProductDoesNotExistException e){
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
}
