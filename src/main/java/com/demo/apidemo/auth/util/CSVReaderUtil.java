package com.demo.apidemo.auth.util;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;

public class CSVReaderUtil {

    public static List<String[]> readAll(MultipartFile file) throws IOException {
        Reader reader = new InputStreamReader(file.getInputStream(), Charset.forName("MS949"));
        CSVReader csvReader  = new CSVReaderBuilder(reader).build();
        return csvReader.readAll();
    }
}
