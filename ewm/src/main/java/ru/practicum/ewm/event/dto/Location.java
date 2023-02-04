package ru.practicum.ewm.event.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.geo.Point;

@Setter
@Getter
public class Location {

    private Double lat;
    private Double lon;

    public static Location of(Point point) {
            Location location = new Location();
            location.setLat(point.getX());
            location.setLon(point.getY());

            return location;
    }
}
