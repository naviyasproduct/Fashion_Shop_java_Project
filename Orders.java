class Orders{
	private String orderId;
	private String phoneNumber;
	private String size;
	private int qty;
	private double amount;
	private int status;
	
	private int XS;
	private int S;
	private int M;
	private int L;
	private int XL;
	private int XXL;
	
	
	Orders(String orderId , String phoneNumber , String size , int qty , double amount , int status , int XS , int S, int M , int L , int XL, int XXL){
		this.orderId = orderId;
		this.phoneNumber = phoneNumber;
		this.size = size;
		this.qty = qty;
		this.amount = amount;
		this.status = status;
		
		this.XS = XS;
		this.S = S;
		this.M = M; 
		this.L = L;
		this.XL = XL;
		this.XXL = XXL;
	}
	
	//==============accessers===========================================
	public void setOrderId(String orderId){
		this.orderId = orderId;
	}
	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}
	public void setSize(String size){
		this.size +=" , "+size;
	}
	public void setQty(int qty){
		this.qty += qty;
	}
	public void setAmount(double amount){
		this.amount += amount;
	}
	public void setStatus(int status){
		this.status = status;
	}
	public void setXS(int XS){
		this.XS += XS;
	}
	public void setS(int S){
		this.S += S;
	}
	public void setM(int M){
		this.M += M;
	}
	public void setL(int L){
		this.L += L;
	}
	public void setXL(int XL){
		this.XL += XL;
	}
	public void setXXL(int XXL){
		this.XXL += XXL;
	}
	
	//===================mutaterss======================================
	
	public String getOrderId(){
		return orderId;
	}
	
	public String getPhoneNumber(){
		return phoneNumber;
	}
	public String getSize(){
		return size;
	}
	public int getQty(){
		return qty;
	}
	public double getAmount(){
		return amount;
	}
	public int getStatus(){
		return status;
	}
	public int getXS(){
		return XS;
	}
	public int getS(){
		return S;
	}
	public int getM(){
		return M;
	}
	public int getL(){
		return L;
	}
	public int getXL(){
		return XL;
	}
	public int getXXL(){
		return XXL;
	}
	
	
	
	
	
	
}





















