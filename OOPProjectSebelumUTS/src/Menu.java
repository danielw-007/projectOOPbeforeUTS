
public class Menu {
	private String menuName;
	private int menuPrice;

	public Menu(String menuName, int menuPrice){
		this.menuName = menuName;
		this.menuPrice = menuPrice;
	}

	// setter getter
	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getMenuPrice() {
		return menuPrice;
	}

	public void setMenuPrice(int menuPrice) {
		this.menuPrice = menuPrice;
	}
	
}
