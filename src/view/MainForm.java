package view;

import business.ContactBusiness;
import entity.ContactEntity;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainForm extends JFrame {
    private JPanel rootPanel;
    private JButton buttonNewContact;
    private JButton buttonRemove;
    private JTable tableContacts;
    private JLabel labelContactCount;

    private ContactBusiness mContactBusiness;
    private String mName;
    private String mPhone;

    public MainForm() {

        setContentPane(rootPanel);
        setSize(500, 250);
        setVisible(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mContactBusiness = new ContactBusiness();

        setListeners();
        loadContacts();
    }

    private void setListeners() {
        buttonNewContact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new ContactForm();
                dispose();
            }
        });

        tableContacts.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                if(listSelectionEvent.getValueIsAdjusting()){

                    if(tableContacts.getSelectedRow() != -1){
                        mName =tableContacts.getValueAt(tableContacts.getSelectedRow(), 0).toString();
                        mPhone =tableContacts.getValueAt(tableContacts.getSelectedRow(), 1).toString();
                    }
                }
            }
        });

        buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {


                try {
                    mContactBusiness.delete(mName,mPhone);

                    loadContacts();
                    mName = "";
                    mPhone = "";
                }catch (Exception error){
                    JOptionPane.showMessageDialog(new JFrame(), error.getMessage());
                }
            }
        });
    }

    private void loadContacts() {
        List<ContactEntity> contactList = mContactBusiness.getList();
        String[] columnNames = {"Name", "Telephone"};
        DefaultTableModel model = new DefaultTableModel(new Object[0][0], columnNames);

        for (ContactEntity contact : contactList) {
            Object[] o = new Object[2];

            o[0] = contact.getName();
            o[1] = contact.getPhone();

            model.addRow(o);
        }

        tableContacts.clearSelection();
        tableContacts.setModel(model);

        labelContactCount.setText(mContactBusiness.getContactCount());
    }
}
