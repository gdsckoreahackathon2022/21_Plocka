package com.jupging.jupgingServer.plogging.controller;

import com.jupging.jupgingServer.auth.jwt.JwtProvider;
import com.jupging.jupgingServer.common.BaseResponse;
import com.jupging.jupgingServer.plogging.dto.*;
import com.jupging.jupgingServer.plogging.service.PloggingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ploggings")
public class PloggingController {

    private final PloggingServiceImpl ploggingService;
    private final JwtProvider jwtProvider;

    private static final String YEAR_MONTH_FORMAT = "yyyy-MM";
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern(YEAR_MONTH_FORMAT);

    /**
     * 플로깅 기록 추가 API
     * [POST] /ploggings
     * 개발자 : 홍민주
     */
    @PostMapping("")
    public BaseResponse<PostPloggingRes> postPlogging(@Valid @ModelAttribute PostPloggingReq postPloggingReq) throws Exception{
        Long userId = jwtProvider.getUserIdx();
        PostPloggingRes postPloggingRes = ploggingService.savePlogging(userId, postPloggingReq);
        return new BaseResponse<>(postPloggingRes);
    }

    /**
     * 플로깅 랭크 조회 API
     * [GET] /ploggings/ranks
     * 개발자 : 홍민주
     */
    @GetMapping("/rank")
    public BaseResponse<GetRankRes> getPloggingRank(@RequestParam String sort) throws Exception{
        Long userId = jwtProvider.getUserIdx();
        String YearMonth = LocalDateTime.now().format(dtf);
        GetRankRes getRankRes = ploggingService.getRank(userId, YearMonth, sort);
        return new BaseResponse<>(getRankRes);
    }

    /**
     * 플로깅 통계 조회 API
     * [GET] /ploggings/{userId}
     * 개발자 : 홍민주
     */
    @GetMapping("/{userId}")
    public BaseResponse<GetPloggingStatRes> getPloggingStat(@PathVariable Long userId) throws Exception{
        Long userId_jwt = jwtProvider.getUserIdx();
        GetPloggingStatRes ploggingStatRes = ploggingService.getPloggingStat(userId);
        return new BaseResponse<>(ploggingStatRes);
    }
}
