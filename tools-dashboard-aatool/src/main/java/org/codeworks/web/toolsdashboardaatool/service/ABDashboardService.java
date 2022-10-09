package org.codeworks.web.toolsdashboardaatool.service;

import org.codeworks.web.toolsdashboardaatool.dto.FutureObject;
import org.codeworks.web.toolsdashboardaatool.dto.RecordCountAndCreateTime;
import org.jooq.Record;
import org.jooq.Result;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public abstract class ABDashboardService implements Serializable {

    public Set<Integer> parseLocalDateToStringSet(LocalDate start, LocalDate end){
        if (start.isAfter(end)) throw new RuntimeException("End Date CAN NOT After Start Date!!");

        Long duratingDays = ChronoUnit.DAYS.between(start, end)+1;

        Set<Integer> durationDates = LongStream.range(0, duratingDays.intValue())
                .mapToObj(i -> i>0 ? start.plusDays(i) : start.minusDays(i)).collect(Collectors.toSet())
                .stream().map(d -> Integer.valueOf(d.format(DateTimeFormatter.ofPattern("yyyyMMdd"))))
                .collect(Collectors.toSet());

        return durationDates;
    }

    public Map<Integer, Long> parseMultiResultCountToMap(List<RecordCountAndCreateTime> result){
        Map<Integer, Long> toMap = new HashMap<>();

        result.stream().forEach(r -> {
            Long count = Optional.ofNullable(r.getCount()).orElse(0L);
            String date = LocalDateTime.parse(r.getCreateTime(),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:ss:mm.S"))
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            toMap.put(Integer.valueOf(date), count);
        });

        return toMap;
    }

    public Long parseMultiResultCountToLong(List<RecordCountAndCreateTime> result, Integer key){

        OptionalLong sum = result.stream().filter(r -> {
            String date = LocalDateTime.parse(r.getCreateTime(),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:ss:mm.S"))
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            return Integer.valueOf(date).equals(key);
        }).mapToLong(r -> Optional.ofNullable(r.getCount()).orElse(0L))
        .reduce((r1, r2) -> BigDecimal.valueOf(r1).add(BigDecimal.valueOf(r2)).longValue());

        return sum.orElse(0L);
    }

    public Long parseMultiResultCountToLong(List<RecordCountAndCreateTime> result){

        OptionalLong sum = result.stream().mapToLong(r -> Optional.ofNullable(r.getCount()).orElse(0L))
                .reduce((r1, r2) -> BigDecimal.valueOf(r1).add(BigDecimal.valueOf(r2)).longValue());

        return sum.orElse(0L);
    }

    public Long parseSingleResultCountToLong(List<RecordCountAndCreateTime> result){
        return result.isEmpty() ? 0 : result.get(0).getCount();
    }

    //多个线程汇聚成一个线程返回所有结果
    public <T extends Object> CompletableFuture<Map<String, T>> sequence(List<FutureObject<T>> futures) {
        //创建一个前置线程
        //CompletableFuture<Void> allDoneFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
        CompletableFuture<Void> allDoneFuture = CompletableFuture.allOf(futures.stream().map(FutureObject::getFuture)
                .collect(Collectors.toList()).toArray(new CompletableFuture[futures.size()]));

        //前置线程立即遍历线程集合,并返回用map转换join等待方法返回的内容
        return allDoneFuture.thenApply(v -> {
            Map<String, T> result = new HashMap<>();
            futures.stream().forEach(fo ->
                    result.put(fo.getName(), fo.getFuture().join()));
            return result;
        });
    }
}
