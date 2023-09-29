package com.example.lyricsapi.service;

import com.example.lyricsapi.model.Song;
import com.example.lyricsapi.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    public Song getSongById(Long id) {
        Optional<Song> optionalSong = songRepository.findById(id);
        return optionalSong.orElse(null);
    }

    public Song saveSong(Song song) {
        return songRepository.save(song);
    }

    public Song updateSong(Long id, Song updatedSong) {
        Optional<Song> optionalSong = songRepository.findById(id);
        if (optionalSong.isPresent()) {
            Song song = optionalSong.get();
            song.setTitle(updatedSong.getTitle());
            song.setYear(updatedSong.getYear());
            song.setAlbum(updatedSong.getAlbum());
            song.setLyrics(updatedSong.getLyrics());
            song.setArtist(updatedSong.getArtist());
            return songRepository.save(song);
        }
        return null;
    }

    public boolean deleteSong(Long id) {
        Optional<Song> optionalSong = songRepository.findById(id);
        if (optionalSong.isPresent()) {
            songRepository.delete(optionalSong.get());
            return true;
        }
        return false;
    }

    public List<Song> searchSongsByKeyword(String keyword) {
        return songRepository.findAll().stream()
                .filter(song -> song.getLyrics().contains(keyword))
                .collect(Collectors.toList());
    }
}
