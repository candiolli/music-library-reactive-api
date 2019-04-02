package br.com.candiolli.musiclibrary;

import br.com.candiolli.musiclibrary.model.Library;
import br.com.candiolli.musiclibrary.model.Music;
import br.com.candiolli.musiclibrary.service.LibraryService;
import br.com.candiolli.musiclibrary.service.MusicService;
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
        Mono<Library> library = req.bodyToMono(Library.class);
        //doOnError(thr -> thr.printStackTrace())

        Mono<ServerResponse> serverResponseMono = library
//                .map(lib -> {
//                    lib.getMusicsIds()
//                            .()
//                            .flatMap(m -> {
//                                musicService.byId(m).doOnError(throwable -> throwable.printStackTrace());
//                            })
//                            .collect(lib);
//                })
                .map(lib2 -> this::validate)
                .flatMap(lib -> libraryService.save(lib))
                .flatMap(p -> ServerResponse.created(URI.create("/library/" + p.getId())).build());
        return serverResponseMono;
    }

    private Mono validate(Library lib) {

    }
//    public ServerResponse.BodyBuilder musicCreate(ServerRequest serverRequest) {
//        Mono<Music> musicMono = serverRequest.bodyToMono(Music.class);
//        musicMono.flatMap(music -> musicService.save(music));
//        return ServerResponse.ok();
//                .flatMap(music,  Music.class)
//                .doOnError(throwable -> new IllegalStateException("oh boy... not againnn =(("));

//        return ServerResponse.ok()
//                .body(musicService.save(music), Music.class)
//                .doOnError(throwable -> new IllegalStateException("oh boy... not againnn =(("));
//    }

//    public Mono<ServerResponse> events(ServerRequest serverRequest) {
//        String carId = serverRequest.pathVariable("carId");
//        return ServerResponse.ok()
//                .contentType(MediaType.TEXT_EVENT_STREAM)
//                .body(musicService.streams(carId), CarEvents.class)
//                .doOnError(throwable -> new IllegalStateException("I give up!! "));
//    }
}