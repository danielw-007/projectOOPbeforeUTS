import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.Vector;

public class Main {

	Scanner sc = new Scanner(System.in);
	Vector<Order> orderList = new Vector<>();
	Vector<Reservation> reservationList = new Vector<>();
	Western westernMenu = new Western();
	Eastern easternMenu = new Eastern();
	Beverage beverageMenu = new Beverage();
	
	private void clearConsole() {
		for (int i = 0; i < 30; i++) {
			System.out.println();
		}
	}
	
	private boolean checkIfMenuIsEmpty() {
		if(westernMenu.getMenu().isEmpty() && easternMenu.getMenu().isEmpty() && beverageMenu.getMenu().isEmpty()) {
			return true;
		}
		return false;
	}
	
	private void addOrder() {
		Vector<Menu> orderedMenu = new Vector<>();
		
		while(true) {
			String menuType = "";
			do {
				System.out.print("Choose menu type [Western | Eastern | Beverage] (case insensitive) ('stop' to stop adding menu): ");
				menuType = sc.nextLine();
			} while (!menuType.equalsIgnoreCase("Western") && !menuType.equalsIgnoreCase("Eastern") && !menuType.equalsIgnoreCase("Beverage") && !menuType.equalsIgnoreCase("stop"));
						
			// Loop stop and add Vector menu to orderList
			if(menuType.equalsIgnoreCase("stop")) {
				if(orderedMenu.isEmpty()) {
					System.out.println("Must order at least 1 menu");
					continue;
				}else {
					orderList.add(new Order(orderedMenu));
					System.out.println("Order successfully added!");
					System.out.print("Press Enter to continue...");
					sc.nextLine();
					break;					
				}
			}
			
			// Print Menu Based on Type
			int menuCtr = 0;
			if(menuType.equalsIgnoreCase("Western")) {
				if(westernMenu.getMenu().isEmpty()) {
					System.out.println("Menu with western type is currently empty, try another type");
					System.out.print("Press Enter to continue...");
					sc.nextLine();
					continue;
				}
				westernMenu.printMenuList();
				menuCtr = westernMenu.getMenu().size();
			}else if(menuType.equalsIgnoreCase("Eastern")) {
				if(easternMenu.getMenu().isEmpty()) {
					System.out.println("Menu with eastern type is currently empty, try another type");
					System.out.print("Press Enter to continue...");
					sc.nextLine();
					continue;
				}
				easternMenu.printMenuList();
				menuCtr = easternMenu.getMenu().size();
			}else if(menuType.equalsIgnoreCase("Beverage")) {
				if(beverageMenu.getMenu().isEmpty()) {
					System.out.println("Menu with beverage type is currently empty, try another type");
					System.out.print("Press Enter to continue...");
					sc.nextLine();
					continue;
				}
				beverageMenu.printMenuList();
				menuCtr = beverageMenu.getMenu().size();
			}
			
			int menuChoose = -1;
			do {
				System.out.print("Choose menu [1-" + menuCtr + "]: ");
				try {
					menuChoose = sc.nextInt();
				} catch (Exception e) {
					menuChoose = -1;
				}
				sc.nextLine();
			} while (menuChoose < 1 || menuChoose > menuCtr);
			
			// Add menu to the temporary Vector
			if(menuType.equalsIgnoreCase("Western")) {
				orderedMenu.add(westernMenu.getMenu().get(menuChoose-1));
			}else if(menuType.equalsIgnoreCase("Eastern")) {
				orderedMenu.add(easternMenu.getMenu().get(menuChoose-1));
			}else if(menuType.equalsIgnoreCase("Beverage")) {
				orderedMenu.add(beverageMenu.getMenu().get(menuChoose-1));
			}
		}
	}
	
