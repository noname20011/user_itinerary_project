package domain.travel.travel_itinerary.service.helper;

import domain.travel.travel_itinerary.helper.dto.FilePhotoDTO;
import domain.travel.travel_itinerary.service.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class AsyncService {

    private final CloudinaryService cloudinaryService;

    @Async("uploadPhotoExecutor")
    public CompletableFuture<String> uploadPhoto(FilePhotoDTO requestDTO) {
        String url = cloudinaryService.uploadPhoto(requestDTO.getFilePhoto());
        return CompletableFuture.completedFuture(url);
    }

    @Async("deletePhotoExecutor")
    public void deletePhoto(String url) {
        cloudinaryService.deleteDestinationPhoto(url);
        CompletableFuture.completedFuture(null);
    }

}
