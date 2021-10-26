package com.project.challengeJava.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class MovieDTO {
    private Long id;
    private String title;
    private String image;
}
