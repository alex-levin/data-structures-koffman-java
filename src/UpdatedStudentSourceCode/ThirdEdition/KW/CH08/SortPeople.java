package KW.CH08;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SortPeople {

// Insert solution to programming exercise 1, section 1, chapter 8 here

// Insert solution to programming exercise 2, section 1, chapter 8 here

// Insert solution to programming exercise 3, section 1, chapter 8 here
    
    /*<example chapter="8" number="3">*/
    /**
     * Method to sort a List of Person objects
     * @param peopleList The list of Person objects to be sorted
     * @post The list is sorted according the the lambda expression
     */
    public static void sortWithLambda(List<Person> peopleList) {
        peopleList.sort((p1, p2) -> p1.getBirthDay() - p2.getBirthDay());
    }
    /*</example>*/
}
