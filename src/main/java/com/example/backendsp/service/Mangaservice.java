package com.example.backendsp.service;

import com.example.backendsp.DAO.entities.Manga;
import com.example.backendsp.DAO.repo.MangaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Mangaservice implements MangaServices{

    @Autowired
    public MangaRepo mangaRepo;
    @Override
    public List<Manga> getAllManga() {
        return mangaRepo.findAll();
    }

//    @Override
//    public Page<Manga> getAllManga(Pageable pageable) {
//        return mangaRepo.findAll(pageable);
//    }
    @Override
    public Manga getMangaById(Long Id) {
        Optional<Manga> mangaOptional = mangaRepo.findById(Id);
        return mangaOptional.orElse(null);
    }

    @Override
    public void saveManga(Manga manga) {
        mangaRepo.save(manga);
    }

    @Override
    public void deleteManga(Long Id) {
        mangaRepo.deleteById(Id);
    }




}
