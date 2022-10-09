package org.codeworks.web.toolsdashboardwebfacade.dto.aplus;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
public class OrganiseDTO implements Serializable{
    @Builder.Default
    private Long creatorCount = 0L;
    private Integer date;
}
