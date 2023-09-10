package com.example.createdirectory.filetransfer.manager;

public class NewAppVersionsTransfer extends NewVersionsTransfer {
    public static final String APP_VERSION_PATH = "sdcard/log/Server/download/app/";
    public static final String NAME_APP_CSV_FILE = "AppVersion.csv";
    public static final String TMP_NAME_APP_CSV_FILE = "tmpAppVersion.csv";
    static final String CSV_APP_HEADER= "制御部アプリケーション,制御部データファイル,乗降リーダアプリケーション,乗降リーダ表示データファイル,乗降リーダ音声データファイル\n";


    public static final String PATH_FTP = "sdcard/log/ftp_root/";
    public static final String PATH_FTP_READER = PATH_FTP+"Reader/";
    public static final String PATH_FTP_LIVU_APP = "LIVUManager/app/";
    public static final String PATH_FTP_LIVU_MANAGER_APP = PATH_FTP+PATH_FTP_LIVU_APP;
//    public static final String CSV_NAME = "AppVersion.csv";
    public static final String BIN_NAME_NCC = "bin13.csv";
    public static final String BIN_NAME_NCC_IPL = "ncc_ipl.bin";
    public static final String BIN_NAME_NCC_ONS = "bin15.csv";
    public static final String BIN_NAME_NCC_PAT = "bin14.csv";
    public static final String TXT_APP_NAME = "r_load.csv";
    public static String[] FTP_FILE_PATH_APP = new String[]{PATH_FTP_LIVU_APP+"11.tar.gz",PATH_FTP_LIVU_APP+"12.tar.gz",PATH_FTP_LIVU_APP+"13.tar.gz",PATH_FTP_LIVU_APP+"14.tar.gz",PATH_FTP_LIVU_APP+"15.tar.gz"};
    public static final String[] APP_VERSION_FOLDER_ARR = new String[]{APP_VERSION_PATH+"11",APP_VERSION_PATH+"12",APP_VERSION_PATH+"13",APP_VERSION_PATH+"14", APP_VERSION_PATH+"15"};
    public static final String[] TXT_APP_LOAD_DIRECTORY = new String[]{APP_VERSION_PATH+"13/",APP_VERSION_PATH+"14/",APP_VERSION_PATH+"15/"};
    public static final String[] FILE_APP_SERVER_PATH = new String[]{APP_VERSION_PATH+"13/"+BIN_NAME_NCC, APP_VERSION_PATH+"14/"+BIN_NAME_NCC_PAT,
            APP_VERSION_PATH+"15/"+BIN_NAME_NCC_ONS,    APP_VERSION_PATH+TXT_APP_NAME};
    public static final String[] FILE_APP_FTP_PATH = new String[]{PATH_FTP_READER+BIN_NAME_NCC, PATH_FTP_READER+BIN_NAME_NCC_PAT,
            PATH_FTP_READER+BIN_NAME_NCC_ONS,  PATH_FTP_READER+TXT_APP_NAME};
    public static final String[] TAG_APP_GZ_DIRECTION = new String[]{APP_VERSION_PATH+"11/",    APP_VERSION_PATH+"12/", APP_VERSION_PATH+"13/", APP_VERSION_PATH+"14/", APP_VERSION_PATH+"15/"};
    public static final String TAG_GZ_FORMAT = ".zip";
    public static final String[] TAG_APP_GZ_FTP_ROOT_DIRECTION = new String[]{PATH_FTP_LIVU_MANAGER_APP+"11"+TAG_GZ_FORMAT,   PATH_FTP_LIVU_MANAGER_APP+"12"+TAG_GZ_FORMAT,
            PATH_FTP_LIVU_MANAGER_APP+"13"+TAG_GZ_FORMAT, PATH_FTP_LIVU_MANAGER_APP+"14"+TAG_GZ_FORMAT, PATH_FTP_LIVU_MANAGER_APP+"15"+TAG_GZ_FORMAT};


    public NewAppVersionsTransfer(boolean isMainController) {
        if (isMainController){
            initListVersion(APP_VERSION_PATH+NAME_APP_CSV_FILE);
        } else {
            initSubCtrlListVersion(APP_VERSION_PATH+NAME_APP_CSV_FILE,  APP_VERSION_PATH+TMP_NAME_APP_CSV_FILE);
        }
        initData();
    }

    private void initData() {
        setVersionPath(APP_VERSION_PATH);
        setDirectoryPath(new String[]{"11","12","13","14","15"});
        setCsvHeader(CSV_APP_HEADER);
        setCsvName(NAME_APP_CSV_FILE);
        setTxtName(TXT_APP_NAME);
        setFileFormat(TAG_GZ_FORMAT);
        setTxtLoadDirectory(TXT_APP_LOAD_DIRECTORY);
        setFileServerPath(FILE_APP_SERVER_PATH);
        setFileFtpPath(FILE_APP_FTP_PATH);
        setTagGzDirection(TAG_APP_GZ_DIRECTION);
        setTagGzFtpRootDirection(TAG_APP_GZ_FTP_ROOT_DIRECTION);
        setPathFTPLIVUManager(PATH_FTP_LIVU_APP);
    }

}