	private void viewOrderList() {
		System.out.println("=====================================================================");
		System.out.println("| OrderID | Order Date | Ordered Menu                | Menu Price   |");
		System.out.println("=====================================================================");
		if(orderList.isEmpty()) {
			System.out.println("|                               Empty                               |");
			System.out.println("=====================================================================");
		} else {
			for (Order order : orderList) {
				boolean first = true;
				for (Menu orderedMenu : order.getOrderedMenu()) {
					if(first) {
						System.out.format("| %-7s | %s | %-27s | Rp%-10d |\n", order.getOrderId(), order.getOrderDate(), orderedMenu.getMenuName(), orderedMenu.getMenuPrice());
						first = false;
					} else {
						System.out.format("|         |            | %-27s | Rp%-10d |\n", orderedMenu.getMenuName(), orderedMenu.getMenuPrice());					
					}
				}
				System.out.println("=====================================================================");
				System.out.format("| Total Price                                        | Rp%-10d |\n", order.getTotalPrice());
				System.out.println("=====================================================================");
			}
		}
		
		System.out.print("Press Enter to continue...");
		sc.nextLine();
	}
	
	private void addMenu() {
		String menuType = "";
		do {
			System.out.print("Choose menu type [Western | Eastern | Beverage] (case insensitive): ");
			menuType = sc.nextLine();
		} while (!menuType.equalsIgnoreCase("Western") && !menuType.equalsIgnoreCase("Eastern") && !menuType.equalsIgnoreCase("Beverage"));
		
		String menuName = "";
		do {
			System.out.print("Input menu name: ");
			menuName = sc.nextLine();
		} while (menuName.isEmpty());
		
		int menuPrice = -1;
		do {
			System.out.print("Input menu price: ");
			try {
				menuPrice = sc.nextInt();
			} catch (Exception e) {
				menuPrice = -1;
			}
			sc.nextLine();
		} while (menuPrice < 0);
		
		if(menuType.equalsIgnoreCase("Western")) {
			westernMenu.addMenu(menuName, menuPrice);
		}else if(menuType.equalsIgnoreCase("Eastern")) {
			easternMenu.addMenu(menuName, menuPrice);
		}else if(menuType.equalsIgnoreCase("Beverage")) {
			beverageMenu.addMenu(menuName, menuPrice);
		}
		
		System.out.println("Menu successfully added!");
		System.out.print("Press Enter to continue...");
		sc.nextLine();
	}
	
	private void viewMenuList() {
		westernMenu.printMenuList();
		System.out.println();
		easternMenu.printMenuList();
		System.out.println();
		beverageMenu.printMenuList();
	}
	
	private void editMenu() {
		viewMenuList();
		String menuType = "";
		do {
			System.out.print("Choose menu type to edit from [Western | Eastern | Beverage] (case insensitive): ");
			menuType = sc.nextLine();
			
			if(menuType.equalsIgnoreCase("Western")) {
				if(westernMenu.getMenu().isEmpty()) {
					System.out.println("Menu with western type is currently empty, try another type");
					System.out.print("Press Enter to continue...");
					sc.nextLine();
					menuType = "";
				}
			}else if(menuType.equalsIgnoreCase("Eastern")) {
				if(easternMenu.getMenu().isEmpty()) {
					System.out.println("Menu with eastern type is currently empty, try another type");
					System.out.print("Press Enter to continue...");
					sc.nextLine();
					menuType = "";
				}
			}else if(menuType.equalsIgnoreCase("Beverage")) {
				if(beverageMenu.getMenu().isEmpty()) {
					System.out.println("Menu with beverage type is currently empty, try another type");
					System.out.print("Press Enter to continue...");
					sc.nextLine();
					menuType = "";
				}
			}
			
		} while (!menuType.equalsIgnoreCase("Western") && !menuType.equalsIgnoreCase("Eastern") && !menuType.equalsIgnoreCase("Beverage"));
		
		
		String menuName = "";
		do {
			System.out.print("Input menu name to edit: ");
			menuName = sc.nextLine();
		} while (menuName.isEmpty());
		
		if(menuType.equalsIgnoreCase("Western")) {
			westernMenu.editMenu(menuName);
		}else if(menuType.equalsIgnoreCase("Eastern")) {
			easternMenu.editMenu(menuName);
		}else if(menuType.equalsIgnoreCase("Beverage")) {
			beverageMenu.editMenu(menuName);
		}
	}
	
