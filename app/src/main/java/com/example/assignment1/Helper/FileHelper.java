package com.example.assignment1.Helper;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class handles reading and writing into review.json file
 */
public class FileHelper {
    private static final String FILE_NAME = "review.json";

    static public void save(Context context, String str) {                                          // rewrite review.json file
        FileOutputStream os = null;
        try {
            os = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            os.write(str.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static public String load(Context context) {                                                    // load review.json file and return String content
        FileInputStream is = null;
        StringBuilder sb = null;
        try {
            is = context.openFileInput(FILE_NAME);
            InputStreamReader sr = new InputStreamReader(is);
            BufferedReader br =  new BufferedReader(sr);
            sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb != null ? sb.toString() : "";
    }

}
