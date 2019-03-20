package br.com.candiolli.musiclibrary.repository;

import br.com.candiolli.musiclibrary.model.Music;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MusicRepository extends ReactiveMongoRepository<Music, String> {
}
