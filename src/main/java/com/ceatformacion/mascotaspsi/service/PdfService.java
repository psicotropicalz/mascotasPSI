package com.ceatformacion.mascotaspsi.service;
import com.ceatformacion.mascotaspsi.model.Mascota;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class PdfService {

    public ByteArrayInputStream exportarMascotas(List<Mascota> mascotaList) {
        Document document = new Document(PageSize.A4.rotate());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph titulo = new Paragraph("Listado de Pacientes Mascotas", tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(7); // 4 columnas
            table.setWidthPercentage(100);
            table.setWidths(new int[]{3, 3, 3, 3, 3, 3, 3});

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            table.setHeaderRows(1);
            Color color = new GrayColor(0.85f);
            PdfPCell headerCell = new PdfPCell();
            headerCell.setBackgroundColor(color);
            headerCell.setBorderColor(color);


            table.addCell(new PdfPCell(new Phrase("ID", headFont)) {{setBackgroundColor(color); setBorderColor(color);}});
            table.addCell(new PdfPCell(new Phrase("Nombre", headFont)) {{setBackgroundColor(color); setBorderColor(color);}});
            table.addCell(new PdfPCell(new Phrase("Especie", headFont)) {{setBackgroundColor(color); setBorderColor(color);}});
            table.addCell(new PdfPCell(new Phrase("Raza", headFont)) {{setBackgroundColor(color); setBorderColor(color);}});
            table.addCell(new PdfPCell(new Phrase("Peso", headFont)) {{setBackgroundColor(color); setBorderColor(color);}});
            table.addCell(new PdfPCell(new Phrase("Edad", headFont)) {{setBackgroundColor(color); setBorderColor(color);}});
            table.addCell(new PdfPCell(new Phrase("DNI Dueño", headFont)) {{setBackgroundColor(color); setBorderColor(color);}});


            for (Mascota m : mascotaList) {
                table.addCell(String.valueOf(m.getId()));
                table.addCell(m.getNombre());
                table.addCell(m.getEspecie());
                table.addCell(m.getRaza());
                table.addCell(String.valueOf(m.getPeso()));
                table.addCell(String.valueOf(m.getEdad()));
                table.addCell(m.getDni());


            }

            document.add(table);
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

}









//package com.ceatformacion.mascotaspsi.service;
//
//import com.ceatformacion.mascotaspsi.model.Mascota;
//
//import com.lowagie.text.*;
//import com.lowagie.text.pdf.*;
//import org.springframework.stereotype.Service;
//
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.util.List;
//
//@Service
//public class PdfService {
//    private static final String TITULO = "Listado de Mascotas";
//    private static final int NUMERO_COLUMNAS = 6;
//
//    public ByteArrayInputStream exportarMascotas(List<Mascota> mascotaList) {
//        Document document = new Document(PageSize.A4.rotate());
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//
//        try {
//            PdfWriter.getInstance(document, out);
//            document.open();
//
//            agregarTitulo(document);
//            agregarTablaMascotas(document, mascotaList);
//
//        } catch (DocumentException e) {
//            throw new RuntimeException("Error al generar el PDF", e);
//        } finally {
//            document.close();
//        }
//
//        return new ByteArrayInputStream(out.toByteArray());
//    }
//
//    private void agregarTitulo(Document document) throws DocumentException {
//        Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
//        Paragraph titulo = new Paragraph(TITULO, tituloFont);
//        titulo.setAlignment(Element.ALIGN_CENTER);
//        document.add(titulo);
//        document.add(Chunk.NEWLINE);
//    }
//
//    private void agregarTablaMascotas(Document document, List<Mascota> mascotaList) throws DocumentException {
//        PdfPTable table = new PdfPTable(NUMERO_COLUMNAS);
//        table.setWidthPercentage(100);
//        table.setWidths(new int[]{1, 3, 3, 3, 1, 3});
//
//        agregarEncabezadosTabla(table);
//        agregarDatosMascotas(table, mascotaList);
//
//        document.add(table);
//    }
//
//    private void agregarEncabezadosTabla(PdfPTable table) {
//        Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
//        String[] encabezados = {"ID", "Nombre", "Especie", "Raza", "Edad", "DNI Dueño"};
//
//        for (String encabezado : encabezados) {
//            table.addCell(new PdfPCell(new Phrase(encabezado, headFont)));
//        }
//    }
//
//    private void agregarDatosMascotas(PdfPTable table, List<Mascota> mascotaList) {
//        for (Mascota mascota : mascotaList) {
//            table.addCell(String.valueOf(mascota.getId()));
//            table.addCell(mascota.getNombre());
//            table.addCell(mascota.getEspecie());
//            table.addCell(mascota.getRaza());
//            table.addCell(String.valueOf(mascota.getEdad()));
//            table.addCell(mascota.getDni());
//        }
//    }
//}