package org.codeworks.web.toolsdashboardaatool.dto;

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

    private Long count;
    private String createTime;
}
