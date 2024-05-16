package com.example.backendsp.service;

import com.example.backendsp.DAO.entities.Manga;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MangaServices {
    List<Manga> getAllManga();

//    Page<Manga> getAllManga(Pageable pageable);

    Manga getMangaById(Long Id);
    void saveManga(Manga manga);
    void deleteManga(Long Id);


}
