package tobyspring.helloboot.controller;

import java.io.IOException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tobyspring.helloboot.service.image.HelloImageService;

@RestController
public class HelloImageController {

    private final HelloImageService imageService;

    public HelloImageController(HelloImageService imageService){
        this.imageService = imageService;
    }

    @GetMapping(value = "/image")
    public ResponseEntity<byte[]> display() {

        try {
            byte[] data = imageService.imageLoading("static/image/fefe.jpg");

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_HTML);

            return new ResponseEntity<>(data, headers, HttpStatus.OK);
        } catch (IOException e) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_PLAIN);

            return new ResponseEntity<>(e.getMessage().getBytes(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
