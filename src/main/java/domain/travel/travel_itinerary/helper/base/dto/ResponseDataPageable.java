package domain.travel.travel_itinerary.helper.base.dto;
import lombok.Data;

@Data
public class ResponseDataPageable<T> extends ResponseData<T> {
    private int page;
    private int size;
    private long total;

    public ResponseDataPageable(int status, String message, T data, int page, int size, long total) {
        super(status, message, data);
        this.total = total;
        this.page = page;
        this.size = size;
    }
}
