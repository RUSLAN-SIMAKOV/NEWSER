package ruslan.simakov.newser.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ruslan.simakov.newser.dto.SubDto;
import ruslan.simakov.newser.service.SubService;

import java.util.List;

@RestController
@RequestMapping("/api/sub")
@AllArgsConstructor
@Slf4j
public class SubController {

    private final SubService subService;

    @PostMapping
    public ResponseEntity<SubDto> createSub(@RequestBody SubDto subDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(subService.save(subDto));
    }

    @GetMapping
    public ResponseEntity<List<SubDto>> getAllSub() {
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(subService.getAll());

    }

    @GetMapping({"/{id}"})
    public ResponseEntity<SubDto> getSub(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(subService.getSub(id));
    }
}
