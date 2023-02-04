package ru.practicum.ewm.event;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.geo.Point;
import ru.practicum.ewm.category.Category;
import ru.practicum.ewm.event.dto.EventState;
import ru.practicum.ewm.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "events", schema = "public")
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String annotation;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
   // private Long confirmedRequests;

    @CreationTimestamp
    private LocalDateTime createdOn;

    private String description;

    @Column(nullable = false)
    private LocalDateTime eventDate;

    @ManyToOne
    @JoinColumn(name = "initiator_id")
    private User initiator;

    @Column(nullable = false)
    private Point location;

    @Column(nullable = false)
    private Boolean paid;

    private Integer participantLimit;

    private LocalDateTime publishedOn;

    private Boolean requestModeration;

    private EventState state;

    @Column(nullable = false)
    private String title;

    private Long views;

}
