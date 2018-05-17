package digitalfusion.poketexpence.Model;

import java.util.Date;
import java.util.SimpleTimeZone;

/**
 * Created by MD003 on 5/8/18.
 */

public class ExpenceTransation {

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    private Integer Id;

    public Integer getCategoriesImg() {
        return CategoriesImg;
    }

    public void setCategoriesImg(Integer categoriesImg) {
        CategoriesImg = categoriesImg;
    }

    private String Expencetype;
    private Double Amount;
    private Integer CategoriesID;
    private String Payee;
    private String Description;
    private String Created_at;
    private Integer CategoriesImg;

    public String getCategoriesDesc() {
        return CategoriesDesc;
    }

    public void setCategoriesDesc(String categoriesDesc) {
        CategoriesDesc = categoriesDesc;
    }

    private String CategoriesDesc;


    public ExpenceTransation()
    {

    }



    public ExpenceTransation(String ExpenseType, Double amount,Integer CategoriesID, String Payee, String Description, String Created_at)

    {
        this.Expencetype=ExpenseType;
        this.Amount=amount;
        this.Payee=Payee;
        this.Description=Description;
        this.Created_at=Created_at;

        this.CategoriesID=CategoriesID;
    }





    public Double getAmount() {
        return Amount;
    }

    public void setAmount(Double amount) {
        Amount = amount;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getExpencetype() {
        return Expencetype;
    }

    public void setExpencetype(String expencetype) {
        Expencetype = expencetype;
    }

    public Integer getCategoriesID() {
        return CategoriesID;
    }

    public void setCategoriesID(Integer categoriesID) {
        CategoriesID = categoriesID;
    }

    public String getCreated_at() {
        return Created_at;
    }

    public void setCreated_at(String created_at) {
        Created_at = created_at;
    }

    public String getPayee() {
        return Payee;
    }

    public void setPayee(String payee) {
        Payee = payee;
    }
}
