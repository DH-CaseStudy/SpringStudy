package dh.spring.springbootdeveloper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;




@SpringBootTest
@AutoConfigureMockMvc
class QuizControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper; // 오브젝트 맵퍼는 Jackson 라이브러리에서 제공하는 클래스로 객체와 Json 간 변환을 처리해준다.

    @BeforeEach
    public void mockMvcSetup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @DisplayName("quiz(): GET/ quiz?code=1 이면 응답 코드는 201, 응답 본문은 Created!를 리턴한다.")
    @Test
    public void getQuiz() throws Exception {

        //given 상황이 주어진다.
        final String url = "/quiz";

        //when 특정 행동을 했을 떄
        final ResultActions result = mockMvc.perform(get(url).param("code", "1"));
        //quiz?code=1 요청을 보내는 것

        //then 결과는 이렇게 되어야한다.
        result.andExpect(status().isCreated())
                .andExpect(content().string("Created!"));
    }

    @DisplayName("quiz(): GET/ quiz?code=2 이면 응답 코드는 400, 응답 본문은 Bad Request!를 리턴한다.")
    @Test
    public void getQuiz2() throws Exception {

        final String url = "/quiz";

        final ResultActions result = mockMvc.perform(get(url).param("code", "2"));
        //quiz?code=2

        result.andExpect(status().isBadRequest())
                .andExpect(content().string("Bad Request!"));
    }

    @DisplayName("quiz() POST/ quiz에 요청 본문이 {\"value\":1} 이면 응답 코드는 403, 응답 본문은 Forbidden!를 리턴한다.")
    @Test
    public void postQuiz1() throws Exception {
        final String url = "/quiz";

        final ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new QuizController.Code(1))));
//        {
//            "value": 1
//        } 이러한 Json 데이터 형식으로 보낸다.


        result.andExpect(status().isForbidden()).andExpect(content().string("Forbidden!"));

    }

    @DisplayName("quiz() POST/ quiz에 요청 본문이 {\"value\":13} 이면 응답 코드는 200, 응답 본문은 OK!를 리턴한다.")
    @Test
    public void postQuiz13() throws Exception {
        final String url = "/quiz";

        final ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new QuizController.Code(13))));

        result.andExpect(status().isOk()).andExpect(content().string("OK!"));
    }
}