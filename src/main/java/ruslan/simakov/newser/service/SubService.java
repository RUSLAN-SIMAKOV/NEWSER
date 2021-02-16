package ruslan.simakov.newser.service;

import ruslan.simakov.newser.dto.SubDto;

import java.util.List;

public interface SubService {

    SubDto save(SubDto subDto);

    List<SubDto> getAll();

    SubDto getSub(Long id);
}
