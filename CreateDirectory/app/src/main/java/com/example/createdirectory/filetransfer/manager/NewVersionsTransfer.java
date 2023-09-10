package com.example.createdirectory.filetransfer.manager;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;


import com.example.createdirectory.filetransfer.data.DefaultVersion;
import com.example.createdirectory.filetransfer.data.NewVersion;
import com.example.createdirectory.filetransfer.util.FileTransferUtils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NewVersionsTransfer {
    public static final String TAG = NewVersionsTransfer.class.getSimpleName();
    public static final String PATH_FTP = "sdcard/log/ftp_root/";
    private String FILE_FORMAT;
    private String CSV_HEADER;
    private String CSV_NAME;
    private String TXT_NAME;
    private String PATH_FTP_LIVU_MANAGER;
    private String[] DIRECTORY_PATH;
    private String VERSION_PATH;
    private String[] FILE_SERVER_PATH;
    private String[] FILE_FTP_PATH;
    private String[] TXT_LOAD_DIRECTORY;
    private String[] TAG_GZ_DIRECTION;
    private String[] TAG_GZ_FTP_ROOT_DIRECTION;

    static NewVersion version = new NewVersion(0, "OLD_VERSION", false, false, false);
    static List<NewVersion> listNewVersion =
            Arrays.asList(version, version, version, version, version);
    static String[] arrCurrentNameVersion;
    static String[] arrNewNameVersion;

    public boolean IS_SKIP_STEP3 = false;
    public boolean IS_SKIP_STEP4 = false;
    public boolean IS_SKIP_STEP5 = false;
    public boolean IS_SKIP_STEP6_7 = false;
    public boolean IS_SKIP_STEP8 = false;
    public boolean IS_SKIP_STEP9 = false;


    static void initListVersion(String path) {
        //Read From File
        Log.d(TAG, "arrCurrentNameVersion" + Arrays.toString(arrCurrentNameVersion)+"   Path:   "+path);

        arrCurrentNameVersion = FileTransferUtils.build(path);
//        arrCurrentNameVersion = new String[]{"11_old","12_old","13_old","14_old","15_old"};

        Log.d(TAG, "arrCurrentNameVersion" + Arrays.toString(arrCurrentNameVersion));
//        arrCurrentNameVersion = new String[]{"11_old","12_old","13_old","14_old","15_old"};


    }
    static void initSubCtrlListVersion(String path1, String path2) {
        //Read From File
//        arrCurrentNameVersion = new String[]{"11_old","12_old","13_old","14_old","15_old"};
        Log.d(TAG, "arrCurrentNameVersion" + Arrays.toString(arrCurrentNameVersion)+"   Path:   "+path1);

        arrCurrentNameVersion = FileTransferUtils.build(path1);

        arrNewNameVersion = FileTransferUtils.build(path2);
//        arrNewNameVersion = new String[]{"11_new","12_old","13_old","14_old","15_old"};
        Log.d(TAG, "arrNewNameVersion" + Arrays.toString(arrNewNameVersion)+"   Path:   "+path2);

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setMainCtrlNewVersions(List<DefaultVersion> listResponse) {
        if(listResponse==null) return;

        arrNewNameVersion = listResponse.stream()
                .filter(Objects::nonNull)
                .map(versions -> versions.url)
                .collect(Collectors.toList())
                .stream()
                .filter(Objects::nonNull)
                .map(s -> s.replace(("http:abc.com")+"/", "")
                        .replace(".tar.gz", "")
                        .replace(".zip", ""))
                .toArray(String[]::new);

        //remove correctly new name version
        logArrNewNameVersion();
        logListVersion();
        listNewVersion = IntStream.range(0, arrNewNameVersion.length)
                .filter(Objects::nonNull)
                .filter(a->a<arrNewNameVersion.length)
//                .filter(i -> !arrNewNameVersion[i].equals(arrCurrentNameVersion[i]))
                .mapToObj(index ->
                        new NewVersion(Integer.parseInt(listResponse.get(index).id.substring(1)), arrNewNameVersion[index]))
                .collect(Collectors.toList());

        logListVersion();
        //Transfer File To Directory
        step3();
        Log.d(TAG, "Sync NewVersions Ended");
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setSubCtrlNewVersions() {
        //remove correctly new name version
        logArrNewNameVersion();
        logArrCurrentNameVersion();
        logListVersion();
        listNewVersion = IntStream.range(0, 5)
                .filter(Objects::nonNull)
                .filter(a->a<arrNewNameVersion.length)
                .filter(a->a<arrCurrentNameVersion.length)
                .filter(i->!arrNewNameVersion[i].equals(arrCurrentNameVersion[i]))
                .mapToObj(index ->
                        new NewVersion(index, arrNewNameVersion[index]))
                .collect(Collectors.toList());
        logListVersion();
        //Transfer File To Directory
        step3();
        Log.d(TAG, "Sync NewVersions Ended");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void step3() {
        if(IS_SKIP_STEP3) return;

        Log.d(TAG, "\n");
        Log.d(TAG, "STEP3:	STARTING........................................");
        logListVersion();
        for (int i = 0; i < DIRECTORY_PATH.length; i++){
            Log.d(TAG, "STEP3:	Folder ID........................................"+ DIRECTORY_PATH[i]);
            Log.d(TAG, "STEP3:	listNewVersion.size()........................................"+ listNewVersion.size());
            if(i>=listNewVersion.size()) return;

            int value = listNewVersion.get(i).getId();
            Log.d(TAG, "STEP3:	Value........................................"+value);
            String dir = VERSION_PATH+DIRECTORY_PATH[value];
            Log.d(TAG, dir);

//            try {
//                Log.d(TAG, listNewVersion.get(i).getVersionName());
//                FileUtils.deleteDirectory(new File(dir+"/"));
//                FileTransferUtils.createDirectory(dir);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
        Log.d(TAG, "STEP3:	ENDED........................................");
        Log.d(TAG, "\n");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void step4() {
        if(IS_SKIP_STEP4) return;

        Log.d(TAG, "DOWNLOAD: ENDED........................................");
        Log.d(TAG, "\n");
        Log.d(TAG, "\n");
        Log.d(TAG, "STEP4: WRITING IN ["+CSV_NAME+"]........................................");
        String strNeedUpdate = strNeedUpdate();
        Log.d(TAG, "STEP4: WRITING IN ["+CSV_NAME+"]...\n"+strNeedUpdate);
        FileTransferUtils.modifyFile(VERSION_PATH, CSV_NAME,  strNeedUpdate);
        Log.d(TAG, "STEP4: WRITING IN ["+CSV_NAME+"]ENDED........................................");
        Log.d(TAG, "\n");
    }
    public void step5() {
        if(IS_SKIP_STEP5) return;

        Log.d(TAG, "\n");
        Log.d(TAG, "STEP5: MERGE INTO 「/log/Server/download/app/」 FILE [r_load.txt]........................................");
        FileTransferUtils.merge(VERSION_PATH, TXT_LOAD_DIRECTORY, TXT_NAME);
        Log.d(TAG, "\n");
        Log.d(TAG, "STEP5: MERGE INTO 「/log/Server/download/app/」 FILE [r_load.txt] ENDED........................................");
    }

    public void step6_7(){
        if(IS_SKIP_STEP6_7) return;

        Log.d(TAG, "\n");
        Log.d(TAG, "STEP6 + 7: COPY FROM 「/log/Server/download/app/」 TO 「log/ftp_root/Reader」OF FILE [___.bin] + [r_load.txt]........................................");
        for (int i = 0; i < FILE_SERVER_PATH.length; i++) {
            FileTransferUtils.copy(FILE_SERVER_PATH[i],FILE_FTP_PATH[i]);
        }
        Log.d(TAG, "STEP6 + 7: COPY FROM 「/log/Server/download/app/」 TO 「log/ftp_root/Reader」OF FILE [___.bin] + [r_load.txt] ENDED........................................");
        Log.d(TAG, "\n");
        logListVersion();
        Log.d(TAG, "Version:..."+Arrays.toString(arrCurrentNameVersion));
    }

    public void step8() {
        if(IS_SKIP_STEP8) return;

        Log.d(TAG, "\n");
        Log.d(TAG, "STEP8: COPY FROM 「/log/Server/download/app/」 TO 「log/ftp_root/Reader」OF FILE [___.tag.zip]........................................");
        IntStream.range(0, listNewVersion.size())
                .forEach(i->{
                    int index = listNewVersion.get(i).getId();
                    FileTransferUtils.copy(TAG_GZ_DIRECTION[index]+listNewVersion.get(i).getVersionName()+FILE_FORMAT,TAG_GZ_FTP_ROOT_DIRECTION[index]);
                    listNewVersion.get(i).setMoved(true);
                });
        Log.d(TAG, "\n");
        Log.d(TAG, "STEP8: COPY FROM 「/log/Server/download/app/」 TO 「log/ftp_root/Reader」OF FILE [___.tag.zip] ENDED........................................");
    }

    public void step9() {
        if(IS_SKIP_STEP9) return;

        Log.d(TAG, "\n");
        Log.d(TAG, "STEP9: COPY FROM 「/log/Server/download/app/AppVersion.csv」 TO 「log/ftp_root/LIVUManager/app」」OF FILE [___.tag.zip]........................................");
        FileTransferUtils.copy(VERSION_PATH+CSV_NAME, PATH_FTP+PATH_FTP_LIVU_MANAGER+CSV_NAME);
        Log.d(TAG, "STEP9: COPY FROM 「/log/Server/download/app/AppVersion.csv」 TO 「log/ftp_root/LIVUManager/app」」OF FILE [___.tag.zip] END........................................");
        Log.d(TAG, "\n");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void stepSubCtrl() {
        Log.d(TAG, "\n");
        Log.d(TAG, "\n");
        Log.d(TAG, "STEP5: MERGE INTO 「/log/Server/download/app/」 FILE [r_load.txt]........................................");
        FileTransferUtils.merge(VERSION_PATH, TXT_LOAD_DIRECTORY, TXT_NAME);
        Log.d(TAG, "\n");
        Log.d(TAG, "STEP5: MERGE INTO 「/log/Server/download/app/」 FILE [r_load.txt] ENDED........................................");
//        doneSubCtrl();
    }


    public void copyStep3Master() {
        Log.d(TAG, "\n");
        Log.d(TAG, "STEP3: COPY FROM 「/log/mast/MasterVersion.csv」 TO 「/log/Server/download/mast」........................................");
        FileTransferUtils.copy(VERSION_PATH+CSV_NAME, PATH_FTP+PATH_FTP_LIVU_MANAGER+CSV_NAME);
        Log.d(TAG, "STEP9: COPY FROM 「/log/mast/MasterVersion.csv」 TO 「/log/Server/download/mast」 END........................................");
        Log.d(TAG, "\n");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void updateCurrentVersion() {

        listNewVersion.stream()
                .filter(list ->(list.getId()< arrCurrentNameVersion.length))
                .forEach(s -> arrCurrentNameVersion[s.getId()] = s.getVersionName());
    }
    public boolean isAllVersionsDownloaded(){

        for (NewVersion version : listNewVersion) {
            if (!version.isDownLoaded()) {
                Log.e(TAG, "All App Version do not download success: Please wait");
                return false;
            }
        }
        Log.d(TAG, "All App Version download success: ");
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String strNeedUpdate() {
        String[] ret = {",","","","",""};
        listNewVersion.forEach(newVersion -> {
            ret[newVersion.getId()] = newVersion.getVersionName();
        });
        IntStream.range(0, 4)
                .filter(i->i<arrCurrentNameVersion.length)
                .filter(a->ret[a].equals(""))
                .forEach(s->ret[s]=arrCurrentNameVersion[s]);

        return CSV_HEADER+String.join(",", ret);
    }

    public void setFileFormat(String FILE_FORMAT) {
        this.FILE_FORMAT = FILE_FORMAT;
    }

    public String getFileFormat() {
        return FILE_FORMAT;
    }

    public void setPathFTPLIVUManager(String PATH_FTP_LIVU_MANAGER) {
        this.PATH_FTP_LIVU_MANAGER = PATH_FTP_LIVU_MANAGER;
    }

    public String getPathFTPLIVUManager() {
        return PATH_FTP_LIVU_MANAGER;
    }

    public void setCsvHeader(String csvHeader) {
        CSV_HEADER = csvHeader;
    }

    public void setCsvName(String csvName) {
        CSV_NAME = csvName;
    }

    public String getCsvName() {
        return CSV_NAME;
    }

    public void setTxtName(String txtName) {
        TXT_NAME = txtName;
    }

    public void setDirectoryPath(String[] directoryPath) {
        DIRECTORY_PATH = directoryPath;
    }

    public void setVersionPath(String versionPath) {
        VERSION_PATH = versionPath;
    }

    public String getVersionPath() {
        return VERSION_PATH;
    }

    public void setFileServerPath(String[] fileServerPath) {
        FILE_SERVER_PATH = fileServerPath;
    }

    public void setFileFtpPath(String[] fileFtpPath) {
        FILE_FTP_PATH = fileFtpPath;
    }

    public void setTxtLoadDirectory(String[] txtLoadDirectory) {
        TXT_LOAD_DIRECTORY = txtLoadDirectory;
    }

    public void setTagGzDirection(String[] tagGzDirection) {
        TAG_GZ_DIRECTION = tagGzDirection;
    }

    public void setTagGzFtpRootDirection(String[] tagGzFtpRootDirection) {
        TAG_GZ_FTP_ROOT_DIRECTION = tagGzFtpRootDirection;
    }

    public List<NewVersion> getListNewVersion() {
        return listNewVersion;
    }

    public String[] getArrCurrentNameVersion() {
        return arrCurrentNameVersion;
    }

    public String[] getArrNewNameVersion() {
        return arrNewNameVersion;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void logListVersion() {
        listNewVersion.forEach(newVersion -> {
            Log.d(TAG,"NewAppVersion: id:  " + newVersion.getId()
                    + " versionName: " + newVersion.getVersionName()
                    + "    isDownLoaded: " + newVersion.isDownLoaded()
                    + "    isSaved: " + newVersion.isSaved()
                    + "    isMoved: " + newVersion.isMoved());
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void logArrCurrentNameVersion() {
        Arrays.stream(arrCurrentNameVersion)
                .forEach(str -> Log.d(TAG,"arrCurrentNameVersion:  " + str));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void logArrNewNameVersion() {
        Arrays.stream(arrNewNameVersion)
                .forEach(str -> Log.d(TAG,"arrNewNameVersion:  " + str));
    }

}
