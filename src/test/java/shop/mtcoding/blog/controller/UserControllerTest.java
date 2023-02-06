package shop.mtcoding.blog.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import shop.mtcoding.blog.model.User;

/*
 * SprngBootTest는 통합테스트 [실제 환경과 동일하게 Bean(new된 객체)이 생성됨]
 * AutoConfigureMockMvc는 Mock 환경의 IoC컨테이너에 MockMvc Bean이 생성됨
 */

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    // test는 순서 보장이 안됨
    public void join_test() throws Exception {
        // given
        String requestBody = "username=cos&password=1234&email=cos@nate.com";

        // when
        ResultActions resultActions = mvc.perform(post("/join").content(requestBody)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE));

        // then
        resultActions.andExpect(status().is3xxRedirection());
    }

    @Test
    public void login_test() throws Exception {
        // given
        String requestBody = "username=ssar&password=1234";

        // when
        // mvc.perform은 메소드를 떄릴 수 있음
        // ResultActions resultActions는 request와 response 정보 다 가지고 있음
        ResultActions resultActions = mvc.perform(post("/login").content(requestBody)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)); // 마임 타입

        HttpSession session = resultActions.andReturn().getRequest().getSession();
        User principal = (User) session.getAttribute("principal");
        System.out.println(principal.getUsername());

        // then
        assertThat(principal.getUsername()).isEqualTo("ssar");
        resultActions.andExpect(status().is3xxRedirection());
    }
}