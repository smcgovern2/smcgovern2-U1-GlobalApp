package smcgovern2;

import java.io.*;

/**
 * @author Matt Green
 * 1.0.0
 */
public class FileOutput {

    Writer out = null;
    private String fileName;

    /**
     * constructor for new output file
     * @param fileName name of output file
     */
    public FileOutput(String fileName) {
        this.fileName = fileName;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
        }
        catch(FileNotFoundException e) {
            System.out.println("File Open Error: " + fileName + " "  + e);
        }
    }

    /**
     * writes a line to an open output file
     * @param line line to be written
     */
    public void fileWrite(String line) {
        try {
//            out.write(line+"\n");
            out.write(line);
        }
        catch(Exception e) {
            System.out.println("File Write Error: " + fileName + " "  + e);
        }
    }

    /**
     * closes an open output file
     */
    public void fileClose() {
        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}