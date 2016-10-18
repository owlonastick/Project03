package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by Greg on 10/4/2016.
 */
public class Concordance {
// Author: Gregory Westbrook
// Class: Advanced Java
// Project: 03
// Date Due: 2016.10.12

    public static void main( String[] args ){

        try{

            Pattern pattern = Pattern.compile( "\\s+" ); //match on spaces

            Map< String, Long > wordCounts =
                    Files.lines( Paths.get( "lorem.txt" ) ). //Read line by line
                            map( l -> l.replaceAll( "(?!')\\p{P}", "" ) ). //get rid of punctuation
                            flatMap( l -> pattern.splitAsStream( l ) ). //Divide the lines into words
                            //Stream; quick, brown, fox, jumped, fox, jumped, brown, jumped, fence
                                    collect( Collectors.groupingBy( String::toLowerCase, TreeMap::new, Collectors.counting() ) ); //Don't know what this does
            //wordCounts == <"quick", 2>, <"fox", 2> , <"brown", 2>

            wordCounts.entrySet().stream().collect(
                    Collectors.groupingBy( entry -> entry.getKey().charAt( 0 ), TreeMap::new, Collectors.toList() ) ).forEach( ( c, w ) -> {
                System.out.printf( "\n%c\n", c );
                w.stream().forEach( wd -> System.out.printf( "%s: %d\n", wd.getKey(), wd.getValue() ) );
            });




        }catch( IOException e ){
            e.printStackTrace();
        }

    }
}
