package tobyspring.helloboot.service.image;

import java.io.IOException;
import java.nio.file.Files;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class HelloImage implements HelloImageService {

    @Override
    public byte[] imageLoading(String imagePath) throws IOException {
        ClassPathResource resource = new ClassPathResource(imagePath);

        if(!resource.exists()){
            throw new IOException("해당 파일을 찾을 수가 없습니다.");
        }

        return Files.readAllBytes(resource.getFile().toPath());
    }
}
