package com.deaenita.asasta.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.andexert.library.RippleView;
import com.deaenita.asasta.R;
import com.deaenita.asasta.fragment.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private RippleView btnRegister;
    private ImageView btnLoginFacebook;
    private ImageView btnLoginGoogle;
    private TextView btnDaftarSekarang;
    private EditText edtEmailRegister;
    private EditText edtPasswordRegister;

    ProgressDialog progressDialog;

    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
    }

    public void PindahLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void initView() {
        edtEmailRegister = (EditText) findViewById(R.id.edt_email_register);
        edtPasswordRegister = (EditText) findViewById(R.id.edt_password_register);

        btnRegister = (RippleView) findViewById(R.id.btn_register);
        btnLoginFacebook = (ImageView) findViewById(R.id.btn_login_facebook);
        btnLoginGoogle = (ImageView) findViewById(R.id.btn_login_google);
        btnDaftarSekarang = (TextView) findViewById(R.id.btn_daftar_sekarang);

        btnRegister.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if (v == btnRegister) {
            registerUser();
        }
    }

    private void registerUser() {
        String email = edtEmailRegister.getText().toString().trim();
        String password = edtPasswordRegister.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Silahkan isi email!", Toast.LENGTH_SHORT).show();
            return;

        }

        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Silahkan isi password!", Toast.LENGTH_SHORT).show();
            return;

        }

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "Register Berhasil!", Toast.LENGTH_SHORT).show();

//                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            Fragment fragment = null;
                            Class fragmentClass = null;
                            fragmentClass = HomeFragment.class;
                            try {
                                fragment = (Fragment) fragmentClass.newInstance();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.layout_untuk_fragment, new HomeFragment())
                                    .commit();
                        }else{
                            Toast.makeText(RegisterActivity.this, "Register Gagal... Coba lagi!", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                });
    }
}
