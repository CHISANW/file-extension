package project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SuccessResponse {
    private boolean success;

    public static SuccessResponse of(){
        return new SuccessResponse(true);
    }

}
