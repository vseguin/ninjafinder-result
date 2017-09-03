package ca.ingeno.ninjafinder.utils;

public class ServiceTestCase<T, U>
{
    private T value;
    private U expectedResult;

    public ServiceTestCase(T value, U expectedResult)
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
