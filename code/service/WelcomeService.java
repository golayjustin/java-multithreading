
package edu.wgu.d387_sample_code.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class WelcomeService {

    private ExecutorService executorService = Executors.newFixedThreadPool(2);

    public List<String> fetchMessages() {
        List<String> messages = new ArrayList<>();
        executorService.execute(() -> {
            messages.add(loadMessage("translation_en_US.properties"));
        });
        executorService.execute(() -> {
            messages.add(loadMessage("translation_fr_CA.properties"));
        });

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return messages;
    }

    private String loadMessage(String resourceName) {
        Properties properties = new Properties();
        try (InputStream stream = new ClassPathResource(resourceName).getInputStream()) {
            properties.load(stream);
            return properties.getProperty("welcome");
        } catch (Exception e) {
            return "Error loading message";
        }
    }
}