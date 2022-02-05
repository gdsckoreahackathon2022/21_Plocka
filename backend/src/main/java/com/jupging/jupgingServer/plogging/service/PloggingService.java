package com.jupging.jupgingServer.plogging.service;

import com.jupging.jupgingServer.plogging.domain.Plogging;
import com.jupging.jupgingServer.plogging.dto.*;
import com.jupging.jupgingServer.user.domain.User;

public interface PloggingService {

    public PostPloggingRes savePlogging(Long userId, PostPloggingReq postPloggingReq) throws Exception;
    public Plogging createPlogging(User runner, String routeImage, String trashImage, PostPloggingReq postPloggingReq) throws Exception;
    public GetRankRes getRank(Long userId, String YearMonth, String sort) throws Exception;
    public GetPloggingStatRes getPloggingStat(Long userId);
}
