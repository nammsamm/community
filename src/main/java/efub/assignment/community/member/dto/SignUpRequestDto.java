package efub.assignment.community.member.dto;

import efub.assignment.community.member.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpRequestDto {
    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "유효하지 않은 이메일 형식입니다.",
            regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
    private String email;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!.?,])[A-Za-z\\d!.?,]{2,16}$",
            message = "16자 이내의 영문자 및 숫자와!,1, , 특수문자로 입력해주세요.")
    private String password;

    @NotBlank(message = "닉네임은 필수입니다.")
    private String nickname;

    @NotBlank(message = "학교 이름은 필수입니다.") //추가
    private String university;

    @NotNull(message = "학번은 필수입니다.") //추가 -> Integer은 NotNull을 이용
    private Integer studentId;

    @Builder
    public SignUpRequestDto(String email, String password, String nickname, String university, Integer studentId){
        this.email=email;
        this.password=password;
        this.nickname=nickname;
        this.university=university;
        this.studentId=studentId;
    }

    public Member toEntity(){
        return Member.builder()
                .email(this.email)
                .password(this.password)
                .nickname(this.nickname)
                .university(this.university)
                .studentId(this.studentId)
                .build();
    }
}