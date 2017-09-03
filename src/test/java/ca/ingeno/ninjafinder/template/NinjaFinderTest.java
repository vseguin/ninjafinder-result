package ca.ingeno.ninjafinder.template;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import junit.framework.TestCase;

/*
 * Please provide your name and email...
 * 
 * Name : Vincent Seguin
 * Email : vincent.seguin.2@gmail.com
 * 
 * Please complete the methods below.
 * 
 * These methods will be called by a JUnit3/4 compatible test case suite to validate their correctness.
 * Use JUnit 4 artifacts if you prefer.
 * This file also provides a number of example test cases calling the method stubs.
 * 
 * You should leave your own unit tests in this file if you are using a TDD approach.
 * 
 * The goal here is not to absolutely get a good answer. We want to see your approach to problem solving.
 * We will ask you thereafter to explain to us why you choose to solve the problem the way you did.
 * So, make the code reflect how you actually write code in real life. Including naming, flow, structure, error handling, etc.
 * For instance, you can create as many sub methods as necessary. Also, your code should be able to handle just about
 * anything thrown at it without raising an Exception. If something is not stated in the problem, assume the worst and
 * code around it.
 * 
 * Please note that we have bundled everything in a single file for simplicity.
 * 
 * It is NOT permitted to use external libraries except hamcrest and assertj, which will be
 * included in the project. However, you can use anything else from the JDK.
 */
public class NinjaFinderTest extends TestCase
{
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    // Questions

    /*
     * Unique Values
     * 
     * Problem : Complete the isContainingOnlyUniqueValues method and return true if
     * all the input values are distinct.
     * 
     * Input : An array of N items.
     * 
     * Output : true if all the input values are distinct or if the array is empty
     * or if the array is null. False otherwise.
     * 
     * Example 1 : [1] returns true.
     * 
     * Example 2 : [1, 3, 1] returns false.
     */

    public boolean isContainingOnlyUniqueValues(int[] values)
    {
        if (values == null || values.length == 0) {
            return true;
        }

        Set<Integer> foundValues = new HashSet<>();

        for (int value : values) {
            if (foundValues.contains(value)) {
                return false;
            }
            foundValues.add(value);
        }

        return true;
    }

    public void testUniqueValues()
    {
        for (TestCase<int[], Boolean> testCase : givenUniqueValuesTestCases()) {
            assertEquals(testCase.getExpectedResult().booleanValue(),
                         isContainingOnlyUniqueValues(testCase.getValue()));
        }
    }

    private List<TestCase<int[], Boolean>> givenUniqueValuesTestCases()
    {
        return Arrays.asList(new TestCase<>(null, true),
                             new TestCase<>(new int[] {}, true),
                             new TestCase<>(new int[] { 0 }, true),
                             new TestCase<>(new int[] { 1 }, true),
                             new TestCase<>(new int[] { 1, 1 }, false),
                             new TestCase<>(new int[] { 1, 2 }, true),
                             new TestCase<>(new int[] { 0, 0, 0 }, false),
                             new TestCase<>(new int[] { 0, 1, 2, 4 }, true),
                             new TestCase<>(new int[] { -1000000, 1000000 }, true));
    }

    /*
     * Permutations
     * 
     * Problem : Complete the permutations method, generating all the possible
     * permutations from a set of integers provided as a String. For instance, given
     * the String "1 3", the method should return "1 3" and "3 1" as a list of
     * strings.
     * 
     * Input : A string of positive integers. The integers in the input strings will
     * be separated by one or more spaces. The input may contain duplicate elements.
     * 
     * Output : An instance of java.util.List<String>, containing a distinct set of
     * permutations. Each integer should be separated by a single space. The Strings
     * should be trimmed.
     * 
     * Example : "1 3" will return ["1 3", "3 1"].
     * 
     */

    public List<String> permutations(String value)
    {
        if (value == null || value.isEmpty()) {
            return new ArrayList<>();
        }

        int[] valuesAsIntegers = generateArrayOfIntegersFromString(value);

        if (valuesAsIntegers.length == 0) {
            return new ArrayList<>();
        }

        List<List<Integer>> permutations = Arrays.asList(new ArrayList<>());

        for (int i = 0; i < valuesAsIntegers.length; i++) {
            List<List<Integer>> current = new ArrayList<>();
            for (List<Integer> permutation : permutations) {
                for (int j = 0, largestSize = permutation.size() + 1; j < largestSize; j++) {
                    List<Integer> temporary = new ArrayList<>(permutation);
                    temporary.add(j, valuesAsIntegers[i]);
                    current.add(temporary);
                }
            }

            permutations = current;
        }

        return generateStringListFromMultiListOfIntegers(permutations);
    }

    public void testPermutations()
    {
        for (TestCase<String, List<String>> testCase : givenPermutationsTestCases()) {
            List<String> permutations = permutations(testCase.getValue());

            assertNotNull(permutations);
            assertEquals(permutations.size(), testCase.getExpectedResult().size());

            permutations.forEach(permutation -> {
                assertTrue(testCase.getExpectedResult().contains(permutation));
            });
        }
    }

