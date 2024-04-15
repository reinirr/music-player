package me.neatomaru.musicplayer.config;

import lombok.Getter;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class Config {
    @Getter
    private final String accessKey;
    @Getter
    private final String secretKey;
    @Getter
    private final String bucketName;
    @Getter
    private final String region;
    @Getter
    private final String endpoint;


    public Config(Environment environment) {
        accessKey = environment.getProperty("AWS_ACCESS_KEY");
        secretKey = environment.getProperty("AWS_SECRET_KEY");
        bucketName = environment.getProperty("AWS_BUCKET_NAME");
        region = environment.getProperty("AWS_REGION");
        endpoint = environment.getProperty("AWS_ENDPOINT");
    }


}
