package digitalfusion.poketexpence.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import belka.us.androidtoggleswitch.widgets.ToggleSwitch;
import digitalfusion.poketexpence.Data.DataBaseHelper;
import digitalfusion.poketexpence.Model.ExpenceTransation;
import digitalfusion.poketexpence.R;

public class UpdateRecordActivity extends AppCompatActivity implements com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener {
     DataBaseHelper DBHelper;
     ToggleSwitch toggleSwitch;
     Button btnCancel,btnSave;
     String transactionType="Income";
     TextView txtDate,txtCategory,txtdatepicker;
     EditText edtamount,edtpayee,edtnote;

     private long receiveRecordId;

    Double amount =null ;
    Long CID ;
    String txtPayee,txtNote;
    Calendar calendar ;
    com.wdullaer.materialdatetimepicker.date.DatePickerDialog datePickerDialog ;
    int Year, Month, Day ;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_transaction);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        DBHelper =new DataBaseHelper(UpdateRecordActivity.this);

        toggleSwitch=(ToggleSwitch) findViewById(R.id.toggleSwitch);
        txtDate=(TextView) findViewById(R.id.txt_date);
        txtCategory=(TextView)findViewById(R.id.txt_category);
        txtdatepicker=(TextView)findViewById(R.id.txt_datepicker);
        edtamount=(EditText)findViewById(R.id.edt_amount);
        edtpayee=(EditText)findViewById(R.id.edt_payee);
        edtnote=(EditText) findViewById(R.id.edt_note);

        btnSave=(Button) findViewById(R.id.btn_save);
        btnCancel=(Button) findViewById(R.id.btn_cancel);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy / MM / dd ");
        txtDate.setText(mdformat.format(calendar.getTime()));

        Year = calendar.get(Calendar.YEAR) ;
        Month = calendar.get(Calendar.MONTH);
        Day = calendar.get(Calendar.DAY_OF_MONTH);


        try{
            receiveRecordId = getIntent().getLongExtra("TRANSACTION_ID",1);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        //transaction data before update
        ExpenceTransation transaction=DBHelper.getAllDataById(receiveRecordId);

        edtamount.setText(transaction.getAmount().toString());
        txtdatepicker.setText(transaction.getCreated_at().toString());
        edtpayee.setText(transaction.getPayee().toString());
        edtnote.setText(transaction.getDescription().toString());
        transactionType=transaction.getExpencetype().toString();
        if(transactionType.equals("Income"))
        {
            toggleSwitch.setCheckedTogglePosition(0);
        }
        else
        {
            toggleSwitch.setCheckedTogglePosition(1);
        }
        //set field to this transaction data
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePerson();
            }
        });
        toggleSwitch.setOnToggleSwitchChangeListener(new ToggleSwitch.OnToggleSwitchChangeListener(){

            @Override
            public void onToggleSwitchChangeListener(int pos, boolean isChecked) {
                if (pos == 0) {
                    transactionType = "Income";
                } else {
                    transactionType = "Expense";
                }


            }}  );

        txtdatepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                datePickerDialog = com.wdullaer.materialdatetimepicker.date.DatePickerDialog.newInstance(UpdateRecordActivity.this, Year, Month, Day);

                datePickerDialog.setThemeDark(false);

                datePickerDialog.showYearPickerFirst(false);

                datePickerDialog.setAccentColor(Color.parseColor("#009688"));

                datePickerDialog.setTitle("Select Date From DatePickerDialog");

                datePickerDialog.show(getFragmentManager(), "DatePickerDialog");
            }
        });



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount =Double.parseDouble(edtamount.getText().toString().trim());
                CID = Long.valueOf(0);
                txtNote=edtnote.getText().toString().trim();
                txtPayee=edtpayee.getText().toString().trim();
                ExpenceTransation updateTransaction= new ExpenceTransation(transactionType,amount,CID,txtPayee,txtNote,txtdatepicker.getText().toString());
                DBHelper.updateTransactionRecord(receiveRecordId,UpdateRecordActivity.this,updateTransaction);

                String addTransaction = transactionType + " "+ amount +" "+ CID +" " +txtNote+" "+txtPayee;
                Toast.makeText(UpdateRecordActivity.this,"Transaction : "+ addTransaction ,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(UpdateRecordActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                UpdateRecordActivity.this.finish();
            }
        });

      /*  button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper.getAllData();
            }
        });*/

    }

    private void updatePerson() {


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
        String date = dayOfMonth + "/" + monthOfYear + "/" + year;
        txtdatepicker.setText(date);

    }
}
