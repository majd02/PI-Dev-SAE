package tn.spring.service;

import org.springframework.web.multipart.MultipartFile;
import tn.spring.entity.DBFile;

import java.io.IOException;
import java.util.List;

public interface DBFileService {

	 List<DBFile> storeandaffect (List<MultipartFile> files , Integer id ) throws IOException;
	 List<DBFile> store (List<MultipartFile> files ) throws IOException ;
	 DBFile getFileById( Long id ) ;
	 List<DBFile> getFileList();
}
