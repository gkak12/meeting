package com.meeting.domain.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestMemberUpdateDto {

    @NotNull
    @Min(1)
    private Long memberSeq;

    private String memberName;

    private Boolean isDeleted;
}
