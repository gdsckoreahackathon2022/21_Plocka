package com.jupging.jupgingServer.trashcan.service;

import com.jupging.jupgingServer.common.BaseException;
import com.jupging.jupgingServer.trashcan.domain.Trashcan;
import com.jupging.jupgingServer.trashcan.dto.GetTrashcanRes;
import com.jupging.jupgingServer.trashcan.dto.PostTrashcanReq;
import com.jupging.jupgingServer.trashcan.dto.PostTrashcanRes;
import com.jupging.jupgingServer.trashcan.repository.TrashcanRepository;
import com.jupging.jupgingServer.user.domain.User;
import com.jupging.jupgingServer.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.jupging.jupgingServer.common.BaseResponseStatus.DATABASE_ERROR;
import static com.jupging.jupgingServer.common.BaseResponseStatus.DUPLICATE_KEY_ERROR;

@Service
@Transactional
@RequiredArgsConstructor
public class TrashcanServiceImpl implements TrashcanService{

    private final TrashcanRepository trashcanRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<GetTrashcanRes> findTrashcans(){
        List<Trashcan> trashcanList = trashcanRepository.findAll();
        List<GetTrashcanRes> trashcanDtoList = new ArrayList<>();
        for (Trashcan trashcan : trashcanList)
            trashcanDtoList.add(new GetTrashcanRes(trashcan));
        return trashcanDtoList;
    }

    @Override
    public PostTrashcanRes saveTrashcan(Long userId, PostTrashcanReq postTrashcanReq) throws BaseException{
        Optional<Trashcan> existTrashcan = trashcanRepository.findByLatitudeAndLongitude(postTrashcanReq.getLatitude(), postTrashcanReq.getLongitude());
        Optional<User> user = userRepository.findById(userId);

        if (existTrashcan.isPresent())
            throw new BaseException(DUPLICATE_KEY_ERROR);
        if (user.isEmpty())
            throw new BaseException(DATABASE_ERROR);

        Trashcan newTrashcan = createTrashcan(user.get(), postTrashcanReq);
        Trashcan savedTrashcan = trashcanRepository.save(newTrashcan);
        return new PostTrashcanRes(savedTrashcan.getId());
    }

    @Override
    public Trashcan createTrashcan(User user, PostTrashcanReq postTrashcanReq) {
        return  Trashcan.builder()
                        .user(user)
                        .latitude(postTrashcanReq.getLatitude())
                        .longitude(postTrashcanReq.getLongitude())
                        .build();
    }
}
