package me.neatomaru.musicplayer.repository;

import me.neatomaru.musicplayer.model.Song;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SongRepository extends MongoRepository<Song, String> {
}
