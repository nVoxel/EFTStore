package com.voxeldev.eftstore.services.interfaces;

import com.voxeldev.eftstore.models.Avatar;
import com.voxeldev.eftstore.models.User;
import com.voxeldev.eftstore.models.enums.AvatarsSaveErrorCode;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface AvatarsService {
    
    void saveAvatar(Part part, User user, AvatarsSaveResultHandler resultHandler);
    
    void removeAvatar(User user);
    
    void writeAvatarToStorage(InputStream inputStream, String fileName) throws IOException;
    
    void readAvatarFromStorage(Avatar avatar, OutputStream outputStream) throws IOException;
    
    interface AvatarsSaveResultHandler {
        void onAvatarSaveSuccess();
        
        void onAvatarSaveFailed(AvatarsSaveErrorCode errorCode);
    }
}
