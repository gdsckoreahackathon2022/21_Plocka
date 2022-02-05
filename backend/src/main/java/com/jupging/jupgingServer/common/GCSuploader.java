package com.jupging.jupgingServer.common;


import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import static com.jupging.jupgingServer.common.BaseResponseStatus.FILE_EMPTY;

@Service
@RequiredArgsConstructor
public class GCSuploader {

    private final Storage storage;

    private static final String BUCKET_NAME = "jupging-bucket";
    private static final String DATE_FORMAT = "yyyy-MM-dd-HH:mm:ss";
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public String uploadFile(MultipartFile fileStream, String dirName) throws IOException, ServletException, BaseException, GoogleJsonResponseException {

        // file valid 체크
        checkFileExtension(fileStream.getOriginalFilename());

        // 중복방지를 위해 시간 추가
        String todayPath = LocalDateTime.now().format(dtf);
        String fileName = dirName + "/" + todayPath + fileStream.getOriginalFilename();

        // MultipartFile -> File로 변환
        File file = convertMultiPartToFile(fileStream);

        // GCS에 업로드
        BlobInfo blobInfo = storage.create(
                        BlobInfo.newBuilder(BUCKET_NAME, fileName)
                                .setAcl(new ArrayList<>(Arrays.asList(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER))))
                                .build(),
                        new FileInputStream(file));

        // 변환될 때 로컬에 저장된 file 삭제
        file.delete();
        return blobInfo.getMediaLink();
    }


    private File convertMultiPartToFile(MultipartFile file) throws IOException
    {
        File convFile = new File( "image/" + file.getOriginalFilename() );
        FileOutputStream fos = new FileOutputStream( convFile );
        fos.write( file.getBytes() );
        fos.close();
        return convFile;
    }


    private boolean checkFileExtension(String fileName) throws ServletException, BaseException {
        if (fileName != null && !fileName.isEmpty() && fileName.contains(".")) {
            String[] allowedExt = {".jpg", ".jpeg", ".png", ".gif", ".PNG", ".JPG", ".JPEG", ".GIF"};
            for (String ext : allowedExt) {
                if (fileName.endsWith(ext)) {
                    return true;
                }
            }
            throw new ServletException("file must be an image");
        }
        throw new BaseException(FILE_EMPTY);
    }
}

