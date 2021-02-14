package ruslan.simakov.newser.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ruslan.simakov.newser.dto.SubDto;
import ruslan.simakov.newser.model.Sub;
import ruslan.simakov.newser.repository.SubRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class SubService {

    private final SubRepository subRepository;

    @Transactional
    public SubDto save(SubDto subDto) {
        Sub sub = subRepository.save(mapSubDto(subDto));
        subDto.setId(sub.getId());
        return subDto;
    }

    @Transactional(readOnly = true)
    public List<SubDto> getAll() {
        return subRepository.findAll()
                .stream()
                .map(this :: mapToDto)
                .collect(Collectors.toList());
    }

    private SubDto mapToDto(Sub sub) {
        return SubDto.builder().name(sub.getName())
                .description(sub.getDescription()).build();
    }

    private Sub mapSubDto(SubDto subDto) {
        return Sub.builder().name(subDto.getName())
                .description(subDto.getDescription())
                .build();
    }
}
