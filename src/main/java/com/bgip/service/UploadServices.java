package com.bgip.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bgip.constants.StatusCodes;
import com.bgip.dao.ProfileDAO;
import com.bgip.dao.UploadDAO;
import com.bgip.exception.BgipException;
import com.bgip.model.upload.FolderResponse;
import com.bgip.model.user.Profile;
import com.bgip.model.ResponseBean;
import com.bgip.model.upload.FilesBean;
import com.bgip.model.upload.FolderRequest;
import com.bgip.model.upload.FolderBean;

@Service
public class UploadServices {

	private static final Logger LOGGER = LoggerFactory.getLogger(UploadServices.class);

	@Autowired
	UploadDAO uploadDao;
	
	@Autowired
	ProfileDAO profileDAO;
	


	public FolderRequest uploadFolder(FolderRequest folder, String loginUser) throws Exception {
		LOGGER.info("BGIP  uploadedFiles  method call :: ");
		FolderRequest result = new FolderRequest();
		try {
			result = uploadDao.uploadedFiles(folder, loginUser);
		} catch (BgipException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public FolderBean createEmptyFolder(FolderBean emptyFolder, String loginUser) throws Exception {

		return uploadDao.createEmptyFolder(emptyFolder, loginUser);
	}

	public FolderResponse getFolderDetails(String folderId, String loginUser) throws Exception {

		return uploadDao.getFolderDetails(folderId, loginUser);
	}
	
	public FilesBean getFilbyId(String folderId, String loginUser) throws Exception {

		return uploadDao.getFileById(folderId, loginUser);
	}

	public FolderResponse getAllFiles(String loginUser) throws Exception {
		LOGGER.info("BGIP  getAllFiles  method call :: ");
		return uploadDao.getAllFiles(loginUser);
	}


	public ResponseBean makefavoriteFolder( String folderId, String loginUser) throws Exception{

	return uploadDao.makeFavoriteFolder(folderId, loginUser);
}
	
	public ResponseBean makefavoriteFile( String fileId, String loginUser) throws Exception{
		ResponseBean res = null;
		try {
			res = uploadDao.makeFavoriteFile(fileId, loginUser);
		} catch (BgipException e) {
			e.printStackTrace();
			throw new BgipException(StatusCodes.NOT_FOUND, " File ID Not Found ");
		}
		return res;
	}
	
	public FolderResponse getfavoriteFolders( String loginUser) throws Exception{
		System.out.println("folder List 2: "+System.currentTimeMillis());
		return uploadDao.getFavoriteFolders(loginUser);
	}
	
	public List<FilesBean> getfavoriteFiles( String loginUser) throws Exception{
		System.out.println("files List 2: "+System.currentTimeMillis());
		return uploadDao.getFavoriteFiles(loginUser);
	}
	
	
	
	
	
	public void downloadFiles(FolderBean files) throws Exception {
		LOGGER.info("BGIP  downloadFiles  method call :: ");
	}


	public void getAllFilesByFolderId(FolderBean files) throws Exception {
		LOGGER.info("BGIP  getAllFilesByFolderId  method call :: ");
	}

	public void getFiles(FolderBean files) throws Exception {
		LOGGER.info("BGIP  getFiles  method call :: ");
	}
	
	
	
	public ResponseBean profileStorageTypeCreate( Profile profile, String loginUser) throws Exception{
		profile.setUserName(loginUser);
		return profileDAO.profileStorageTypeCreate(profile);
		
	}
	
	public Profile getProfile(String user) throws Exception{
		
		return profileDAO.getProfile(user);
	}
	
	
	
}
