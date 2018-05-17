package digitalfusion.poketexpence.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import digitalfusion.poketexpence.Data.DataBaseHelper;
import digitalfusion.poketexpence.Model.ExpenceCategories;
import digitalfusion.poketexpence.Model.ExpenceTransation;

/**
 * Created by Asus on 5/13/2018.
 */

public class AddCategoriesModel extends AndroidViewModel {
    DataBaseHelper dbHelper;
    private LiveData<List<ExpenceCategories>> categoryListObservable;
    private LiveData<ExpenceTransation> transactionListByIdObservable;
    public AddCategoriesModel(Application application) {
        super(application);
    }

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
   public LiveData<List<ExpenceCategories>> getCategoryListObservable() {
       return categoryListObservable;

    }
    public LiveData<ExpenceTransation> getTransactionListByIdObservable() {
        return transactionListByIdObservable;
    }
    public void insertTransaction(ExpenceTransation expenceTransation) {
        dbHelper=new DataBaseHelper(this.getApplication());
        dbHelper.insertcontat(expenceTransation);
    }

    public void getTransactionById(long receiveRecordId) {
        dbHelper=new DataBaseHelper(this.getApplication());
        transactionListByIdObservable = dbHelper.getAllDataById(receiveRecordId);

    }

    public void updateTransaction(long receiveRecordId, ExpenceTransation updateTransaction) {
        dbHelper=new DataBaseHelper(this.getApplication());
        dbHelper.updateTransactionRecord(receiveRecordId,updateTransaction);
    }

    public void getAllCategories() {
        dbHelper=new DataBaseHelper(this.getApplication());
        categoryListObservable = dbHelper.getAllCategories();

    }

    public void insertCategory(String cattxt, Integer iconID) {
        dbHelper=new DataBaseHelper(this.getApplication());
       dbHelper.insertCategory(cattxt,iconID);
    }
}
