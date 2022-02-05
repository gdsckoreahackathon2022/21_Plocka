package com.jupging.jupgingServer.plogging.service;

import com.jupging.jupgingServer.common.BaseException;
import com.jupging.jupgingServer.common.GCSuploader;
import com.jupging.jupgingServer.plogging.domain.Plogging;
import com.jupging.jupgingServer.plogging.dto.*;
import com.jupging.jupgingServer.plogging.repository.PloggingRepository;
import com.jupging.jupgingServer.user.domain.User;
import com.jupging.jupgingServer.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.jupging.jupgingServer.common.BaseResponseStatus.EMPTY_USER;

@Service
@Transactional
@RequiredArgsConstructor
public class PloggingServiceImpl implements PloggingService{

    private final PloggingRepository ploggingRepository;
    private final UserRepository userRepository;
    private final GCSuploader GCSuploader;

    private static final String DIR_PLOGGING = "plogging";
    private static final String DIR_TRASH = "trash";

    @Override
    public PostPloggingRes savePlogging(Long userId, PostPloggingReq postPloggingReq) throws Exception {
        User user = userRepository.findById(userId).orElseThrow();
        String routeImage = GCSuploader.uploadFile(postPloggingReq.getRouteImage(), DIR_PLOGGING);
        String trashImage = GCSuploader.uploadFile(postPloggingReq.getTrashImage(), DIR_TRASH);
        Plogging newPlogging = createPlogging(user, routeImage, trashImage, postPloggingReq);
        Plogging savedPlogging = ploggingRepository.save(newPlogging);
        return new PostPloggingRes(savedPlogging.getId());
    }

    @Override
    public Plogging createPlogging(User runner, String routeImage, String trashImage, PostPloggingReq postPloggingReq) throws Exception {
        return Plogging.builder().routeImage(routeImage).trashImage(trashImage)
                .calorie(postPloggingReq.getCalorie()).distance(postPloggingReq.getDistance())
                .avgPaceMin(postPloggingReq.getAvgPaceMin()).avgPaceSec(postPloggingReq.getAvgPaceSec())
                .runTimeHour(postPloggingReq.getRunTimeHour()).runTimeMin(postPloggingReq.getRunTimeMin())
                .runTimeSec(postPloggingReq.getRunTimeSec()).user(runner).build();
    }

    @Override
    public GetRankRes getRank(Long userId, String YearMonth, String sort) throws Exception{
        User user = userRepository.findById(userId).orElseThrow();
        GetRankRes getRankRes = new GetRankRes();
        List<RankInfo> ploggingDtoList = new ArrayList<>();
        getRankRes.setRankList(ploggingDtoList);
        // myRank 초기화
        getRankRes.setMyRank(new RankInfo(-1, user.getId(), user.getNickName(), user.getPicture()));

        int userIdx = -1;
        List<RankInfoProjection> ploggingList = ploggingRepository.findPloggingRankByCreatedDateWithPagination(YearMonth, PageRequest.of(0, 50, Sort.Direction.DESC, sort));
        for (int idx = 0; idx < ploggingList.size(); idx++){
            // 1등부터 차례대로 돌면서 ploggingDtoList에 추가
            RankInfoProjection projection = ploggingList.get(idx);
            Optional<User> optionalRunner = userRepository.findById(projection.getUserId());

            // plogging 주인 User를 찾을 수 없음
            if (optionalRunner.isEmpty())
                throw new BaseException(EMPTY_USER);

            User runner = optionalRunner.get();
            // ploggingDtoList에 추가하기 위한 RankInfo 생성
            RankInfo rank = new RankInfo(idx + 1,runner.getId(), runner.getNickName(), runner.getPicture(), projection.getCnt().intValue(), projection.getDistance());
            ploggingDtoList.add(rank);

            // 만약 자신의 plogging rank인 경우 저장
            if (user.getId() == projection.getUserId())
                userIdx = idx;
        }
        if (userIdx != -1) {
            // ploggingDtoList에 자신의 plogging rank가 존재하는 경우 myRank 갱신
            RankInfoProjection projection = ploggingList.get(userIdx);
            getRankRes.setMyRank(userIdx + 1, projection.getCnt().intValue(),  projection.getDistance() );
        }
        return getRankRes;
    }

    @Override
    public GetPloggingStatRes getPloggingStat(Long userId){
        GetPloggingStatRes ploggingStatRes = new GetPloggingStatRes();
        List<Plogging> ploggingList = ploggingRepository.findByUserId(userId);
        List<PloggingInfo> ploggingInfoList = new ArrayList<>();
        for (Plogging plogging: ploggingList){
            ploggingStatRes.sumTotalInfo(plogging.getDistance(), plogging.getCalorie(), plogging.getRunTimeMin(), plogging.getRunTimeSec());
            ploggingInfoList.add(new PloggingInfo(plogging));
        }
        ploggingStatRes.setPloggingList(ploggingInfoList);
        return ploggingStatRes;
    }
}
