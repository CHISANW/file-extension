package project.service;

import project.dto.FileExtensionDto;

import java.util.List;

import static project.dto.FileRequestDto.*;

public interface FileExtensionService {

    List<FileExtensionDto> getAllDefault();
    
    List<FileExtensionDto> getAllFileExtensions();
    
    FileExtensionDto createFileExtension(CreateFileRequestDto body);
    
    void deleteFileExtension(Long id);
    
    FileExtensionDto updateFileExtensionStatus(String extension, boolean isActive);
}
