package tobyspring.helloboot.service.image;

import java.io.IOException;

public class HelloImageProxyService implements HelloImageService {
    private final HelloImageService helloImageService;

    public HelloImageProxyService(HelloImageService helloImageService) {
        this.helloImageService = helloImageService;
    }

    @Override
    public byte[] imageLoading(String imagePath) throws IOException {
        // 원격 객체에 대한 로컬 인터페이스 제공, 접근 제어, 리소스가 많이 필요한 객체의 생성 지연 등의 로직 구현
        System.out.println("Proxy handling the image loading");
        return helloImageService.imageLoading(imagePath);
    }

}