	private void deleteMenu() {
		viewMenuList();
		String menuType = "";
		do {
			System.out.print("Choose menu type to delete from [Western | Eastern | Beverage] (case insensitive): ");
			menuType = sc.nextLine();
			
			if(menuType.equalsIgnoreCase("Western")) {
				if(westernMenu.getMenu().isEmpty()) {
					System.out.println("Menu with western type is currently empty, try another type");
					System.out.print("Press Enter to continue...");
					sc.nextLine();
					menuType = "";
				}
			}else if(menuType.equalsIgnoreCase("Eastern")) {
				if(easternMenu.getMenu().isEmpty()) {
					System.out.println("Menu with eastern type is currently empty, try another type");
					System.out.print("Press Enter to continue...");
					sc.nextLine();
					menuType = "";
				}
			}else if(menuType.equalsIgnoreCase("Beverage")) {
				if(beverageMenu.getMenu().isEmpty()) {
					System.out.println("Menu with beverage type is currently empty, try another type");
					System.out.print("Press Enter to continue...");
					sc.nextLine();
					menuType = "";
				}
			}
			
		} while (!menuType.equalsIgnoreCase("Western") && !menuType.equalsIgnoreCase("Eastern") && !menuType.equalsIgnoreCase("Beverage"));
		
		String menuName = "";
		do {
			System.out.print("Input menu name to delete: ");
			menuName = sc.nextLine();
		} while (menuName.isEmpty());
		
		if(menuType.equalsIgnoreCase("Western")) {
			westernMenu.deleteMenu(menuName);
		}else if(menuType.equalsIgnoreCase("Eastern")) {
			easternMenu.deleteMenu(menuName);
		}else if(menuType.equalsIgnoreCase("Beverage")) {
			beverageMenu.deleteMenu(menuName);
		}
	}
	
	private void addReservation() {
		String name = "";
		do {
			System.out.print("Input reservation name: ");
			name = sc.nextLine();
		} while (name.isEmpty());
		
		String reserveDate = "";
		do {
			System.out.print("Input reservation date in yyyy-mm-dd format: ");
			reserveDate = sc.nextLine();
		} while (reserveDate.isEmpty());
		
		LocalDate local = LocalDate.parse(reserveDate);
			
		Vector<Menu> orderedMenu = new Vector<>();
		
		while(true) {
			String menuType = "";
			do {
				System.out.print("Choose menu type [Western | Eastern | Beverage] (case insensitive) ('stop' to stop adding menu): ");
				menuType = sc.nextLine();
			} while (!menuType.equalsIgnoreCase("Western") && !menuType.equalsIgnoreCase("Eastern") && !menuType.equalsIgnoreCase("Beverage") && !menuType.equalsIgnoreCase("stop"));
						
			// Loop stop and add Vector menu to reservationList
			if(menuType.equalsIgnoreCase("stop")) {
				if(orderedMenu.isEmpty()) {
					System.out.println("Must order at least 1 menu");
					continue;
				}else {
					reservationList.add(new Reservation(orderedMenu, local, name));
					System.out.println("Reservation successfully added!");
					System.out.print("Press Enter to continue...");
					sc.nextLine();
					break;					
				}
			}
			
			// Print Menu Based on Type
			int menuCtr = 0;
			if(menuType.equalsIgnoreCase("Western")) {
				if(westernMenu.getMenu().isEmpty()) {
					System.out.println("Menu with western type is currently empty, try another type");
					System.out.print("Press Enter to continue...");
					sc.nextLine();
					continue;
				}
				westernMenu.printMenuList();
				menuCtr = westernMenu.getMenu().size();
			}else if(menuType.equalsIgnoreCase("Eastern")) {
				if(easternMenu.getMenu().isEmpty()) {
					System.out.println("Menu with eastern type is currently empty, try another type");
					System.out.print("Press Enter to continue...");
					sc.nextLine();
					continue;
				}
				easternMenu.printMenuList();
				menuCtr = easternMenu.getMenu().size();
			}else if(menuType.equalsIgnoreCase("Beverage")) {
				if(beverageMenu.getMenu().isEmpty()) {
					System.out.println("Menu with beverage type is currently empty, try another type");
					System.out.print("Press Enter to continue...");
					sc.nextLine();
					continue;
				}
				beverageMenu.printMenuList();
				menuCtr = beverageMenu.getMenu().size();
			}
			
			int menuChoose = -1;
			do {
				System.out.print("Choose menu [1-" + menuCtr + "]: ");
				try {
					menuChoose = sc.nextInt();
				} catch (Exception e) {
					menuChoose = -1;
				}
				sc.nextLine();
			} while (menuChoose < 1 || menuChoose > menuCtr);
			
			// Add menu to the temporary Vector
			if(menuType.equalsIgnoreCase("Western")) {
				orderedMenu.add(westernMenu.getMenu().get(menuChoose-1));
			}else if(menuType.equalsIgnoreCase("Eastern")) {
				orderedMenu.add(easternMenu.getMenu().get(menuChoose-1));
			}else if(menuType.equalsIgnoreCase("Beverage")) {
				orderedMenu.add(beverageMenu.getMenu().get(menuChoose-1));
			}
		}
	}
	
