package demo.com.userregistrationfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Database extends AppCompatActivity {

    EditText customerNameEt, customerAddressEt;

    FirebaseDatabase database;
    DatabaseReference rootRef;
    DatabaseReference customerRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        customerNameEt = findViewById(R.id.customerName);
        customerAddressEt = findViewById(R.id.customerAddress);

        database = FirebaseDatabase.getInstance();
        rootRef = database.getReference();

        customerRef = rootRef.child("Customer");


    }

    public void Add(View view) {

        String customerName = customerNameEt.getText().toString();
        String customerAddress = customerAddressEt.getText().toString();
        String customerId = customerRef.push().getKey();

        Customer customer = new Customer(customerId, customerName, customerAddress);

        customerRef.child(customerId).setValue(customer);

    }
}
