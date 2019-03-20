package br.com.candiolli.musiclibrary;

import br.com.candiolli.musiclibrary.model.Music;
import br.com.candiolli.musiclibrary.service.FluxMusicService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
public class RouteHandles {
    private final FluxMusicService fluxMusicService;

    public RouteHandles(FluxMusicService FluxMusicService) {
        this.fluxMusicService = FluxMusicService;
    }

    public Mono<ServerResponse> allMusics(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .body(fluxMusicService.all(), Music.class)
                .doOnError(throwable -> new IllegalStateException("My godness NOOOOO!!"));
    }

    public Mono<ServerResponse> musicById(ServerRequest serverRequest) {
        String musicId = serverRequest.pathVariable("musicId");
        return ServerResponse.ok()
                .body(fluxMusicService.byId(musicId), Music.class)
                .doOnError(throwable -> new IllegalStateException("oh boy... not againnn =(("));
    }

    public Mono<ServerResponse> musicCreate(ServerRequest req) {
        return req.bodyToMono(Music.class)
                .flatMap(music -> fluxMusicService.save(music))
                .flatMap(p -> ServerResponse.created(URI.create("/music/" + p.getId())).build());
    }

//    public ServerResponse.BodyBuilder musicCreate(ServerRequest serverRequest) {
//        Mono<Music> musicMono = serverRequest.bodyToMono(Music.class);
//        musicMono.flatMap(music -> fluxMusicService.save(music));
//        return ServerResponse.ok();
//                .flatMap(music,  Music.class)
//                .doOnError(throwable -> new IllegalStateException("oh boy... not againnn =(("));

//        return ServerResponse.ok()
//                .body(fluxMusicService.save(music), Music.class)
//                .doOnError(throwable -> new IllegalStateException("oh boy... not againnn =(("));
//    }

//    public Mono<ServerResponse> events(ServerRequest serverRequest) {
//        String carId = serverRequest.pathVariable("carId");
//        return ServerResponse.ok()
//                .contentType(MediaType.TEXT_EVENT_STREAM)
//                .body(fluxMusicService.streams(carId), CarEvents.class)
//                .doOnError(throwable -> new IllegalStateException("I give up!! "));
//    }
}