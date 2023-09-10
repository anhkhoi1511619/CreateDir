package com.example.createdirectory;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.createdirectory.filetransfer.data.DefaultVersion;
import com.example.createdirectory.filetransfer.manager.NewAppVersionsTransfer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createMainAppDir();
            }
        });
    }

    private void createMainAppDir() {
//        String[] ret = FileTransferUtils.build(APP_VERSION_PATH);
//        Log.d(NewVersionsTransfer.TAG, Arrays.toString(ret));
        NewAppVersionsTransfer transfer = new NewAppVersionsTransfer(true);

        List<DefaultVersion> list = new ArrayList<>();
        IntStream.range(0,5).forEach(i->{
            list.add(new DefaultVersion("0"+i, "","http:abc.com/1"+(i+1)+"_new.tar.gz"));
        });
//        IntStream.range(3,5).forEach(i->{
//            list.add(new DefaultVersion("0"+i, "","http:abc.com/1"+(i+1)+"_old.tar.gz"));
//        });
        transfer.setMainCtrlNewVersions(list);
        transfer.step4();
        transfer.step5();
        transfer.step6_7();
        transfer.step8();
        transfer.step9();
    }

    private void createSubAppDir() {
        NewAppVersionsTransfer transfer = new NewAppVersionsTransfer(false);
        transfer.setSubCtrlNewVersions();
    }
}
