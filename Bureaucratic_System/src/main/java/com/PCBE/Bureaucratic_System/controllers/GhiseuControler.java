package com.PCBE.Bureaucratic_System.controllers;

import com.PCBE.Bureaucratic_System.Ghiseu;
import com.PCBE.Bureaucratic_System.services.DocumentService;
import com.PCBE.Bureaucratic_System.services.GhiseuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ghiseu")  // Prefix pentru toate rutele API
public class GhiseuControler {

    private final GhiseuService ghiseuService;

    public GhiseuControler(GhiseuService ghiseuService) {
        this.ghiseuService = ghiseuService;
    }

    // 1. CREATE - Creare un ghiseu nou
    @PostMapping("/create")
    public ResponseEntity<Ghiseu> createGhiseu(@RequestBody Ghiseu ghiseu) {
        if (ghiseu == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // Returnează HTTP 400 (Bad Request) dacă ghiseul este null
        }
        Ghiseu savedGhiseu = ghiseuService.createGhiseu(ghiseu);  // Folosim serviciul pentru a salva ghiseul
        return new ResponseEntity<>(savedGhiseu, HttpStatus.CREATED);  // Returnează HTTP 201 (Created)
    }

    // 2. READ - Obține toate ghișeele
    @GetMapping("/get")
    public ResponseEntity<List<Ghiseu>> getAllGhisee() {
        List<Ghiseu> ghisee = ghiseuService.getAllGhisee();  // Preia toate ghișeele
        return new ResponseEntity<>(ghisee, HttpStatus.OK);  // Returnează HTTP 200 (OK)
    }

    // 4. UPDATE - Actualizează un ghiseu existent
    /*@PutMapping("/{id}")
    public ResponseEntity<Ghiseu> updateGhiseu(@PathVariable int id, @RequestBody Ghiseu ghiseu) {
        Ghiseu updatedGhiseu = ghiseuService.updateGhiseu(id, ghiseu);  // Încercăm să actualizăm ghiseul
        return updatedGhiseu != null ?
                new ResponseEntity<>(updatedGhiseu, HttpStatus.OK) :  // Dacă ghiseul a fost actualizat
                new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Dacă ghiseul nu există
    }*/

    // 5. DELETE - Șterge un ghiseu după ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGhiseu(@PathVariable int id) {
        boolean isDeleted = ghiseuService.deleteGhiseu(id);  // Ștergem ghiseul
        return isDeleted ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :  // Dacă ștergerea a avut succes
                new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Dacă ghiseul nu a fost găsit
    }
}
