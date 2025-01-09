import java.util.*; // Importing for collections and utility methods
// Abstract class representing a Person (common parent for Donor and Charity)
abstract class Person {
    private String name;
    // Constructor
    public Person(String name) {
        this.name = name;
    }
    // Getter for name
    public String getName() {
        return name;
    }
    // Setter for name
    public void setName(String name) {
        this.name = name;
    }
    // Abstract method to display details (to be implemented by subclasses)
    public abstract void displayInfo();
    @Override
    public String toString() {
        return "Person{name='" + name + "'}";
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return Objects.equals(name, person.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
// Class representing a Donor (inherits from Person)
class Donor extends Person {
    private double totalDonations;
    // Constructor
    public Donor(String name) {
        super(name);
        this.totalDonations = 0.0;
    }
    // Getter for total donations
    public double getTotalDonations() {
        return totalDonations;
    }
    // Method to add a donation
    public void addDonation(double amount) {
        this.totalDonations += amount;
    }
    // Override displayInfo() to show donor details
    @Override
    public void displayInfo() {
        System.out.println("Donor Name: " + getName());
        System.out.println("Total Donations: $" + totalDonations);
    }
    @Override
    public String toString() {
        return "Donor{name='" + getName() + "', totalDonations=" + totalDonations + '}';
    }
}
// Class representing a Charity (inherits from Person)
class Charity extends Person {
    private double totalReceived;
    // Constructor
    public Charity(String name) {
        super(name);
        this.totalReceived = 0.0;
    }
    // Getter for total donations received
    public double getTotalReceived() {
        return totalReceived;
    }
    // Method to add a donation amount
    public void receiveDonation(double amount) {
        this.totalReceived += amount;
    }
    // Override displayInfo() to show charity details
    @Override
    public void displayInfo() {
        System.out.println("Charity Name: " + getName());
        System.out.println("Total Donations Received: $" + totalReceived);
    }
    @Override
    public String toString() {
        return "Charity{name='" + getName() + "', totalReceived=" + totalReceived + '}';
    }
}
// Class representing a Donation
class Donation {
    private Donor donor;
    private Charity charity;
    private double amount;

    // Constructor
    public Donation(Donor donor, Charity charity, double amount) {
        this.donor = donor;
        this.charity = charity;
        this.amount = amount;
        donor.addDonation(amount); // Update donor's total donations
        charity.receiveDonation(amount); // Update charity's total received
    }
    // Getter methods
    public Donor getDonor() {
        return donor;
    }
    public Charity getCharity() {
        return charity;
    }
    public double getAmount() {
        return amount;
    }
    // Override toString() to display donation details
    @Override
    public String toString() {
        return "Donation{donor=" + donor.getName() + ", charity=" + charity.getName() + ", amount=" + amount + '}';
    }
}
// Class representing the Charity Donation Platform
public class CharityDonationPlatform {
    private List<Donor> donors;        // List of donors
    private List<Charity> charities;  // List of charities
    private List<Donation> donations; // List of donations
    // Constructor to initialize the platform
    public CharityDonationPlatform() {
        donors = new ArrayList<>();
        charities = new ArrayList<>();
        donations = new ArrayList<>();
    }
    // Add a donor
    public void addDonor(Donor donor) {
        donors.add(donor);
    }
    // Add a charity
    public void addCharity(Charity charity) {
        charities.add(charity);
    }
    // Add a donation
    public void addDonation(Donation donation) {
        donations.add(donation);
    }

    // Display all donors
    public void displayDonors() {
        System.out.println("\n--- Donors ---");
        for (Donor donor : donors) {
            donor.displayInfo();
        }
    }
    // Display all charities
    public void displayCharities() {
        System.out.println("\n--- Charities ---");
        for (Charity charity : charities) {
            charity.displayInfo();
        }
    }
    // Display all donations
    public void displayDonations() {
        System.out.println("\n--- Donations ---");
        for (Donation donation : donations) {
            System.out.println(donation);
        }
    }
    // Filter donors by minimum donations
    public void filterDonorsByDonation(double minDonation) {
        System.out.println("\n--- Donors with Donations >= $" + minDonation + " ---");
        for (Donor donor : donors) {
            if (donor.getTotalDonations() >= minDonation) {
                donor.displayInfo();
            }
        }
    }
    // Sort charities by total donations received
    public void sortCharitiesByDonations() {
        charities.sort(Comparator.comparingDouble(Charity::getTotalReceived).reversed());
        System.out.println("\n--- Charities Sorted by Donations ---");
        displayCharities();
    }
    // Search for a donor by name
    public Donor searchDonor(String name) {
        for (Donor donor : donors) {
            if (donor.getName().equalsIgnoreCase(name)) {
                return donor;
            }
        }
        return null;
    }
    // Main method
    public static void main(String[] args) {
        // Initialize the platform
        CharityDonationPlatform platform = new CharityDonationPlatform();

        // Create donors and charities
        Donor donor1 = new Donor("Alice");
        Donor donor2 = new Donor("Bob");
        platform.addDonor(donor1);
        platform.addDonor(donor2);

        Charity charity1 = new Charity("Save the Children");
        Charity charity2 = new Charity("Red Cross");
        platform.addCharity(charity1);
        platform.addCharity(charity2);

        // Create donations
        Donation donation1 = new Donation(donor1, charity1, 100);
        Donation donation2 = new Donation(donor2, charity1, 150);
        Donation donation3 = new Donation(donor1, charity2, 200);
        platform.addDonation(donation1);
        platform.addDonation(donation2);
        platform.addDonation(donation3);

        // Display data
        platform.displayDonors();
        platform.displayCharities();
        platform.displayDonations();

        // Filter and sort
        platform.filterDonorsByDonation(150);
        platform.sortCharitiesByDonations();

        // Search for a donor
        Donor searchResult = platform.searchDonor("Alice");
        if (searchResult != null) {
            System.out.println("\n--- Search Result ---");
            searchResult.displayInfo();
        } else {
            System.out.println("\nDonor not found.");
        }
    }
}
