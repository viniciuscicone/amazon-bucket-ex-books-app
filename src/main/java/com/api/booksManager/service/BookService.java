package com.api.booksManager.service;

import com.api.booksManager.domain.Book;
import com.api.booksManager.domain.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.UUID;


@Service
public class BookService {

    @Value("${BUCKETNAME}")
    private String bucket;

    @Autowired
    private S3Client s3Client;

    public String uploadImage(MultipartFile multipartFile) {

        String imgName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

        try {

            File file = this.convertMultipartTofile(multipartFile);

            PutObjectRequest putOb = PutObjectRequest.builder()
                    .bucket(bucket)
                    .key(imgName)
                    .build();
            s3Client.putObject(putOb, RequestBody.fromByteBuffer(ByteBuffer.wrap(multipartFile.getBytes())));
            GetUrlRequest request = GetUrlRequest.builder()
                    .bucket(bucket)
                    .key(imgName)
                    .build();
            file.delete();
            return s3Client.utilities().getUrl(request).toString();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    public Book uploadBookService(BookDTO book, MultipartFile imagem) {

        /*String url = uploadImage(imagem);*/

        Book newbook = Book.builder()
                .id(UUID.randomUUID())
                .nameBook(book.getNameBook())
                .autor(book.getAutor())
                .sinopse(book.getSinopse())
                .img_url("url")
                .nota(book.getNota()).build();

        return newbook;
    }


    private File convertMultipartTofile(MultipartFile multipartFile) throws IOException {

        File convertFile = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convertFile);
        fos.write(multipartFile.getBytes());
        fos.close();
        return convertFile;
    }

}
