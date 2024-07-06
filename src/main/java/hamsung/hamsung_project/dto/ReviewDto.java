package hamsung.hamsung_project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private Long id;
    @JsonProperty("no_late")
    private Boolean noLate;

    private Boolean faithful;

    private Boolean kind;

    private Boolean unkind;
    @JsonProperty("fast_answer")
    private Boolean fastAnswer;
    @JsonProperty("slow_answer")
    private Boolean slowAnswer;

    private Boolean passive;

    private Boolean absent;
    @JsonProperty("user_id")
    private Long userId;

    public Map<String, Boolean> toMap(){
        Map<String, Boolean> dtoMap=new HashMap<>();
        dtoMap.put("noLate", noLate);
        dtoMap.put("faithful", faithful);
        dtoMap.put("kind", kind);
        dtoMap.put("unkind", unkind);
        dtoMap.put("fastAnswer", fastAnswer);
        dtoMap.put("slowAnswer", slowAnswer);
        dtoMap.put("passive", passive);
        dtoMap.put("absent", absent);
        return dtoMap;
    }
}
