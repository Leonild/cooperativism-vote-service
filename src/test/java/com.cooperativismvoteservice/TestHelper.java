package com.cooperativismvoteservice;

import java.io.BufferedReader;
import java.io.FileReader;

public class TestHelper {

    /**
     * Upload a json file to the tests
     * @param fileName input file to test
     * @return a json body string
     */
    public String loadJson(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(getClass().getClassLoader().getResource(fileName).getFile()));
            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
            br.close();
        } catch (Exception e) {
            System.err.println("loadJson(" + fileName + ")");
            System.err.println(e);
        }

        return stringBuilder.toString();
    }
}
