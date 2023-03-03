package com.example.user.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class UserParamUpdate {

    @Schema(description = "用户id", example = "123")
    private String userId;

    private String userAccount;

    private String userName;

    private String nameAndAccount;

}
