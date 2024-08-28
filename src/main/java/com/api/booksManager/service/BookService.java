package com.api.booksManager.service;

import com.api.booksManager.domain.Book;
import com.api.booksManager.domain.BookDTO;
import com.api.booksManager.repository.BookRepository;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.*;



@Service
public class BookService {

    @Value("${BUCKETNAME}")
    private String bucket;

    @Autowired
    private S3Client s3Client;

    @Autowired
    private BookRepository bookrepository;

    public String uploadImage(MultipartFile multipartFile) {

        String imgName = UUID.randomUUID() +  "imagem_bucket.jpeg";

        try {

            File file = this.convertAndResizeImage(multipartFile);

            PutObjectRequest putOb = PutObjectRequest.builder()
                    .bucket(bucket)
                    .key(imgName)
                    .build();
            s3Client.putObject(putOb, RequestBody.fromFile(file));
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

    public String deleteBook(String id) {

        try {

            Book book = bookrepository.findById(id).orElse(null);

            if (book == null) {
                throw new RuntimeException("Livro nao encontrado !");
            }
            bookrepository.deleteById(id);

            String key = new URL(book.getImg_url()).getPath().substring(1);
            // Remove o '/' inicial
            DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                    .bucket(bucket)
                    .key(key)
                    .build();

            s3Client.deleteObject(deleteObjectRequest);

            return "Livro : " + id + ", Apagado";

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public Book uploadBookService(BookDTO book, MultipartFile imagem) {

        if (imagem == null || imagem.isEmpty()) {
            throw new RuntimeException("Imagem nao anexada");
        }
        validateImageFile(imagem);

        String url = uploadImage(imagem);

        Book newbook = Book.builder()
                .id(UUID.randomUUID().toString())
                .name_book(book.getNameBook())
                .autor(book.getAutor())
                .sinopse(book.getSinopse())
                .img_url(url)
                .nota(book.getNota()).build();

        return bookrepository.save(newbook);
    }


    private File convertAndResizeImage(MultipartFile multipartFile) throws IOException {

        try {

        File tempFile = File.createTempFile("temp", ".jpeg");
        Thumbnails.of(multipartFile.getInputStream())
                .forceSize(1630, 1473) // Defina o tamanho desejado para a capa do livro
                .outputFormat("jpeg")
                .toFile(tempFile);

        return tempFile;

        } catch (Exception e) {
           throw new RuntimeException(e.getMessage());
        }
    }
    private void validateImageFile(MultipartFile file) {
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new RuntimeException("Arquivo não é uma imagem válida");
        }
    }
}
