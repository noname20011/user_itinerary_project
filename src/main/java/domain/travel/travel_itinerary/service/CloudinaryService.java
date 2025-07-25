package domain.travel.travel_itinerary.service;

import jakarta.transaction.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CloudinaryService {
    String uploadPhoto(MultipartFile file);
    List<String> uploadListPhotos(List<MultipartFile> files);

    @Transactional
    void deleteDestinationPhoto(String url);
}
