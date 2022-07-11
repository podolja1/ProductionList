package cz.uhk.production.data;

import cz.uhk.production.gui.MainFrame;

import java.io.Serializable;
import java.text.Collator;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductionList implements Serializable {
    private List<Product> products = new LinkedList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public void deleteProduct(int productNum) {
        products.remove(productNum-1);
    }

    public Product getProduct(int position) {
        return products.get(position);
    }

    public int getNumberOfProducts() {
        return products.size();
    }

    public double getMinWeight() {
        double min = 999999;
        for(int i = 0; i < products.size(); i++) {
            if(products.get(i).getWeight() <= min) {
                min = products.get(i).getWeight();
            }
        }
        return min;
    }

    public double getMaxWeight() {
        double max = 0;
        for(int i = 0; i < products.size(); i++) {
            if(products.get(i).getWeight() > max) {
                max = products.get(i).getWeight();
            }
        }
        return max;
    }

    public double getAverageWeight() {
        double sum = 0;
        for(int i = 0; i < products.size(); i++) {
            sum += products.get(i).getWeight();
        }
        double average = sum / products.size();
        return average;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void findRed() {
        products = products.stream()
                .sorted((p1, p2) -> Collator.getInstance().compare(p1.getColor(), p2.getColor()))
                .filter((p0) -> p0.getColor().startsWith("red"))
                .collect(Collectors.toList());
    }

    public void sortMaterial() {
        Comparator<Product> materialComparator = new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Collator.getInstance().compare(p1.getMaterial(), p2.getMaterial());
            }
        };
        products = products.stream().sorted(materialComparator).collect(Collectors.toList());
    }
}