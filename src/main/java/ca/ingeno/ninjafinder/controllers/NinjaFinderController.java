package ca.ingeno.ninjafinder.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ca.ingeno.ninjafinder.models.InputModel;
import ca.ingeno.ninjafinder.models.ResultModel;
import ca.ingeno.ninjafinder.service.NinjaFinderService;

@RestController
public class NinjaFinderController
{
    @Autowired
    private NinjaFinderService ninjaFinderService;
    
    @GetMapping("/")
    public String home()
    {
        return "Welcome to NinjaFinder";
    }

    @PostMapping("/uniquevalues")
    public ResultModel<Boolean> uniqueValues(@RequestBody InputModel<int[]> input)
    {
        return new ResultModel<Boolean>().withResult(new Boolean(ninjaFinderService.isContainingOnlyUniqueValues(input.getInput())));
    }
    
    @PostMapping("/permutations")
    public ResultModel<List<String>> permutations(@RequestBody InputModel<String> input)
    {
        return new ResultModel<List<String>>().withResult(ninjaFinderService.permutations(input.getInput()));
    }
    
    @PostMapping("/reverse")
    public ResultModel<String> reverse(@RequestBody InputModel<String> input)
    {
        return new ResultModel<String>().withResult(ninjaFinderService.reverse(input.getInput()));
    }
    
    @GetMapping("/primepalindrome")
    public ResultModel<Integer> primePalindrome()
    {
        return new ResultModel<Integer>().withResult(ninjaFinderService.primePalindrome());
    }
}