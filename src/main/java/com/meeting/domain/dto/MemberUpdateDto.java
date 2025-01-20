package com.meeting.domain.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberUpdateDto {

    @NotNull
    @Min(0)
    private Long memberSeq;

    private String memberName;

    private Boolean isDeleted;
}
