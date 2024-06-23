package hamsung.hamsung_project.dto;

import lombok.Getter;

import java.math.BigInteger;
import java.util.Date;

@Getter
public class RecruitsRequestsDto {

    private Long id;
    private BigInteger user_id;
    private BigInteger study_id;
    private String title;
    private String description;
    private String category;
    private String place;
    private BigInteger capacity;
    private Boolean isRecruit;
    private BigInteger view;
    private Date startDate;
    private Date endDate;


    public Long getRecruitsId() {
        return id;
    }

    public BigInteger getUserId() {
        return user_id;
    }

    public BigInteger getStudyId() {
        return study_id;
    }
}

//    public RecruitsRequestsDto(Board entity){
//        this.recruit_id=entity.getRecruitsId();
//        this.title=entity.getTitle();
//        this.user_id=entity.getUserId();
////        this.study_id=entity.getStudyId();
//        this.description=entity.getDescription();
//        this.category=entity.getCategory();
//        this.place=entity.getPlace();
//        this.capacity=entity.getCapacity();
//        this.isRecruit=entity.getIsRecruit();
//        this.view=entity.getView();
//        this.startDate=entity.getStartDate();
//        this.endDate=entity.getEndDate();
//    }
//
//    public BigInteger getRecruitsId() {
//        return recruit_id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public BigInteger getUserId() {
//        return user_id;
//    }
//
//    public BigInteger getStudyId() {
//        return study_id;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public String getCategory() {
//        return category;
//    }
//
//    public String getPlace() {
//        return place;
//    }
//
//    public Boolean getIsRecruit() {
//        return isRecruit;
//    }
//
//    public BigInteger getView() {
//        return view;
//    }
//
//    public BigInteger getCapacity() {
//        return capacity;
//    }
//
//    public Date getStartDate() {
//        return startDate;
//    }
//
//    public Date getEndDate() {
//        return endDate;
//    }
