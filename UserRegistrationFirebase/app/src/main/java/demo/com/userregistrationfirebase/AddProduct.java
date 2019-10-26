package demo.com.userregistrationfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AddProduct extends AppCompatActivity {

    String id;
    String customerName;
    TextView customerNameTv;
    EditText productNameET, productPriceET;
    ListView productListView;
    DatabaseReference productRef;
    DatabaseReference productDetailsRef;
    ArrayList<Product> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        customerNameTv = findViewById(R.id.customerNameTv);
        productNameET = findViewById(R.id.productNameET);
        productPriceET = findViewById(R.id.productPriceET);
        productListView = findViewById(R.id.productListView);

        arrayList = new ArrayList<>();

        customerName = getIntent().getStringExtra(Database.CUSTOMER_NAME);
        id = getIntent().getStringExtra(Database.CUSTOMER_ID);

        customerNameTv.setText(customerName);

        productRef = FirebaseDatabase.getInstance().getReference("Product").child(id);

        productDetailsRef = FirebaseDatabase.getInstance().getReference("Product").child(id);

        productDetailsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                arrayList.clear();
                for (DataSnapshot mSnapshot : dataSnapshot.getChildren())
                {

                    Product product = mSnapshot.getValue(Product.class);
                    arrayList.add(product);
                    ProductAdapter productAdapter = new ProductAdapter(AddProduct.this, arrayList);
                    productListView.setAdapter(productAdapter);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void addProduct(View view) {

        String product_id = productRef.push().getKey();

        Product product = new Product(product_id, productNameET.getText().toString(), productPriceET.getText().toString());

        productRef.child(product_id).setValue(product);

    }
}
