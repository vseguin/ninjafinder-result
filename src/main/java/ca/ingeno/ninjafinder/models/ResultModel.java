package ca.ingeno.ninjafinder.models;

public class ResultModel<T>
{
    private T result;

    public T getResult()
    {
        return result;
    }

    public void setResult(T result)
    {
        this.result = result;
    }

    public ResultModel<T> withResult(T result)
    {
        setResult(result);
        return this;
    }
}