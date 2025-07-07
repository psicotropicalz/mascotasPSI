package com.ceatformacion.mascotaspsi.controller;

import com.ceatformacion.mascotaspsi.model.Mascota;
import com.ceatformacion.mascotaspsi.repository.MascotaRepository;
import com.ceatformacion.mascotaspsi.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.util.List;

// Se utliiza @RestController para generar ficheros Json, Pdf, etc.
@RestController("/mascotas")
public class PdfController {

    // Llamada al repositorio
    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private PdfService pdfService;

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> exportarMascotas() {
//        // Obtener la lista de mascotas desde el repositorio
//        var mascotaList = mascotaRepository.findAll();
//
//        // Generar el PDF utilizando el servicio
//        var pdfBytes = pdfService.exportarMascotas(mascotaList);
//
//        // Retornar el PDF como respuesta
//        return ResponseEntity.ok()
//                .header("Content-Disposition", "attachment; filename=\"mascotas.pdf\"")
//                .body(pdfBytes.readAllBytes());
        //Generar la lista
        List<Mascota> mascotaList = mascotaRepository.findAll();
        //Los flujos o datos se convierten a bytes
        ByteArrayInputStream pdfStream = pdfService.exportarMascotas(mascotaList);

        // se crean las cabeceras http para indicar que el archivo será mostrado como un PDF
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=mascotas.pdf");
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfStream.readAllBytes());
    }
}