package KW.CH07;

public class Person {

    private final String givenName;
    private final String familyName;

    public Person(String givenName, String familyName) {
        this.givenName = givenName;
        this.familyName = familyName;
    }

    public String getFirstName() {
        return givenName;
    }

    public String getLastName() {
        return familyName;
    }

// Insert solution to programming exercise 2, section 5, chapter 7 here
}
