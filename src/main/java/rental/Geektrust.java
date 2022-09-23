package rental;

import rental.service.FileInputProcessor;

import java.io.File;

public class Geektrust {
    public static void main(String[] args) throws Exception {
        String filePath = args[0];
        FileInputProcessor fileInputProcessor = new FileInputProcessor(new File(filePath));
        fileInputProcessor.process();
    }
}
