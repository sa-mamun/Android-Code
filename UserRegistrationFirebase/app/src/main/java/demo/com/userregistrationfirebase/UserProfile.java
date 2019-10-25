package demo.com.userregistrationfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.squareup.picasso.Picasso;

public class UserProfile extends AppCompatActivity {

    EditText userName, userEmail, userVerify;
    ImageView profilePic;
    Button verifyBtn;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    boolean verified;
    String verifyMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        userName = findViewById(R.id.userName);
        userEmail = findViewById(R.id.userEmail);
        userVerify = findViewById(R.id.userVerify);
        profilePic = findViewById(R.id.profilePic);
        verifyBtn = findViewById(R.id.verifyBtn);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        if (mUser.getDisplayName() == null)
        {
            userName.setText("Al Mamun");
        }
        else {
            userName.setText(mUser.getDisplayName().toString());
        }

        userEmail.setText(mUser.getEmail().toString());

        verified = mUser.isEmailVerified();

        if (verified)
        {
            verifyMsg = "Email is verified";
            userVerify.setText(verifyMsg);
        }
        else {
            verifyMsg = "Email is not verified";
            userVerify.setText(verifyMsg);
            verifyBtn.setVisibility(View.VISIBLE);
        }

        Uri photoUrl = mUser.getPhotoUrl();

        Picasso.get().load(photoUrl).into(profilePic);


    }

    public void Update(View view) {

        String name = userName.getText().toString();

        UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .setPhotoUri(Uri.parse("https://knbhost.com/radar2/wp-content/uploads/2018/05/empoly.jpg"))
                .build();

        mUser.updateProfile(profileChangeRequest)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful())
                        {
                            Toast.makeText(UserProfile.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UserProfile.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void Verify(View view) {

        mUser.sendEmailVerification()
                .addOnCompleteListener(UserProfile.this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful())
                        {
                            Toast.makeText(UserProfile.this, "Email Verification Sent, Please Check your Email", Toast.LENGTH_SHORT).show();
                        }

                    }
                }).addOnFailureListener(UserProfile.this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UserProfile.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
