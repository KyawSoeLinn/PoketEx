package digitalfusion.poketexpence.Business;

public class ShowTransactionByFilter {


    public String getAllDataByFilter(String transactionFilter, String dateFilter) {
        String getQuery = null;

            //getQuery="SELECT * FROM Expence where created_at = date('now')";//current date
            //getQuery="SELECT * FROM Expence WHERE created_at BETWEEN datetime('now', '-6 days') AND datetime('now', 'localtime')";//last week


            if(dateFilter.equals("Today"))
            {
                if(transactionFilter.equals("All"))
                {
                    getQuery = "SELECT * from Expence JOIN Categories ON Expence.categoryId=Categories.id WHERE   created_at = date ('now') ";
                }
                else
                {
                    getQuery = "SELECT  * FROM Expence WHERE  type='" + transactionFilter +"'"+ " AND created_at = date ('now') ";
                }
            }
           else if(dateFilter.equals("Yesterday"))
            {
                if(transactionFilter.equals("All"))
                {
                    getQuery = "SELECT * from Expence where created_at = date('now','-1 day')";
                }
                else
                {
                    getQuery = "SELECT * from Expence WHERE  type='"+ transactionFilter+ "'" + " AND created_at = date('now','-1 day')";
                }
            }
            else if(dateFilter.equals("Last Month"))
            {
                if(transactionFilter.equals("All"))
                {
                    getQuery="SELECT * FROM Expence where created_at >= date('now','start of month','-1 month') AND created_at < date('now','start of month')";//last month
                }
                else
                {
                    getQuery="SELECT * FROM Expence where type='"+transactionFilter+ "'" + " AND created_at >= date('now','start of month','-1 month')";//last month
                }
            }
            else if(dateFilter.equals("This Week"))
            {
                if(transactionFilter.equals("All"))
                {
                    getQuery="SELECT * FROM Expence where  created_at > datetime('now', 'start of day', 'weekday 6', '-7 day')";//this week
                }
                else
                {
                    getQuery="SELECT * FROM Expence where type='"+transactionFilter+ "'" + " AND created_at > datetime('now', 'start of day', 'weekday 6', '-7 day')";
                }
            }



        return getQuery;
    }
}
