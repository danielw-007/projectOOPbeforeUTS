
import java.time.LocalDate;
import java.util.Random;
import java.util.Vector;

public class Order {
    private String orderId;
    private Vector<Menu> orderedMenu;
    private LocalDate orderDate;
    private int totalPrice;

    public Order(Vector<Menu> orderedMenu) {
    	Random rand = new Random();
    	int rand1 = rand.nextInt(10);
    	int rand2 = rand.nextInt(10);
    	int rand3 = rand.nextInt(10);
		this.orderId = "OD"+rand1+rand2+rand3;
		
		this.orderedMenu = orderedMenu;
		this.orderDate = LocalDate.now();
		this.totalPrice = calculateTotalPrice(orderedMenu);
	}
    
    // setter getter
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Vector<Menu> getOrderedMenu() {
		return orderedMenu;
	}

	public void setOrderedMenu(Vector<Menu> orderedMenu) {
		this.orderedMenu = orderedMenu;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	// Method
	private int calculateTotalPrice(Vector<Menu> orderedMenu) {
    	int totalPrice = 0;
    	for (Menu menu : orderedMenu) {
			totalPrice += menu.getMenuPrice();
		}
    	return totalPrice;
    }
    
}