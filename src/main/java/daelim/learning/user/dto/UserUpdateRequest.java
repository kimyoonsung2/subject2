package daelim.learning.user.dto;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private String userName;
    private String userEmail;
    private String userPhone;
}
