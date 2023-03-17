package com.ning.design_template.creator_template;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class Product {
    private String partA;
    private String partB;
    private String partC;

    public static void main(String[] args) {
        Product product = Product.builder().partA("A").partB("B").partC("C").build();
        System.out.println(product);
    }
}

