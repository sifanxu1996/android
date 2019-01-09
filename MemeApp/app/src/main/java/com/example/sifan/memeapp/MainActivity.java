package com.example.sifan.memeapp;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static int result_load_image = 1;
    TextView toptext;
    TextView bottomtext;
    EditText editTop;
    EditText editBottom;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toptext = findViewById(R.id.memeToptext);
        bottomtext = findViewById(R.id.memeBottomtext);
        editTop = findViewById(R.id.editToptext);
        editBottom = findViewById(R.id.editBottomtext);
        imageView = findViewById(R.id.memeImage);
    }

    public void addImage(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, result_load_image);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == result_load_image && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String [] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = findViewById(R.id.memeImage);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }
    }

    public void tryMeme(View view) {
        toptext.setText(editTop.getText().toString());
        bottomtext.setText(editBottom.getText().toString());
        hideKeyboard(view);
    }

    public void hideKeyboard (View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    public void saveImage (View view) {
        View content = findViewById(R.id.relativeLayout);
        Bitmap bitmap = getScreenshot(content);
        String currentImage = "meme" + System.currentTimeMillis() + ".png";
        store(bitmap, currentImage);
    }

    public void store(Bitmap bitmap, String filename) {
        String dirpath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString();
        File dir = new File(dirpath);
        if (!dir.exists()) {
            dir.mkdir();
        }

        File file = new File(dirpath, filename);
        try {
            FileOutputStream fos = null;
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
            Toast.makeText(this, "Image Saved", Toast.LENGTH_LONG);
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Bitmap getScreenshot (View view) {
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        return bitmap;
    }
}
