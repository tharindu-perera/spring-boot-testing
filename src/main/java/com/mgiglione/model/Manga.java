package com.mgiglione.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Manga {
    private String title="xxxx";
    private String description="des";
    private Integer volumes=123;
    private Double score=3d;
}
