package africa.semicolon.phoenix.web.Controllers;

import africa.semicolon.phoenix.data.models.Product;
import africa.semicolon.phoenix.data.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts= "/db/insert.sql")
class ProductRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ProductRepository productRepository;

    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Get product api test")
    void getProductsTest() throws Exception {
        mockMvc.perform(get("/api/product")
                        .contentType("application/json"))
                .andExpect(status().is(200))
                .andDo(print());
    }

    @Test
    @DisplayName("Create a product api test")
    void createProductsTest() throws Exception {
//        Product product = new Product();
//        product.setName("Bamboo Chair");
//        product.setDescription("World class bamboo");
//        product.setPrice(12345);
//        product.setQuantity(9);

//        String requestBody = objectMapper.writeValueAsString(product);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/product")
                        .param("name", "Bamboo Chair")
                        .param("description", "World class bamboo")
                        .param("price", "12345")
                        .param("quantity", "9");

        mockMvc.perform(request
                        .contentType("multipart/form-data"))
                .andExpect(status().is(200))
                .andDo(print());
    }

    @Test
    @DisplayName("Update product")
    void updateProductTest() throws Exception {
        Product product = productRepository.findById(14L).orElse(null);
        assertThat(product).isNotNull();

        mockMvc.perform(patch("/api/product/14")
                .contentType("application/json-patch+json")
                .content(Files.readAllBytes(Path.of("payload.json"))))
                .andExpect(status().is(200))
                .andDo(print());

        product = productRepository.findById(14L).orElse(null);
        assertThat(product).isNotNull();
        assertThat(product.getDescription()).isEqualTo("This is a bamboo");
        assertThat(product.getQuantity()).isEqualTo(14);
    }


}