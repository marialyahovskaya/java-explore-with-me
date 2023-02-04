package ru.practicum.ewm.request;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import ru.practicum.ewm.request.dto.ParticipationRequestStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "requests", schema = "public")
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ParticipationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime created;

    @Column(nullable = false)
    private Long eventId;

    @Column(nullable = false)
    private Long requesterId;

    @Column(nullable = false)
    private ParticipationRequestStatus status;

    public static ParticipationRequest of(Long userId, Long eventId){
        ParticipationRequest request = new ParticipationRequest();
        request.setRequesterId(userId);
        request.setEventId(eventId);
        request.setStatus(ParticipationRequestStatus.PENDING);

        return request;
    }

}
