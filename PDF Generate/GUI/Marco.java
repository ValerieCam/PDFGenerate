package GUI;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Marco extends JFrame {

    private final JDesktopPane contenedor;
    private Panel[] panel = new Panel[50];
    private Panel abrir;
    private int x = 0;
    private int pos;
    private int guardar;
    private boolean v = false;
    private final JMenuBar menu;
    private final JMenu archivo; 
    private final JMenuItem nuevo;
    private JInternalFrame internal;

    public Marco() {
        super("PDF Generate");
        contenedor = new JDesktopPane();
        add(contenedor);

        nuevo = new JMenuItem("Nuevo");
        nuevo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pos > 0 || v == true) {
                    internal = new JInternalFrame("Nuevo ", true, true, true, 
                            true);
                    panel[pos] = new Panel();
                    panel[pos].nuevo();
                    internal.setName(String.valueOf(pos));
                    añadirFrameInterno(panel[pos]);
                    pos = 0;
                    v = false;
                } 
                else if (panel[x] == null) {
                    internal = new JInternalFrame("Nuevo ", true, true, true, 
                            true);
                    panel[x] = new Panel();
                    panel[x].nuevo();
                    internal.setName(String.valueOf(x));
                    añadirFrameInterno(panel[x]);
                }
                x++;
            }
        });
        archivo = new JMenu("Archivo");
        archivo.add(nuevo);
        menu = new JMenuBar();
        menu.add(archivo);
        setJMenuBar(menu);
    }
    
    public void añadirFrameInterno(Panel p) {
        internal.add(p);
        internal.setSize(300,300);
        internal.setClosable(true);
        internal.setMaximizable(true);
        internal.setIconifiable(true);
        internal.setMaximizable(true);
        internal.setVisible(true);
        contenedor.add(internal);
    }
}