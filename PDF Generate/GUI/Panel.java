package GUI;

import Etiquetas.Etiquetas;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.*;

public class Panel extends JPanel{

    private final JPanel panel;
    private JTextArea editor;
    private JScrollPane scroll;
    private final JMenuBar menu;
    private final JMenu archivo;
    private final JMenuItem save;
    private final JMenuItem saveAs;
    private final JMenuItem open;
    private JFileChooser elegir;
    private File ruta; 
    private File rutaT;

    public Panel() {
        BorderLayout layout = new BorderLayout(10, 10);
        archivo = new JMenu("Archivo");
        setLayout(layout);
        panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1));
        add(panel, BorderLayout.CENTER);
        menu = new JMenuBar();
        save = new JMenuItem("Guardar");
        save.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });
        open = new JMenuItem("Abrir");
        open.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                open();
            }
        });
        saveAs = new JMenuItem("Guardar como");
        saveAs.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                rutaT = ruta;
                ruta = null;
                save();
            }
        });
        archivo.add(open);
        archivo.add(save);
        archivo.add(saveAs);
        menu.add(archivo);
        add(menu, BorderLayout.NORTH);
    }

    public void nuevo() {
        editor = new JTextArea();
        scroll = new JScrollPane(editor, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        panel.add(scroll);
    }

    public String open() {
        String nombre = "";
        elegir = new JFileChooser();
        if (elegir.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                ruta = elegir.getSelectedFile();
                String texto = getContenido();
                editor.setText(texto);
                nombre = elegir.getSelectedFile().getName();
            } 
            catch (Exception e) {}
        }
        return nombre;
    }

    public void save() {
        try {
            elegir = new JFileChooser();
            if (ruta == null) {
                if (elegir.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                    ruta = elegir.getSelectedFile();
                    Etiquetas guardar = new Etiquetas(String.valueOf(editor.getText()),
                            ruta);
                    guardar.etiquetas();
                    saveAs.setEnabled(true);
                    JOptionPane.showMessageDialog(null, "Documento Guardado");
                } 
                else {
                    ruta = rutaT;
                }
            } 
            else if (ruta != null) {
                Etiquetas guardar = new Etiquetas(String.valueOf(editor.getText()),
                        ruta);
                guardar.etiquetas();
                JOptionPane.showMessageDialog(null, "Documento Guardado");
            }
        } 
        catch (Exception e) {}
    }
    public String getContenido() {
        String contenido = "";
        FileReader fr;
        BufferedReader br = null;
        try {
            fr = new FileReader(ruta);
            br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido += linea + "\n";
            }
        } 
        catch (Exception e) {} 
        finally {
            try {
                br.close();
            } 
            catch (Exception ex) {}
        }
        return contenido;
    }
}