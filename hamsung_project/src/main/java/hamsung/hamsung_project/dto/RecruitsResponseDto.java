package hamsung.hamsung_project.dto;

import hamsung.hamsung_project.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;

@Getter
@NoArgsConstructor
public class RecruitsResponseDto {
    private Long recruits_id;
    private BigInteger user_id;
    private BigInteger study_id;
    private String title;
    private String description;
    private String category;
    private BigInteger capacity;
    private String place;
    private BigInteger view;
    private Date startDate;
    private Date endDate;

    public RecruitsResponseDto(Board entity){
        this.recruits_id=entity.getRecruitsId();
        this.user_id=entity.getUserId();
        this.study_id=entity.getStudyId();
        this.title=entity.getTitle();
        this.description=entity.getDescription();
        this.category=entity.getCategory();
        this.capacity=entity.getCapacity();
        this.place=entity.getPlace();
        this.view=entity.getView();
        this.startDate=entity.getStartDate();
        this.endDate=entity.getEndDate();
    }



}


