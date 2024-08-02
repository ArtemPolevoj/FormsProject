package forms.application.service;

public interface ImageService {
    String uploadImage(String resultFileName, byte[] file);

    byte[] downloadImage(String resultFileName);

    void removeImage(String objectName);
}
