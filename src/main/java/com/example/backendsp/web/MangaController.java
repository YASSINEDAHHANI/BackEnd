package com.example.backendsp.web;


import com.example.backendsp.DAO.entities.Manga;
import com.example.backendsp.service.MangaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manga")
public class MangaController {


    @Autowired
    private MangaServices mangaServices;
    @GetMapping
    public ResponseEntity<List<Manga>> getAllManga() {
        List<Manga> mangaList = mangaServices.getAllManga();
        return new ResponseEntity<>(mangaList, HttpStatus.OK);
    }
//        @GetMapping
//        public ResponseEntity<Page<Manga>> getAllManga(
//                @RequestParam(defaultValue = "0") int page,
//                @RequestParam(defaultValue = "10") int size)
//        {
//            Pageable pageable = PageRequest.of(page, size);
//            Page<Manga> mangaPage = mangaServices.getAllManga(pageable);
//            return new ResponseEntity<>(mangaPage, HttpStatus.OK);
//        }


    @GetMapping("/{id}")
    public ResponseEntity<Manga> getMangaById(@PathVariable Long id) {
        Manga manga = mangaServices.getMangaById(id);
        if (manga != null) {
            return new ResponseEntity<>(manga, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<Manga> createManga(@RequestBody Manga manga) {
        mangaServices.saveManga(manga);
        return new ResponseEntity<>(manga, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteManga(@PathVariable Long id) {
        mangaServices.deleteManga(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
