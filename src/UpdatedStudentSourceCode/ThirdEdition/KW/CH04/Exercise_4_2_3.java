package KW.CH04;

public class Exercise_4_2_3 {

// Insert solution to programming exercise 3, section 2, chapter 4 here

    private static class TestCase {

        private final String s;
        private final boolean result;

        public TestCase(String s, boolean result) {
            this.s = s;
            this.result = result;
        }
    }

    private static boolean doTest(TestCase t) {
        return t.result == isPalindrome(t.s);
    }
    private static final TestCase[] tests = {
        new TestCase("kayak", true),
        new TestCase("I saw I was I", true),
        new TestCase("Able was I ere I saw Elba", true),
        new TestCase("x", true),
        new TestCase("xy", false),
        new TestCase("xyx", true),
        new TestCase("xxy", false),
        new TestCase("abcbca", false)
    };

    public static void main(String[] args) {
        boolean allPass = true;
        for (int i = 0; i < tests.length; i++) {
            boolean pass = doTest(tests[i]);
            if (pass) {
                System.out.println("Test " + i + " passed");
            } else {
                System.out.println("Test " + i + "       FAILED");
            }
            allPass = allPass && pass;
        }
        if (allPass) {
            System.out.println("All tests passed");
        }
    }
}
