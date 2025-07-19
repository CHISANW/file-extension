package project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.entity.FileExtension;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileExtensionDto {

    private Long id;
    private String extension;
    private boolean isDefault;
    private boolean isActive;


    public static FileExtensionDto toEntity(FileExtension fileExtension){
        return new FileExtensionDto(fileExtension.getId(),fileExtension.getExtension(),fileExtension.isDefault(),fileExtension.isActive());
    }
}
