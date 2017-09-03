package ca.ingeno.ninjafinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
public class NinjaFinderApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(NinjaFinderApplication.class, args);
    }
}