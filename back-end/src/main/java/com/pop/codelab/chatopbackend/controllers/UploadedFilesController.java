package com.pop.codelab.chatopbackend.controllers;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The UploadedFilesController class is a rest controller that handles requests related to uploaded files.
 * It provides methods to serve files from a specified upload directory.
 *
 * @author Pignon Pierre-Olivier
 * @version 1.0
 */
@RestController
public class UploadedFilesController {

    /**
     * Upload directory.
     */
    @Value("${application.local-storage.upload-directory}")
    private String uploadDirectory;

    /**
     * Serves a file with the given fileName from the upload directory.
     *
     * @param fileName The name of the file to serve.
     * @return The ResponseEntity containing the resource if the file exists, otherwise a ResponseEntity with status code 404.
     */
    @GetMapping("/images/{fileName:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable final String fileName) {
        try {
            // Load file as Resource
            Path filePath = Paths.get(uploadDirectory).resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            // Check if the file exists
            if (resource.exists()) {
                // Set Content-Disposition header for file download
                HttpHeaders headers = new HttpHeaders();
                // Special characters treatment with an encoding to avoid wrong headers.
                String encodedFilename = URLEncoder.encode(resource.getFilename(), "UTF-8")
                        .replaceAll("\\+", "%20");
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
                        encodedFilename + "\"");

                return ResponseEntity.ok().headers(headers).body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException ex) {
            return ResponseEntity.notFound().build();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
