package himedia.myportal.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.github.cdimascio.dotenv.Dotenv;

@Service
public class FileUploadService {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadService.class);
    private static final Dotenv dotenv = Dotenv.load(); // .env 파일 로드
    private final String savePath;

    public FileUploadService() {
        // OS에 따라 저장 경로 동적 설정
        String osName = System.getProperty("os.name").toLowerCase();

        if (osName.contains("mac")) {
            savePath = dotenv.get("UPLOAD_DIR_MAC");
        } else if (osName.contains("win")) {
            savePath = dotenv.get("UPLOAD_DIR_WINDOWS");
        } else if (osName.contains("nux")) {
            savePath = dotenv.get("UPLOAD_DIR_LINUX");
        } else {
            throw new IllegalArgumentException("Unsupported OS: " + osName);
        }

        // 디렉토리 생성
        File dir = new File(savePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public String store(MultipartFile multipartFile) {
        String saveFilename = "";

        try {
            String originalFilename = multipartFile.getOriginalFilename();
            String extName = originalFilename.substring(originalFilename.lastIndexOf("."));
            saveFilename = getSaveFilename(extName);

            logger.debug("########## " + saveFilename);

            // 멀티파트 파일을 저장
            writeFile(multipartFile, saveFilename);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        return saveFilename; // 새로 생성된 파일 이름
    }

    private void writeFile(MultipartFile multipartFile, String saveFilename) throws IOException {
        byte[] fileData = multipartFile.getBytes();
        FileOutputStream fos = new FileOutputStream(savePath + File.separator + saveFilename);
        fos.write(fileData);
        fos.close();
    }

    private String getSaveFilename(String ext) {
        Calendar cal = Calendar.getInstance();
        return String.valueOf(cal.getTimeInMillis() / 1000) + ext.toLowerCase();
    }
}
