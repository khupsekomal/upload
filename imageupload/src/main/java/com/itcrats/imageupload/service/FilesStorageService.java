package com.itcrats.imageupload.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;


public interface FilesStorageService {
//  public void init();
//
//  public void save(MultipartFile file);
//
//  public Resource load(String filename);
//
//  public void deleteAll();
//
//  public Stream<Path> loadAll();
  public String storeFile(MultipartFile file) throws Exception ;
  public Resource loadFileAsResource(String fileName) ;
}
