package Book;
import java.sql.Date;
public class Order extends Cart {
	private Date buyDate;

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public Order(String userID,int bookID,float price,Date buyDate)
	{
		super(userID,bookID,price);
	//	this.status=0;
		this.buyDate=buyDate;
	}
	@Override
	public String getValToInsert() {
		int temp=0;
		//if(status==1)
		//temp=1;
		return (String.format("(%s,%d,%d,%f",getUserID(),getBookID(),temp,getPrice())+buyDate);
	}
	@Override
	public String getAttributeToInsert() {
		return "(userID,bookID,status,price,buyDate)";
	}

}