    private List<TestCase<String, List<String>>> givenPermutationsTestCases()
    {
        return Arrays.asList(new TestCase<>(null, new ArrayList<>()),
                             new TestCase<>("", new ArrayList<>()),
                             new TestCase<>("null", new ArrayList<>()),
                             new TestCase<>("0", Arrays.asList("0")),
                             new TestCase<>("1", Arrays.asList("1")),
                             new TestCase<>("isthisreallife", new ArrayList<>()),
                             new TestCase<>("1.234", new ArrayList<>()),
                             new TestCase<>("isthisreallife 1", Arrays.asList("1")),
                             new TestCase<>("1 2", Arrays.asList("2 1", "1 2")),
                             new TestCase<>("1 2 3",
                                            Arrays.asList("1 2 3", "1 3 2", "3 2 1", "3 1 2", "2 1 3", "2 3 1")),
                             new TestCase<>("1     2", Arrays.asList("2 1", "1 2")),
                             new TestCase<>("1 test 2", Arrays.asList("2 1", "1 2")),
                             new TestCase<>("1 test 2 test test", Arrays.asList("2 1", "1 2")));
    }

    /*
     * Reverse
     * 
     * Problem : Complete the reverse method and return the provided integers in
     * reverse order.
     * 
     * Input : A string of positive integers. The integers in the input string will
     * be separated by one or more spaces. The input may contain duplicate elements.
     * 
     * Output : The reverse version of the input string. Each integer should be
     * separated by a single space. The string should be trimmed.
     * 
     * Example : "1   2 3  " should return "3 2 1"
     */
    public String reverse(String value)
    {
        if (value == null || value.isEmpty()) {
            return "";
        }

        int[] valuesAsIntegers = generateArrayOfIntegersFromString(value);

        if (valuesAsIntegers.length == 0) {
            return "";
        }

        List<Integer> integerList = IntStream.of(valuesAsIntegers).boxed().collect(Collectors.toList());

        Collections.sort(integerList);
        Collections.reverse(integerList);

        return formatIntegerListToString(integerList);
    }

    public void testReverse()
    {
        for (TestCase<String, String> testCase : givenReverseTestCases()) {
            assertEquals(testCase.getExpectedResult(), reverse(testCase.getValue()));
        }
    }

    private List<TestCase<String, String>> givenReverseTestCases()
    {
        return Arrays.asList(new TestCase<>(null, ""),
                             new TestCase<>("", ""),
                             new TestCase<>("null", ""),
                             new TestCase<>("0", "0"),
                             new TestCase<>("1", "1"),
                             new TestCase<>("1            ", "1"),
                             new TestCase<>("isthisreallife", ""),
                             new TestCase<>("1.234", ""),
                             new TestCase<>("isthisreallife 1", "1"),
                             new TestCase<>("1 2", "2 1"),
                             new TestCase<>("1 2 3", "3 2 1"),
                             new TestCase<>("1 2 3 1 4 3", "4 3 3 2 1 1"),
                             new TestCase<>("1     2", "2 1"),
                             new TestCase<>("1 test 2", "2 1"),
                             new TestCase<>("1 test 2 test test", "2 1"));
    }

    /*
     * Prime Palindrome
     * 
     * Problem : Complete the method primePalindrome to find the biggest prime
     * number, which is also a palindrome, within the range of 1 - 1000. This being
     * a constant, a good coder would just return the hard coded value. But, in the
     * spirit of this test, please provide a complete solution to programmatically
     * find the answer.
     * 
     * Input : none.
     * 
     * Output : An integer that meets the problem statement.
     * 
     * Example : No example.
     */

    public int primePalindrome()
    {
        return computeLargestPrimePalindrome(1000);
    }

    public void testPrimePalindrome()
    {
        int primePalindrome = primePalindrome();
        
        assertTrue(primePalindrome >= 1);
        assertEquals(929, primePalindrome);
    }
    
    private int computeLargestPrimePalindrome(int limit) {
        int result = -1;
        
        for (int i = limit; i >= 1; i--) {
            if (isPrime(i) && isPalindrome(i)) {
                result = i;
                break;
            }
        }
        
        return result;
    }

    private int[] generateArrayOfIntegersFromString(String value)
    {
        return Arrays.stream(value.split(" ")).filter(v -> isInteger(v)).mapToInt(Integer::parseInt).toArray();
    }

    private List<String> generateStringListFromMultiListOfIntegers(List<List<Integer>> integers)
    {
        return integers.stream()
                       .map(integerSubList -> formatIntegerListToString(integerSubList))
                       .collect(Collectors.toList());
    }

    private String formatIntegerListToString(List<Integer> integerList)
    {
        return integerList.toString().replace(",", "").replace("[", "").replace("]", "").trim();
    }

    private static boolean isInteger(String string)
    {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    private static boolean isPalindrome(int number)
    {
        int palindrome = number;
        int reverse = 0;

        while (palindrome != 0) {
            int remainder = palindrome % 10;
            reverse = reverse * 10 + remainder;
            palindrome = palindrome / 10;
        }

        if (number == reverse) {
            return true;
        }
        
        return false;
    }

    private static boolean isPrime(int number)
    {
        if (number % 2 == 0) {
            return false;
        }

        for (int i = 2; 2 * i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        
        return true;
    }

    private class TestCase<T, U>
    {
        private T value;
        private U expectedResult;

        public TestCase(T value, U expectedResult)
        {
            setValue(value);
            setExpectedResult(expectedResult);
        }

        public T getValue()
        {
            return value;
        }

        public void setValue(T value)
        {
            this.value = value;
        }

        public U getExpectedResult()
        {
            return expectedResult;
        }

        public void setExpectedResult(U expectedResult)
        {
            this.expectedResult = expectedResult;
        }
    }
}
