package tobyspring.helloboot.service.image;

import java.io.IOException;

public interface HelloImageService {
    public byte[] imageLoading(String imagePath) throws IOException;
}
