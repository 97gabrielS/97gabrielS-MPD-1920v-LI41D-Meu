package utils;

import weather.utils.BaseRequest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class FileRequest extends BaseRequest {
    @Override
    protected InputStream openStream(String url) throws IOException {
        String[] parts = url.split("/");
        String path = parts[parts.length-1]
                .replace('?', '-')
                .replace('&', '-')
                .replace('=', '-')
                .replace(',', '-')
                .toLowerCase();

        int idx = path.indexOf("tp-");

        path = path.substring(0, idx);

        return ClassLoader.getSystemResource(path).openStream();
    }
}
