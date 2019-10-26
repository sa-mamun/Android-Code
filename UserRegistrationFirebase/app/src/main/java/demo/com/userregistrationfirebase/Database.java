package demo.com.userregistrationfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Database extends AppCompatActivity {

    EditText customerNameEt, customerAddressEt;
    ListView customerListView;
    ArrayList<Customer> arrayList;

    FirebaseDatabase database;
    DatabaseReference rootRef;
    DatabaseReference customerRef;

    public static final String CUSTOMER_NAME = "customer_name";
    public static final String CUSTOMER_ID = "customer_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        customerNameEt = findViewById(R.id.customerName);
        customerAddressEt = findViewById(R.id.customerAddress);
        customerListView = findViewById(R.id.customerListView);
        arrayList = new ArrayList<>();

        database = FirebaseDatabase.getInstance();
        rootRef = database.getReference();

        customerRef = rootRef.child("Customer");

        customerRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                arrayList.clear();
                for (DataSnapshot mSnapshot : dataSnapshot.getChildren())
                {

                    Customer customer = mSnapshot.getValue(Customer.class);
//                    Customer customer1 = new Customer(customer.getCustomerId(), customer.getCustomerName(), customer.getCustomerAddress());
                    arrayList.add(customer);
                    CustomerAdapter adapter = new CustomerAdapter(Database.this, arrayList);
                    customerListView.setAdapter(adapter);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Database.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        customerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String customer_Name = arrayList.get(position).getCustomerName();
                String customer_id = arrayList.get(position).getCustomerId();

                Intent intent = new Intent(Database.this, AddProduct.class);
                intent.putExtra(CUSTOMER_NAME, customer_Name);
                intent.putExtra(CUSTOMER_ID, customer_id);
                startActivity(intent);

            }
        });


    }

    public void Add(View view) {

        String customerName = customerNameEt.getText().toString();
        String customerAddress = customerAddressEt.getText().toString();
        String customerId = customerRef.push().getKey();

        Customer customer = new Customer(customerId, customerName, customerAddress);

        customerRef.child(customerId).setValue(customer);

    }
}
