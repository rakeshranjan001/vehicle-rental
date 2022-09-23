package rental;

import rental.service.FileInputProcessor;

import java.io.File;

public class Geektrust {
    public static void main(String[] args) throws Exception {
        String filePath = args[0];
      //  String filePath = "C:\\Users\\rkshr\\IdeaProjects\\rakesh_vehicle_rental\\vehicle\\src\\main\\resources\\sample_input";
        FileInputProcessor fileInputProcessor = new FileInputProcessor(new File(filePath));
        fileInputProcessor.process();
    }
}
