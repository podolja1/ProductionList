package cz.uhk.production.gui;

import cz.uhk.production.data.Product;
import cz.uhk.production.data.ProductionList;
import cz.uhk.production.util.UploadDownload;
import cz.uhk.production.util.UploadDownloadCsv;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;

public class MainFrame extends JFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });
    }

    private ProductionList productionList = new ProductionList();

//    private UploadDownload uploadDownload = new UploadDownloadObject("object.obj");
    private UploadDownload uploadDownload = new UploadDownloadCsv("txt.csv");

    private JTextArea txaWindow = new JTextArea(20, 40);

    public MainFrame() {
        setTitle("Production list");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(1024,768);
        setVisible(true);
        
        initGui();

        initData();
    }

    private void initGui() {
        JPanel pnlMain = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton btnAddProduct = new JButton("Add product");
        btnAddProduct.setToolTipText("Add product into the list and then press \"Show products\"");
        btnAddProduct.setBackground(Color.green);

        JButton btnShowProducts = new JButton("Show products");
        btnShowProducts.setToolTipText("Display products");
        btnShowProducts.setBackground(Color.white);

        JButton btnEditProduct = new JButton("Edit product");
        btnEditProduct.setToolTipText("Edit product from the list and then press \"Show products\"");
        btnEditProduct.setBackground(Color.yellow);

        JButton btnDeleteProduct = new JButton("Delete product");
        btnDeleteProduct.setToolTipText("Delete product from the list and then press \"Show products\"");
        btnDeleteProduct.setBackground(Color.red);

        JButton btnFindProduct = new JButton("Filter red products");
        btnFindProduct.setToolTipText("Find only red products and then press \"Show products\"");
        btnFindProduct.setBackground(Color.white);

        JButton btnSortProducts = new JButton("Sort products by material");
        btnSortProducts.setToolTipText("Sort products by material and then press \"Show products\"");
        btnSortProducts.setBackground(Color.white);

        JButton btnSaveData = new JButton("Save data");
        btnSaveData.setToolTipText("Save products into the file");
        btnSaveData.setBackground(Color.white);

        JButton btnDowloadData = new JButton("Download data");
        btnDowloadData.setToolTipText("Download products from file and then press \"Show products\"");
        btnDowloadData.setBackground(Color.white);


        btnAddProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProduct();
            }
        });

        btnShowProducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showData();
            }
        });

        btnEditProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editData();
            }
        });

        btnDeleteProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteData();
            }
        });

        btnFindProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findData();
            }
        });

        btnSortProducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortData();
            }
        });

        btnSaveData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveData();
            }
        });

        btnDowloadData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                downloadData();
            }
        });

        pnlMain.add(btnAddProduct);
        pnlMain.add(btnShowProducts);
        pnlMain.add(btnEditProduct);
        pnlMain.add(btnDeleteProduct);
        pnlMain.add(btnFindProduct);
        pnlMain.add(btnSortProducts);
        pnlMain.add(btnSaveData);
        pnlMain.add(btnDowloadData);

        add(pnlMain, BorderLayout.NORTH);
        add(new JScrollPane(txaWindow), BorderLayout.CENTER);
    }

    private void addProduct() {
        DialogFrame productDialog = new DialogFrame(this);
        Product item = productDialog.newProduct();
        if(productDialog != null) {
            productionList.addProduct(item);
        }
    }

    private void initData() {
        productionList.addProduct(new Product("Key box","red","plastic",120.15,
                LocalDate.of(2021,01,01)));
        productionList.addProduct(new Product("Frame","brown","wood",1525.22,
                LocalDate.of(2021,05,04)));
        productionList.addProduct(new Product("Rod","silver","steel",11025.07,
                LocalDate.of(2021,04,12)));
        productionList.addProduct(new Product("Apple","red","glass",895,
                LocalDate.of(2021,02,22)));
    }

    private void showData() {
        txaWindow.setText("");
        for(int i = 0; i < productionList.getNumberOfProducts(); i++) {
            Product product = productionList.getProduct(i);
            txaWindow.append(i+1 + " " + product.toString() + "\n");
        }
    }

    private void editData() {
        int productNumber = Integer.valueOf(JOptionPane.showInputDialog("Product line"));
        DialogFrame dlgEditProduct = new DialogFrame(this);
        if(productNumber <= productionList.getNumberOfProducts() && productNumber > 0) {
            dlgEditProduct.editProduct(productionList.getProduct(productNumber-1));
        } else {
            JOptionPane.showMessageDialog(this,
                    "Please fill exist product number!",
                    "Wrong product number", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteData() {
        int productNumber = Integer.valueOf(JOptionPane.showInputDialog("Product line"));
        if(productNumber <= productionList.getNumberOfProducts() && productNumber > 0) {
            productionList.deleteProduct(productNumber);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Please fill exist product number!",
                    "Wrong product number", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void findData() {
        productionList.findRed();
    }

    private void sortData() {
        productionList.sortMaterial();
    }

    private void saveData() {
        try {
            uploadDownload.upload(productionList);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,"Error: "+ e.getMessage());
            e.printStackTrace();
        }
    }

    private void downloadData() {
        try {
            productionList = uploadDownload.download();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,"Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}