package com.meeting.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestMemberCreateDto {

    @NotBlank
    private String memberName;

    @NotNull
    private Boolean isDeleted;
}
