package br.com.candiolli.musiclibrary.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Album {

    @Id
    private String id;
    private String title;
    private String author;
    private Productor productor;
    private LocalDate dateCreate;

}
