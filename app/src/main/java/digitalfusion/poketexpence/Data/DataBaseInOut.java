package digitalfusion.poketexpence.Data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import digitalfusion.poketexpence.Model.ExpenceTransation;

/**
 * Created by MD003 on 5/8/18.
 */

public class DataBaseInOut {

    SQLiteDatabase database;
    private ExpenceTransation ExpenceTranstion;



    public ArrayList<ExpenceTransation> getTransationFromDataBase(String type, String date1, String date2) {
        ArrayList <ExpenceTransation> expenceTransationArrayList = new ArrayList<>();

        Cursor c = database.rawQuery("SELECT * FROM ExpanceTransation WHERE ExpenceType= '"+type+"' AND  Created_at >= '"+date1+"' AND Created_at <= '"+date2+"'  LIMIT 10",null);

        while (c.moveToNext()){
            ExpenceTransation expenceTransation = new ExpenceTransation();
            expenceTransation.setId((c.getInt(c.getColumnIndex("id"))));
            expenceTransation.setAmount(c.getDouble(c.getColumnIndex("Amount")));
            expenceTransation.setCategoriesID(c.getInt(c.getColumnIndex("CategoriesID")));
            expenceTransation.setPayee(c.getString(c.getColumnIndex("Payee")));
            expenceTransation.setDescription(c.getString(c.getColumnIndex("Description")));
            expenceTransation.setCreated_at(c.getString(c.getColumnIndex("Created_at")));
            expenceTransationArrayList.add(expenceTransation);
        }
        c.close();

        return expenceTransationArrayList;
    }

  /*  public void addNewTranstion () {

        return null;
    }*/


}
