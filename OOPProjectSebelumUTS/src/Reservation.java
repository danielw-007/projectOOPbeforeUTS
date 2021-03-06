import java.time.LocalDate;
import java.util.Vector;

public class Reservation extends Order {

	private LocalDate reservationDate;
    private String name;

    public Reservation(Vector<Menu> orderedMenu, LocalDate reservationDate, String name) {
		super(orderedMenu);
		this.reservationDate = reservationDate;
		this.name = name;
	}

    // setter getter
	public LocalDate getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(LocalDate reservationDate) {
		this.reservationDate = reservationDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}