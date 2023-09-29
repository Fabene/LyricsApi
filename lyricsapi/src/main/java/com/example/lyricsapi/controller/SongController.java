package com.example.lyricsapi.controller;

import com.example.lyricsapi.model.Song;
import com.example.lyricsapi.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private SongService songService;

    @GetMapping("/")
    public ResponseEntity<List<Song>> getAllSongs() {
        List<Song> songs = songService.getAllSongs();
        return ResponseEntity.ok(songs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Song> getSongById(@PathVariable Long id) {
        Song song = songService.getSongById(id);
        if (song != null) {
            return ResponseEntity.ok(song);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<Song> addSong(@RequestBody Song song) {
        Song savedSong = songService.saveSong(song);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSong);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSong(@PathVariable Long id, @RequestBody Song updatedSong) {
        Song updated = songService.updateSong(id, updatedSong);
        if (updated != null) {
            return ResponseEntity.ok("song updated !");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSong(@PathVariable Long id) {
        boolean deleted = songService.deleteSong(id);
        if (deleted) {
            return ResponseEntity.ok("Song deleted !");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Song>> searchSongsByKeyword(@RequestParam String keyword) {
        List<Song> songs = songService.searchSongsByKeyword(keyword);
        return ResponseEntity.ok(songs);
    }
}
