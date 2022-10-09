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
public class RecordOpenIdAndAda implements Serializable{

    private String openId;
    private String ada;
}
