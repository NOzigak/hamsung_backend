package hamsung.hamsung_project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import hamsung.hamsung_project.entity.Review;
import hamsung.hamsung_project.entity.StudyMember;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class ApplyingSummaryDto {
    private Long id;
    private String username;
    @JsonProperty("review")
    private Review review;


//        // summaryMember 메서드
//    public static List<ApplyingSummaryDto> summaryMembers(StudyMemberDto dto) {
//        return List<ApplyingSummaryDto>(dto.getUser().getId(), dto.getUser().getUsername(), dto.getReview());
//    }


// summaryMembers 메서드
        public static List<ApplyingSummaryDto> summaryMembers (List < StudyMemberDto > studyMemberDto) {
            List<ApplyingSummaryDto> summaryDtos = new ArrayList<>();
            for (StudyMemberDto dto : studyMemberDto) {
                summaryDtos.add(new ApplyingSummaryDto(dto.getUsers().getId(), dto.getUsers().getUsername(), dto.getUsers().getReview()));
            }
            return summaryDtos;
        }


            public static ApplyingSummaryDto createSummaryDto (StudyMember studyMember){
                if (studyMember == null || studyMember.getUsers() == null) {
                    throw new IllegalArgumentException("스터디 멤버나 스터디는 null이 될 수 없음");
                }

                return new ApplyingSummaryDto(
                        studyMember.getUsers().getId(),
                        studyMember.getUsers().getUsername(),
                        studyMember.getUsers().getReview()
                );

            }
        }


