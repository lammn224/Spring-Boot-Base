package com.lammai.SpringBootBase.common;

import com.google.common.io.ByteStreams;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class SqlBuilder {
    public static String getSqlQueryById(String module, String queryId) {
        InputStream inputStream = null;
        try {
            String filePath = "sql" + File.separator + module + File.separator + queryId + ".sql";
            Resource resource = new ClassPathResource(filePath);
            inputStream = resource.getInputStream();
            return new String(ByteStreams.toByteArray(inputStream));
        } catch (IOException e) {
            return null;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
