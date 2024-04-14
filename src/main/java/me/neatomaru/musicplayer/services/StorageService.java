package me.neatomaru.musicplayer.services;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StorageService {

    private final String accessKey;
    private final String secretKey;
    private final String bucketName;
    private final String region;
    private final String endpoint;


    private final AmazonS3 space;

    public StorageService(Environment environment) {
        accessKey = environment.getProperty("AWS_ACCESS_KEY");
        secretKey = environment.getProperty("AWS_SECRET_KEY");
        bucketName = environment.getProperty("AWS_BUCKET_NAME");
        region = environment.getProperty("AWS_REGION");
        endpoint = environment.getProperty("AWS_ENDPOINT");
        AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(
                new BasicAWSCredentials(accessKey, secretKey)
        );

        space = AmazonS3ClientBuilder
                .standard()
                .withCredentials(awsCredentialsProvider)
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration(
                                endpoint, region
                        )
                )
                .build();
    }

    public List<String> getSongFileNames() {
        ListObjectsV2Result result = space.listObjectsV2(bucketName);
        List<S3ObjectSummary> objects = result.getObjectSummaries();

        return objects.stream()
                .map(S3ObjectSummary::getKey).collect(Collectors.toList());
    }

    public void uploadSong(MultipartFile file) {
        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            space.putObject(new PutObjectRequest(bucketName, file.getOriginalFilename(), file.getInputStream(), objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
