package KW.CH01;

/**
 * Person is a class that represents a human being.
 *
 * @author Koffman and Wolfgang
 */
public class Person {
    // Constants

    /**
     * The age at which a person can vote
     */
    private static final int VOTE_AGE = 18;
    /**
     * The age at which a person is considered a senior citizen
     */
    private static final int SENIOR_AGE = 65;
    // Data Fields
    /**
     * The given name
     */
    private String givenName;
    /**
     * The family name
     */
    private String familyName;
    /**
     * The ID number
     */
    private final String IDNumber;
    /**
     * The birth year
     */
    private int birthYear = 1900;

    // Constructors
    /**
     * Construct a person with given values
     *
     * @param given The given name
     * @param family The family name
     * @param ID The ID number
     * @param birth The birth year
     */
    public Person(String given, String family, String ID, int birth) {
        givenName = given;
        familyName = family;
        IDNumber = ID;
        birthYear = birth;
    }

    /**
     * Construct a person with only an IDNumber specified.
     *
     * @param ID The ID number
     */
    public Person(String ID) {
        IDNumber = ID;
    }

    // Modifier Methods
    /**
     * Sets the givenName field.
     *
     * @param given The given name
     */
    public void setFirstName(String given) {
        givenName = given;
    }

    /**
     * Sets the familyName field.
     *
     * @param family The family name
     */
    public void setLastName(String family) {
        familyName = family;
    }

    /**
     * Sets the birthYear field.
     *
     * @param birthYear The year of birth
     */
    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    // Accessor Methods
    /**
     * Gets the person's given name.
     *
     * @return the given name as a String
     */
    public String getGivenName() {
        return givenName;
    }

    /**
     * Gets the person's family name.
     *
     * @return the family name as a String
     */
    public String getFamilyName() {
        return familyName;
    }

    /**
     * Gets the person's ID number.
     *
     * @return the ID number as a String
     */
    public String getIDNumber() {
        return IDNumber;
    }

    /**
     * Gets the person's year of birth.
     *
     * @return the year of birth as an int value
     */
    public int getBirthYear() {
        return birthYear;
    }

    // Other Methods
    /**
     * Calculates a person's age at this year's birthday.
     *
     * @param year The current year
     *
     * @return the year minus the birth year
     */
    public int age(int year) {
        return year - birthYear;
    }

    /**
     * Determines whether a person can vote.
     *
     * @param year The current year
     *
     * @return true if the person's age is greater than or equal to the voting
     * age
     */
    public boolean canVote(int year) {
        int theAge = age(year);

        return theAge >= VOTE_AGE;
    }

    /**
     * Determines whether a person is a senior citizen.
     *
     * @param year the current year
     *
     * @return true if person's age is greater than or equal to the age at which
     * a person is considered to be a senior citizen
     */
    public boolean isSenior(int year) {
        return age(year) >= SENIOR_AGE;
    }

    /**
     * Retrieves the information in a Person object.
     *
     * @return the object state as a string
     */
    @Override
    public String toString() {
        return "First name: " + givenName + "\n" + "Last name: "
                + familyName + "\n" + "ID number: " + IDNumber + "\n"
                + "Year of birth: " + birthYear + "\n";
    }

    /**
     * Compares two Person objects for equality.
     *
     * @param per The second Person object
     *
     * @return true if the Person objects have same ID number; false if they
     * don't
     */
    public boolean equals(Person per) {
        if (per == null) {
            return false;
        } else {
            return IDNumber.equals(per.IDNumber);
        }
    }

// Insert solution to programming exercise 2, section 1, chapter 1 here
    public int compareTo(Person per) {
        if (familyName.compareTo(per.familyName) == 0) {
            return givenName.compareTo(per.givenName);
        } else {
            return familyName.compareTo(per.familyName);
        }
    }

// Insert solution to programming exercise 3, section 1, chapter 1 here
    public void changeFamilyName(boolean justMarried, String newFamily) {
        if (justMarried) {
            familyName = newFamily;
        }
    }
}
