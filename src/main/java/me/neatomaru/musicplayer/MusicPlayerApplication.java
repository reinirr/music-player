package me.neatomaru.musicplayer;

import me.neatomaru.musicplayer.services.StorageService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MusicPlayerApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(MusicPlayerApplication.class, args);
        StorageService storageService = context.getBean(StorageService.class);
        System.out.println(storageService.getSongFileNames());
    }

}
