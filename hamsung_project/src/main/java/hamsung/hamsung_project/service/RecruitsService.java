package hamsung.hamsung_project.service;

import hamsung.hamsung_project.dto.*;
import hamsung.hamsung_project.entity.Board;
import hamsung.hamsung_project.entity.StudyMember;
import hamsung.hamsung_project.repository.RecruitsRepository;
import hamsung.hamsung_project.repository.StudyMemberRepository;
import hamsung.hamsung_project.repository.StudyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor


public class RecruitsService {

    @Autowired
    private final RecruitsRepository recruitsRepository; //final
    private final StudyRepository studyRepository;
//        private final ReviewRepository reviewRepository;
    private final StudyMemberRepository studyMemberRepository;
    private final StudyMemberService studyMemberService;
    //게시글 생성 //스터디 생성도 해줘야.
    @Transactional
    public RecruitsResponseDto createRecruit(RecruitsRequestsDto requestsDto){
        //엔티티 생성
        Board recruit =Board.createRecruit(requestsDto);
        //엔티티->데이터베이스에 저장
        recruitsRepository.save(recruit);
        //dto로 변환해 return
        return RecruitsResponseDto.createRecruitsDTO(recruit);
    }

    //게시글 상세 조회
    @Transactional
    public RecruitsResponseDto getAPost(Long id){
        Board myRecruits=recruitsRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("해당 게시글을 찾을 수 없습니다.")
        );
        return RecruitsResponseDto.createRecruitsDTO(myRecruits);

    }

    //게시글 상세보기-조회수 증가
    @Transactional
    public int updateReadCount(Long id){
        return recruitsRepository.updateReadCount(id);
    }

    //게시글 목록 조회
    @Transactional
    public List<BoardSummaryDto> showAllRecruits(){
        List<BoardSummaryDto> recruitList=recruitsRepository.findAllBoardSummaries();
        return recruitList;
    }

    //게시글 수정
    @Transactional
    public RecruitsResponseDto updateRecruits(Long id,RecruitsRequestsDto requestsDto) {
        Board updatedRecruits=Board.createRecruit(requestsDto);
        Board findBoard = recruitsRepository.findById(updatedRecruits.getId()).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );
        if(findBoard!=null)
            recruitsRepository.save(updatedRecruits);
        return RecruitsResponseDto.createRecruitsDTO(updatedRecruits);
    }


    //게시글 삭제-완료
    @Transactional
    public boolean deletePost(Long id){
        Board target=recruitsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글을 찾을 수 없습니다."));
        recruitsRepository.delete(target);
        return true;
    }

    //스터디 모집 상태 변경(버튼 클릭?)
    //기본(모집중)=1, (버튼 누르면)모집완료=0
    @Transactional
    public boolean changeStatus(Long id){
        Board target=recruitsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글을 찾을 수 없습니다."));
        if(!target.getIsRecruit()){
            target.setIsRecruit(true);
        } else{
            target.setIsRecruit(false);
        }
        recruitsRepository.save(target);
        return target.getIsRecruit();
    }

    //스터디 지원하기 , user, review 가져오기.
    @Transactional
    public boolean applyStudy(Long id, ApplyingDto applyingDto){
        Board target=recruitsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글을 찾을 수 없습니다."));
        //user,review 추가
        StudyMember studyMember=studyMemberService.createStudyMember(applyingDto);
        studyMemberRepository.save(studyMember);
        return true; //유효한지 true, false 수정해줘야..
    }

//    public RecruitsResponseDto findById(Long id){
//        Board board=recruitsRepository.findById(id).orElseThrow(
//                ()->new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
//        return new RecruitsResponseDto(board);
//    }
}