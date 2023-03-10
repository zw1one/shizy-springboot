package com.example.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
public class UserVo implements Serializable {

    public static final long serialVersionUID = 1L;

    private String userId;

    private String userAccount;

    private String userName;

}
