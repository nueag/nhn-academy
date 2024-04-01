package com.nhnacademy.assignment.controller.request;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentRegisterRequest {
    private Long id;
    @NotBlank(message = "이름은 필수 항목입니다.")
    @Size(min = 1, message = "이름은 공백을 제거한 후 1글자 이상이어야 합니다.")
    private String name;

    @NotBlank(message = "이메일은 필수 항목입니다.")
    @Email(message = "올바른 이메일 형식이어야 합니다.")
    private String email;

    @Min(value = 0, message = "점수는 0점 이상이어야 합니다.")
    @Max(value = 100, message = "점수는 100점 이하이어야 합니다.")
    private int score;

    @NotBlank(message = "평가는 필수 항목입니다.")
    private String comment;


    @Override
    public String toString() {
        return "StudentRegisterRequest{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", score=" + score +
                ", comment='" + comment + '\'' +
                '}';
    }
}