//package domain.travel.travel_itinerary.mapper;
//
//import domain.travel.travel_itinerary.domain.entity.Destination;
//import domain.travel.travel_itinerary.domain.entity.User;
//import domain.travel.travel_itinerary.domain.entity.Visited;
//import domain.travel.travel_itinerary.dto.visited.VisitedRequestDTO;
//import domain.travel.travel_itinerary.dto.visited.VisitedResponseDTO;
//import domain.travel.travel_itinerary.dto.visited.VisitedUpdateDTO;
//import domain.travel.travel_itinerary.helper.base.mapper.BaseMapper;
//import domain.travel.travel_itinerary.repository.DestinationRepository;
//import domain.travel.travel_itinerary.repository.UserRepository;
//import org.mapstruct.*;
//
//import java.util.List;
//import java.util.UUID;
//
//@Mapper(componentModel = "spring", uses = {UserRepository.class, DestinationRepository.class})
//public interface VisitedMapper extends BaseMapper<Visited, VisitedRequestDTO, VisitedResponseDTO, VisitedUpdateDTO> {
//
//    @Override
//    @Mapping(source = "userId", target = "user", qualifiedByName = "mapUser")
//    @Mapping(source = "destinationId", target = "destination", qualifiedByName = "mapDestination")
//    Visited mapToEntity(VisitedRequestDTO visitedRequestDTO);
//
//    @Override
//    @Mapping(source = "user.id", target = "userId")
//    @Mapping(source = "destination.id", target = "destinationId")
//    VisitedResponseDTO mapToResponseDto(Visited visited);
//
//    @Override
//    @Mapping(source = "userId", target = "user", qualifiedByName = "mapUser")
//    @Mapping(source = "destinationId", target = "destination", qualifiedByName = "mapDestination")
//    void updateEntity(VisitedUpdateDTO visitedUpdateDTO, Visited visited);
//
//    @Override
//    List<Visited> mapToListEntities(List<VisitedRequestDTO> visitedRequestDTOS);
//
//    @Override
//    List<VisitedResponseDTO> mapToListResponseDto(List<Visited> visiteds);
//
//    @Override
//    default void updateListEntities(List<VisitedUpdateDTO> updateDTOs, @MappingTarget List<Visited> entities) {
//        if (updateDTOs == null || entities == null || updateDTOs.size() != entities.size()) {
//            throw new IllegalArgumentException("DTO list and entity list must be same size and not null.");
//        }
//        for (int i = 0; i < updateDTOs.size(); i++) {
//            updateEntity(updateDTOs.get(i), entities.get(i));
//        }
//    }
//
//    @Named("mapUser")
//    default User mapUser(UUID userId, @Context UserRepository userRepository) {
//        return userId == null ? null : userRepository.findByIdOrThrow(userId);
//    }
//
//    @Named("mapUser")
//    default Destination mapDestination(UUID destinationId, @Context DestinationRepository destinationRepository) {
//        return destinationId == null ? null : destinationRepository.findByIdOrThrow(destinationId);
//    }
//}
