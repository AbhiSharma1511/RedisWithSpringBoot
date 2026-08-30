package com.company.rediswithspringboot.models;


import lombok.*;

import java.io.Serializable;

@Data
@Setter
@Getter
@AllArgsConstructor
@ToString
public class User implements Serializable {

    private String userId;

    private String name;

    private String email;

    private String phone;

}
