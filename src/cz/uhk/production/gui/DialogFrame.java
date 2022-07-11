package cz.uhk.production.gui;

import cz.uhk.production.data.Product;
import cz.uhk.production.data.ProductionList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DialogFrame extends JDialog {

    public DialogFrame(JFrame dialogOwner) {
        super(dialogOwner, "Add product", true);

        initGui();
    }

    public DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private JTextField tfdName = new JTextField(50);
    private JTextField tfdColor = new JTextField(20);
    private JTextField tfdMaterial = new JTextField(30);
    private JTextField tfdWeight = new JTextField(6);
    private JTextField tfdProductionDate = new JTextField(10);

    private boolean success = false;

    public Product newProduct() {
        Product item = null;
        setVisible(true);
        if(success) {
            item = new Product(tfdName.getText(), tfdColor.getText(), tfdMaterial.getText(),
                    Double.valueOf(tfdWeight.getText()), LocalDate.parse(tfdProductionDate.getText(), dtFormat));
        }
        return item;
    }

    public void editProduct(Product item) {
        tfdName.setText(item.getName());
        tfdColor.setText(item.getColor());
        tfdMaterial.setText(item.getMaterial());
        tfdWeight.setText(String.valueOf(item.getWeight()));
        tfdProductionDate.setText(item.getProductionDate().format(dtFormat));
        setVisible(true);
        if(success) {
            item.setName(tfdName.getText());
            item.setColor(tfdColor.getText());
            item.setMaterial(tfdMaterial.getText());
            try {
                item.setWeight(Double.valueOf(tfdWeight.getText()));
            } catch (NumberFormatException nbFoEx) {
                JOptionPane.showMessageDialog(this, "Wrong format number!", "Error!",
                        JOptionPane.ERROR_MESSAGE);
                }
            item.setProductionDate(LocalDate.parse(tfdProductionDate.getText(), dtFormat));
        }
    }

    private void initGui() {
        JPanel pnlCenter = new JPanel(new GridLayout(5,2,2,2));
        JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        add(pnlCenter, BorderLayout.CENTER);
        add(pnlSouth, BorderLayout.SOUTH);

        JButton btnConfirm = new JButton("Confirm");
        btnConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                success = true;
                setVisible(false);
            }
        });
        btnConfirm.setBackground(Color.white);
        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                success = false;
                setVisible(false);
            }
        });
        btnCancel.setBackground(Color.lightGray);
        pnlSouth.add(btnConfirm);
        pnlSouth.add(btnCancel);

        JLabel labName = new JLabel("Name");
        labName.setDisplayedMnemonic('N');
        labName.setLabelFor(tfdName);
        pnlCenter.add(labName);
        pnlCenter.add(tfdName);

        JLabel labColor = new JLabel("Color");
        labColor.setDisplayedMnemonic('C');
        labColor.setLabelFor(tfdColor);
        pnlCenter.add(labColor);
        pnlCenter.add(tfdColor);

        JLabel labMaterial = new JLabel("Material");
        labMaterial.setDisplayedMnemonic('M');
        labMaterial.setLabelFor(tfdMaterial);
        pnlCenter.add(labMaterial);
        pnlCenter.add(tfdMaterial);

        JLabel labWeight = new JLabel("Weight [g]");
        labWeight.setDisplayedMnemonic('W');
        labWeight.setLabelFor(tfdWeight);
        pnlCenter.add(labWeight);
        pnlCenter.add(tfdWeight);

        JLabel labProductionDate = new JLabel("Production Date [dd.mm.yyyy]");
        labProductionDate.setDisplayedMnemonic('P');
        labProductionDate.setLabelFor(tfdProductionDate);
        pnlCenter.add(labProductionDate);
        pnlCenter.add(tfdProductionDate);

        pack();
    }
}