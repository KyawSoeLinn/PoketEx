package digitalfusion.poketexpence.Util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import digitalfusion.poketexpence.Activity.Category;
import digitalfusion.poketexpence.Data.DataBaseHelper;
import digitalfusion.poketexpence.R;

public class AddCategory extends Activity {

    EditText CatEdittxt;
    Button btncatAdd, btncatCancel;
    DataBaseHelper dbhelper;
    String Cattxt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_category);

        CatEdittxt = (EditText) findViewById(R.id.catNameedttxt);
        btncatAdd = (Button) findViewById(R.id.catAdd);
        btncatCancel = (Button) findViewById(R.id.catCancel);
        dbhelper = new DataBaseHelper(this);
        final Bundle bundle = getIntent().getExtras();

        btncatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (bundle.getString("key")!= null){

                    int id;
                    id = Integer.parseInt(bundle.getString("key"));
                    Cattxt = CatEdittxt.getText().toString();
                    dbhelper.updateCategory(id, Cattxt, "grr");

                    Toast.makeText(AddCategory.this, Cattxt + "edited", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(AddCategory.this, Category.class);
                    startActivity(intent);

                }
                else {


                    Cattxt = CatEdittxt.getText().toString();
                    dbhelper.insertCategory((dbhelper.getAllCategories().size() + 1), Cattxt, "grrr");

                    Toast.makeText(AddCategory.this, Cattxt, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(AddCategory.this, Category.class);
                    startActivity(intent);
                }
            }
        });

        btncatCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddCategory.this, Category.class);
                startActivity(intent);
            }
        });


    }
}
