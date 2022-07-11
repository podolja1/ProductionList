package cz.uhk.production.util;

import cz.uhk.production.data.Product;
import cz.uhk.production.data.ProductionList;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UploadDownloadCsv implements UploadDownload {
    private String csv;

    public UploadDownloadCsv(String fileName) {
        csv = fileName;
    }

    @Override
    public void upload(ProductionList productionList) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(csv));
        for(Product p : productionList.getProducts()) {
            out.printf("%s;%s;%s;%s;%s\n", p.getName(), p.getColor(), p.getMaterial(),
                    p.getWeight(), p.getProductionDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
        }
        out.close();
    }

    @Override
    public ProductionList download() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(csv));
        String row;
        ProductionList productionList = new ProductionList();
        while((row = in.readLine()) != null) {
            String[] element = row.split(";");
            Product p = new Product(element[0], element[1], element[2], Double.valueOf(element[3]),
                    LocalDate.parse(element[4], DateTimeFormatter.ISO_LOCAL_DATE));
            productionList.addProduct(p);
        }
        in.close();
        return productionList;
    }
}
