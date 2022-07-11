package cz.uhk.production.util;

import cz.uhk.production.data.ProductionList;

import java.io.IOException;

public interface UploadDownload {
    /**
     * Save data into the file
     * @param productionList
     */
    void upload(ProductionList productionList) throws IOException;

    /**
     * Download data from file
     * @return
     */
    ProductionList download() throws IOException;
}
