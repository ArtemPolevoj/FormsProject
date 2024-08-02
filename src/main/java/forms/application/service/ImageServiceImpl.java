package forms.application.service;

import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveBucketArgs;
import io.minio.RemoveObjectArgs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageServiceImpl implements ImageService {
    private static final String BUCKET_NAME = "images-bucket";

    private final MinioClient minioClient;

    @Override
    public String uploadImage(String resultFileName, byte[] file) {
        return putObjectToBucket(file, resultFileName);
    }

    @Override
    public byte[] downloadImage(String resultFileName) {
        return getObjectFromBucket(resultFileName);
    }

    @Override
    public void removeImage(String objectName) {
        try {
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(BUCKET_NAME)
                            .object(objectName)
                            .build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private byte[] getObjectFromBucket(String objectName) {
        log.info("image to search: " + objectName);
        try (InputStream stream =
                     minioClient.getObject(GetObjectArgs
                             .builder()
                             .bucket(BUCKET_NAME)
                             .object(objectName)
                             .build())) {
            return IOUtils.toByteArray(stream);
        } catch (Exception e) {
            throw new RuntimeException("image with path: " + objectName + " not found");
        }
    }

    private void removeBucket(String bucketName) {
        RemoveBucketArgs build = RemoveBucketArgs.builder()
                .bucket(bucketName)
                .build();
    }

    private void createBucketIfNotExists(String name) {
        try {
            boolean isAlreadyExists = minioClient.bucketExists(
                    BucketExistsArgs.builder()
                            .bucket(name)
                            .build());
            if (isAlreadyExists) {
                log.info("Trying to create new Minio bucket. Bucket with name: " + name + " is already exists!");
            } else {
                minioClient.makeBucket(MakeBucketArgs.builder()
                        .bucket(name)
                        .build());
            }
        } catch (Exception e) {
            throw new RuntimeException("Minio: " + e.getMessage());
        }
    }

    private String putObjectToBucket(byte [] image, String resultFileName) {
        createBucketIfNotExists(BUCKET_NAME);

        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(image)) {
            minioClient.putObject(PutObjectArgs
                    .builder()
                    .bucket(BUCKET_NAME)
                    .object(resultFileName)
                    .stream(inputStream, inputStream.available(), -1)
                    .build());
            return "The image has been uploaded successfully with name: " + resultFileName;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
