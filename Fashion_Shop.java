
import java.util.*;

class Fashion_Shop {
    // ----------- CREATE Object ARRAY ------------
   
	public static Orders[] ordersObjectArray = new Orders[0];
   
   
    // ----------- CONSTANTS ------------------
    
    public static final int PROCESSING = 0;
    public static final int DELIVERING = 1;
    public static final int DELIVERED = 2;


	//=====Scanner====================================================
    public static Scanner input = new Scanner(System.in);

    // -------------- MAIN METHOD -----------------
    public static void main(String[] args) {
        homePage();
    }

    // -------------- CLEAR CONSOLE -----------------
    public final static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c",
                        "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            e.printStackTrace();
            // Handle any exceptions.
        }
    }

    // -------------- GENERATE ORDER ID -----------------
    public static String generateId(int x , String phoneNumber) {
		
		
        int id1;
        
        if(x==0){
			if (ordersObjectArray.length > 0) {
				id1 = Integer.parseInt(ordersObjectArray[ordersObjectArray.length - 1 ].getOrderId().split("[#]")[1]);
				id1++;
			} else {
				return "ODR#00001";
			}
		}else{
			id1 = Integer.parseInt(ordersObjectArray[searchCustomerByPhoneNumber(phoneNumber)].getOrderId().split("[#]")[1]);
		}
		
		
		
        return String.format("ODR#%05d", id1);
    }

    // --------------- HOME PAGE ---------------
    public static void homePage() {
        System.out.println("================== FASHION SHOP ========================");
        System.out.println("\n[01] Place Order");
        System.out.println("\n[02] Search Customer");
        System.out.println("\n[03] Search Order");
        System.out.println("\n[04] View Reports");
        System.out.println("\n[05] Set Order Status");
        System.out.println("\n[06] Delete Order");
        System.out.print("\nEnter option : ");
        int option = input.nextInt();
        clearConsole();

        switch (option) {
            case 1:
                placeOrder();
                break;
            case 2:
                searchCustomer();
                break;
            case 3:
                searchOrder();
                break;
            case 4:
                viewReports();
                break;
            case 5:
                setOrderStatus();
                break;
            case 6:
                deleteOrder();
                
                break;
            default:
                System.out.println("Invalid option...");
                homePage();
        }
    }

    // --------------- VALIDATE PHONE NUMBER ---------------
    public static boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber.length() == 10 && phoneNumber.charAt(0) == '0') {
            return true;
        }
        return false;
    }

    // --------------- VALIDATE QTY ---------------
    public static boolean validateQty(int qty) {
        if (qty > 0) {
            return true;
        }
        return false;
    }

    // --------------- EXTEND ARRAYS ---------------
    public static void extendArrays() {
        
        Orders[] tempOrdersObjectArray = new Orders[ordersObjectArray.length+1];
        
        for (int i = 0; i < ordersObjectArray.length; i++) {
           tempOrdersObjectArray[i] = ordersObjectArray[i]; 
        }
		ordersObjectArray = tempOrdersObjectArray;
    }

    // --------------- PLACE ORDER -----------------
    public static void placeOrder() {
        L1: do {
            System.out.println("================ PLACE ORDER =================");
            String orderId = generateId(0,"");
            System.out.println("\nEnter order Id : " + orderId);
            String phoneNumber;

            L2: do {
                System.out.print("\nEnter Customer phone number : ");
                phoneNumber = input.next();
                boolean isValidPhoneNumber = validatePhoneNumber(phoneNumber);

                if (isValidPhoneNumber) {
                    break L2;
                }
                System.out.println("\n\tInvalid phone number...Try again");
                System.out.print("\nDo you want to enter phone number again : ");
                char ch = input.next().charAt(0);
                if (ch == 'y' || ch == 'y') {
                    // Move the cursor up five lines
                    System.out.print("\033[6A");
                    // Clear the lines
                    System.out.print("\033[0J");
                    continue L2;

                } else if (ch == 'n' || ch == 'N') {
                    clearConsole();
                    homePage();
                }
            } while (true);

            System.out.print("\nEnter T-Shirt size : ");
            String size = input.next();

            int qty;

            L3: do {
                System.out.print("\nEnter QTY : ");
                qty = input.nextInt();

                boolean isValidQty = validateQty(qty);
                if (isValidQty) {
                    break L3;
                }
                System.out.println("\n\tInvalid qty...try again");
                System.out.print("\nDo you want to enter QTY again : ");
                char ch = input.next().charAt(0);
                if (ch == 'y' || ch == 'y') {
                    // Move the cursor up five lines
                    System.out.print("\033[6A");
                    // Clear the lines
                    System.out.print("\033[0J");
                    continue L3;

                } else if (ch == 'n' || ch == 'N') {
                    // clearConsole();
                    // homePage();
                }
            } while (true);

			int XS = 0;
			int S = 0;
			int M = 0;
			int L = 0;
			int XL = 0;
			int XXL = 0;
			
            double amount = 0;
            switch (size.toUpperCase()) {
                case "XS":
                    amount = 600 * qty;
                    XS = qty;
                    break;
                case "S":
                    amount = 800 * qty;
                    S = qty;
                    break;
                case "M":
                    amount = 900 * qty;
                    M = qty;
                    break;
                case "L":
                    amount = 1000 * qty;
                    L = qty;
                    break;
                case "XL":
                    amount = 1100 * qty;
                    XL = qty;
                    break;
                case "XXL":
                    amount = 1200 * qty;
                    XXL = qty;
                    break;
            }

            System.out.println("\nAmount : " + amount);
            System.out.print("\nDo you want to place this order ? ");
            char ch = input.next().charAt(0);
            if (ch == 'Y' || ch == 'y') {
                
                
                if(searchCustomerByPhoneNumber(phoneNumber)>=0){
					
					Orders existingOrder = ordersObjectArray[searchCustomerByPhoneNumber(phoneNumber)];
                
					
					existingOrder.setSize(size);
					existingOrder.setQty(qty);
					existingOrder.setAmount(amount);
					existingOrder.setStatus(PROCESSING);
					
					existingOrder.setXS(XS);
					existingOrder.setS(S);
					existingOrder.setM(M);
					existingOrder.setL(L);
					existingOrder.setXL(XL);
					existingOrder.setXXL(XXL);
					generateId(-1,phoneNumber);
					System.out.println("Order Placed Existing Customer successfully");
					
				
				}else{
                
                extendArrays();
                Orders newOrder = new Orders(orderId,phoneNumber,size,qty,amount,PROCESSING,XS,S,M,L,XL,XXL);
                ordersObjectArray[ordersObjectArray.length-1] = newOrder;
                System.out.println("Order Placed successfully");
				}
            
            }
            System.out.print("\nDo you want to place another order ? ");
            char op = input.next().charAt(0);
            clearConsole();
            if (op == 'y' || op == 'Y') {
                continue L1;
            } else if (op == 'n' || op == 'N') {
                homePage();
            }

        } while (true);
    }
    
    

    // --------------- SEARCH ORDER BY ORDER ID ----------------
    public static int searchOrderByOrderId(String orderId) {
        for (int i = 0; i < ordersObjectArray.length; i++) {
            if (ordersObjectArray[i].getOrderId().equals(orderId)) {
                return i;
            }
        }
        return -1;
    }

    // --------------- SEARCH CUSTOMER BY PHONE NUMBER ----------------
    public static int searchCustomerByPhoneNumber(String phoneNumber) {
        for (int i = 0; i < ordersObjectArray.length; i++) {
            if (ordersObjectArray[i].getPhoneNumber().equals(phoneNumber)) {
                return i;
            }
        }
        return -1;
    }

    // --------------- GET CUSTOMER DETAILS -----------------
    public static void getCustomerDetails(String phoneNumber) {

		int index = searchCustomerByPhoneNumber(phoneNumber);
		
        System.out.println("+---------------+---------------+-----------------+");
        System.out.println("|     Size      |      QTY      |      Amount     |");
        System.out.println("+---------------+---------------+-----------------+");

        System.out.printf("| %-13s | %-13s | %15d |\n","XS",ordersObjectArray[index].getXS(),(ordersObjectArray[index].getXS()*600) );
        System.out.printf("| %-13s | %-13s | %15d |\n","S",ordersObjectArray[index].getS(),(ordersObjectArray[index].getS()*800));
        System.out.printf("| %-13s | %-13s | %15d |\n","M",ordersObjectArray[index].getM(),(ordersObjectArray[index].getM()*900));
        System.out.printf("| %-13s | %-13s | %15d |\n","L",ordersObjectArray[index].getL(),(ordersObjectArray[index].getL()*1000));
        System.out.printf("| %-13s | %-13s | %15d |\n","XL",ordersObjectArray[index].getXL(),(ordersObjectArray[index].getXL()*1100));
        System.out.printf("| %-13s | %-13s | %15d |\n","XXL",ordersObjectArray[index].getXXL(),(ordersObjectArray[index].getXXL()*1200));
        System.out.println("+---------------+---------------+-----------------+");
        System.out.printf("| %-30s| %15.2f |\n", "Total Amount", ordersObjectArray[index].getAmount());
        System.out.println("+-------------------------------------------------+");
		
    }

    // ---------------- SEARCH CUSTOMER -----------------
    public static void searchCustomer() {
        do {
            System.out.println("==================== SEARCH CUSTOMER ====================");
            System.out.print("\nEnter customer phone number : ");
            String phoneNumber = input.next();

            int index = searchCustomerByPhoneNumber(phoneNumber);

            if (index == -1) {
                System.out.println("\n\tInvalid phone number...");
            } else {
                getCustomerDetails(phoneNumber);
            }
            System.out.print("\nDo you want to search another customer report ? ");
            char ch = input.next().charAt(0);
            clearConsole();
            if (ch == 'y' || ch == 'Y') {
                continue;
            } else if (ch == 'n' || ch == 'N') {
                homePage();
            }
        } while (true);

    }

    // ------------------ PRINT ORDER DETAILS --------------------
    public static void printOrderDetails(int index) {
        System.out.println("\nPhone Number : " + ordersObjectArray[index].getPhoneNumber());
        System.out.println("Size         : " +   ordersObjectArray[index].getSize());
        System.out.println("QTY          : " +   ordersObjectArray[index].getQty());
        System.out.println("Amount       : " +   ordersObjectArray[index].getAmount());
        if (ordersObjectArray[index].getStatus() == 0) {
            System.out.println("Status       : Processing");
        } else if (ordersObjectArray[index].getStatus() == 1) {
            System.out.println("Status       : Delivering");
        } else {
            System.out.println("Status       : Delivered");
        }
    }

    // ------------------ SEARCH ORDER -----------------
    public static void searchOrder() {
        do {
            System.out.println("================ SEARCH ORDER ================");
            System.out.print("\nEnter order ID : ");
            String orderId = input.next();

            int index = searchOrderByOrderId(orderId);

            if (index == -1) {
                System.out.println("\n\tInvalid order ID...");
            } else {
                printOrderDetails(index);
            }
            System.out.print("\nDo you want to search another order ? ");
            char ch = input.next().charAt(0);
            clearConsole();
            if (ch == 'y' || ch == 'Y') {
                continue;
            } else if (ch == 'n' || ch == 'N') {
                homePage();
            }
        } while (true);
    }

    // ------------------ VIEW REPORTS -----------------
    public static void viewReports() {
        System.out.println("=============== REPORTS ================");
        System.out.println("\n[01] Customer Report");
        System.out.println("\n[02] Item Report");
        System.out.println("\n[03] Order Report");
        System.out.print("\nEnter Option : ");
        int option = input.nextInt();
        clearConsole();
        switch (option) {
            case 1:
                customerReport();
                break;
            case 2:
                itemReport();
                break;
            case 3:
                orderReport();
                break;
            default:
                System.out.println("\nInvalid Option...");
        }

    }

    // ------------------- CUSTOMER REPORTS ------------------
    public static void customerReport() {
        System.out.println("=============== CUSTOMER REPORTS ================");
        System.out.println("\n[01] Best in Customers");
        System.out.println("\n[02] View Customers");
        System.out.println("\n[03] All Customer Report");
        System.out.print("\nEnter Option : ");
        int option = input.nextInt();
        clearConsole();
        switch (option) {
            case 1:
                bestInCustomer();
                break;
            case 2:
                viewCustomers();
                break;
            case 3:
                allCustomerReports();
                break;
            default:
                System.out.println("\nInvalid Option...");
        }
    }

    // ------------------- BEST IN CUSTOMER ------------------
    public static void bestInCustomer() {
        do {
            System.out.println("================== BEST IN CUSTOMERS =================");
            bestInCustomersByAmount();

            System.out.print("\nTo access the Main Menu, please enter 0 : ");
            int choice = input.nextInt();
            clearConsole();
            if (choice == 0) {
                homePage();
            } else {
                System.out.println("\nInvalid Option...");
                continue;
            }
        } while (true);
    }

    //----------------- BEST IN CUSTOMERS BY AMOUNT --------------------
    public static void bestInCustomersByAmount() {
		Orders []tempObjectArray = new Orders[ordersObjectArray.length];
		
		
		for (int i = 0; i < ordersObjectArray.length; i++){
			tempObjectArray[i] = ordersObjectArray[i];
		}
		
		
		for (int i = 0; i < ordersObjectArray.length - 1; i++){
			for (int j = i+1; j < ordersObjectArray.length; j++){
				if(tempObjectArray[i].getAmount()< tempObjectArray[j].getAmount()){
				Orders tempOrder = tempObjectArray[i];
				tempObjectArray[i] = tempObjectArray[j];
				tempObjectArray[j] = tempOrder;
				}
			}
			  
		}
		
    
        System.out.println("+---------------+----------------+-----------------+");
        System.out.println("|  Customer ID  |      QTY       |   Total Amount  |");
        System.out.println("+---------------+----------------+-----------------+");

        for (int i = 0; i < tempObjectArray.length; i++) {
            System.out.printf("| %-13s | %-14s | %15.2f |\n", tempObjectArray[i].getOrderId(), tempObjectArray[i].getQty(),tempObjectArray[i].getAmount());
        }
        System.out.println("+---------------+----------------+-----------------+");

    }

    // ------------------- VIEW CUSTOMERS ------------------
    public static void viewCustomers() {
        do {
            System.out.println("================== VIEW CUSTOMERS =================");
            viewCustomer();

            System.out.print("\nTo access the Main Menu, please enter 0 : ");
            int choice = input.nextInt();
            clearConsole();
            if (choice == 0) {
                homePage();
            } else {
                System.out.println("\nInvalid Option...");
                continue;
            }
        } while (true);
    }

    public static void viewCustomer() {
        
        Orders [] tempObjectArray = new Orders[ordersObjectArray.length];
        
        for (int i = 0; i < ordersObjectArray.length; i++){
			tempObjectArray[i] = ordersObjectArray[i];
		}
		
        
        
        
        
        System.out.println("+---------------+----------------+-----------------+");
        System.out.println("|  Customer ID  |      QTY       |   Total Amount  |");
        System.out.println("+---------------+----------------+-----------------+");

       for (int i = 0; i < tempObjectArray.length; i++) {
            System.out.printf("| %-13s | %-14s | %15.2f |\n", tempObjectArray[i].getOrderId(), tempObjectArray[i].getQty(),tempObjectArray[i].getAmount());
        }
        System.out.println("+---------------+----------------+-----------------+");
    }

    // ------------------- ALL CUSTOMER REPORTS ------------------
    public static void allCustomerReports() {
        do {
            System.out.println("================== All CUSTOMER REPORTS =================");
            viewAllCustomerReports();

            System.out.print("\nTo access the Main Menu, please enter 0 : ");
            int choice = input.nextInt();
            clearConsole();
            if (choice == 0) {
                homePage();
            } else {
                System.out.println("\nInvalid Option...");
                continue;
            }
        } while (true);
    }

    public static void viewAllCustomerReports() {
		Orders [] tempObjectArray = new Orders[ordersObjectArray.length];
		
		for (int i = 0; i < ordersObjectArray.length; i++){
			tempObjectArray[i] = ordersObjectArray[i];
		}
		
		
     

        System.out.println("+-----------------+---------+---------+---------+---------+---------+---------+-----------------+");
        System.out.printf("| %-15s | %-7s | %-7s | %-7s | %-7s | %-7s | %-7s | %-15s |\n", "Phone Number","XS", "S", "M",
                "L", "XL", "XXL", "Total Amount");
        System.out.println("+-----------------+---------+---------+---------+---------+---------+---------+-----------------+");

        for (int i = 0; i < tempObjectArray.length; i++) {
            System.out.printf("| %-15s | %-7s | %-7s | %-7s | %-7s | %-7s | %-7s | %15.2f |\n",tempObjectArray[i].getPhoneNumber(),tempObjectArray[i].getXS(),
            tempObjectArray[i].getS(),tempObjectArray[i].getM(),tempObjectArray[i].getL(),tempObjectArray[i].getXL(),tempObjectArray[i].getXXL(),tempObjectArray[i].getAmount());

        }
        System.out.println("+-----------------+---------+---------+---------+---------+---------+---------+-----------------+");

    }

    // ------------------- ITEM REPORTS ------------------
    public static void itemReport() {
        System.out.println("=============== ITEM REPORTS ================");
        System.out.println("\n[01] Best selling Categories Sorted by QTY");
        System.out.println("\n[02] Best selling Categories Sorted by Amount");
        System.out.print("\nEnter Option : ");
        int option = input.nextInt();
        clearConsole();
        switch (option) {
            case 1:
                bestSellingCategoriesSortedByQty();
                break;
            case 2:
                bestSellingCategoriesSortedByAmount();
                break;
            default:
                System.out.println("\nInvalid Option...");
        }
    }

    // ------------------- BEST SELLING CATEGORIES SORTED BY qty -----------------------
    public static void bestSellingCategoriesSortedByQty() {
        do {
            System.out.println("================ SORTED BY QTY =================");
            sortedByQty();

            System.out.print("\nTo access the Main Menu, please enter 0 : ");
            int choice = input.nextInt();
            clearConsole();
            if (choice == 0) {
                homePage();
            } else {
                System.out.println("\nInvalid Option...");
                continue;
            }
        } while (true);
    }

    public static void sortedByQty() {
        
		int [] tempQty = new int[6];
		String [] tempSize = new String[6];
		int [] tempAmount = new int[6];
		int [] tempPrice = new int[6];
		
	
		
		tempPrice[0] = 600;
		tempPrice[1] = 800;
		tempPrice[2] = 900;
		tempPrice[3] = 1000;
		tempPrice[4] = 1100;
		tempPrice[5] = 1200;
		
		
		for (int i = 0; i < ordersObjectArray.length; i++) {
			tempQty[0] += ordersObjectArray[i].getXS(); 
		}
		
		for (int i = 0; i < ordersObjectArray.length; i++) {
			tempQty[1] += ordersObjectArray[i].getS(); 
		}
		
		for (int i = 0; i < ordersObjectArray.length; i++) {
			tempQty[2] += ordersObjectArray[i].getM(); 
		}
		
		for (int i = 0; i < ordersObjectArray.length; i++) {
			tempQty[3] += ordersObjectArray[i].getL(); 
		}
		
		for (int i = 0; i < ordersObjectArray.length; i++) {
			tempQty[4] += ordersObjectArray[i].getXL(); 
		}
		
		for (int i = 0; i < ordersObjectArray.length; i++) {
			tempQty[5] += ordersObjectArray[i].getXXL(); 
		}
		
		
		
		for (int i = 0; i < 6; i++){
			tempAmount[i] = tempQty[i] * tempPrice[i];
		}

		tempSize[0] = "XS"; 
		tempSize[1] = "S"; 
		tempSize[2] = "M"; 
		tempSize[3] = "L"; 
		tempSize[4] = "XL"; 
		tempSize[5] = "XXL"; 

		
		for (int i = 0; i < 6-1; i++){
			for (int j = i + 1 ; j < 6; j++){
				if(tempQty[i]<tempQty[j]){
					int temp = tempQty[i];
					tempQty[i] = tempQty[j];
					tempQty[j] = temp;
					
					String SizeTemp = tempSize[i];
					tempSize[i] = tempSize[j];
					tempSize[j] = SizeTemp;
					
					int AmountTemp = tempAmount[i];
					tempAmount[i] = tempAmount[j];
					tempAmount[j] = AmountTemp; 
					
				}
			}
		}
    
        System.out.println("+---------------+----------------+-----------------+");
        System.out.println("|      Size     |      QTY       |      Amount     |");
        System.out.println("+---------------+----------------+-----------------+");

        for (int i = 0; i < 6; i++) {
            System.out.printf("| %-13s | %-14d | %15d |\n", tempSize[i], tempQty[i],tempAmount[i]);
        }
        System.out.println("+---------------+----------------+-----------------+");
        
        
    }

    // ------------------- BEST SELLING CATEGORIES SORTED BY AMOUNT ---------------------
    public static void bestSellingCategoriesSortedByAmount() {
        do {
            System.out.println("================ SORTED BY AMOUNT =================");
            sortedByAmount();

            System.out.print("\nTo access the Main Menu, please enter 0 : ");
            int choice = input.nextInt();
            clearConsole();
            if (choice == 0) {
                homePage();
            } else {
                System.out.println("\nInvalid Option...");
                continue;
            }
        } while (true);

    }

    public static void sortedByAmount() {
       

		int [] tempQty = new int[6];
		String [] tempSize = new String[6];
		int [] tempAmount = new int[6];
		int [] tempPrice = new int[6];
		
	
		
		tempPrice[0] = 600;
		tempPrice[1] = 800;
		tempPrice[2] = 900;
		tempPrice[3] = 1000;
		tempPrice[4] = 1100;
		tempPrice[5] = 1200;
		
		
		for (int i = 0; i < ordersObjectArray.length; i++) {
			tempQty[0] += ordersObjectArray[i].getXS(); 
		}
		for (int i = 0; i < ordersObjectArray.length; i++) {
			tempQty[1] += ordersObjectArray[i].getS(); 
		}
		for (int i = 0; i < ordersObjectArray.length; i++) {
			tempQty[2] += ordersObjectArray[i].getM(); 
		}
		for (int i = 0; i < ordersObjectArray.length; i++) {
			tempQty[3] += ordersObjectArray[i].getL(); 
		}
		for (int i = 0; i < ordersObjectArray.length; i++) {
			tempQty[4] += ordersObjectArray[i].getXL(); 
		}
		for (int i = 0; i < ordersObjectArray.length; i++) {
			tempQty[5] += ordersObjectArray[i].getXXL(); 
		}
		
		
		
		for (int i = 0; i < 6; i++){
			tempAmount[i] = tempQty[i] * tempPrice[i];
		}

		tempSize[0] = "XS"; 
		tempSize[1] = "S"; 
		tempSize[2] = "M"; 
		tempSize[3] = "L"; 
		tempSize[4] = "XL"; 
		tempSize[5] = "XXL"; 

		
		for (int i = 0; i < 6-1; i++){
			for (int j = i + 1 ; j < 6; j++){
				if(tempAmount[i]<tempAmount[j]){
					int temp = tempQty[i];
					tempQty[i] = tempQty[j];
					tempQty[j] = temp;
					
					String SizeTemp = tempSize[i];
					tempSize[i] = tempSize[j];
					tempSize[j] = SizeTemp;
					
					int AmountTemp = tempAmount[i];
					tempAmount[i] = tempAmount[j];
					tempAmount[j] = AmountTemp; 
					
				}
			}
		}
    
        System.out.println("+---------------+----------------+-----------------+");
        System.out.println("|      Size     |      QTY       |      Amount     |");
        System.out.println("+---------------+----------------+-----------------+");

        for (int i = 0; i < 6; i++) {
            System.out.printf("| %-13s | %-14d | %15d |\n", tempSize[i], tempQty[i],tempAmount[i]);
        }
        System.out.println("+---------------+----------------+-----------------+");
        
        
    }

    //------------------- ORDER REPORTS -------------------
    public static void orderReport() {
        System.out.println("=============== ORDER REPORTS ================");
        System.out.println("\n[01] All Orders");
        System.out.println("\n[02] Orders By Amount");
        System.out.print("\nEnter Option : ");
        int option = input.nextInt();
        clearConsole();
        switch (option) {
            case 1:
                allOrders();
                break;
            case 2:
                ordersByAmount();
                break;
            default:
                System.out.println("\nInvalid Option...");
        }
    }

    //----------------------- ALL ORDERS -----------------------
    public static void allOrders() {
        do {
            System.out.println("============== VIEW ORDERS =================");
            ordersSortByOrderId();

            System.out.print("\nTo access the Main Menu, please enter 0 : ");
            int choice = input.nextInt();
            clearConsole();
            if (choice == 0) {
                homePage();
            } else {
                System.out.println("\nInvalid Option...");
                continue;
            }
        } while (true);
    }

    public static void ordersSortByOrderId() {
       
       
       Orders [] tempObjectArray = new Orders[ordersObjectArray.length];
		
		for (int i = 0; i < ordersObjectArray.length; i++){
			tempObjectArray[i] = ordersObjectArray[i];
		}
		
		
     

        System.out.println("+-----------------+---------+---------+---------+---------+---------+---------+-----------------+");
        System.out.printf("| %-15s | %-7s | %-7s | %-7s | %-7s | %-7s | %-7s | %-15s |\n", "Phone Number","XS", "S", "M",
                "L", "XL", "XXL", "Total Amount");
        System.out.println("+-----------------+---------+---------+---------+---------+---------+---------+-----------------+");

        for (int i = 0; i < tempObjectArray.length; i++) {
            System.out.printf("| %-15s | %-7d | %-7d | %-7d | %-7d | %-7d | %-7d | %15.2f |\n",tempObjectArray[i].getPhoneNumber(),tempObjectArray[i].getXS(),
            tempObjectArray[i].getS(),tempObjectArray[i].getM(),tempObjectArray[i].getL(),tempObjectArray[i].getXL(),tempObjectArray[i].getXXL(),tempObjectArray[i].getAmount());

        }
        System.out.println("+-----------------+---------+---------+---------+---------+---------+---------+-----------------+");
       
       
       
    }

    public static void ordersByAmount() {
        do {
            System.out.println("=================== ORDERS BY AMOUNT =================");
            ordersSortByAmount();

            System.out.print("\nTo access the Main Menu, please enter 0 : ");
            int choice = input.nextInt();
            clearConsole();
            if (choice == 0) {
                homePage();
            } else {
                System.out.println("\nInvalid Option...");
                continue;
            }
        } while (true);
    }

    public static void ordersSortByAmount() {
          
       Orders [] tempObjectArray = new Orders[ordersObjectArray.length];
		
		for (int i = 0; i < ordersObjectArray.length; i++){
			tempObjectArray[i] = ordersObjectArray[i];
		}
		
			
		for (int i = 0; i < ordersObjectArray.length - 1; i++){
			for (int j = i+1; j < ordersObjectArray.length; j++){
				if(tempObjectArray[i].getAmount()< tempObjectArray[j].getAmount()){
				Orders tempOrder = tempObjectArray[i];
				tempObjectArray[i] = tempObjectArray[j];
				tempObjectArray[j] = tempOrder;
				}
			}
			  
		}
     

        System.out.println("+-----------------+---------+---------+---------+---------+---------+---------+-----------------+");
        System.out.printf("| %-15s | %-7s | %-7s | %-7s | %-7s | %-7s | %-7s | %-15s |\n", "Phone Number","XS", "S", "M",
                "L", "XL", "XXL", "Total Amount");
        System.out.println("+-----------------+---------+---------+---------+---------+---------+---------+-----------------+");

        for (int i = 0; i < tempObjectArray.length; i++) {
            System.out.printf("| %-15s | %-7d | %-7d | %-7d | %-7d | %-7d | %-7d | %15.2f |\n",tempObjectArray[i].getPhoneNumber(),tempObjectArray[i].getXS(),
            tempObjectArray[i].getS(),tempObjectArray[i].getM(),tempObjectArray[i].getL(),tempObjectArray[i].getXL(),tempObjectArray[i].getXXL(),tempObjectArray[i].getAmount());

        }
        System.out.println("+-----------------+---------+---------+---------+---------+---------+---------+-----------------+");
       
       
    }

    // ------------------ CHANGE ORDER STATUS --------------------
    public static void setOrderStatus() {
        L1: do {
            System.out.println("================= ORDER STATUS =================");
            System.out.print("\nEnter order ID : ");
            String orderId = input.next();

            int index = searchOrderByOrderId(orderId);

            if (index == -1) {
                System.out.println("\n\tInvalid order ID...");
            } else {
                printOrderDetails(index);
                System.out.print("\nDo you want to change this order status ? ");
                char ch = input.next().charAt(0);
                if (ch == 'y' || ch == 'Y') {
                    if (ordersObjectArray[index].getStatus() == 0) {
                        System.out.println("\n\t[01] Order Delivering");
                        System.out.println("\n\t[02] Order Delivered");
                        L2: do {
                            System.out.print("\nEnter Option : ");
                            int option = input.nextInt();

                            switch (option) {
                                case 1:
                                    ordersObjectArray[index].setStatus(1);
                                    System.out.println("\nStatus Updated !");
                                    break L2;
                                case 2:
                                    ordersObjectArray[index].setStatus(2);
                                    System.out.println("\nStatus Updated !");
                                    break L2;
                                default:
                                    System.out.println("\nInvalid Option...");
                                    continue L2;
                            }
                        } while (true);
                    } else if (ordersObjectArray[index].getStatus() == 1) {
                        System.out.println("\n\t[01] Order Delivered");
                        L3: do {
                            System.out.print("\nEnter Option : ");
                            int option = input.nextInt();
                            switch (option) {
                                case 1:
                                    ordersObjectArray[index].setStatus(2);
                                    System.out.println("\nStatus Updated !");
                                    break L3;
                                default:
                                    System.out.println("\nInvalid Option...");
                                    continue L3;
                            }
                        } while (true);
                    } else {
                        System.out.println("\n\tCan't change this Order status, Order already Delivered...!");
                    }
                }

            }
            System.out.print("\nDo you want to change another Order status ? ");
            char ch = input.next().charAt(0);
            clearConsole();
            if (ch == 'y' || ch == 'Y') {
                continue L1;
            } else if (ch == 'n' || ch == 'N') {
                homePage();
            }

        } while (true);
    }

    // ----------------- REMOVE ORDER ------------------
    public static void removeOrder(int index) {
        Orders [] tempObjectArray = new Orders[ordersObjectArray.length-1];
        
        
        for (int i = index; i < tempObjectArray.length; i++){
			tempObjectArray[i] = ordersObjectArray[i+1];
		}
		ordersObjectArray = tempObjectArray;
    }

    // ----------------- DELETE ORDER ---------------
    public static void deleteOrder() {
        do {
            System.out.println("==================== DELETE ORDER =================");
            System.out.print("\nEnter order ID : ");
            String orderId = input.next();

            int index = searchOrderByOrderId(orderId);

            if (index == -1) {
                System.out.println("\n\tInvalid order ID...");
            } else {
                printOrderDetails(index);
                System.out.print("\nDo you want to delete this order ? ");
                char ch = input.next().charAt(0);
                if (ch == 'y' || ch == 'Y') {
                    removeOrder(index);
                    System.out.println("\n\tOrder deleted...!");
                }
            }
            System.out.print("\nDo yo want to delete another order ? ");
            char ch = input.next().charAt(0);
            clearConsole();
            if (ch == 'Y' || ch == 'y') {
                continue;
            } else if (ch == 'n' || ch == 'N') {
                homePage();
            }
        } while (true);
    }
   
}
