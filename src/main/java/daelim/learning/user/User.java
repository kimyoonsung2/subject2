package daelim.learning.user;

import daelim.learning.user.dto.UserUpdateRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_USER")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNo;
    private String userId;
    private String password;
    private String userName;
    private String userEmail;
    private String userPhone;
    public void update(UserUpdateRequest updateRequest) {
        this.userName = updateRequest.getUserName();
        this.userEmail = updateRequest.getUserEmail();
        this.userPhone = updateRequest.getUserPhone();
    }
}
