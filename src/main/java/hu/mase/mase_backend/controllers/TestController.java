package hu.mase.mase_backend.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.mase.mase_backend.services.TesseractService;

@RestController
public class TestController {

    private final TesseractService tesseractService;

    public TestController(TesseractService tesseractService) {
        this.tesseractService = tesseractService;
    }

    @GetMapping("/test")
    public String testEndpoint() throws FileNotFoundException, IOException {
        InputStream fileInputStream = new FileInputStream(new File("src/main/resources/static/kep.jpg"));
        String result = tesseractService.recognizeText(fileInputStream);
        return result;
    }

}
