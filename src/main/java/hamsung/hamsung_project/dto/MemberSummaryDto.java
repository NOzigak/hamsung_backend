package hamsung.hamsung_project.dto;

import hamsung.hamsung_project.entity.StudyMember;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class MemberSummaryDto {
    private Long id;
    private String username;

    public static MemberSummaryDto createSummaryDto (StudyMember studyMember){
        if (studyMember == null || studyMember.getUsers() == null) {
            throw new IllegalArgumentException("스터디 멤버나 스터디는 null이 될 수 없음");
        }

        return new MemberSummaryDto(
                studyMember.getUsers().getId(),
                studyMember.getUsers().getUsername()
        );

    }


}
