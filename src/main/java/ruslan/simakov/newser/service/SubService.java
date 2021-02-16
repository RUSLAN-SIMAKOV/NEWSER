package ruslan.simakov.newser.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ruslan.simakov.newser.dto.SubDto;
import ruslan.simakov.newser.exeption.SpringSubNotFondException;
import ruslan.simakov.newser.mapper.SubMapper;
import ruslan.simakov.newser.model.Sub;
import ruslan.simakov.newser.repository.SubRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class SubService {

    private final SubRepository subRepository;
    private final SubMapper subMapper;

    @Transactional
    public SubDto save(SubDto subDto) {
        Sub sub = subRepository.save(subMapper.mapDtoToSub(subDto));
        subDto.setId(sub.getId());
        return subDto;
    }

    @Transactional(readOnly = true)
    public List<SubDto> getAll() {
        return subRepository.findAll()
                .stream()
                .map(subMapper :: mapSubToDto)
                .collect(Collectors.toList());
    }

    public SubDto getSub(Long id) {
        Sub sub = subRepository.findById(id)
                .orElseThrow(() -> new SpringSubNotFondException("Sub not found with id: " + id));
        return subMapper.mapSubToDto(sub);
    }
}
