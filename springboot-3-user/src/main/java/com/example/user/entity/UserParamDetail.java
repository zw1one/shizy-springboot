package com.example.user.entity;

import com.example.common.json.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 *
 * </p>
 *
 * @author shizy
 * @since 2019-08-19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserParamDetail {

    @NotBlank(message = "userId cannot be null!")
    @Schema(description = "用户id", example = "123")
    private String userId;

}
