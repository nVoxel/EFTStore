package com.voxeldev.eftstore.services.impls;

import com.voxeldev.eftstore.dao.interfaces.AvatarsRepository;
import com.voxeldev.eftstore.dao.interfaces.UsersRepository;
import com.voxeldev.eftstore.models.Avatar;
import com.voxeldev.eftstore.models.User;
import com.voxeldev.eftstore.models.enums.AvatarsSaveErrorCode;
import com.voxeldev.eftstore.services.interfaces.AvatarsService;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

public class AvatarsServiceImpl implements AvatarsService {
    
    private final String avatarsStoragePath;
    private final AvatarsRepository avatarsRepository;
    private final UsersRepository usersRepository;
    
    public AvatarsServiceImpl(String avatarsStoragePath, AvatarsRepository avatarsRepository, UsersRepository usersRepository) {
        this.avatarsStoragePath = avatarsStoragePath;
        this.avatarsRepository = avatarsRepository;
        this.usersRepository = usersRepository;
    }
    
    @Override
    public void saveAvatar(Part part, User user, AvatarsSaveResultHandler resultHandler) {
        long fileSize = part.getSize();
        if (fileSize == 0 || fileSize > 1024 * 1024 * 10) {
            resultHandler.onAvatarSaveFailed(AvatarsSaveErrorCode.WRONG_FILE_SIZE);
            return;
        }
        
        String contentType = part.getContentType();
        if (!contentType.equals("image/png") && !contentType.equals("image/jpeg")) {
            resultHandler.onAvatarSaveFailed(AvatarsSaveErrorCode.UNSUPPORTED_CONTENT_TYPE);
            return;
        }
        
        String[] split = part.getSubmittedFileName().split("\\.");
        if (split.length < 2) {
            resultHandler.onAvatarSaveFailed(AvatarsSaveErrorCode.INVALID_FILE);
            return;
        }
        
        Avatar avatar = Avatar.builder()
                .fileName(UUID.randomUUID().toString())
                .contentType(contentType)
                .size((int) fileSize) // fileSize is always smaller than Integer.MAX_VALUE
                .originalFileName(part.getSubmittedFileName())
                .uploaderId(user.getId())
                .build();
        
        try (InputStream inputStream = part.getInputStream()) {
            writeAvatarToStorage(inputStream, avatar.getFileName());
        } catch (Exception exception) {
            System.out.println("Failed to write avatar to storage");
            exception.printStackTrace();
            resultHandler.onAvatarSaveFailed(AvatarsSaveErrorCode.INVALID_FILE);
            return;
        }
        
        removeAvatar(user);
        
        avatarsRepository.insertAvatar(avatar);
        
        user.setAvatarId(avatar.getId());
        usersRepository.updateUser(user);
        
        resultHandler.onAvatarSaveSuccess();
    }
    
    public void writeAvatarToStorage(InputStream inputStream, String fileName) throws IOException {
        Path path = Paths.get(avatarsStoragePath + fileName);
        
        if (!Files.exists(path.getParent())) {
            Files.createDirectories(path.getParent());
        }
        
        Files.copy(inputStream, path);
    }
    
    public void readAvatarFromStorage(Avatar avatar, OutputStream outputStream) throws IOException {
        Path path = Paths.get(avatarsStoragePath + avatar.getFileName());
        
        Files.copy(path, outputStream);
    }
    
    @Override
    public void removeAvatar(User user) {
        Optional<Integer> avatarId = user.getAvatarId();
        if (!avatarId.isPresent()) {
            return;
        }
        
        Optional<Avatar> avatar = avatarsRepository.getAvatar(avatarId.get());
        if (!avatar.isPresent()) {
            return;
        }
        
        try {
            deleteAvatarFromStorage(avatar.get());
        } catch (Exception exception) {
            System.out.println("Failed to delete avatar from storage");
            exception.printStackTrace();
            return;
        }
        
        avatarsRepository.deleteAvatar(avatar.get().getId());
        
        user.setAvatarId(null);
        usersRepository.updateUser(user);
    }
    
    private void deleteAvatarFromStorage(Avatar avatar) throws IOException {
        Path path = Paths.get(avatarsStoragePath, avatar.getFileName());
        Files.delete(path);
    }
}