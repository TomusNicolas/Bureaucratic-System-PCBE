package com.PCBE.Bureaucratic_System.controllers;

import com.PCBE.Bureaucratic_System.Birou;
import com.PCBE.Bureaucratic_System.services.BirouService;
import com.PCBE.Bureaucratic_System.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/birou")  // Prefix pentru toate rutele API
public class BirouControler {

    private BirouService birouService;  // Injectăm serviciul pentru interacțiune cu repo

    public BirouControler(BirouService birouService) {
        this.birouService = birouService;
    }
    // 1. CREATE - Creare un Birou nou
    @PostMapping("/createBirou")
    public ResponseEntity<Birou> createBirou(@RequestBody Birou birou) {
        // Folosim serviciul pentru a salva biroul în baza de date
        Birou savedBirou = birouService.createBirou(birou);
        return new ResponseEntity<>(savedBirou, HttpStatus.CREATED);  // Returnează HTTP 201 (Created)
    }

    // 2. READ - Obține toate birourile
    @GetMapping("/get")
    public ResponseEntity<List<Birou>> getAllBirouri() {
        List<Birou> birouri = birouService.getAllBirouri();  // Preia toate birourile din baza de date
        return new ResponseEntity<>(birouri, HttpStatus.OK);  // Returnează HTTP 200 (OK)
    }

    // 4. UPDATE - Actualizează un birou existent
    @PutMapping("/{id}")
    public ResponseEntity<Birou> updateBirou(@PathVariable int id, @RequestBody Birou birou) {
        Birou updatedBirou = birouService.updateBirou(id, birou);  // Încercăm să actualizăm biroul
        return updatedBirou != null ?
                new ResponseEntity<>(updatedBirou, HttpStatus.OK) :  // Dacă actualizarea a avut succes
                new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Dacă biroul nu a fost găsit
    }

    // 5. DELETE - Șterge un birou după ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBirou(@PathVariable int id) {
        boolean isDeleted = birouService.deleteBirou(id);  // Ștergem biroul
        return isDeleted ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :  // Dacă ștergerea a fost cu succes
                new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Dacă biroul nu a fost găsit
    }
}
