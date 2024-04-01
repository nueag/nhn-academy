package com.nhnacademy.exam.interceptor;

import com.nhnacademy.exam.exception.AuthNotFoundException;
import com.nhnacademy.exam.exception.MediaNotFountException;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class UserCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String userCheck = request.getHeader("X-USER-ID");
        String mediaType = request.getHeader("Accept");
        if (Objects.isNull(userCheck)) {
            throw new AuthNotFoundException("Unauthorized");
        }
        System.out.println(mediaType);
        if (Objects.nonNull(mediaType)) {
            if (!mediaType.equals("application/json")) {
                throw new MediaNotFountException("Could not find acceptable representation");
            }
        }

        boolean isEmpty = ("").equals(userCheck);
        boolean isUser = request.getHeader("X-USER-ID").equals("nhnacademy");

        if (isEmpty || !isUser) {
            throw new AuthNotFoundException("Unauthorized");
        }

        return true;
    }
}
