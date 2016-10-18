package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *   Author: Gregory Westbrook
 *   Class: Advanced Java
 *   Project: 03: Use Java 8's Stream and Lambda Expressions to process a CSV ﬁle
 *   Date Due: 2016.10.12
 */
public class Main {

    public static void main(String[] args) {

        Predicate<String> doesntEndInNA = s -> (!s.endsWith("NA")); //to use with a filter below

        try {
            //Read the file and convert each line into a Cpu object - store in a list.
            //
            List< Cpu > cpulist =
                    Files.lines( Paths.get("Project03Data.csv")). // Stream of each line
                    skip(1).//skip first line in file (the title line)
                    filter(doesntEndInNA). //ignores lines ending in "NA"
                    map(l -> new Cpu(l)). // Cpu object representing each line
                    collect(Collectors.toList())//put the Cpu objects in a list
                    ;

            //Build a list of cpu prices
            List< Double > cpuPrices = cpulist.stream().map( c -> c.getPrice() ).collect(Collectors.toList());
            //Build a list of cpu values
            List< Double > cpuValues = cpulist.stream().map( c -> c.getPrice() ).collect(Collectors.toList());

            //Get set up to compare by price and value
            Function<Cpu, Double > byPrice = Cpu::getPrice;
            Function<Cpu, Double > byValue = Cpu::getValue;
            Comparator<Cpu> price = Comparator.comparing(byPrice);
            Comparator<Cpu> value = Comparator.comparing(byValue);

            //Print out the relevant values to the command line
            System.out.printf("Average CPU price: $%.2f\n", cpulist.stream().mapToDouble((x)-> x.getPrice()).average().getAsDouble() );
            System.out.printf("Highest priced CPU: $%.2f\n", cpulist.stream().max( price ).get().getPrice() );
            System.out.printf("Lowest priced CPU: $%.2f\n", cpulist.stream().min( price ).get().getPrice() );
            System.out.printf("Best value CPU (Performance/Price): %s\n", cpulist.stream().max( value ).get().getCpuName() );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
