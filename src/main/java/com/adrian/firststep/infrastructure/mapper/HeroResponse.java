package com.adrian.firststep.infrastructure.mapper;

import java.time.LocalDateTime;

public record HeroResponse(
    String nombre,
    Integer nivel,
    LocalDateTime creadoEl
) {

}
