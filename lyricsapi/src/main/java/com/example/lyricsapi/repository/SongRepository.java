package com.example.lyricsapi.repository;

import com.example.lyricsapi.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {
}