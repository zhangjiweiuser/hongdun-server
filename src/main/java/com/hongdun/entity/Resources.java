package com.hongdun.entity;

import lombok.*;

/**
 * @author zhang
 * @date 2019-03-07 下午 17:58
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Resources {
    private Integer id;
    private String url;

    public Resources(String url) {
        this.url = url;
    }
}
