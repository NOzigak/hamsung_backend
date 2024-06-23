package hamsung.hamsung_project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Study {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name="category",nullable=false)
        private String category;

        @Column(name="place")
        private String place;

        @Column(name = "member_num")
        private Long member_num;

        @Column(name="status")
        private Boolean status;

        @Column(name = "start_date")
        private LocalDate startDate;

        @Column(name = "end_date")
        private LocalDate endDate;

        @Column(name="score")
        private Long score;

        @Column(name="leader_id", nullable=false)
        private Long leader_id;

    }

