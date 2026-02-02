package com.dlms.backend.media.service;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface StorageProvider {
    /**
     * Uploads a file to the storage provider.
     * 
     * @param file The file to upload
     * @return The unique identifier (CID for IPFS, Key for S3)
     * @throws IOException If upload fails
     */
    String uploadFile(MultipartFile file) throws IOException;

    /**
     * Generates a URL to access the file.
     * 
     * @param cid The content identifier
     * @return The access URL
     */
    String getAccessUrl(String cid);
}
