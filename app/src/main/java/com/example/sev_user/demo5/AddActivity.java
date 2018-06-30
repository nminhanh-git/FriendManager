package com.example.sev_user.demo5;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    private static final int IMAGE_PERMISSION_REQUEST_CODE = 1;
    private Toolbar mToolBar;
    private ImageView imgBirthDay;
    private int mYear;
    private int mMonth;
    private int mDay;
    private EditText edtBirthDay;
    private EditText mEdtName;
    private EditText mEdtPhone;
    private EditText mEdtEmail;
    private EditText mEdtAddress;

    private ImageButton mButtonAddImage;
    private  String imagePath;

    MyDB myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initWidget();

        edtBirthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker();
            }
        });
        mButtonAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("ADD IMAGE", "0000");
                if (ContextCompat.checkSelfPermission(AddActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    Log.v("ADD IMAGE", "2222");
                    if (ActivityCompat.shouldShowRequestPermissionRationale(AddActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)  == false) {
                        Toast.makeText(AddActivity.this, "this app need permission to get the image you want for the item!", Toast.LENGTH_LONG).show();
                        ActivityCompat.requestPermissions(AddActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, IMAGE_PERMISSION_REQUEST_CODE);
                        Log.v("ADD IMAGE", "1234");
                    }
//                     else {
//                        ActivityCompat.requestPermissions(EditDetailActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, IMAGE_PERMISSION_REQUEST_CODE);
//                    }
                } else {
                    Log.v("ADD IMAGE", "3456");
                    Intent getImageIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(getImageIntent, 0);
                }
            }
        });


    }

    private void initWidget() {
        mToolBar = (Toolbar) findViewById(R.id.add_tool_bar);
        mToolBar.setTitle("Add a friend");
        mToolBar.setTitleTextColor(getResources().getColor(R.color.colorTitleText));
        setSupportActionBar(mToolBar);

        edtBirthDay = (EditText) findViewById(R.id.edt_brithday);
        edtBirthDay.setFocusable(false);
        edtBirthDay.setClickable(true);

        mEdtName = (EditText) findViewById(R.id.add_name_edt);
        mEdtPhone = (EditText) findViewById(R.id.add_phone_edt);
        mEdtEmail = (EditText) findViewById(R.id.add_email_edt);
        mEdtAddress = (EditText) findViewById(R.id.add_address_edt);

        myDB = new MyDB(this);
        mButtonAddImage = (ImageButton) findViewById(R.id.add_image_button);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cancel:
                finish();
                return true;
            case R.id.save:
                Intent i = getIntent();
                String name = mEdtName.getText().toString();
                String phone = mEdtPhone.getText().toString();
                String email = mEdtEmail.getText().toString();
                String address = mEdtAddress.getText().toString();
                String birthday = edtBirthDay.getText().toString();

                String command = i.getStringExtra("command");
                if (command.equals("create")) {
                    createFriend(name, phone, email, address, birthday);
                }
        }
        return super.onOptionsItemSelected(item);
    }

    public void createFriend(String name, String phone, String email, String address, String birthday) {
        Friend aFriend = new Friend(0, name, birthday, phone, email, address, imagePath, 1);
        myDB.addFriend(aFriend);
        finish();
    }

    private void datePicker() {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String date_time;
                        String mMonthText = "0";
                        if (monthOfYear < 10) {
                            mMonthText += (monthOfYear + 1);
                        } else {
                            mMonthText = "" + (monthOfYear + 1);
                        }

                        String mDayText = "0";
                        if (dayOfMonth < 10) {
                            mDayText += dayOfMonth;
                        } else {
                            mDayText = "" + dayOfMonth;
                        }
                        date_time = mDayText + "\t-\t" + mMonthText + "\t-\t" + year;
                        edtBirthDay.setText(date_time);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri targetUri = data.getData();
            imagePath = targetUri.toString();

            String[] fileColumns = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(targetUri, fileColumns, null, null, null);
            cursor.moveToFirst();
            int cIndex = cursor.getColumnIndex(fileColumns[0]);
            String picturePath = cursor.getString(cIndex);
            cursor.close();

            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(Uri.parse(imagePath)));
                mButtonAddImage.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case IMAGE_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent getImageIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(getImageIntent, 0);
                }
            }
        }
    }
}
