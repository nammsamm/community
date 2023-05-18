package efub.assignment.community.heart.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HeartRequestDto {
    @NotNull(message = "작성자는 필수로 입력되어야 합니다.")
    private Long memberId;

    @Builder
    public HeartRequestDto(Long memberId){
        this.memberId=memberId;
    }
}
