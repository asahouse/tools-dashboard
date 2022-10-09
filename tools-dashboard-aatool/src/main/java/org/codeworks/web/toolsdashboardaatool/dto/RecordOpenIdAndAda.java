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
public class RecordOpenIdAndAda implements Serializable{

    private String openId;
    private String ada;
}
