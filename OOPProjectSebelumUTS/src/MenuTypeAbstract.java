import java.util.Scanner;
import java.util.Vector;

public abstract class MenuTypeAbstract {
	Scanner sc = new Scanner(System.in);

	private String menuTypeName;
	private Vector<Menu> menu = new Vector<>();
	
	public MenuTypeAbstract(String menuTypeName, Vector<Menu> menu) {
		this.menuTypeName = menuTypeName;
		this.menu = menu;
	}

	// setter getter
	public Vector<Menu> getMenu() {
		return menu;
	}

	public void setMenu(Vector<Menu> menu) {
		this.menu = menu;
	}

	public String getMenuTypeName() {
		return menuTypeName;
	}
	
	public void setMenuTypeName(String menuTypeName) {
		this.menuTypeName = menuTypeName;
	}
	
	// Method
	public void addMenu(String menuName, int price) {
		menu.add(new Menu(menuName, price));
	}
	
	public void editMenu(String menuName) {
		if(menu.isEmpty()) {
			System.out.println("Menu with this type is empty!");
		} else {
			boolean found = false;
			for (int i = 0; i < menu.size(); i++) {
				if(menu.get(i).getMenuName().contentEquals(menuName)) {
					found = true;
					
					String newMenuName = "";
					do {
						System.out.print("Input new menu name: ");
						newMenuName = sc.nextLine();
					} while (newMenuName.isEmpty());
					
					int newMenuPrice = -1;
					do {
						System.out.print("Input new menu price: ");
						try {
							newMenuPrice = sc.nextInt();
						} catch (Exception e) {
							newMenuPrice = -1;
						}
						sc.nextLine();
					} while (newMenuPrice < 0);
					
					menu.get(i).setMenuName(newMenuName);
					menu.get(i).setMenuPrice(newMenuPrice);
					System.out.println("Menu successfully updated!");
				}
			}
			if(!found) {
				System.out.println("Menu not found!");
			}
		}
		System.out.print("Press Enter to continue...");
		sc.nextLine();
	}
	
	public void deleteMenu(String menuName) {
		if(menu.isEmpty()) {
			System.out.println("Menu with this type is empty!");
		} else {			
			boolean found = false;
			for (int i = 0; i < menu.size(); i++) {
				if(menu.get(i).getMenuName().contentEquals(menuName)) {
					found = true;
					menu.remove(i);
					System.out.println("Menu successfully deleted!");
				}
			}
			if(!found) {
				System.out.println("Menu not found!");
			}
		}
		System.out.print("Press Enter to continue...");
		sc.nextLine();
	}
	
	public void printMenuList(){
		System.out.println("====================================================");
		System.out.format("| %-48s |\n", getMenuTypeName());
		System.out.println("====================================================");
		System.out.println("| No. | Menu Name               | Menu Price       |");
		System.out.println("====================================================");
		if(menu.isEmpty()) {
			System.out.println("|                       Empty                      |");
		}else {			
			for (int i = 0; i < menu.size(); i++) {
				System.out.format("| %-3d | %-23s | Rp%-14d |\n", (i+1), menu.get(i).getMenuName(), menu.get(i).getMenuPrice());
			}
		}
		System.out.println("====================================================");
	}
}
