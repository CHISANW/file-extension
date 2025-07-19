package project.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import project.dto.FileRequestDto;

import java.time.LocalDateTime;

import static project.dto.FileRequestDto.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileExtension {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private boolean isDefault;

    @Column
    private String extension;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column
    private boolean isActive;

    @LastModifiedDate
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public FileExtension(String extension,boolean isDefault,boolean isActive) {
        this.isDefault = isDefault;
        this.extension = extension;
        this.isActive = isActive;
    }

    public static FileExtension of(String extension, boolean isDefault) {
        return new FileExtension(extension,isDefault,false);
    }

    public void updateIsActive(boolean isActive){
        this.isActive = isActive;
    }

    public static FileExtension createOf(CreateFileRequestDto body){
        return new FileExtension(body.getExtension(),body.isDefault(),true);
    }

}
