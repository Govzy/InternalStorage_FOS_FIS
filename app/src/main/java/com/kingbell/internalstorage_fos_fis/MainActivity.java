package com.kingbell.internalstorage_fos_fis;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ConnectException;


public class MainActivity extends ActionBarActivity {

    EditText userName, password;
    TextView userNameText, passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (EditText) findViewById(R.id.userEdit);
        password = (EditText) findViewById(R.id.passwordEdit);
        userNameText = (TextView) findViewById(R.id.userText);
        passwordText = (TextView) findViewById(R.id.passwordText);
    }
    public void saveFile(View view) {

        FileOutputStream fileOutputStream = null;
        File file = null;
        try {
            file = getFilesDir();
            fileOutputStream = openFileOutput("Govzy.txt", Context.MODE_PRIVATE);
            String userBytes = userName.getText().toString();
            userBytes = userBytes+" ";
            String passwordBytes = password.getText().toString();
            fileOutputStream.write(userBytes.getBytes());
            fileOutputStream.write(passwordBytes.getBytes());
            Log.e("getFilesDir", " " + file);
            Log.e("getAbsolutePath"," "+ file.getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void loadFile(View view) {

        try {
            FileInputStream fileInputStream = openFileInput("Govzy.txt");
            int read = -1;
            StringBuffer buffer = new StringBuffer();
            while((read=fileInputStream.read())!=-1){
                buffer.append((char)read);

            }

            Log.e("UserName",""+buffer);
            userNameText.setText(buffer.substring(0,buffer.indexOf(" ")));
            passwordText.setText(buffer.substring(buffer.indexOf(" ")+1));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
