package com.pop.codelab.chatopbackend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;


/**
 * The ImageService class provides functionality for saving, retrieving, and deleting image files.
 *
 * @author Pignon Pierre-Olivier
 * @version 1.0
 */
@Service
public class ImageService {

    /**
     * Instance du logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ImageService.class);

    /**
     * Saves the given image file to the specified upload directory.
     *
     * @param uploadDirectory the directory where the image file should be stored
     * @param imageFile       the image file to be saved
     * @return the unique filename assigned to the saved image
     * @throws IOException if an I/O error occurs during the saving process
     */
    public String saveImageToStorage(final String uploadDirectory, final MultipartFile imageFile) throws IOException {
        logger.debug("Uploading {} MultipartFile ...", imageFile.getOriginalFilename());
        String uniqueFileName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();

        Path uploadPath = Path.of(uploadDirectory);
        Path filePath = uploadPath.resolve(uniqueFileName);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        logger.debug("{} has been uploaded", uniqueFileName);
        return uniqueFileName;
    }

    /**
     * Retrieves the image with the specified imageDirectory and imageName.
     *
     * @param imageDirectory the directory where the image is located
     * @param imageName      the name of the image file
     * @return the bytes of the image file
     * @throws IOException if an I/O error occurs while retrieving the image
     */
    public byte[] getImage(final String imageDirectory, final String imageName) throws IOException {
        Path imagePath = Path.of(imageDirectory, imageName);

        if (Files.exists(imagePath)) {
            byte[] imageBytes = Files.readAllBytes(imagePath);
            return imageBytes;
        } else {
            return null; // Handle missing images
        }
    }

    /**
     * Deletes the image with the specified imageDirectory and imageName.
     *
     * @param imageDirectory the directory where the image is located
     * @param imageName      the name of the image file
     * @return a string indicating the result of the deletion operation ("Success" or "Failed")
     * @throws IOException if an I/O error occurs while deleting the image
     */
    public String deleteImage(final String imageDirectory, final String imageName) throws IOException {
        Path imagePath = Path.of(imageDirectory, imageName);

        if (Files.exists(imagePath)) {
            Files.delete(imagePath);
            return "Success";
        } else {
            return "Failed"; // Handle missing images
        }
    }
}