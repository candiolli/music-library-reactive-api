package br.com.candiolli.musiclibrary;

import br.com.candiolli.musiclibrary.model.Music;
import br.com.candiolli.musiclibrary.service.FluxMusicService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class RouteHandles {
    private final FluxMusicService fluxMusicService;

    public RouteHandles(FluxMusicService FluxMusicService) {
        this.fluxMusicService = FluxMusicService;
    }

    public Mono<ServerResponse> allCars(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .body(fluxMusicService.all(), Music.class)
                .doOnError(throwable -> new IllegalStateException("My godness NOOOOO!!"));
    }

    public Mono<ServerResponse> carById(ServerRequest serverRequest) {
        String carId = serverRequest.pathVariable("carId");
        return ServerResponse.ok()
                .body(fluxMusicService.byId(carId), Music.class)
                .doOnError(throwable -> new IllegalStateException("oh boy... not againnn =(("));
    }

//    public Mono<ServerResponse> events(ServerRequest serverRequest) {
//        String carId = serverRequest.pathVariable("carId");
//        return ServerResponse.ok()
//                .contentType(MediaType.TEXT_EVENT_STREAM)
//                .body(fluxMusicService.streams(carId), CarEvents.class)
//                .doOnError(throwable -> new IllegalStateException("I give up!! "));
//    }
}