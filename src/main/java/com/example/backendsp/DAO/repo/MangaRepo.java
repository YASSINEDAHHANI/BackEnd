package com.example.backendsp.DAO.repo;

import com.example.backendsp.DAO.entities.Manga;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MangaRepo extends JpaRepository<Manga,Long> {

}
