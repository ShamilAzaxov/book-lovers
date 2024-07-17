package sh.tech.bookloverslibrary.service.rent;



import sh.tech.bookloverslibrary.model.dto.rent.RentRequestDto;
import sh.tech.bookloverslibrary.model.dto.rent.RentResponseDto;

import java.util.List;

public interface RentBookService {
    List<RentResponseDto> getAllRents();
    RentResponseDto getRentById(Long id);
    void saveRent(RentRequestDto requestDto);
    void updateRent(Long id, RentRequestDto rentDto);
    void deleteRentById(Long id);
    List<RentResponseDto> getActiveRents();
}
