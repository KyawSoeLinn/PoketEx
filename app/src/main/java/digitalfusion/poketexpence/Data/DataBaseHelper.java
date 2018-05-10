package digitalfusion.poketexpence.Data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import digitalfusion.poketexpence.Model.ExpenceCategories;
import digitalfusion.poketexpence.Model.ExpenceTransation;

/**
 * Created by MD003 on 5/7/18.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String LOG = "DatabaseHelper";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ExpenceDataBase";

    //table name

    private static final String ExpenceTransations  = "expence";
    private static final String Categories = "category";

    //ExpenceTransation
    private static final String ETTableName = "Expence";
    private static final String ETId = "id";
    private static final String ETtype = "type";
    private static final String ETamount = "amount";
    private static final String ETcategoriesId = "categoryId";
    private static final String ETpayee = "payee";
    private static final String ETdescription = "description";
    private static final String ETcreated_at = "created_at";

    //Categories
    private static final String CTableName = "Categories";
    private static final String CId = "id";
    private static final String CName = "name";
    private static final String CIcon = "icon";

    //Create Table

    private static final String CREATE_TABLE_expence = "CREATE TABLE "
            + ETTableName + "(" + ETId + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ETtype + " TEXT,"
            + ETamount + " REAL,"
            + ETcategoriesId + " INTEGER,"
            + ETpayee + " TEXT,"
            + ETdescription + " TEXT,"
            + ETcreated_at + " TEXT" + ")";

    private static final String CREATE_TABLE_categories = "CREATE TABLE "
            + CTableName + "(" + CId + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + CName + " TEXT,"
            + CIcon + " TEXT" + ")";

    public boolean insertcontat ( String type, Double amount, Integer categoriesId, String payee, String description, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put(ETId, id );
        contentValues.put(ETtype, type );
        contentValues.put(ETamount, amount);
        contentValues.put(ETcategoriesId, categoriesId);
        contentValues.put(ETpayee, payee);
        contentValues.put(ETdescription, description);
        contentValues.put(ETcreated_at, date);
        db.insert(ETTableName, null, contentValues);
        return true;
    }

    public List getAllData() {
        List detailist = new ArrayList();
        String getQuery = "SELECT * FROM Expence";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(getQuery, null);

        if (cursor.moveToFirst()) {
            do{
                ExpenceTransation expenceTransation = new ExpenceTransation();
                expenceTransation.setId(cursor.getLong(cursor.getColumnIndex("id")));
                expenceTransation.setExpencetype(cursor.getString(cursor.getColumnIndex("type")));
                expenceTransation.setAmount(cursor.getDouble(cursor.getColumnIndex("amount")));
                expenceTransation.setCategoriesID(cursor.getLong(cursor.getColumnIndex("categoryId")));
                expenceTransation.setPayee(cursor.getString(cursor.getColumnIndex("payee")));
                expenceTransation.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                expenceTransation.setCreated_at(cursor.getString(cursor.getColumnIndex("created_at")));


                detailist.add(expenceTransation);
            } while (cursor.moveToNext());
        }
        db.close();

        return detailist;
    }


    //Category CRUD

    public boolean insertCategory (Integer id, String name, String icon){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CId, id );
        contentValues.put(CName, name );
        contentValues.put(CIcon, icon);
        db.insert(CTableName, null, contentValues);
        return true;
    }

    /*public void  insertCategorywithID (String name, String icon){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO Categories (id, name, icon) VALUES ("+getAllCategories().size()+" +1, "+name +" , "+icon+")");
    }*/

    public boolean updateCategory (Integer id, String name, String icon){
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues contentValues = new ContentValues();
        contentValues.put(CId,id);
        contentValues.put(CName, name );
        contentValues.put(CIcon, icon);
        db.update(CTableName, contentValues, "id ="+id , null);
        return true;
    }

    public List getAllCategories() {
        List CategoryList = new ArrayList();
        String getQuery = "SELECT * FROM Categories";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(getQuery, null);

        if (cursor.moveToFirst()) {
            do{
                ExpenceCategories expenceCategories = new ExpenceCategories();
                expenceCategories.setCategoriesID(cursor.getString(cursor.getColumnIndex("id")));
                expenceCategories.setCategoriesName(cursor.getString(cursor.getColumnIndex(CName)));
                expenceCategories.setCategoriesIcon(cursor.getString(cursor.getColumnIndex(CIcon)));


                CategoryList.add(expenceCategories);
            } while (cursor.moveToNext());
        }
        db.close();

        return CategoryList;
    }

    public void deleteCategory (String id, Context context){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+ CTableName +" WHERE id='"+id+"'");
        Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show();
    }


    public DataBaseHelper(Context context) {
        super(context, "ExpenseDatabase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_expence);
        db.execSQL(CREATE_TABLE_categories);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + ETTableName);
        db.execSQL("DROP TABLE IF EXISTS " + CTableName);
    }

    public void deleteExpenseRecord(Long id, Context mcontext) {
        SQLiteDatabase db=this.getWritableDatabase();

        db.execSQL("DELETE FROM "+ ETTableName + " WHERE id='"+id+"'");
        Toast.makeText(mcontext, "Deleted Successfully", Toast.LENGTH_SHORT).show();
    }
}