	private void viewReservationList() {
		System.out.println("==================================================================================================");
		System.out.println("| OrderID | Reservation Name     | Reservation Date | Ordered Menu                | Menu Price   |");
		System.out.println("==================================================================================================");
		if(reservationList.isEmpty()) {
			System.out.println("|                                             Empty                                              |");
			System.out.println("==================================================================================================");
		} else {
			for (Reservation reservation : reservationList) {
				boolean first = true;
				for (Menu orderedMenu : reservation.getOrderedMenu()) {
					if(first) {
						System.out.format("| %-7s | %-20s | %-16s | %-27s | Rp%-10d |\n", reservation.getOrderId(), reservation.getName(), reservation.getReservationDate(), orderedMenu.getMenuName(), orderedMenu.getMenuPrice());
						first = false;
					} else {
						System.out.format("|         |                      |                  | %-27s | Rp%-10d |\n", orderedMenu.getMenuName(), orderedMenu.getMenuPrice());					
					}
				}
				System.out.println("==================================================================================================");
				System.out.format("| Total Price                                                                     | Rp%-10d |\n", reservation.getTotalPrice());
				System.out.println("==================================================================================================");
			}
		}
		
		System.out.print("Press Enter to continue...");
		sc.nextLine();
	}
	
	public Main() {
		int menu = -1;
		do {
			clearConsole();
			System.out.println("1. Add Order");
			System.out.println("2. View Order List");
			System.out.println("3. Add Menu");
			System.out.println("4. View Menu List");
			System.out.println("5. Edit Menu");
			System.out.println("6. Delete Menu");
			System.out.println("7. Add Reservation");
			System.out.println("8. View Reservation List");
			
			System.out.println("9. Exit");
			System.out.print(">> ");
			do {
				try {
					menu = sc.nextInt();
				} catch (Exception e) {
					menu = -1;
				}
				sc.nextLine();
			} while (menu < 1 || menu > 9);
			
			switch (menu) {
			case 1:
				if(checkIfMenuIsEmpty()) {
					System.out.println("Please add at least 1 menu before add an order");
					System.out.print("Press Enter to continue...");
					sc.nextLine();
				} else {
					addOrder();					
				}
				break;
			case 2:
				viewOrderList();
				break;
			case 3:
				addMenu();
				break;
			case 4:
				viewMenuList();
				System.out.print("Press Enter to continue...");
				sc.nextLine();
				break;
			case 5:
				if(checkIfMenuIsEmpty()) {
					System.out.println("Please add at least 1 menu before edit a menu");
					System.out.print("Press Enter to continue...");
					sc.nextLine();
				} else {
					editMenu();					
				}
				break;
			case 6:
				if(checkIfMenuIsEmpty()) {
					System.out.println("Please add at least 1 menu before delete a menu");
					System.out.print("Press Enter to continue...");
					sc.nextLine();
				} else {
					deleteMenu();					
				}
				break;
			case 7:
				if(checkIfMenuIsEmpty()) {
					System.out.println("Please add at least 1 menu before add a reservation");
					System.out.print("Press Enter to continue...");
					sc.nextLine();
				} else {
					addReservation();					
				}
				break;
			case 8:
				viewReservationList();
				break;
			case 9:
				System.out.println("Thankyou for using our program!");
				break;
			}
			
		} while (menu != 9);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

}
