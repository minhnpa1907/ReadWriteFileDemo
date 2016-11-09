package com.minhnpa.readwritefiledemo;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final static String FILE_NAME = "notes.txt";
    private EditText editText;
    private Button btnWrite;
    private Button btnRead;
    private Button btnClear;
    private Button btnClose;
    String mySdPath = Environment.getExternalStorageDirectory().getAbsolutePath();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnWrite = (Button) findViewById(R.id.btnWrite);
        btnRead = (Button) findViewById(R.id.btnRead);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnClose = (Button) findViewById(R.id.btnClose);
        editText = (EditText) findViewById(R.id.editText);

        btnWrite.setOnClickListener(this);
        btnRead.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnClose.setOnClickListener(this);
    }

    /*public void onStart() {
        super.onStart();

        try {
            InputStream inputStream = openFileInput(FILE_NAME);

            if (inputStream != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String str = "";
                StringBuffer stringBuffer = new StringBuffer();

                while ((str = bufferedReader.readLine()) != null) {
                    stringBuffer.append(str + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                editText.setText(stringBuffer.toString());
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void onPause() {
        super.onPause();

        try {
            OutputStreamWriter outputStream = new OutputStreamWriter(openFileOutput(FILE_NAME, 0));
            outputStream.write(editText.getText().toString());
            outputStream.close();
        } catch (Throwable t) {
            editText.setText(t.getMessage());
        }
    }*/

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.btnWrite) {
            try {
                File myFile = new File(mySdPath + "/mysdfile.txt");

                OutputStreamWriter myOutWriter = new OutputStreamWriter(new FileOutputStream(myFile));
                myOutWriter.append(editText.getText());
                myOutWriter.close();

                Toast.makeText(this, "Done writing SD 'mysdfile.txt'", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        if (id == R.id.btnRead) {
            try {
                BufferedReader myReader = new BufferedReader(new InputStreamReader(
                        new FileInputStream(
                                new File(mySdPath + "/mysdfile.txt"))));
                String aDataRow = "";
                String aBuffer = "";
                while ((aDataRow = myReader.readLine()) != null) {
                    aBuffer += aDataRow + "\n";
                }
                editText.setText(aBuffer);
                myReader.close();
                Toast.makeText(this, "Done reading SD 'mysdfile.txt'", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
