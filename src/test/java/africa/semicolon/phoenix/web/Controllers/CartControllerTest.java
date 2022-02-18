package africa.semicolon.phoenix.web.Controllers;

import africa.semicolon.phoenix.data.dto.CartRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
@Sql(scripts={"/db/insert.sql"})
class CartControllerTest {

    @Autowired
    MockMvc mockMvc;

    ObjectMapper mapper;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Add new item to cart")
    void addItemToCart() throws Exception {
        mapper = new ObjectMapper();
        CartRequestDto requestDto = new CartRequestDto();
        requestDto.setUserId(5011L);
        requestDto.setProductId(14L);
        requestDto.setQuantity(1);

        mockMvc.perform(post("/api/cart")
                .contentType("application/json")
                .content(mapper.writeValueAsString(requestDto)))
                .andExpect(status().is(200))
                .andDo(print());
    }
}