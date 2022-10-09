package org.codeworks.web.toolsdashboardwebfacade.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.concurrent.CompletableFuture;

@Data
@Builder
public class FutureObject<T> implements Serializable{
    private String name;
    private CompletableFuture<T> future;
}
