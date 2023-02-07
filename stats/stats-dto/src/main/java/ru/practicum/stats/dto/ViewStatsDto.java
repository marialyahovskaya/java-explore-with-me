package ru.practicum.stats.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ViewStatsDto {
    private String app;  // "ewm-service"
    private String uri;  // "/events/124"
    private Long hits;   // 2
}
