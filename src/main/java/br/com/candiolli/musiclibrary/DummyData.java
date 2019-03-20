package br.com.candiolli.musiclibrary;

import br.com.candiolli.musiclibrary.model.Album;
import br.com.candiolli.musiclibrary.model.Music;
import br.com.candiolli.musiclibrary.model.Productor;
import br.com.candiolli.musiclibrary.repository.MusicRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.UUID;

@Component
class DummyData implements CommandLineRunner {

    private final MusicRepository musicRepository;

    DummyData(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Productor george_martin = Productor.builder()
                .id(UUID.randomUUID().toString())
                .name("George Martin")
                .build();

        musicRepository.deleteAll()
                .thenMany(
                        Flux.just(
                                Music.builder()
                                        .id(UUID.randomUUID().toString())
                                        .title("Help!")
                                        .timeDuration(3D)
                                        .album(
                                                Album.builder()
                                                        .id(UUID.randomUUID().toString())
                                                        .title("Help!")
                                                        .author("The Beatles")
                                                        .dateCreate(LocalDate.now())
                                                        .productor(
                                                                george_martin
                                                        )
                                                        .build()
                                        ),
                                Music.builder()
                                        .id(UUID.randomUUID().toString())
                                        .title("Let It Be!")
                                        .timeDuration(3.2D)
                                        .album(
                                                Album.builder()
                                                        .id(UUID.randomUUID().toString())
                                                        .title("Album")
                                                        .author("The Beatles")
                                                        .dateCreate(LocalDate.now())
                                                        .productor(
                                                                george_martin
                                                        )
                                                        .build()
                                        )
                        )
                                .map(music -> music.build())
                                .flatMap(musicRepository::save))
                .subscribe(System.out::println);
    }
}