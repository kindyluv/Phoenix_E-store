package africa.semicolon.phoenix.web.Controllers;

import africa.semicolon.phoenix.data.dto.ProductDto;
import africa.semicolon.phoenix.data.models.Product;
import africa.semicolon.phoenix.service.product.ProductService;
import africa.semicolon.phoenix.web.Exceptions.BusinessLogicException;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<?> createProduct(@ModelAttribute ProductDto request){
        try{
            Product product = productService.createProduct(request);
            return ResponseEntity.ok().body(product);
        }catch(IllegalArgumentException | BusinessLogicException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody JsonPatch productPatch) throws BusinessLogicException {
        try{
           Product updateProduct = productService.updateProductDetails(id, productPatch);
            return ResponseEntity.status(HttpStatus.OK).body(updateProduct);
        } catch (BusinessLogicException e) {
          ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return null;
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
