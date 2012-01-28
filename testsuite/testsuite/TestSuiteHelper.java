package testsuite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.rules.TemporaryFolder;

import turtle.Main;

public class TestSuiteHelper {

    
    public static String runMain(String inputFile, TemporaryFolder tmpFolder) {
        File outFile;
        try {
            outFile = tmpFolder.newFile("out.res");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Main.main(new String[] { inputFile, outFile.getPath()});
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return readFile(outFile.getPath());
    }
    
    public static String readFile(String inputFile) {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(inputFile));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        
        StringBuilder sb = new StringBuilder();
        
        String line;
        try {
            while((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        return sb.toString();
    }
}
