package com.example.user.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

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
public class UserParamAdd {

    @Schema(description = "用户id", example = "123")
    @NotBlank(message = "userAccount cannot be null!")
    private String userAccount;

    @NotBlank(message = "userName cannot be null!")
    private String userName;

}
