package africa.semicolon.phoenix.service.cloud;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service("aws")
public class AwsServiceImpl implements CloudinaryService{

    @Override
    public Map<?, ?> upload(byte[] bytes, Map<?, ?> params) throws IOException {
        return null;
    }
}
