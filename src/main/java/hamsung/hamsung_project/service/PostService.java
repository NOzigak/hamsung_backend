package hamsung.hamsung_project.service;

import hamsung.hamsung_project.dto.PostDto;
import hamsung.hamsung_project.entity.Post;
import hamsung.hamsung_project.entity.Study;
import hamsung.hamsung_project.repository.PostRepository;
import hamsung.hamsung_project.repository.StudyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    StudyRepository studyRepository;

    @Transactional
    public PostDto createPost(Long studyId, PostDto dto){
        //대상 스터디 있나 체크
        Study target=studyRepository.findById(studyId).orElseThrow(()-> new IllegalArgumentException("공지사항 생성 실패! "+"대상 스터디가 없습니다."));
        //공지사항 엔티티 생성
        if(dto.getType().equals("announcement")&&(dto.getTitle()==null||dto.getTitle().isEmpty()))
            throw new IllegalArgumentException("공지사항은 제목이 필요합니다");
        if(dto.getType().equals("schedule")&&(dto.getDueDate()==null))
            throw new IllegalArgumentException("일정 기록은 수행 날짜가 필요합니다");
        Post post=Post.createPost(target,dto);
        //공지사항 엔티티 db에 저장
        postRepository.save(post);
        //공지사항 dto로 변환해 return
        return PostDto.createPostDto(post);
    }

    @Transactional
    public PostDto update(Long postId, PostDto dto) {
        Post target=postRepository.findById(postId).orElseThrow(()-> new IllegalArgumentException("수정 실패! 해당 id의 대상이 없습니다"));
        Study study=studyRepository.findById(dto.getStudyId()).orElseThrow(()->new IllegalArgumentException("수정 실패! 해당 스터디가 없습니다"));
        Post post=dto.toEntity(study);
        Post updated=postRepository.save(post);
        return PostDto.createPostDto(updated);
    }

    public PostDto showOnePost(Long postId) {

        Post target=postRepository.findById(postId).orElseThrow(()->new IllegalArgumentException("조회 실패! 해당 id의 공지사항/일정이 존재하지 않습니다."));

        return PostDto.createPostDto(target);
    }

    public boolean deletePost(Long postId) {
        Post target=postRepository.findById(postId).orElseThrow(()->new IllegalArgumentException("삭제 실패! 해당 id의 공지사항/일정이 존재하지 않습니다."));
        postRepository.delete(target);
        return true;
    }

    public List<PostDto> showStudyPost(Long studyId, String type) {
        studyRepository.findById(studyId).orElseThrow(() -> new IllegalArgumentException("해당 스터디가 존재하지 않습니다!"));
        List<Post> studyPost = postRepository.findByStudy_Id(studyId);
        List<PostDto> announceList = new ArrayList<PostDto>();
        List<PostDto> scheduleList = new ArrayList<PostDto>();
        if (type.equals("announcement")) {
            for (Post post : studyPost) {
                if (post.getType().equals("announcement")) {
                    announceList.add(PostDto.createPostDto(post));
                }
            }
            return announceList;
        } else if (type.equals("schedule")) {
            for (Post post : studyPost) {
                if (post.getType().equals("schedule")) {
                    scheduleList.add(PostDto.createPostDto(post));
                }
            }
            return scheduleList;
        } else return null;

    }

    public List<PostDto> showDatePost(Long studyId, LocalDate date) {
        List<Post> studyPost=postRepository.findByStudy_Id(studyId);
        List<PostDto> datePostList=new ArrayList<>();
        for(Post post:studyPost){
            if(date.equals(post.getDueDate())){
                datePostList.add(PostDto.createPostDto(post));
            }
        }
        return datePostList;
    }

    public List<PostDto> showAllPosts(Long studyId) {
        studyRepository.findById(studyId).orElseThrow(() -> new IllegalArgumentException("해당 스터디가 존재하지 않습니다!"));
        List<Post> postList=postRepository.findByStudy_Id(studyId);

        List<PostDto> postDtoList=new ArrayList<>();
        for(Post post:postList){
            postDtoList.add(PostDto.createPostDto(post));
        }
        return postDtoList;
    }
}
