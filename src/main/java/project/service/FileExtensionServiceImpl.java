package project.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dto.FileExtensionDto;
import project.dto.FileRequestDto;
import project.entity.FileExtension;
import project.exception.ErrorCode;
import project.exception.FileExtensionException;
import project.repository.FileExtensionRepository;

import java.util.List;

import static project.constants.FileExtensionConstants.DEFAULT_FILE_EXTENSIONS;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class FileExtensionServiceImpl implements FileExtensionService{

    private final FileExtensionRepository fileExtensionRepository;

    @PostConstruct
    public void initFileExtensions() {
        List<FileExtension> allByIsDefaultTrue = fileExtensionRepository.findAllByIsDefaultTrue();
        List<String> existingExtensions = allByIsDefaultTrue.stream()
            .map(FileExtension::getExtension)
            .toList();

        List<String> missingExtensions = DEFAULT_FILE_EXTENSIONS.stream()
            .filter(extension -> !existingExtensions.contains(extension))
            .toList();

        List<FileExtension> fileExtensions = missingExtensions.stream().map(s -> FileExtension.of(s, true)).toList();
        fileExtensionRepository.saveAll(fileExtensions);
    }

    @Override
    public List<FileExtensionDto> getAllDefault() {
        return fileExtensionRepository.findAllByIsDefaultTrue().stream().map(FileExtensionDto::toEntity).toList();
    }
    
    @Override
    public List<FileExtensionDto> getAllFileExtensions() {
        return fileExtensionRepository.findAll().stream().map(FileExtensionDto::toEntity).toList();
    }
    
    @Override
    public FileExtensionDto createFileExtension(FileRequestDto.CreateFileRequestDto body) {
        FileExtension fileExtension = fileExtensionRepository.save(FileExtension.createOf(body));
        return FileExtensionDto.toEntity(fileExtension);
    }
    
    @Override
    public void deleteFileExtension(Long id) {
        fileExtensionExists(id);
        fileExtensionRepository.deleteById(id);
    }
    
    @Override
    public FileExtensionDto updateFileExtensionStatus(String extension, boolean isActive) {
        FileExtension fileExtension = fileExtensionRepository.findByExtension(extension)
                .orElseThrow(() -> new FileExtensionException(ErrorCode.NOT_FOUND));
        fileExtension.updateIsActive(isActive);
        FileExtension updatedExtension = fileExtensionRepository.save(fileExtension);
        return FileExtensionDto.toEntity(updatedExtension);
    }

    private void fileExtensionExists(Long id) {
        boolean existsById = fileExtensionRepository.existsById(id);
        if(!existsById){
            throw new FileExtensionException(ErrorCode.NOT_FOUND);
        }
    }
}
