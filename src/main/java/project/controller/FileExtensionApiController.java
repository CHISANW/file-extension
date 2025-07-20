package project.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.dto.FileExtensionDto;
import project.dto.SuccessResponse;
import project.service.FileExtensionService;

import static project.dto.FileRequestDto.*;

@Slf4j
@RestController
@RequestMapping("/api/extensions")
@RequiredArgsConstructor
public class FileExtensionApiController {

    private final FileExtensionService fileExtensionService;

    @PostMapping
    public ResponseEntity<FileExtensionDto> createFileExtension(@Valid @RequestBody CreateFileRequestDto body) {
        FileExtensionDto createdExtension = fileExtensionService.createFileExtension(body);
        return ResponseEntity.ok(createdExtension);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse> deleteFileExtension(@PathVariable Long id) {
        fileExtensionService.deleteFileExtension(id);
        return ResponseEntity.ok(SuccessResponse.of());
    }

    @PutMapping("/default/{extension}")
    public ResponseEntity<FileExtensionDto> updateFileExtensionStatus(
            @PathVariable String extension,
            @Valid @RequestBody UpdateFileRequestDto body) {
        FileExtensionDto updatedExtension = fileExtensionService.updateFileExtensionStatus(extension, body.isActive());
        return ResponseEntity.ok(updatedExtension);
    }
}