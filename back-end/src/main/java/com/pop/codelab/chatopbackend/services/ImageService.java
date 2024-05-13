package com.pop.codelab.chatopbackend.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * The ImageService interface provides methods for saving images to storage and retrieving their URLs.
 */
public interface ImageService {

    /**
     * Saves an image file to a specified directory in a storage system.
     *
     * @param uploadDirectory the directory where the image file will be saved
     * @param imageFile       the image file to be saved
     * @return the URL of the saved image file
     * @throws IOException if an I/O error occurs during the saving process
     */
    String saveImageToStorage(String uploadDirectory, MultipartFile imageFile) throws IOException;

    /**
     * Returns the URL of an image file to be served.
     *
     * @param filePath the file path of the image
     * @return the URL of the image file
     */
    String getImageToServeUrl(String filePath);
}
