package ru.practicum.ewm.event;

import org.springframework.data.geo.Point;
import ru.practicum.ewm.category.Category;
import ru.practicum.ewm.category.CategoryMapper;
import ru.practicum.ewm.event.dto.EventFullDto;
import ru.practicum.ewm.event.dto.Location;
import ru.practicum.ewm.event.dto.NewEventDto;
import ru.practicum.ewm.user.User;
import ru.practicum.ewm.user.UserMapper;
import ru.practicum.ewm.user.dto.UserDto;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class EventMapper {

    public static Event toEvent(User initiator, Category category, NewEventDto eventDto) {
        Event event = new Event();
        event.setAnnotation(eventDto.getAnnotation());
        event.setCategory(category);
        event.setDescription(eventDto.getDescription());
        event.setEventDate(eventDto.getEventDate());
        event.setLocation(new Point(eventDto.getLocation().getLat(), eventDto.getLocation().getLon()));
        event.setPaid(eventDto.getPaid());
        event.setParticipantLimit(eventDto.getParticipantLimit());
        event.setRequestModeration(eventDto.getRequestModeration());
        event.setTitle(eventDto.getTitle());
        event.setInitiator(initiator);

        return event;
    }

    public static EventFullDto toEventFullDto(Event event){

        EventFullDto eventDto = new EventFullDto();
        eventDto.setId(event.getId());
        eventDto.setAnnotation(event.getAnnotation());
        eventDto.setCategory(CategoryMapper.toCategoryDto(event.getCategory()));
        //eventDto.setConfirmedRequests(event.getConfirmedRequests());
        eventDto.setCreatedOn(event.getCreatedOn());
        eventDto.setDescription(event.getDescription());
        eventDto.setEventDate(event.getEventDate());
        eventDto.setInitiator(UserMapper.toUserShortDto(event.getInitiator()));
        eventDto.setLocation(Location.of(event.getLocation()));
        eventDto.setPaid(event.getPaid());
        eventDto.setParticipantLimit(event.getParticipantLimit());
        eventDto.setPublishedOn(event.getPublishedOn());
        eventDto.setRequestModeration(event.getRequestModeration());
        eventDto.setState(event.getState());
        eventDto.setTitle(event.getTitle());
        eventDto.setViews(event.getViews());

        return eventDto;
    }

    public static Collection<EventFullDto> toEventFullDto(Iterable<Event> events) {
        return StreamSupport.stream(events.spliterator(), false)
                .map(EventMapper::toEventFullDto)
                .collect(Collectors.toUnmodifiableList());
    }

}
