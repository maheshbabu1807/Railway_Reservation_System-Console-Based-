import java.util.*;

// Train class
class Train {
    int trainNumber;
    String trainName;
    String source;
    String destination;
    int seatsAvailable;

    public Train(int trainNumber, String trainName, String source, String destination, int seatsAvailable) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.source = source;
        this.destination = destination;
        this.seatsAvailable = seatsAvailable;
    }

    public void displayInfo() {
        System.out.println(trainNumber + " - " + trainName + " (" + source + " to " + destination + ") | Seats Available: " + seatsAvailable);
    }
}

// Booking class
class Booking {
    static int bookingIdCounter = 1;
    int bookingId;
    String passengerName;
    int trainNumber;

    public Booking(String passengerName, int trainNumber) {
        this.bookingId = bookingIdCounter++;
        this.passengerName = passengerName;
        this.trainNumber = trainNumber;
    }

    public void displayBooking() {
        System.out.println("Booking ID: " + bookingId + " | Name: " + passengerName + " | Train Number: " + trainNumber);
    }
}

// Main class
public class RailwayReservationSystem {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Train> trains = new ArrayList<>();
    static ArrayList<Booking> bookings = new ArrayList<>();

    public static void main(String[] args) {
        initializeTrains();
        int choice;

        do {
            System.out.println("\n=== Railway Reservation System ===");
            System.out.println("1. View Trains");
            System.out.println("2. Book Ticket");
            System.out.println("3. Cancel Ticket");
            System.out.println("4. View Bookings");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Clear input buffer

            switch (choice) {
                case 1:
                    viewTrains();
                    break;
                case 2:
                    bookTicket();
                    break;
                case 3:
                    cancelTicket();
                    break;
                case 4:
                    viewBookings();
                    break;
                case 5:
                    System.out.println("Thank you for using the Railway Reservation System.");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 5);
    }

    static void initializeTrains() {
        trains.add(new Train(101, "Express 1", "Hyderabad", "Tirupati", 5));
        trains.add(new Train(102, "Express 2", "Chennai", "Delhi", 3));
        trains.add(new Train(103, "Express 3", "Mumbai", "Pune", 2));
    }

    static void viewTrains() {
        System.out.println("\nAvailable Trains:");
        for (Train t : trains) {
            t.displayInfo();
        }
    }

    static void bookTicket() {
        System.out.print("\nEnter your name: ");
        String name = scanner.nextLine();

        viewTrains();
        System.out.print("Enter train number to book: ");
        int trainNumber = scanner.nextInt();

        for (Train t : trains) {
            if (t.trainNumber == trainNumber) {
                if (t.seatsAvailable > 0) {
                    t.seatsAvailable--;
                    Booking b = new Booking(name, trainNumber);
                    bookings.add(b);
                    System.out.println("Ticket booked successfully! Booking ID: " + b.bookingId);
                    return;
                } else {
                    System.out.println("No seats available in this train.");
                    return;
                }
            }
        }
        System.out.println("Train not found.");
    }

    static void cancelTicket() {
        System.out.print("\nEnter your booking ID to cancel: ");
        int id = scanner.nextInt();

        Iterator<Booking> iterator = bookings.iterator();
        while (iterator.hasNext()) {
            Booking b = iterator.next();
            if (b.bookingId == id) {
                iterator.remove();
                for (Train t : trains) {
                    if (t.trainNumber == b.trainNumber) {
                        t.seatsAvailable++;
                        break;
                    }
                }
                System.out.println("Booking cancelled successfully.");
                return;
            }
        }
        System.out.println("Booking ID not found.");
    }

    static void viewBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        System.out.println("\nYour Bookings:");
        for (Booking b : bookings) {
            b.displayBooking();
        }
    }
}
