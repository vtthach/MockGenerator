package com.example;


import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class TemplateUtils {
    String templateFilePath;
    public String templateData;

    public String outputData;

    public TemplateUtils(String templateFilePath) {
        this.templateFilePath = templateFilePath;
        try {
            init();
        } catch (IOException e) {
            System.out.println("Init file template error: " + e.getMessage());
        }
    }

    private void init() throws IOException {
        File fileTemplate = new File(templateFilePath);
        templateData = FileUtils.readFileToString(fileTemplate, Charsets.UTF_8);
    }

    public TemplateUtils prepare() {
        outputData = templateData;
        return this;
    }

    public TemplateUtils replace(String key, String value) {
        outputData = outputData.replace(key, value);
        return this;
    }

    public void build(String fileName) {
        try {
            FileUtils.writeStringToFile(getWFile(fileName), outputData, Charsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Build file template error: " + e.getMessage());
        }
    }

    private File getWFile(String fileName) {
        File file = new File("Output/" + fileName);
        if (file.exists()) {
            file.delete();
        }
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        return file;
    }
}
