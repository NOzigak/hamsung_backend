package hamsung.hamsung_project.service;

import hamsung.hamsung_project.dto.RecruitsRequestsDto;
import hamsung.hamsung_project.dto.RecruitsResponseDto;
import hamsung.hamsung_project.entity.Board;
import hamsung.hamsung_project.repository.RecruitsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecruitsService {

    //    @Autowired
    private static RecruitsRepository recruitsRepository; //final

    //게시글 생성
    @Transactional
    public static RecruitsResponseDto createPost(RecruitsRequestsDto requestsDto){
        Board board =new Board(requestsDto);
        recruitsRepository.save(board);
        return new RecruitsResponseDto(board);
    }

//    @Transactional
//    public static RecruitsResponseDto getAPost(RecruitsResponseDto requestsDto){
//
//    }

//    @Transactional
//    public static List<RecruitsResponseDto> getPosts(){
//        return recruitsRepository.findById(Long id)
//    }

//    //게시글 수정
//    @Transactional
//    public RecruitsResponseDto updatePost(Long id,RecruitsRequestsDto requestsDto) {
//        Board board = recruitsRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
//        );
//        board.update(requestsDto); //update 오류...
//        return new RecruitsResponseDto(board);
//    }

    public RecruitsResponseDto findById(Long id){
        Board board=recruitsRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        return new RecruitsResponseDto(board);
    }
}
