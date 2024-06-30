package hamsung.hamsung_project.task;

import hamsung.hamsung_project.repository.RefreshRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ExpriationCleanUpTask {

    private final RefreshRepository refreshRepository;

    public ExpriationCleanUpTask(RefreshRepository refreshRepository) {
        this.refreshRepository = refreshRepository;
    }

    @Scheduled(cron = "0 0 0 * * *") // 매일 자정(00시 00분)에 실행
    public void cleanUpExpiredData(){
        System.out.println("Task 실행");
        refreshRepository.deleteExpiredTokens();
    }

}
