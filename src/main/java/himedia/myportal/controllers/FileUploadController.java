package himedia.myportal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import himedia.myportal.services.FileUploadService;

@Controller
public class FileUploadController {

    private final FileUploadService fileUploadService;

    @Autowired
    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    // 업로드 폼 페이지
    @GetMapping({"", "/form"})
    public String form() {
        return "fileupload/form"; // form.jsp 호출
    }

    // 파일 업로드 처리
    @PostMapping("/upload")
    public String uploadFile(MultipartFile file, Model model, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "파일을 선택해주세요.");
            return "redirect:/fileupload/form";
        }

        try {
            // 파일 저장 및 이름 반환
            String filename = fileUploadService.store(file);

            // 결과 페이지로 전달
            model.addAttribute("imageFilename", filename);
            return "result"; // result.jsp 호출
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "업로드 실패: " + e.getMessage());
            return "redirect:/fileupload/form";
        }
    }
}
