package com.dlms.backend.media.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import java.util.Map;

import java.io.IOException;

@Service
public class IPFSStorageProvider implements StorageProvider {

    @Value("${pinata.api.key}")
    private String pinataApiKey;

    @Value("${pinata.secret.api.key}")
    private String pinataSecretApiKey;

    private final String PINATA_UPLOAD_URL = "https://api.pinata.cloud/pinning/pinFileToIPFS";
    private final String GATEWAY_URL = "https://gateway.pinata.cloud/ipfs/";

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(IPFSStorageProvider.class);

    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        logger.info("Attempting upload to Pinata. File: {}, Size: {}", file.getOriginalFilename(), file.getSize());

        if (pinataApiKey == null || pinataApiKey.isEmpty()) {
            logger.error("Pinata API Key is MISSING or NULL");
        } else {
            logger.info("Pinata API Key is present (Ends with: {})",
                    pinataApiKey.substring(Math.max(0, pinataApiKey.length() - 4)));
        }

        // Prepare Headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("pinata_api_key", pinataApiKey);
        headers.set("pinata_secret_api_key", pinataSecretApiKey);

        // Prepare Body
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        // FIX: Use InputStreamResource to avoid loading entire file into memory (fixes
        // OOM)
        body.add("file", new org.springframework.core.io.InputStreamResource(file.getInputStream()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }

            @Override
            public long contentLength() throws IOException {
                return file.getSize();
            }
        });

        // Create Request
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // Send POST Request using RestTemplate
        // Use SimpleClientHttpRequestFactory to enable streaming if possible, though
        // RestTemplate defaults heavily to buffering.
        // For standard Multipart, avoiding getBytes() is the biggest win.
        RestTemplate restTemplate = new RestTemplate();
        org.springframework.http.client.SimpleClientHttpRequestFactory requestFactory = new org.springframework.http.client.SimpleClientHttpRequestFactory();
        requestFactory.setBufferRequestBody(false); // Enable streaming
        restTemplate.setRequestFactory(requestFactory);

        try {
            logger.info("Sending request to Pinata...");
            ResponseEntity<Map> response = restTemplate.postForEntity(PINATA_UPLOAD_URL, requestEntity, Map.class);

            logger.info("Pinata Response Code: {}", response.getStatusCode());

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                String ipfsHash = (String) response.getBody().get("IpfsHash");
                logger.info("Upload Successful. IpfsHash: {}", ipfsHash);
                return ipfsHash;
            } else {
                logger.error("Pinata Upload Failed. Status: {}, Body: {}", response.getStatusCode(),
                        response.getBody());
                throw new IOException("Failed to upload to Pinata: " + response.getStatusCode());
            }
        } catch (Exception e) {
            logger.error("Exception communicating with Pinata API", e);
            throw new IOException("Error communicating with Pinata API: " + e.getMessage(), e);
        }
    }

    @Override
    public String getAccessUrl(String cid) {
        return GATEWAY_URL + cid;
    }
}
