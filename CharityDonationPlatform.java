import java.util.Objects; // Importing for overriding equals and hashCode

// Class representing a Donor
class Donor {
    // Private fields (encapsulation)
    private String name; // Donor's name
    private double totalDonations; // Total donations made by the donor

    // Constructor to initialize donor's name and set donations to 0
    public Donor(String name) {
        this.name = name;
        this.totalDonations = 0.0;
    }

    // Getter for donor's name
    public String getName() {
        return name;
    }

    // Setter for donor's name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for total donations
    public double getTotalDonations() {
        return totalDonations;
    }

    // Method to add a donation amount to the donor's total
    public void addDonation(double amount) {
        this.totalDonations += amount;
    }

    // Overriding the toString() method to provide a string representation of a Donor
    @Override
    public String toString() {
        return "Donor{name='" + name + "', totalDonations=" + totalDonations + '}';
    }

    // Overriding equals() to compare two Donor objects based on their name and donations
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Check if the objects are the same instance
        if (obj == null || getClass() != obj.getClass()) return false; // Check type
        Donor donor = (Donor) obj;
        return Double.compare(donor.totalDonations, totalDonations) == 0 &&
               Objects.equals(name, donor.name);
    }

    // Overriding hashCode() to generate a unique hash for Donor objects
    @Override
    public int hashCode() {
        return Objects.hash(name, totalDonations);
    }

    // Method to display donor's information
    public void displayInfo() {
        System.out.println("Donor Name: " + name);
        System.out.println("Total Donations: $" + totalDonations);
    }
}

// Class representing a Charity
class Charity {
    // Private fields (encapsulation)
    private String name; // Charity's name
    private double totalReceived; // Total donations received by the charity

    // Constructor to initialize charity's name and donations received
    public Charity(String name) {
        this.name = name;
        this.totalReceived = 0.0;
    }

    // Getter for charity's name
    public String getName() {
        return name;
    }

    // Setter for charity's name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for total donations received
    public double getTotalReceived() {
        return totalReceived;
    }

    // Method to add a donation amount to the charity's total
    public void receiveDonation(double amount) {
        this.totalReceived += amount;
    }

    // Overriding the toString() method to provide a string representation of a Charity
    @Override
    public String toString() {
        return "Charity{name='" + name + "', totalReceived=" + totalReceived + '}';
    }

    // Overriding equals() to compare two Charity objects based on their name and donations received
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Check if the objects are the same instance
        if (obj == null || getClass() != obj.getClass()) return false; // Check type
        Charity charity = (Charity) obj;
        return Double.compare(charity.totalReceived, totalReceived) == 0 &&
               Objects.equals(name, charity.name);
    }

    // Overriding hashCode() to generate a unique hash for Charity objects
    @Override
    public int hashCode() {
        return Objects.hash(name, totalReceived);
    }

    // Method to display charity's information
    public void displayInfo() {
        System.out.println("Charity Name: " + name);
        System.out.println("Total Donations Received: $" + totalReceived);
    }
}

// Class representing a Donation (association between Donor and Charity)
class Donation {
    private Donor donor; // Donor who made the donation
    private Charity charity; // Charity that received the donation
    private double amount; // Amount of the donation

    // Constructor to initialize the donation and update donor and charity totals
    public Donation(Donor donor, Charity charity, double amount) {
        this.donor = donor;
        this.charity = charity;
        this.amount = amount;
        donor.addDonation(amount); // Update donor's total donations
        charity.receiveDonation(amount); // Update charity's total received
    }

    // Method to display the details of the donation
    public void displayDetails() {
        System.out.println("Donation Details:");
        System.out.println("Donor: " + donor.getName());
        System.out.println("Charity: " + charity.getName());
        System.out.println("Amount: $" + amount);
    }

    // Overriding the toString() method to provide a string representation of a Donation
    @Override
    public String toString() {
        return "Donation{donor=" + donor + ", charity=" + charity + ", amount=" + amount + '}';
    }
}

// Main class representing the charity donation platform
public class CharityDonationPlatform {
    public static void main(String[] args) {
        // Create donor objects
        Donor donor1 = new Donor("Alice");
        Donor donor2 = new Donor("Bob");

        // Create charity objects
        Charity charity1 = new Charity("Save the Children");
        Charity charity2 = new Charity("Red Cross");

        // Create donation objects
        Donation donation1 = new Donation(donor1, charity1, 100);
        Donation donation2 = new Donation(donor2, charity1, 150);
        Donation donation3 = new Donation(donor1, charity2, 200);

        // Display donation details
        System.out.println("\n--- Donation Details ---");
        donation1.displayDetails();
        donation2.displayDetails();
        donation3.displayDetails();

        // Display donor information
        System.out.println("\n--- Donor Information ---");
        donor1.displayInfo();
        donor2.displayInfo();

        // Display charity information
        System.out.println("\n--- Charity Information ---");
        charity1.displayInfo();
        charity2.displayInfo();

        // Compare donors by total donations
        System.out.println("\n--- Comparing Donors ---");
        if (donor1.getTotalDonations() > donor2.getTotalDonations()) {
            System.out.println(donor1.getName() + " donated more than " + donor2.getName());
        } else if (donor1.getTotalDonations() < donor2.getTotalDonations()) {
            System.out.println(donor2.getName() + " donated more than " + donor1.getName());
        } else {
            System.out.println(donor1.getName() + " and " + donor2.getName() + " donated the same amount.");
        }

        // Compare charities by total donations received
        System.out.println("\n--- Comparing Charities ---");
        if (charity1.getTotalReceived() > charity2.getTotalReceived()) {
            System.out.println(charity1.getName() + " received more donations than " + charity2.getName());
        } else if (charity1.getTotalReceived() < charity2.getTotalReceived()) {
            System.out.println(charity2.getName() + " received more donations than " + charity1.getName());
        } else {
            System.out.println(charity1.getName() + " and " + charity2.getName() + " received the same amount of donations.");
        }
    }
}
