package beans;

public class Customer {		// bean class for customer which consists of all the variables,their getter setter methods to operate on the data of database to view
	int pid,status,quantity,cquant,orderid,addtocartid,reviewid;
	
	String pname,weight,desc,image,cid,cname,cmob,cadd,cpass,orderdate,review;
	double rating;
	public int getReviewid() {
		return reviewid;
	}

	public void setReviewid(int reviewid) {
		this.reviewid = reviewid;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public int getAddtocartid() {
		return addtocartid;
	}

	public void setAddtocartid(int addtocartid) {
		this.addtocartid = addtocartid;
	}
	public String getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(String date) {
		this.orderdate = date;
	}
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	 public String getCpass() {
		return cpass;
	}

	public void setCpass(String cpass) {
		this.cpass = cpass;
	}
	
	 public Customer(String cid, String cname,String cpass, String cmob, String cadd) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.cmob = cmob;
		this.cadd = cadd;
		this.cpass=cpass;
	}
	double ta,sumamount;
	 
	public int getCquant() {
		return cquant;
	}

	public void setCquant(int cquant) {
		this.cquant = cquant;
	}

	public double getSumamount() {
		return sumamount;
	}

	public void setSumamount(double sumamount) {
		this.sumamount = sumamount;
	}

	public double getTa() {
		
		return ta;
	}

	public void setTa(double ta) {
		this.ta = ta;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCmob() {
		return cmob;
	}
	public void setCmob(String cmob) {
		this.cmob = cmob;
	}
	public String getCadd() {
		return cadd;
	}
	public void setCadd(String cadd) {
		this.cadd = cadd;
	}
	double price;
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Customer() {
		// TODO Auto-generated constructor stub
	}

}
