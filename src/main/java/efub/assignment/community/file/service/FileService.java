package efub.assignment.community.file.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    void upload(MultipartFile file,String fileName);
    void delete(String fileName);
}
