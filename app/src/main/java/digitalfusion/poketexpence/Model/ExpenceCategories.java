package digitalfusion.poketexpence.Model;

/**
 * Created by MD003 on 5/8/18.
 */

public class ExpenceCategories {

    private String CategoriesID;
    private String CategoriesName;

    public Integer getCategoriesIcon() {
        return CategoriesIcon;
    }

    public void setCategoriesIcon(Integer categoriesIcon) {
        CategoriesIcon = categoriesIcon;
    }

    private Integer CategoriesIcon;



    public String getCategoriesID() {
        return CategoriesID;
    }

    public void setCategoriesID(String categoriesID) {
        CategoriesID = categoriesID;
    }

    public String getCategoriesName() {
        return CategoriesName;
    }


    public void setCategoriesName(String categoriesName) {
        CategoriesName = categoriesName;
    }


}
