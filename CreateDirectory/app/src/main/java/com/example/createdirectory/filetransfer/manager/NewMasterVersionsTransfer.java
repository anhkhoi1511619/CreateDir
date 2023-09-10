package com.example.createdirectory.filetransfer.manager;

public class NewMasterVersionsTransfer extends NewVersionsTransfer{
    public static final String VERSION_PATH_MASTER = "log/Server/download/mast/";
    private static final String NAME_MASTER_CSV_FILE = "MasterVersion.csv";
    public static final String TMP_NAME_MASTER_CSV_FILE = "tmpMasterVersion.csv";
    static final String CSV_MASTER_HEADER= "車両システムマスタ,車載機設定マスタ,系統マスタ,メッセージマスタ,車両運用マスタ\n";

    public static final String ZIP_FORMAT = ".zip";
    public static final String PATH_FTP = "/log/ftp_root/";
    public static final String PATH_FTP_MASTER = "/log/mast/";
    public static final String PATH_FTP_LIVU_MASTER = "LIVUManager/mast/";
    public static final String PATH_FTP_LIVU_MANAGER_MASTER = PATH_FTP+PATH_FTP_LIVU_MASTER;
    public static final String CSV_NAME_MASTER = "MasterVersion.csv";
    public static final String DEVICE_VEHICLE_SYSTEM_CSV = "DeviceVehicleSystem.csv";
    public static final String DEVICE_SETTINGS_CSV = "DeviceSettings.csv";
    public static final String DEVICE_ROUTE_DATA_CSV = "DeviceRouteData.csv";
    public static final String DEVICE_MESSAGE_DATA_CSV = "DeviceMessageData.csv";
    public static final String DEVICE_VEHICLE_OPERATION_CSV = "DeviceVehicleOperation.csv";
    public static String[] FTP_FILE_PATH_MASTER = new String[]{PATH_FTP_LIVU_MASTER+"01.zip",PATH_FTP_LIVU_MASTER+"02.zip",PATH_FTP_LIVU_MASTER+"03.zip",PATH_FTP_LIVU_MASTER+"04.zip",PATH_FTP_LIVU_MASTER+"05.zip"};
    public static final String[] VERSION_FOLDER_MASTER = new String[]{VERSION_PATH_MASTER+"01",VERSION_PATH_MASTER+"02",VERSION_PATH_MASTER+"03",VERSION_PATH_MASTER+"04", VERSION_PATH_MASTER+"05"};

    public static final String[] FILE_MASTER_SERVER_PATH = new String[]{VERSION_PATH_MASTER+"01/"+DEVICE_VEHICLE_SYSTEM_CSV,   VERSION_PATH_MASTER+"02/"+DEVICE_SETTINGS_CSV,
            VERSION_PATH_MASTER+"03/"+DEVICE_ROUTE_DATA_CSV,    VERSION_PATH_MASTER+"04/"+DEVICE_MESSAGE_DATA_CSV,    VERSION_PATH_MASTER+"05/"+DEVICE_VEHICLE_OPERATION_CSV,VERSION_PATH_MASTER+CSV_NAME_MASTER};
    public static final String[] FILE_MASTER_FTP_PATH = new String[]{PATH_FTP_MASTER+DEVICE_VEHICLE_SYSTEM_CSV,    PATH_FTP_MASTER+DEVICE_SETTINGS_CSV,
            PATH_FTP_MASTER+DEVICE_ROUTE_DATA_CSV,  PATH_FTP_MASTER+DEVICE_MESSAGE_DATA_CSV,  PATH_FTP_MASTER+DEVICE_VEHICLE_OPERATION_CSV, PATH_FTP_MASTER+CSV_NAME_MASTER};

    public static final String[] TAG_MASTER_ZIP_DIRECTION = new String[]{VERSION_PATH_MASTER+"01/",    VERSION_PATH_MASTER+"02/", VERSION_PATH_MASTER+"03/", VERSION_PATH_MASTER+"04/", VERSION_PATH_MASTER+"05/"};
    public static final String[] TAG_MASTER_ZIP_FTP_ROOT_DIRECTION = new String[]{PATH_FTP_LIVU_MANAGER_MASTER+"01"+ZIP_FORMAT,   PATH_FTP_LIVU_MANAGER_MASTER+"02"+ZIP_FORMAT,
            PATH_FTP_LIVU_MANAGER_MASTER+"03"+ZIP_FORMAT, PATH_FTP_LIVU_MANAGER_MASTER+"04"+ZIP_FORMAT, PATH_FTP_LIVU_MANAGER_MASTER+"05"+ZIP_FORMAT};

    public NewMasterVersionsTransfer(boolean isMainController) {
        if (isMainController){
            initListVersion(VERSION_PATH_MASTER+NAME_MASTER_CSV_FILE);
        } else {
            initSubCtrlListVersion(PATH_FTP_MASTER+NAME_MASTER_CSV_FILE,  VERSION_PATH_MASTER+NAME_MASTER_CSV_FILE);
        }
        initData();
    }
    private void initData() {
        setVersionPath(VERSION_PATH_MASTER);
        setDirectoryPath(new String[]{"01","02","03","04","05"});
        setCsvHeader(CSV_MASTER_HEADER);
        setCsvName(CSV_NAME_MASTER);
        setFileFormat(ZIP_FORMAT);
        setFileServerPath(FILE_MASTER_SERVER_PATH);
        setFileFtpPath(FILE_MASTER_FTP_PATH);
        setTagGzDirection(TAG_MASTER_ZIP_DIRECTION);
        setTagGzFtpRootDirection(TAG_MASTER_ZIP_FTP_ROOT_DIRECTION);
        setPathFTPLIVUManager(PATH_FTP_LIVU_MASTER);
    }
}
