package tech.trgt.trgtcmsapi.services.UtilServices;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tech.trgt.trgtcmsapi.services.StorageService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@Slf4j
public class S3StorageService implements StorageService {

    @Value("${application.bucket.name}")
    private String bucketName;

    private AmazonS3 s3Client;

    public S3StorageService(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadFile(MultipartFile file) {
        String fileName = System.currentTimeMillis()+"_"+file.getOriginalFilename()
                .replaceAll(" ", "_");

        File newFile = convertMultiPartFileToFile(file);
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, newFile)
                .withCannedAcl(CannedAccessControlList.PublicRead));

        newFile.delete();

        return "https://"+bucketName+".s3.amazonaws.com/"+fileName;
    }

    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());

        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting MultiPartFile to File");
        }

        return convertedFile;
    }
}
