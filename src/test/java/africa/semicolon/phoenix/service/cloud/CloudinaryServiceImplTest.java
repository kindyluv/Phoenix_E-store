package africa.semicolon.phoenix.service.cloud;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class CloudinaryServiceImplTest {

    @Qualifier("cloudinary-service")
    @Autowired
    CloudinaryService cloudinaryService;

    @Autowired
    Cloudinary cloudinary;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Cloudinary object instantiated test")
    void cloudinaryObjectInstanceTest(){
        assertThat(cloudinary).isNotNull();
    }

    @Test
    void uploadToCloudinaryTest() throws IOException {
        Path path = Paths.get("src/test/resources/photo.jpg");
        assertThat(path.toFile().exists()).isTrue();
        Map<?,?> uploadResult = cloudinaryService.upload(Files.readAllBytes(path), ObjectUtils.emptyMap());
        log.info("Upload result to cloud -> {}", uploadResult);
        assertThat(uploadResult.get("url")).isNotNull();
    }

    @Test
    @DisplayName("Upload Multiple Files To Cloudinary")
    void uploadMultipleFilesToCloudinaryTest() throws IOException {
        //load the file
        Path path = Paths.get("src/test/resources/photo.jpg");
        assertThat(path.toFile().exists());
        assertThat(path.getFileName().toString()).isEqualTo("photo.jpg");
        //load multipart
        MultipartFile multipartFile = new MockMultipartFile(path.getFileName().toString(), path.getFileName().toString(),
                "img/jpg", Files.readAllBytes(path));
        assertThat(multipartFile).isNotNull();
        assertThat(multipartFile.isEmpty()).isFalse();
        //store a multipart to file

        //convert multipartFile to file
//        File image
//        upload to cloud
        Map<?,?> uploadResult =cloudinaryService.upload(multipartFile.getBytes(), ObjectUtils.emptyMap());
        assertThat(uploadResult.get("url")).isNotNull();
    }
}