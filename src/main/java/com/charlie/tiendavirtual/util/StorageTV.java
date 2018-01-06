package com.charlie.tiendavirtual.util;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.WriteChannel;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;

public class StorageTV {
    
    private Storage storage;
    
    private final String blobName =  "tiendavirtual-37f2e.appspot.com";
    private final String carpeta = "img/productos/";
    
    public StorageTV() throws FileNotFoundException, IOException{
        URL location = getClass().getClassLoader().getResource("firebase.json");
        storage = StorageOptions.newBuilder()
                .setProjectId("tiendavirtual-37f2e")
                .setCredentials(ServiceAccountCredentials.fromStream(new FileInputStream(location.getFile())))
                .build().getService();
    }
    
    public String subirImagen(String nombre, byte[] img, String contentType) throws IOException{
        BlobInfo blobInfo = BlobInfo.newBuilder(blobName, carpeta+nombre).setContentType(contentType).build();
        InputStream inputStream = new ByteArrayInputStream(img);
        boolean subido = false;
        try (WriteChannel writer = storage.writer(blobInfo)) {
            byte[] buffer = new byte[1024];
            int limit;
            try {
                while ((limit = inputStream.read(buffer)) >= 0) {
                    writer.write(ByteBuffer.wrap(buffer, 0, limit));
                }
                subido = true;

            } catch (Exception ex) {
                // handle exception
            }finally {
                writer.close();
            }
        }
        if(!subido){
            return blobName+"/"+carpeta+nombre;
        }
        return null;
    }
    
}
