package org.codeworks.web.toolsdashboardwebfacade.dto.aatool;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecordCountAndCreateTime implements Serializable{

    @Builder.Default
    private Long count = 0L;
    private String createTime;
}
