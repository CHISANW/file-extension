package project.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.dto.FileExtensionDto;
import project.service.FileExtensionService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
public class FileExtensionViewController {

    private final FileExtensionService fileExtensionService;

    @GetMapping("/")
    public String viewFileExtensions(Model model) {
        List<FileExtensionDto> defaultExtensions = fileExtensionService.getAllDefault();
        model.addAttribute("defaultExtensions", defaultExtensions);
        List<FileExtensionDto> allExtensions = fileExtensionService.getAllFileExtensions();
        List<FileExtensionDto> customExtensions = allExtensions.stream()
                .filter(ext -> !ext.isDefault())
                .collect(Collectors.toList());
        model.addAttribute("customExtensions", customExtensions);
        model.addAttribute("customExtensionCount", customExtensions.size());
        return "index";
    }
}