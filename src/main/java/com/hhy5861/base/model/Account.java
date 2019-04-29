package com.hhy5861.base.model;

import lombok.Data;

@Data
public class Account {
    private Integer id;

    private String uuid;

    private String name;

    private String actualName;

    private Integer createdByUserId;

    private String department;

    private String email;

    private String mobile;

    private String roleId;

    private Integer isAdmin;

    private Integer status;

    private Long createdAt;

    private Long updatedAt;

    private Boolean valid;
}