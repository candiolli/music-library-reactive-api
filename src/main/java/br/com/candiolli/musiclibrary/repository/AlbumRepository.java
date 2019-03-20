package br.com.candiolli.musiclibrary.repository;

import br.com.candiolli.musiclibrary.model.Album;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface AlbumRepository extends ReactiveMongoRepository<Album, String> {
}
