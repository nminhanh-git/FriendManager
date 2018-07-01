package com.example.sev_user.demo5;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailActivity extends AppCompatActivity {

    CircleImageView mImageViewAva;

    TextView mTextViewName;
    TextView mTextViewPhone;
    TextView mTextViewEmail;
    TextView mTextViewAddress;
    TextView mTextViewBirthday;
    android.support.v7.widget.Toolbar mToolBar;

    Friend currentFriend;
    MyDB dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        dbHelper = new MyDB(this);
        initWidget();
        loadInfo();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.detail_edit:
                Intent i = new Intent(DetailActivity.this, AddActivity.class);
                i.putExtra("command", "edit");
                i.putExtra("current edit", currentFriend);
                startActivity(i);
                break;
            case R.id.detail_delete:
                showDeleteWarningDialog();
                break;
            case R.id.detail_cancel:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showDeleteWarningDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete warning")
                .setMessage("Do you want to delete this friend?").setPositiveButton("yes, delete it!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dbHelper.deleteFriend(currentFriend);
                finish();
                Toast.makeText(DetailActivity.this, "deleted.", Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void loadInfo() {
        Intent i = getIntent();
        currentFriend = (Friend) i.getSerializableExtra("current friend");
        Uri avaPath = null;
        if (currentFriend.getAvata() != null) {
            avaPath = Uri.parse(currentFriend.getAvata());
        }
        String name = currentFriend.getName();
        String phone = currentFriend.getPhone();
        String email = currentFriend.getEmail();
        String address = currentFriend.getAddress();
        String birthday = currentFriend.getBirthday();

        if (avaPath != null) {
            GlideApp.with(this).load(avaPath).into(mImageViewAva);
        }else{
            GlideApp.with(this).load(R.drawable.ic_place_holder).into(mImageViewAva);
        }

        mTextViewName.setText(name);
        mTextViewPhone.setText(phone);
        mTextViewEmail.setText(email);
        mTextViewAddress.setText(address);
        mTextViewBirthday.setText(birthday);
    }

    private void initWidget() {
        mToolBar = (android.support.v7.widget.Toolbar) findViewById(R.id.detail_tool_bar);
        mToolBar.setTitle("Detail");
        mToolBar.setTitleTextColor(getResources().getColor(R.color.colorTitleText));
        setSupportActionBar(mToolBar);

        mImageViewAva = (CircleImageView) findViewById(R.id.detaiL_friend_image_view);
        mTextViewName = (TextView) findViewById(R.id.detail_friend_name_tv);
        mTextViewPhone = (TextView) findViewById(R.id.detail_friend_phone_tv);
        mTextViewEmail = (TextView) findViewById(R.id.detail_friend_email_tv);
        mTextViewAddress = (TextView) findViewById(R.id.detail_friend_address_tv);
        mTextViewBirthday = (TextView) findViewById(R.id.detail_friend_birthday_tv);
    }
}
