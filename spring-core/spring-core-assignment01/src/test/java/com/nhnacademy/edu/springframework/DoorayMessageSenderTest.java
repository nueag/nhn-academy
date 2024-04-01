package com.nhnacademy.edu.springframework;

import com.nhn.dooray.client.DoorayHookSender;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

class DoorayMessageSenderTest {
    @Mock
    private DoorayHookSender doorayHookSender;

    @InjectMocks
    private MessageSendService messageSendService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("DoorayMessageSender 성공 테스트")
    public void testO() {
        User user = new User("이가은");
        ReflectionTestUtils.setField(messageSendService, "messageSender", new DoorayMessageSender(doorayHookSender));
        boolean actual = messageSendService.sendMessage(user, "hello");
        Assertions.assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("DoorayMessageSender 실패 테스트")
    public void testX() {
        ReflectionTestUtils.setField(messageSendService, "messageSender", new DoorayMessageSender(doorayHookSender));
        boolean actual = messageSendService.sendMessage(null, "hello");
        Assertions.assertThat(actual).isFalse();
    }
}