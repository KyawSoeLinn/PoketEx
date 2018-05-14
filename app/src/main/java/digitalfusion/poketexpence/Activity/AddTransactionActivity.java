package digitalfusion.poketexpence.Activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import belka.us.androidtoggleswitch.widgets.ToggleSwitch;

import digitalfusion.poketexpence.Adapter.AddTransactionAdapter;
import digitalfusion.poketexpence.R;
import digitalfusion.poketexpence.Adapter.CategoryListAdapter;
import digitalfusion.poketexpence.Data.DataBaseHelper;
import digitalfusion.poketexpence.Fragment.HomeFragment;
import digitalfusion.poketexpence.Fragment.QuickSummaryFragment;
import digitalfusion.poketexpence.Model.ExpenceCategories;
import digitalfusion.poketexpence.Model.ExpenceTransation;

import digitalfusion.poketexpence.Util.SelectCategory;
import digitalfusion.poketexpence.ViewModel.AddTransactionModel;

import static digitalfusion.poketexpence.Adapter.CategorySelectAdapter.CatId;
import static digitalfusion.poketexpence.Adapter.CategorySelectAdapter.CatName;

public class AddTransactionActivity extends AppCompatActivity implements com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener {
    DataBaseHelper DBHelper;
    ToggleSwitch toggleSwitch;
    Button btnCancel, btnSave;
    String transactionType = "Income";
    TextView txtDate, txtCategory, txtdatepicker;
    EditText edtamount, edtpayee, edtnote;
    RecyclerView mRecyclerview;
    List<ExpenceCategories> mCategorieList;
    private GridLayoutManager categoryLayout;


    DataBaseHelper dbHelper;
    AddTransactionAdapter categoryListAdapter;
    Double amount = null;
    Integer CID;
    String CName;
    String txtPayee, txtNote;
    Calendar calendar;
    com.wdullaer.materialdatetimepicker.date.DatePickerDialog datePickerDialog;
    int Year, Month, Day;
    AddTransactionModel viewModel;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_transaction);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        DBHelper = new DataBaseHelper(AddTransactionActivity.this);

        toggleSwitch = (ToggleSwitch) findViewById(R.id.toggleSwitch);
        txtDate = (TextView) findViewById(R.id.txt_date);
        txtCategory = (TextView) findViewById(R.id.txt_category);
        txtdatepicker = (TextView) findViewById(R.id.txt_datepicker);
        edtamount = (EditText) findViewById(R.id.edt_amount);
        edtpayee = (EditText) findViewById(R.id.edt_payee);
        edtnote = (EditText) findViewById(R.id.edt_note);

        btnSave = (Button) findViewById(R.id.btn_save);
        btnCancel = (Button) findViewById(R.id.btn_cancel);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy / MM / dd ");
        txtDate.setText(mdformat.format(calendar.getTime()));

        Year = calendar.get(Calendar.YEAR);
        Month = calendar.get(Calendar.MONTH);
        Day = calendar.get(Calendar.DAY_OF_MONTH);


        viewModel = ViewModelProviders.of(this).get(AddTransactionModel.class);

        //toggleSwitch function

        toggleSwitch.setOnToggleSwitchChangeListener(new ToggleSwitch.OnToggleSwitchChangeListener() {

            @Override
            public void onToggleSwitchChangeListener(int pos, boolean isChecked) {
                if (pos == 0) {
                    transactionType = "Income";
                } else {
                    transactionType = "Expense";
                }


            }
        });

        //Show Category Dialog

        txtCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        txtdatepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                datePickerDialog = com.wdullaer.materialdatetimepicker.date.DatePickerDialog.newInstance(AddTransactionActivity.this, Year, Month, Day);

                datePickerDialog.setThemeDark(false);

                datePickerDialog.showYearPickerFirst(false);

                datePickerDialog.setAccentColor(Color.parseColor("#009688"));

                datePickerDialog.setTitle("Select Date From DatePickerDialog");

                datePickerDialog.show(getFragmentManager(), "DatePickerDialog");
            }
        });








                txtCategory.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(AddTransactionActivity.this, SelectCategory.class);
                        startActivity(intent);
                    }
                });
                txtCategory.setText(CatName);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount = Double.parseDouble(edtamount.getText().toString());
                CID = CatId;
                txtNote = edtnote.getText().toString();
                txtPayee = edtpayee.getText().toString();
                ExpenceTransation expenceTransation = new ExpenceTransation(transactionType, amount, CID, txtPayee, txtNote, txtdatepicker.getText().toString());

                viewModel.insertTransaction(expenceTransation);
                String addTransaction = transactionType + " " + amount + " " + CID + " " + txtNote + " " + txtPayee;
                Toast.makeText(AddTransactionActivity.this, "Transaction : " + addTransaction, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(AddTransactionActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                AddTransactionActivity.this.finish();
            }
        });




      /*  button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper.getAllData();
            }
        });*/

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onDateSet(com.wdullaer.materialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Integer month = monthOfYear + 1;
        String date = dayOfMonth + "/" + month + "/" + year;
        txtdatepicker.setText(date);

    }



}
