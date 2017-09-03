package ca.ingeno.ninjafinder.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

@Service
public class NinjaFinderService
{
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

    public int primePalindrome()
    {
        return computeLargestPrimePalindrome(1000);
    }

    private int computeLargestPrimePalindrome(int limit)
    {
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
}
