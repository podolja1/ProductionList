package cz.uhk.production.util;

import cz.uhk.production.data.ProductionList;

import java.io.*;

public class UploadDownloadObject implements UploadDownload {

    private final String object;

    public UploadDownloadObject(String fileName) {
        object = fileName;
    }

    @Override
    public void upload(ProductionList productionList) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(object));
        out.writeObject(productionList);
        out.close();
    }

    @Override
    public ProductionList download() throws IOException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(object));
        ProductionList pList = null;
        try {
            pList = (ProductionList) in.readObject();
            in.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        return pList;
    }
}
