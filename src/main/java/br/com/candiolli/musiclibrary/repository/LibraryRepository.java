package br.com.candiolli.musiclibrary.repository;

import br.com.candiolli.musiclibrary.model.Library;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface LibraryRepository extends ReactiveMongoRepository<Library, String> {
}
