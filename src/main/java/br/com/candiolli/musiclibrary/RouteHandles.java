package br.com.candiolli.musiclibrary;

import br.com.candiolli.musiclibrary.model.Library;
import br.com.candiolli.musiclibrary.model.Music;
import br.com.candiolli.musiclibrary.service.LibraryService;
import br.com.candiolli.musiclibrary.service.MusicService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
public class RouteHandles {
    private final MusicService musicService;
    private final LibraryService libraryService;

    public RouteHandles(MusicService MusicService, LibraryService libraryService) {
        this.musicService = MusicService;
        this.libraryService = libraryService;
    }

    public Mono<ServerResponse> allMusics(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .body(musicService.all(), Music.class)
                .doOnError(throwable -> new IllegalStateException("My godness NOOOOO!!"));
    }

    public Mono<ServerResponse> musicById(ServerRequest serverRequest) {
        String musicId = serverRequest.pathVariable("musicId");
        return ServerResponse.ok()
                .body(musicService.byId(musicId), Music.class)
                .doOnError(throwable -> new IllegalStateException("oh boy... not againnn =(("));
    }

    public Mono<ServerResponse> musicCreate(ServerRequest req) {
        return req.bodyToMono(Music.class)
                .flatMap(music -> musicService.save(music))
                .flatMap(p -> ServerResponse.created(URI.create("/music/" + p.getId())).build());
    }

    public Mono<ServerResponse> allLibrarys(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .body(libraryService.all(), Library.class)
                .doOnError(throwable -> new IllegalStateException("My godness NOOOOO!!"));
    }

    public Mono<ServerResponse> libraryById(ServerRequest serverRequest) {
        String musicId = serverRequest.pathVariable("musicId");
        return ServerResponse.ok()
                .body(libraryService.byId(musicId), Library.class)
                .doOnError(throwable -> new IllegalStateException("oh boy... not againnn =(("));
    }

    public Mono<ServerResponse> libraryCreate(ServerRequest req) {
        Mono<Library> libraryMono = req.bodyToMono(Library.class);

        return validate(libraryMono)
                .flatMap(lib -> libraryService.save(lib))
                .flatMap(p -> ServerResponse.ok()
                            .contentType(MediaType.TEXT_PLAIN)
                            .syncBody(p))
                .onErrorResume(e -> Mono.just("Error " + e.getMessage())
                        .flatMap(s -> ServerResponse.ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .syncBody(s)));
    }

    private Mono<Library> validate(Mono<Library> lib) {
//        Mono<Library> libraryMono = lib.flatMap(li -> {
//            li.getMusicsIds().forEach(m -> musicService.byId(m).doOnError((Consumer<? super Throwable>) ServerResponse.notFound().build()));
//        });
        return Mono.error(new RuntimeException("Music not found"));
    }


}