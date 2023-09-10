package com.example.createdirectory.filetransfer.util;

import android.util.Log;

import com.example.createdirectory.filetransfer.manager.NewVersionsTransfer;

import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileTransferUtils {

    public static String[] build(String path)
    {
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            br.readLine();
            if((line = br.readLine()) != null)return line.split(",");
        } catch (FileNotFoundException e) {
            Log.e(NewVersionsTransfer.TAG, "FileNotFoundException");
            e.printStackTrace();
        }
        catch (Exception e) {
            Log.e(NewVersionsTransfer.TAG, "Exception");
            e.printStackTrace();
        }
        return new String[]{""};
    }


    public static void copy(String sourcePath, String destinationPath) {
        File source = new File(sourcePath);
        Log.d(NewVersionsTransfer.TAG, "sourcePath:..."+sourcePath);

        File destination = new File(destinationPath);
        Log.d(NewVersionsTransfer.TAG, "destinationPath:..."+destinationPath);

        try
        {
            if(!source.isFile()){
                Log.e(NewVersionsTransfer.TAG, "sourcePath does not exist:..."+sourcePath);
                source.createNewFile();
            }
            if(!destination.isFile()){
                Log.e(NewVersionsTransfer.TAG, "sourcePath does not exist:..."+destination);
                destination.createNewFile();
            }

            if(!source.canWrite() ||!destination.canWrite()) return;
            FileUtils.copyFile(source, destination);
            Log.d(NewVersionsTransfer.TAG, "Copied:...");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public static void createDirectory(String pathname) {
        File f1 = new File(pathname);
        if(f1.mkdir()){
            Log.d(NewVersionsTransfer.TAG, pathname +"   Folder is created successfully");
        } else {
            Log.e(NewVersionsTransfer.TAG, pathname  +"  Folder is created fail");
        }
    }


    public static void merge(String VERSION_PATH, String[] TXT_LOAD_DIRECTORY, String TXT_NAME){
        File mergeFile = new File(VERSION_PATH+TXT_NAME);
        FileWriter fileWriter = null;
        if(mergeFile.delete()|| mergeFile.exists()) {
            try {
                mergeFile.createNewFile();
                fileWriter = new FileWriter(mergeFile);
                fileWriter.write(FileTransferUtils.makeStrToMerge(TXT_LOAD_DIRECTORY, TXT_NAME));
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String makeStrToMerge(String[] TXT_LOAD_DIRECTORY, String TXT_NAME) {
        StringBuilder result= new StringBuilder();
        for (String path : TXT_LOAD_DIRECTORY) {
            String line = "";
            Log.d(NewVersionsTransfer.TAG, "Path: "+ path+TXT_NAME);
            try (BufferedReader br = new BufferedReader(new FileReader(path+TXT_NAME))){
                while ((line = br.readLine()) != null) {
                    Log.d(NewVersionsTransfer.TAG, "String: "+ line);
                    if(line.equals("LEND")) continue;
                    result.append(line).append("\n");
                }
            } catch (FileNotFoundException e) {
                Log.e(NewVersionsTransfer.TAG, "FileNotFoundException");
                e.printStackTrace();
            }
            catch (Exception e) {
                Log.e(NewVersionsTransfer.TAG, "Exception");
                e.printStackTrace();
            }
        }
        Log.d(NewVersionsTransfer.TAG, "Result: "+result+"LEND");
        return String.valueOf(result.append("LEND"));
    }

    public static void modifyFile(String filePath, String nameFile, String newString)
    {
        Log.d(NewVersionsTransfer.TAG, "Modifying... filePath:  "+filePath+nameFile);
        try {
            File file = new File(filePath+nameFile);
            if(file.delete())file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);
//            Log.d(NewVersionsTransfer.TAG, "Writing:..."+" :  \n"+header);
            fileWriter.write(newString+"\n");
            fileWriter.close();
        }
        catch (IOException e)
        {
            Log.d(NewVersionsTransfer.TAG, "Exception:...");
            e.printStackTrace();
        }
    }

}
