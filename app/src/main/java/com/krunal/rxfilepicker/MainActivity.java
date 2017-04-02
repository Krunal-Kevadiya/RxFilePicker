package com.krunal.rxfilepicker;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kevadiyakrunalk.rxfilepicker.RxFilePicker;
import com.kevadiyakrunalk.rxfilepicker.model.FileType;
import com.kevadiyakrunalk.rxpermissions.PermissionResult;
import com.kevadiyakrunalk.rxpermissions.RxPermissions;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private int MAX_ATTACHMENT_COUNT;
    private ArrayList<String> docPaths;
    private ArrayList<FileType> fileTypes;
    private Button btnFile;
    private TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MAX_ATTACHMENT_COUNT = 10;
        docPaths = new ArrayList<>();

        fileTypes = new ArrayList<>();
        FileType fileType = new FileType();
        fileType.setGroupTitle("IMAGE");
        fileType.setGroupIcon(R.drawable.ic_pdf);
        fileType.setGroupExtension("jpg,jpeg,png");
        fileTypes.add(fileType);

        fileType = new FileType();
        fileType.setGroupTitle("PDF");
        fileType.setGroupIcon(R.drawable.ic_pdf);
        fileType.setGroupExtension("pdf");
        fileTypes.add(fileType);

        fileType = new FileType();
        fileType.setGroupTitle("PPT");
        fileType.setGroupIcon(R.drawable.icon_ppt);
        fileType.setGroupExtension("ppt,pptx");
        fileTypes.add(fileType);

        fileType = new FileType();
        fileType.setGroupTitle("DOC");
        fileType.setGroupIcon(R.drawable.ic_doc);
        fileType.setGroupExtension("doc,docx,dot,dotx");
        fileTypes.add(fileType);

        fileType = new FileType();
        fileType.setGroupTitle("XLS");
        fileType.setGroupIcon(R.drawable.ic_xls);
        fileType.setGroupExtension("xls,xlsx");
        fileTypes.add(fileType);

        fileType = new FileType();
        fileType.setGroupTitle("TXT");
        fileType.setGroupIcon(R.drawable.ic_txt);
        fileType.setGroupExtension("txt");
        fileTypes.add(fileType);
        initView();
    }

    private void initView() {
        btnFile = (Button) findViewById(R.id.btn_file);
        txtResult = (TextView) findViewById(R.id.txt_result);

        btnFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxPermissions.getInstance(MainActivity.this)
                        .checkMPermission(new PermissionResult() {
                            @Override
                            public void onPermissionResult(String permission, boolean granted) {
                                if(granted) {
                                    if(docPaths.size()==MAX_ATTACHMENT_COUNT)
                                        Toast.makeText(MainActivity.this, "Cannot select more than " + MAX_ATTACHMENT_COUNT + " items", Toast.LENGTH_SHORT).show();
                                    else
                                        RxFilePicker.getInstance(MainActivity.this)
                                                .setMaxCount(MAX_ATTACHMENT_COUNT)
                                                .setFileType(fileTypes)
                                                .setDirectory(Environment.getExternalStorageDirectory())
                                                .setActivityTheme(R.style.FilePickerTheme)
                                                .pickDocument(MainActivity.this, new RxFilePicker.FileResult() {
                                                    @Override
                                                    public void PickFileList(ArrayList<String> list) {
                                                        Log.e("Files", list.toString());
                                                        docPaths.addAll(list);
                                                        txtResult.setText(list.toString());
                                                    }
                                                });
                                }
                            }
                        }, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
        });
    }
}
