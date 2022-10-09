package org.codeworks.web.toolsdashboardwebfacade.dto.aplus;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class APlusIndexDigestTopDTO implements Serializable{

    @Builder.Default
    private Long count = 0L;
    private String nickName;
    private String ada;
    private String openId;

    @Override
    public String toString() {
        return "{" +
                "制作量=" + count +
                ", 昵称='" + nickName + '\'' +
                ", ADA卡号='" + ada + '\'' +
                ", openId='" + openId + '\'' +
                '}';
    }
}
