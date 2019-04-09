package com.example.springbootdemo01.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class TXTForStringUtil {
    public static void writeTxt(String content, String path){
        BufferedWriter bw = null;
        File file = new File(path);
        try{
            bw = new BufferedWriter( new FileWriter(file));
            bw.write(content);
            bw.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
