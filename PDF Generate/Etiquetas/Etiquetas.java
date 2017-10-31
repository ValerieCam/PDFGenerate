package Etiquetas;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.*;
import javax.swing.JOptionPane;

public class Etiquetas {

    private final String texto;
    private final File file;
    private Document doc;

    public Etiquetas(String t, File f) {
        texto = t;
        file = f;
    }

    public void etiquetas() {
        Font font = new Font();
        char c;
        boolean z = false;
        boolean dir = false;
        String sobra = "";
        String x = "";
        try {
            FileOutputStream archivo = new FileOutputStream(file.toString()
                    + ".pdf");
            doc = new Document(PageSize.LETTER);
            PdfWriter.getInstance(doc, archivo);
            doc.open();
            for (int i = 0; i < texto.length(); i++) {
                c = texto.charAt(i);
                if (c == '{' && texto.charAt(i + 1) == 'T'
                        && texto.charAt(i + 2) == '}') {
                    i = i + 3;
                    c = texto.charAt(i);
                    z = true;
                } 
                else if (c == '{' && texto.charAt(i + 1) == '#'
                        && texto.charAt(i + 2) == 'T' && texto.charAt(i + 3) == '}') {
                    c = ' ';
                    i = i + 3;
                    x += c;
                    font.setSize(25);
                    font.setFamily(FontFamily.TIMES_ROMAN.toString());
                    font.setStyle(Font.BOLD);
                    font.setColor(BaseColor.BLACK);
                    Paragraph title = new Paragraph(x, font);
                    title.setAlignment(Paragraph.ALIGN_CENTER);
                    doc.add(title);
                    x = "";
                    z = false;
                }
                if (c == '{' && texto.charAt(i + 1) == 'C'
                        && texto.charAt(i + 2) == '}') {
                    i = i + 3;
                    c = texto.charAt(i);
                    z = true;
                } 
                else if (c == '{' && texto.charAt(i + 1) == '#'
                        && texto.charAt(i + 2) == 'C' && texto.charAt(i + 3) == '}') {
                    c = ' ';
                    i = i + 3;
                    x += c;
                    font.setSize(15);
                    font.setFamily(FontFamily.TIMES_ROMAN.toString());
                    font.setStyle(Font.BOLD);
                    Paragraph cap = new Paragraph("\n");
                    cap.setFont(new Font(font));
                    cap.add(x);
                    cap.setAlignment(Paragraph.ALIGN_LEFT);
                    doc.add(cap);
                    x = "";
                    z = false;
                }
                if (c == '{' && texto.charAt(i + 1) == 'S'
                        && texto.charAt(i + 2) == '}') {
                    i = i + 3;
                    c = texto.charAt(i);
                    z = true;
                } 
                else if (c == '{' && texto.charAt(i + 1) == '#'
                        && texto.charAt(i + 2) == 'S' && texto.charAt(i + 3) == '}') {
                    c = ' ';
                    i = i + 3;
                    x += c;
                    font.setSize(14);
                    font.setFamily(FontFamily.TIMES_ROMAN.toString());
                    Paragraph cap = new Paragraph(x, font);
                    cap.setAlignment(Paragraph.ALIGN_LEFT);
                    doc.add(cap);
                    x = "";
                    z = false;
                } 
                else if (c == '{' && texto.charAt(i + 1) == 'P'
                        && texto.charAt(i + 2) == '}') {
                    i = i + 3;
                    c = texto.charAt(i);
                    z = true;
                } else if (c == '{' && texto.charAt(i + 1) == '#'
                        && texto.charAt(i + 2) == 'P' && texto.charAt(i + 3) == '}') {
                    c = ' ';
                    i = i + 3;
                    x += c;
                    Paragraph p = new Paragraph();
                    p.setAlignment(Paragraph.ALIGN_LEFT);
                    x = etiquetasForma(x, p);
                    p.setFont(new Font());
                    p.add(x);
                    doc.add(p);
                    x = "";
                    z = false;
                } 
                else if (c == '{' && texto.charAt(i + 1) == 'I'
                        && texto.charAt(i + 2) == '}') {
                    i = i + 3;
                    c = texto.charAt(i);
                    dir = true;
                } 
                else if (c == '{' && texto.charAt(i + 1) == '#'
                        && texto.charAt(i + 2) == 'I' && texto.charAt(i + 3) == '}') {
                    i = i + 3;
                    Paragraph image = new Paragraph();
                    Image im = Image.getInstance(x);
                    im.setAlignment(Image.ALIGN_CENTER);
                    if (im.getScaledHeight() >= 1000 && im.getScaledWidth() >= 1000) {
                        im.scaleAbsolute(im.getScaledWidth() / 8, im.getScaledHeight() / 8);
                    }
                    image.add(im);
                    doc.add(image);
                    x = "";
                    dir = false;
                }
                if (z) {
                    x += c;
                } 
                else {
                    sobra += c;
                }
                if (dir && c != ' ') {
                    x += c;
                }
            }
            doc.close();
        } 
        catch (Exception e) {}
    }

    public String etiquetasForma(String t, Paragraph parrafo) {
        char c;
        boolean z = false;
        String sobra = "";
        String x = "";
        Font font = new Font();
        for (int i = 0; i < t.length(); i++) {
            c = t.charAt(i);
            if (c == '{' && t.charAt(i + 1) == 'b' && t.charAt(i + 2) == '}') {
                i = i + 3;
                c = t.charAt(i);
                parrafo.setFont(new Font());
                parrafo.add(sobra);
                sobra = "";
                z = true;
            } 
            else if (c == '{' && t.charAt(i + 1) == '#' && t.charAt(i + 2) == 'b'
                    && t.charAt(i + 3) == '}') {
                c = ' ';
                i = i + 3;
                x += c;
                font.setStyle(Font.BOLD);
                parrafo.setFont(new Font(font));
                parrafo.add(x);
                x = "";
                z = false;
            } 
            else if (c == '{' && t.charAt(i + 1) == 'i' && t.charAt(i + 2) == '}') {
                i = i + 3;
                c = t.charAt(i);
                parrafo.setFont(new Font());
                parrafo.add(sobra);
                sobra = "";
                z = true;
            } 
            else if (c == '{' && t.charAt(i + 1) == '#' && t.charAt(i + 2) == 'i'
                    && t.charAt(i + 3) == '}') {
                c = ' ';
                i = i + 3;
                x += c;
                Font fuente = new Font();
                fuente.setStyle(Font.ITALIC);
                parrafo.setFont(new Font(fuente));
                parrafo.add(x);
                x = "";
                z = false;
            } 
            else if (c == '{' && t.charAt(i + 1) == 'u' && t.charAt(i + 2) == '}') {
                i = i + 3;
                c = t.charAt(i);
                parrafo.setFont(new Font());
                parrafo.add(sobra);
                sobra = "";
                z = true;
            } 
            else if (c == '{' && t.charAt(i + 1) == '#' && t.charAt(i + 2) == 'u'
                    && t.charAt(i + 3) == '}') {
                c = ' ';
                i = i + 3;
                x += c;
                font.setStyle(Font.UNDERLINE);
                parrafo.setFont(new Font(font));
                parrafo.add(x);
                x = "";
                z = false;
            } 
            else if (c == '{' && t.charAt(i + 1) == 'n' && t.charAt(i + 2) == '}') {
                i = i + 2;
                c = ' ';
                if (z) {
                    x += "\n ";
                } 
                else {
                    sobra += "\n ";
                }
            }
            if (z) {
                x += c;
            } 
            else {
                sobra += c;
            }
        }
        return sobra;
    }
}