package Image;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {

    @Bean
    public AmazonS3 amazonS3Client() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials("access_key", "secret_key");
        return AmazonS3ClientBuilder.standard()
                .withRegion("ap-northeast-2") // Change to your region
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();
    }
}
