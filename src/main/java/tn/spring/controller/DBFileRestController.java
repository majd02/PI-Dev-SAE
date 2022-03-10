package tn.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.spring.entity.DBFile;
import tn.spring.service.DBFileService;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("api/v1/registartion/file")
public class DBFileRestController {
	@Autowired
	private DBFileService DBFileSer ;

	
	@PostMapping
	public List<DBFile> uploadFile (@RequestParam("file") List<MultipartFile> files ) throws IOException
	{
		return DBFileSer.store(files);
	}

	
	
	@PostMapping("/{id}")
	@ResponseBody
	public List<DBFile> uploadFileandaffect ( @RequestParam("file") List<MultipartFile> files, @PathVariable("id") Integer id) throws IOException
	{
		return DBFileSer.storeandaffect(files, id);
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public DBFile getFile ( @PathVariable Long id )
	{
		return DBFileSer.getFileById(id);
	}
	
	@GetMapping("/list")
	@ResponseBody
	public List<DBFile> getFileList()
	{
		return DBFileSer.getFileList();
	}
	
	
}
