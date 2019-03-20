package br.com.candiolli.musiclibrary.repository;

import br.com.candiolli.musiclibrary.model.Productor;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductorRepository extends ReactiveMongoRepository<Productor, String> {
}
