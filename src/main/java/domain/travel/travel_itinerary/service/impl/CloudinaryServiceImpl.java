package domain.travel.travel_itinerary.service.impl;

import com.cloudinary.Cloudinary;
import domain.travel.travel_itinerary.exception.IllegalArgumentException;
import domain.travel.travel_itinerary.exception.UploadFileFailException;
import domain.travel.travel_itinerary.service.CloudinaryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryServiceImpl implements CloudinaryService {

    private final Cloudinary cloudinary;

    @Transactional
    @Override
    public String uploadPhoto(MultipartFile file) {
        try {
//            Check file's size less than 10MB
            if(file.getSize() > 10 * 1024 * 1024) {
                throw new IllegalArgumentException("File is too large. Maximum allowed is 10MB");
            }

            Map<?, ?> response = cloudinary.uploader().upload(file.getBytes(), Map.of());
            return response.get("secure_url").toString();
        } catch (IOException e) {
            throw new UploadFileFailException(e.getMessage(),  e.getCause());
        }
    }

    @Transactional
    @Override
    public List<String> uploadListPhotos(List<MultipartFile> files) {
        List<String> listUrls = new ArrayList<>();
        files.forEach(file -> {
            listUrls.add(uploadPhoto(file));
        });
        return listUrls;
    }

    @Transactional
    @Override
    public void deleteDestinationPhoto(String url) {
        try {
//            Get name File Photo
            String[] parts = url.split("/");
            String nameFile = parts[parts.length - 1];
            String publicId = nameFile.split("\\.")[0];

            cloudinary.uploader().destroy(publicId, null);
        } catch (IOException e) {
            throw new UploadFileFailException(e.getMessage(),  e.getCause());
        }
    }
}
