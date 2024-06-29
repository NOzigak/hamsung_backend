package hamsung.hamsung_project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import hamsung.hamsung_project.entity.Board;
import hamsung.hamsung_project.entity.Study;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
public class RecruitsRequestsDto {

    private Long id;
    @JsonProperty("user_id")
    private Long userId;
    //    @JsonProperty("study_id")
//    private Long study_id;
    private String title;
    private String description;
    private String category;
    private String place;
    private BigInteger capacity;
    private Boolean isRecruit;
    private Long view; //response로 가야할듯?
    private LocalDate startDate;
    private LocalDate endDate;

//    //?
//    public Board toEntity(){
//        Board board=new Board();
//        board.setId(this.id);
//        board.setUser_id(this.user_id);
////        board.setStudy_id(this.study_id);
//        board.setTitle(this.title);
//        board.setDescription(this.description);
//        board.setCategory(this.category);
//        board.setPlace(this.place);
//        board.setView(this.view);
//        board.setStartDate(this.start_date);
//        board.setEndDate(this.end_date);
//
//        return board;



}


