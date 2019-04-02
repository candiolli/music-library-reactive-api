package br.com.candiolli.musiclibrary.service;

import br.com.candiolli.musiclibrary.model.Library;
import br.com.candiolli.musiclibrary.repository.LibraryRepository;
import br.com.candiolli.musiclibrary.repository.MusicRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class LibraryService {

    private final LibraryRepository libraryRepository;
    private final MusicRepository musicRepository;

    public LibraryService(LibraryRepository libraryRepository, MusicRepository musicRepository) {
        this.libraryRepository = libraryRepository;
        this.musicRepository = musicRepository;
    }

    public Flux<Library> all () {
        return libraryRepository.findAll();
    }

    public Mono<Library> byId(String id) {
        return libraryRepository.findById(id);
    }

    public Mono<Library> save(Library library) {
        library.setId(UUID.randomUUID().toString());
        return libraryRepository.save(library);
    }

}
