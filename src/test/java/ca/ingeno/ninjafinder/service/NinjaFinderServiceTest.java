package ca.ingeno.ninjafinder.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ca.ingeno.ninjafinder.utils.ServiceTestCase;
import junit.framework.TestCase;

public class NinjaFinderServiceTest extends TestCase
{
    private NinjaFinderService service;

    protected void setUp() throws Exception
    {
        super.setUp();

        service = new NinjaFinderService();
    }

    public void testUniqueValues()
    {
        for (ServiceTestCase<int[], Boolean> ServiceTestCase : givenUniqueValuesServiceTestCases()) {
            assertEquals(ServiceTestCase.getExpectedResult().booleanValue(),
                         service.isContainingOnlyUniqueValues(ServiceTestCase.getValue()));
        }
    }

    private List<ServiceTestCase<int[], Boolean>> givenUniqueValuesServiceTestCases()
    {
        return Arrays.asList(new ServiceTestCase<>(null, true),
                             new ServiceTestCase<>(new int[] {}, true),
                             new ServiceTestCase<>(new int[] { 0 }, true),
                             new ServiceTestCase<>(new int[] { 1 }, true),
                             new ServiceTestCase<>(new int[] { 1, 1 }, false),
                             new ServiceTestCase<>(new int[] { 1, 2 }, true),
                             new ServiceTestCase<>(new int[] { 0, 0, 0 }, false),
                             new ServiceTestCase<>(new int[] { 0, 1, 2, 4 }, true),
                             new ServiceTestCase<>(new int[] { -1000000, 1000000 }, true));
    }

    public void testPermutations()
    {
        for (ServiceTestCase<String, List<String>> ServiceTestCase : givenPermutationsServiceTestCases()) {
            List<String> permutations = service.permutations(ServiceTestCase.getValue());

            assertNotNull(permutations);
            assertEquals(permutations.size(), ServiceTestCase.getExpectedResult().size());

            permutations.forEach(permutation -> {
                assertTrue(ServiceTestCase.getExpectedResult().contains(permutation));
            });
        }
    }

    private List<ServiceTestCase<String, List<String>>> givenPermutationsServiceTestCases()
    {
        return Arrays.asList(new ServiceTestCase<>(null, new ArrayList<>()),
                             new ServiceTestCase<>("", new ArrayList<>()),
                             new ServiceTestCase<>("null", new ArrayList<>()),
                             new ServiceTestCase<>("0", Arrays.asList("0")),
                             new ServiceTestCase<>("1", Arrays.asList("1")),
                             new ServiceTestCase<>("isthisreallife", new ArrayList<>()),
                             new ServiceTestCase<>("1.234", new ArrayList<>()),
                             new ServiceTestCase<>("isthisreallife 1", Arrays.asList("1")),
                             new ServiceTestCase<>("1 1", Arrays.asList("1 1", "1 1")),
                             new ServiceTestCase<>("1 2", Arrays.asList("2 1", "1 2")),
                             new ServiceTestCase<>("1 2 3",
                                                   Arrays.asList("1 2 3", "1 3 2", "3 2 1", "3 1 2", "2 1 3", "2 3 1")),
                             new ServiceTestCase<>("1     2", Arrays.asList("2 1", "1 2")),
                             new ServiceTestCase<>("1 test 2", Arrays.asList("2 1", "1 2")),
                             new ServiceTestCase<>("1 test 2 test test", Arrays.asList("2 1", "1 2")));
    }

    public void testReverse()
    {
        for (ServiceTestCase<String, String> ServiceTestCase : givenReverseServiceTestCases()) {
            assertEquals(ServiceTestCase.getExpectedResult(), service.reverse(ServiceTestCase.getValue()));
        }
    }

    private List<ServiceTestCase<String, String>> givenReverseServiceTestCases()
    {
        return Arrays.asList(new ServiceTestCase<>(null, ""),
                             new ServiceTestCase<>("", ""),
                             new ServiceTestCase<>("null", ""),
                             new ServiceTestCase<>("0", "0"),
                             new ServiceTestCase<>("1", "1"),
                             new ServiceTestCase<>("1            ", "1"),
                             new ServiceTestCase<>("isthisreallife", ""),
                             new ServiceTestCase<>("1.234", ""),
                             new ServiceTestCase<>("isthisreallife 1", "1"),
                             new ServiceTestCase<>("1 1", "1 1"),
                             new ServiceTestCase<>("1 2", "2 1"),
                             new ServiceTestCase<>("1 2 3", "3 2 1"),
                             new ServiceTestCase<>("1 2 3 1 4 3", "4 3 3 2 1 1"),
                             new ServiceTestCase<>("1     2", "2 1"),
                             new ServiceTestCase<>("1 test 2", "2 1"),
                             new ServiceTestCase<>("1 test 2 test test", "2 1"));
    }

    public void testPrimePalindrome()
    {
        int primePalindrome = service.primePalindrome();

        assertTrue(primePalindrome >= 1);
        assertEquals(929, primePalindrome);
    }
}
