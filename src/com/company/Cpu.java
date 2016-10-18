package com.company;
// Author: Gregory Westbrook
// Class: Advanced Java
// Project: 03
// Date Due: 2016.10.12

import java.util.List;
import java.util.concurrent.atomic.DoubleAccumulator;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by Greg on 9/28/2016.
 */
public class Cpu {

    private String cpuName;
    private Double performance;
    private Double price;
    private Double value;

    public Cpu(Double price, String cpuName, Double performance) {
        this.price = price;
        this.cpuName = cpuName;
        this.performance = performance;
        this.value=performance/price;
    }

    public Cpu(String s) {
        //Intel Core i7-3770K @ 3.50GHz,"9,556",$560.50
        Pattern quotesPattern = Pattern.compile("\""); //find quotes

            List<String> data = quotesPattern.splitAsStream(s).//split line up at the quotes
                map(l -> l.replaceAll("(,|\\$)", "")).//get rid of commas and the dollar sign
                collect(Collectors.toList()); //collect the values into a list called 'data'
        this.price = Double.parseDouble(data.get(2));
        this.cpuName = data.get(0);
        this.performance = Double.parseDouble(data.get(1));
        this.value = performance/price;

    }

    public String getCpuName() {
        return cpuName;
    }

    public void setCpuName(String cpuName) {
        this.cpuName = cpuName;
    }

    public Double getPerformance() {
        return performance;
    }

    public void setPerformance(Double performance) {
        this.performance = performance;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String toString() {
        return "Cpu{" +
                "cpuName='" + cpuName + '\'' +
                ", performance='" + performance + '\'' +
                ", price=" + price +
                '}';
    }
}
