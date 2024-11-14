/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package skibidy;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Skibidy extends JFrame {

    private JTextField textFieldCopy;
    private JButton btnCopy;
    private JTextField textFieldPaste;
    private JButton btnPaste;

    public Skibidy() {
        setTitle("Copiar y pegar a los skibidy toilet");
        setSize(500, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 1, 5, 5));

        initComponentes();
    }

    private void initComponentes() {
        // Panel para copiar
        JPanel panelCopy = new JPanel();
        panelCopy.setLayout(new FlowLayout(FlowLayout.LEFT));
        textFieldCopy = new JTextField(20);
        btnCopy = new JButton("Copiar");
        panelCopy.add(textFieldCopy);
        panelCopy.add(btnCopy);
        add(panelCopy);

        // Panel para pegar
        JPanel panelPaste = new JPanel();
        panelPaste.setLayout(new FlowLayout(FlowLayout.LEFT));
        textFieldPaste = new JTextField(20);
        btnPaste = new JButton("Pegar");
        panelPaste.add(textFieldPaste);
        panelPaste.add(btnPaste);
        add(panelPaste);

        // Acción del botón Copiar
        btnCopy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textToCopy = textFieldCopy.getText();
                StringSelection stringSelection = new StringSelection(textToCopy);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
            }
        });

        // Acción del botón Pegar
        btnPaste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                Transferable contents = clipboard.getContents(null);
                if (contents != null && contents.isDataFlavorSupported(java.awt.datatransfer.DataFlavor.stringFlavor)) {
                    try {
                        String textToPaste = (String) contents.getTransferData(java.awt.datatransfer.DataFlavor.stringFlavor);
                        textFieldPaste.setText(textToPaste);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        Skibidy app = new Skibidy();
        app.setVisible(true);
    }
}
