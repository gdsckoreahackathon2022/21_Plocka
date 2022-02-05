package com.jupging.jupgingServer.trashcan.controller;

import com.jupging.jupgingServer.auth.jwt.JwtProvider;
import com.jupging.jupgingServer.common.BaseException;
import com.jupging.jupgingServer.common.BaseResponse;
import com.jupging.jupgingServer.trashcan.dto.GetTrashcanRes;
import com.jupging.jupgingServer.trashcan.dto.PostTrashcanReq;
import com.jupging.jupgingServer.trashcan.dto.PostTrashcanRes;
import com.jupging.jupgingServer.trashcan.service.TrashcanServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/trashcan-locations")
public class TrashcanController {

    private final TrashcanServiceImpl trashcanService;
    private final JwtProvider jwtProvider;

    /**
     * 쓰레기통 조회 API
     * [GET] /trashcan-locations
     * 개발자 : 홍민주
     */
    @GetMapping("")
    public BaseResponse<List<GetTrashcanRes>> getTrashcan() throws Exception{
        Long userId = jwtProvider.getUserIdx();
        List<GetTrashcanRes> trashcanList = trashcanService.findTrashcans();
        return new BaseResponse<>(trashcanList);
    }

    /**
     * 쓰레기통 추가 API
     * [POST] /trashcan-locations
     * 개발자 : 홍민주
     */
    @PostMapping("")
    public BaseResponse<PostTrashcanRes> postTrashcan(@Valid @RequestBody PostTrashcanReq postTrashcanReq) throws BaseException {
        Long userId = jwtProvider.getUserIdx();
        PostTrashcanRes postTrashcanRes = trashcanService.saveTrashcan(userId, postTrashcanReq);
        return new BaseResponse<>(postTrashcanRes);
    }
}
