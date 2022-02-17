package africa.semicolon.phoenix.data.repository;

import africa.semicolon.phoenix.data.models.AppUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Sql(scripts="/db/insert.sql")
class AppUserRepositoryTest {

    @Autowired
    AppUserRepository appUserRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Create a new user with cart test")
    void whenUserIsCreated_ThenCreateCartTest(){
        //creating a user object
        AppUser appUser = new AppUser();
        appUser.setFirstName("John");
        appUser.setLastName("Badmus");
        appUser.setEmail("john@email.com");
        appUser.setAddress("11 shomolu Road");

        appUserRepository.save(appUser);

        assertThat(appUser.getId()).isNotNull();
        assertThat(appUser.getMyCart()).isNotNull();

        log.info("App user created :: {}", appUser);
        //creating a cart object

    }
}