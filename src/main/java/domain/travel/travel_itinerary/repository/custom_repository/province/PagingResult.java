package domain.travel.travel_itinerary.repository.custom_repository.province;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class PagingResult<T> {
    private int page;
    private int size;
    private long total;
    private List<T> data;
}
