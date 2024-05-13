package com.pop.codelab.chatopbackend.services.Impl;

import com.pop.codelab.chatopbackend.services.ImageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

/**
 * The ImageServiceImpl class is an implementation of the ImageService interface.
 * It provides methods for saving images to a storage system and retrieving their URLs.
 */
@Service
@Log4j2
public class ImageServiceImpl implements ImageService {

    /**
     * The uploadDirectory variable represents the directory where the image files will be saved.
     * It is configured using the application.local-storage.upload-directory property in the application's configuration.
     * It is injected using the @Value annotation and can be accessed throughout the class.
     */
    @Value("${application.local-storage.upload-directory}")
    private String uploadDirectory;


    /**
     * Saves an image file to a specified directory in a storage system.
     *
     * @param uploadDirectory the directory where the image file will be saved
     * @param imageFile       the image file to be saved
     * @return the URL of the saved image file
     * @throws IOException if an I/O error occurs during the saving process
     */
    public String saveImageToStorage(final String uploadDirectory, final MultipartFile imageFile) throws IOException {
        log.debug("Uploading {} MultipartFile ...", imageFile.getOriginalFilename());
        String uniqueFileName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();

        Path uploadPath = Path.of(uploadDirectory);
        Path filePath = uploadPath.resolve(uniqueFileName);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        log.debug("{} has been uploaded", uniqueFileName);
        return uniqueFileName;
    }

    /**
     * Returns the URL of an image file to be served.
     *
     * @param filePath the file path of the image
     * @return the URL of the image file
     */
    public String getImageToServeUrl(final String filePath) {
        log.debug("Checking the {} image.", filePath);
        String imageToServeUrl = "";
        String fileName = String.valueOf(Path.of(uploadDirectory).resolve(filePath));

        if (Files.exists(Path.of(fileName))) {
            log.debug("Retrieving the image : {} ", fileName);
            imageToServeUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/images/")
                    .path(filePath)
                    .toUriString();
            log.debug("Image will be served at  = {}", imageToServeUrl);
        } else {
            log.warn("The image {} has not been found !", fileName);
        }
        return imageToServeUrl;
    }
}
