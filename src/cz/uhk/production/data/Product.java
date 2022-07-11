package cz.uhk.production.data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Product implements Serializable {
    private String name;
    private String color;
    private String material;
    private double weight;
    private LocalDate productionDate;

    public Product(String name, String color, String material, double weight, LocalDate productionDate) {
        this.name = name;
        this.color = color;
        this.material = material;
        this.weight = weight;
        this.productionDate = productionDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }

    @Override
    public String toString() {
        return  "\t" + name + "\t\t" +
                "Color: " + color + "\t\t" +
                "Material: " + material + "\t\t" +
                "Weight [g]: " + weight + "\t\t" +
                "Production date: " +
                productionDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
    }
}