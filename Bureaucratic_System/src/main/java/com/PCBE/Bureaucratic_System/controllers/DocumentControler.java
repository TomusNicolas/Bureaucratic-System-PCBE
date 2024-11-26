package com.PCBE.Bureaucratic_System.controllers;

import com.PCBE.Bureaucratic_System.Document;
import com.PCBE.Bureaucratic_System.services.DocumentService;

import java.util.List;
import java.util.Scanner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/document")
public class DocumentControler {

    private final DocumentService documentService;

    public DocumentControler(DocumentService documentService) {
        this.documentService = documentService;
    }

    // GET: Obține toate documentele
    @GetMapping("/getAll")
    public ResponseEntity<List<Document>> getAllDocuments() {
        List<Document> documents = documentService.getAllDocuments();
        return ResponseEntity.ok(documents);
    }


    // POST: Crează un document
    @PostMapping("/post")
    public ResponseEntity<Document> addDocument(@RequestBody Document document) {
        Document createdDocument = documentService.addDocument(document);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDocument);
    }

    // PUT: Actualizează un document pe baza ID-ului
    @PutMapping("/{id}")
    public ResponseEntity<Document> updateDocument(@PathVariable("id") int id, @RequestBody String tip) {
        Document document = documentService.updateDocument(id, tip);
        return document != null ? ResponseEntity.ok(document) : ResponseEntity.notFound().build();
    }

    // DELETE: Șterge un document pe baza ID-ului
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable("id") int id) {
        boolean deleted = documentService.deleteDocument(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}