package com.example.createdirectory.filetransfer.ftp;//package com.example.fragment_app.filetransfer.ftp;
//
//
//import android.os.Build;
//import android.util.Log;
//
//import androidx.annotation.RequiresApi;
//
//import org.apache.commons.net.ftp.FTP;
//import org.apache.commons.net.ftp.FTPClient;
//
//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.nio.file.Files;
//import java.util.List;
//
//
//public class FTPConnectHelper {
//    private static final String TAG = FTPConnectHelper.class.getSimpleName();
//    private static FTPClient ftpClient;
//    private static boolean isLoginSuccess;
//
//
//    public FTPConnectHelper() {
//        ftpClient = new FTPClient();
//    }
//
//
//    private boolean connect() {
//        try {
//            ftpClient.connect(FTP_ADDRESS, FTP_PORT);
//            isLoginSuccess = ftpClient.login(FTP_USERNAME, FTP_PASSWORD);
//            if (isLoginSuccess) {
//                Log.d(TAG, "ログインに成功しました。");
//            } else {
//                Log.d(TAG,"ログインに失敗しました。");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return isLoginSuccess;
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    public void download(String remoteFilePath, String localFilePath, RepositoryCallback<String> callback){
//        new Thread(() -> {
//            try {// FTPサーバーに接続
//                if (!isLoginSuccess||!ftpClient.sendNoOp()) connect();
//
//                Log.d(TAG,"remoteFilePath:  "+remoteFilePath+"  localFilePath"+localFilePath);
//
//                File fileObj = new File(localFilePath);
//                if(fileObj.delete())Files.createFile(fileObj.toPath());
//
//                try (OutputStream os =new BufferedOutputStream(new FileOutputStream(fileObj))){
//                    ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
//                    boolean isFileRetrieve = ftpClient.retrieveFile(remoteFilePath, os);
//                    os.flush();
//                    os.close();
//                    if (isFileRetrieve) {
//                        Log.d(TAG,"ファイルをダウンロードしました。");
//                        callback.onStatusChanged(RepositoryCallback.Status.Success, " Download Success via FTP", null);
//                        os.close();
//                    } else {
//                        callback.onStatusChanged(RepositoryCallback.Status.Error, "Download via FTP Fail", null);
//                        os.close();
//                        Log.d(TAG,"ファイルのダウンロードに失敗しました。");
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//
//                // ログアウトして接続を閉じる
////                ftpClient.logout();
////                ftpClient.disconnect();
//            }  catch(IOException e){
//                callback.onStatusChanged(RepositoryCallback.Status.Error, "Download IOException via FTP", null);
//                e.printStackTrace();
//            }
//        }).start();
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    public void downloadMultipleFile(int size, List<String> FTP_PATH, List<String> APP_PATH, RepositoryCallback<Integer> callback){
//        new Thread(() -> {
//            try {
//
//                if (!isLoginSuccess||!ftpClient.sendNoOp()) connect();
//
//                for (int i = 0; i < size; i++) {
//
//                    if(FTP_PATH.get(i).equals("")||APP_PATH.get(i).equals("")) continue;
//
//                    File fileObj = new File(APP_PATH.get(i));
//                    if(fileObj.delete())Files.createFile(fileObj.toPath());
//                    OutputStream os =new BufferedOutputStream(new FileOutputStream(fileObj));
//                    try {
//                        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
//                        boolean isFileRetrieve = ftpClient.retrieveFile(FTP_PATH.get(i), os);
//                        os.flush();
//                        os.close();
//                        if (isFileRetrieve) {
//                            callback.onStatusChanged(RepositoryCallback.Status.Success, i, null);
//                            Log.d(TAG,"ファイルをダウンロードしました。");
//                        } else {
//                            callback.onStatusChanged(RepositoryCallback.Status.Success, -1, null);
//                            Log.d(TAG,"ファイルのダウンロードに失敗しました。");
//                        }
//                    } catch (IOException e) {
//                        callback.onStatusChanged(RepositoryCallback.Status.Success, -1, null);
//                        Log.d(TAG,"ファイルのダウンロードに失敗しました。");
//                        e.printStackTrace();
//                    }
//                }
//                // ログアウトして接続を閉じる
////                ftpClient.logout();
////                ftpClient.disconnect();
//            }catch(IOException e){
//                callback.onStatusChanged(RepositoryCallback.Status.Error, -1, null);
//                e.printStackTrace();
//            }
//        }).start();
//    }
//
//
//
//    public void disConnect() {
//         //ログアウトして接続を閉じる
//        try {
//            ftpClient.logout();
//            ftpClient.disconnect();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//}
