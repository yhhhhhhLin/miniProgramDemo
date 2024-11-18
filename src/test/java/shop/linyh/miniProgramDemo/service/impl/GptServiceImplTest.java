package shop.linyh.miniProgramDemo.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GptServiceImplTest {

    @Autowired
    private GptServiceImpl gptService;

    @Test
    public void testGptService() {
        gptService.test2();

    }

}