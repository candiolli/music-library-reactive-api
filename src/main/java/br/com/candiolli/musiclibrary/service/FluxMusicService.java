package br.com.candiolli.musiclibrary.service;

import br.com.candiolli.musiclibrary.model.Music;
import br.com.candiolli.musiclibrary.repository.MusicRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class FluxMusicService {

    private final MusicRepository musicRepository;

    FluxMusicService(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    public Flux<Music> all () {
        return musicRepository.findAll();
    }

    public Mono<Music> byId(String carId) {
        return musicRepository.findById(carId);
    }

    public Mono<Music> save(Music music) {
        music.setId(UUID.randomUUID().toString());
        return musicRepository.save(music);
    }

//    public Flux<CarEvents> streams(String carId) {
//        return byId(carId).flatMapMany(car -> {
//            Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
//            Flux<CarEvents> events = Flux.fromStream(
//                    Stream.generate(() -> new CarEvents(car, new Date())));
//            return Flux.zip(interval, events).map(Tuple2::getT2);
//        });
//    }
}