package digitalfusion.poketexpence.Activity;

import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import belka.us.androidtoggleswitch.widgets.ToggleSwitch;

import digitalfusion.poketexpence.Adapter.AddTransactionAdapter;
import digitalfusion.poketexpence.Fragment.SelectCategoryFragment;
import digitalfusion.poketexpence.R;
import digitalfusion.poketexpence.Data.DataBaseHelper;
import digitalfusion.poketexpence.Model.ExpenceCategories;
import digitalfusion.poketexpence.Model.ExpenceTransation;

import digitalfusion.poketexpence.Util.AddCategoryDialogFragment;
import digitalfusion.poketexpence.Util.PassDataToActivity;
import digitalfusion.poketexpence.Util.RecyclerViewClickListener;
import digitalfusion.poketexpence.ViewModel.AddTransactionModel;


public class AddTransactionActivity extends AppCompatActivity implements com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener, PassDataToActivity {
    DataBaseHelper DBHelper;
    ToggleSwitch toggleSwitch;
    Button btnCancel, btnSave;
    String transactionType = "Income";
    TextView txtDate, txtCategory, txtdatepicker;
    EditText edtamount, edtpayee, edtnote;
    private Integer receiveRecordId;
    FragmentManager fm = getSupportFragmentManager();

    AddTransactionAdapter categoryListAdapter;
    Double amount = null;
    Integer CID;
    String CName;
    String txtPayee, txtNote;
    Calendar calendar;
    com.wdullaer.materialdatetimepicker.date.DatePickerDialog datePickerDialog;
    int Year, Month, Day;
    AddTransactionModel viewModel;

    ImageView btncatAdd;


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
        btncatAdd = (ImageView) findViewById(R.id.img_add);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy / MM / dd ");
        txtDate.setText(mdformat.format(calendar.getTime()));

        Year = calendar.get(Calendar.YEAR);
        Month = calendar.get(Calendar.MONTH);
        Day = calendar.get(Calendar.DAY_OF_MONTH);

        viewModel = ViewModelProviders.of(this).get(AddTransactionModel.class);

        try {
            receiveRecordId = getIntent().getIntExtra("TRANSACTION_ID", 0);
            CID = getIntent().getIntExtra("CATEGORIES_ID", 1);
            CName = getIntent().getStringExtra("CATEGORY_NAME");

        } catch (Exception e) {
            e.printStackTrace();
        }


        if (receiveRecordId != 0) {
            viewModel = ViewModelProviders.of(this).get(AddTransactionModel.class);
            //transaction data before update
            viewModel.getTransactionById(receiveRecordId);
            viewModel.getTransactionListByIdObservable().observe(this, new Observer<ExpenceTransation>() {
                @Override
                public void onChanged(ExpenceTransation transaction) {
                    if (transaction != null) {
                        edtamount.setText(transaction.getAmount().toString());
                        txtdatepicker.setText(transaction.getCreated_at().toString());
                        edtpayee.setText(transaction.getPayee().toString());
                        edtnote.setText(transaction.getDescription().toString());
                        transactionType = transaction.getExpencetype().toString();
                        txtCategory.setText(CName);


                        btnSave.setText("Update");

                        if (transactionType.equals("Income")) {
                            toggleSwitch.setCheckedTogglePosition(0);
                        } else {
                            toggleSwitch.setCheckedTogglePosition(1);
                        }
                    }
                }

            });
        }
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


      /*  btncatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTransactionActivity.this, AddCategory.class);
                startActivity(intent);
            }
        });*/


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


                SelectCategoryFragment fragment = new SelectCategoryFragment();
                fragment.show(getSupportFragmentManager(), "fragment");


            }
        });
        txtCategory.setText("Select Category");
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String btnStatus = btnSave.getText().toString();
                amount = Double.parseDouble(edtamount.getText().toString());
                txtNote = edtnote.getText().toString();
                txtPayee = edtpayee.getText().toString();
                ExpenceTransation expenceTransation = new ExpenceTransation(transactionType, amount, CID, txtPayee, txtNote, txtdatepicker.getText().toString());

                if (btnStatus.equals("Update")) {
                    viewModel.updateTransaction(receiveRecordId, expenceTransation);
                } else {
                    viewModel.insertTransaction(expenceTransation);
                }
                Intent intent = new Intent(AddTransactionActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                AddTransactionActivity.this.finish();
            }
        });




        btncatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddCategoryDialogFragment fragment = new AddCategoryDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putString("BtnStatus","Add");
                fragment.setArguments(bundle);
                fragment.show(getSupportFragmentManager(),"SelectCategoryFragment");


            }
        });

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
        String date = null;
        if (month < 10) {
            date = year + "-" + "0" + month + "-" + dayOfMonth;
        } else {
            date = year + "-" + month + "-" + dayOfMonth;
        }
        txtdatepicker.setText(date);

    }


    @Override
    public void passDataToActivity(String someValue, Integer CatID) {
        txtCategory.setText(someValue);
        CID = CatID;

    }

    @Override
    public void loadNewData() {

    }
}
