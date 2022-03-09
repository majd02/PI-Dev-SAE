package tn.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.spring.entity.DBFile;
import tn.spring.repository.ArticleRepository;
import tn.spring.repository.DBFileRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DBFileServiceImpl implements DBFileService {
	
	@Autowired
	private DBFileRepository DBFileRep ;
	@Autowired
	private ArticleRepository articleRepository ;

	

	

	@Override
	public List<DBFile> getFileList() {
		List<DBFile> dbFiles = (List<DBFile>) DBFileRep.findAll();
		return dbFiles;
	}

	@Override
	public List<DBFile> store(List<MultipartFile> files) throws IOException {
		List<DBFile> dbFiles = new ArrayList<DBFile>();
		for (MultipartFile multipartFile : files) {
			String fileName = multipartFile.getOriginalFilename();
			DBFile dbFile = new DBFile(UUID.randomUUID().getLeastSignificantBits(), fileName, multipartFile.getContentType(), multipartFile.getBytes());
			
		dbFiles.add(dbFile);
		}
		
		DBFileRep.saveAll(dbFiles);
		return dbFiles ;
	}

	@Override
	public List<DBFile> storeandaffect(List<MultipartFile> files, Integer id) throws IOException {
		List<DBFile> dbFiles = new ArrayList<DBFile>();
		for (MultipartFile multipartFile : files) {
			String fileName = multipartFile.getOriginalFilename();
			DBFile dbFile = new DBFile(UUID.randomUUID().getLeastSignificantBits(), fileName, multipartFile.getContentType(), multipartFile.getBytes());
			dbFile.setArticle(articleRepository.findById(id).get());
	        dbFiles.add(dbFile);
		}
		
		DBFileRep.saveAll(dbFiles);
		return dbFiles ;
		
	}

	@Override
	public DBFile getFileById(Long id) {
	DBFile d =	DBFileRep.findById(id).get();
		return d;
	}
	
	

}
