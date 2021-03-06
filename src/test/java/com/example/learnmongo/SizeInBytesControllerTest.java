package com.example.learnmongo;

import com.example.learnmongo.entity.SizeInBytesBean;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SizeInBytesControllerTest {

    @Autowired
    public WebTestClient webTestClient;

    @Test
    public void testSizeInBytesSuccess() {

        SizeInBytesBean sizeInBytesBean = new SizeInBytesBean();
        sizeInBytesBean.setInputData("abcdefgh");

        webTestClient.post().uri("/validatesize")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(sizeInBytesBean), SizeInBytesBean.class)
                .exchange()
                .expectStatus().isCreated();

    }

    @Test
    public void testSizeInBytesFailure() {

        SizeInBytesBean sizeInBytesBean = new SizeInBytesBean();
        sizeInBytesBean.setInputData("abcdefgh*=aw3134");

        webTestClient.post().uri("/validatesize")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(sizeInBytesBean), SizeInBytesBean.class)
                .exchange()
                .expectStatus().is4xxClientError();

    }
}
