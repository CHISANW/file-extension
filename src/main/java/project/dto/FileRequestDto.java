package project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class FileRequestDto {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class CreateFileRequestDto {
        @NotBlank
        @Size(max = 20,message = "20글자 이상은 추가 할 수 없습니다.")
        private String extension;

        private boolean isDefault;

        public boolean isEmpty() {
            return this.extension.trim().isEmpty();
        }

        public String getExtension() {
            return extension != null ? extension.trim().toLowerCase() : null;
        }

    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class UpdateFileRequestDto {
        @NotNull
        private boolean isActive;

        public void setIsActive(boolean active) {
            this.isActive = active;
        }
    }

    public static class DeleteFileRequestDto {}
}
