package com.mycompany.smartride.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SupabaseStorageUtil {
    private static final String SUPABASE_URL = "https://zfvgigfjmbtgwgirdify.supabase.co";
    // Replace this with the actual secret key for internal usage
    private static final String SUPABASE_KEY = "YOUR_SUPABASE_KEY_HERE";
    private static final String BUCKET_NAME = "images";

    public static String uploadFile(java.io.InputStream is, String fileName) {
        return uploadFile(is, fileName, "images");
    }

    public static String uploadFile(java.io.InputStream is, String fileName, String bucketName) {
        try {
            String endpoint = SUPABASE_URL + "/storage/v1/object/" + bucketName + "/" + fileName;
            URL url = new URL(endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("apikey", SUPABASE_KEY);
            conn.setRequestProperty("Authorization", "Bearer " + SUPABASE_KEY);
            
            // Map file extension to the correct image Content-Type for in-browser preview
            String contentType = "application/octet-stream";
            String lowerName = fileName.toLowerCase();
            if (lowerName.endsWith(".jpg") || lowerName.endsWith(".jpeg")) {
                contentType = "image/jpeg";
            } else if (lowerName.endsWith(".png")) {
                contentType = "image/png";
            } else if (lowerName.endsWith(".gif")) {
                contentType = "image/gif";
            } else if (lowerName.endsWith(".webp")) {
                contentType = "image/webp";
            }
            conn.setRequestProperty("Content-Type", contentType);
            conn.setRequestProperty("x-upsert", "true"); // Allow overwriting existing files

            try (OutputStream os = conn.getOutputStream()) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == 200 || responseCode == 201) {
                return SUPABASE_URL + "/storage/v1/object/public/" + bucketName + "/" + fileName;
            } else {
                System.out.println("Supabase upload failed with response code: " + responseCode);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean deleteFile(String publicUrl) {
        if (publicUrl == null || !publicUrl.startsWith(SUPABASE_URL)) {
            return false;
        }
        try {
            // Support deleting from both 'images' and 'profileImg' dynamically
            String bucketName = "images";
            if (publicUrl.contains("/profileImg/")) {
                bucketName = "profileImg";
            }
            
            String prefix = SUPABASE_URL + "/storage/v1/object/public/" + bucketName + "/";
            if (!publicUrl.startsWith(prefix)) {
                return false;
            }
            String fileName = publicUrl.substring(prefix.length());
            
            String endpoint = SUPABASE_URL + "/storage/v1/object/" + bucketName + "/" + fileName;
            URL url = new URL(endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            conn.setRequestProperty("apikey", SUPABASE_KEY);
            conn.setRequestProperty("Authorization", "Bearer " + SUPABASE_KEY);
            
            int responseCode = conn.getResponseCode();
            if (responseCode == 200 || responseCode == 204) {
                return true;
            } else {
                System.out.println("Supabase delete failed with response code: " + responseCode);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
