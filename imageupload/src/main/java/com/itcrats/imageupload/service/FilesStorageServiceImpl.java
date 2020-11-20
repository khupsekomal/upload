package com.itcrats.imageupload.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.itcrats.imageupload.util.FileStorageProperties;
import com.itcrats.imageupload.exception.MyFileNotFoundException;
import com.itcrats.imageupload.entity.Image;
import com.itcrats.imageupload.repo.ImageRepository;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {

	@Autowired
	FileStorageProperties fileStorageProperties;

	@Autowired
	ImageRepository imageRepository;

	private final Path fileStorageLocation;

	@Autowired
	public FilesStorageServiceImpl(FileStorageProperties fileStorageProperties) throws Exception {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new Exception("Could not create the directory where the uploaded files will be stored.", ex);
		}
	}

//  @Override
//  public void init() {
//    try {
//      Files.createDirectory(root);
//    } catch (IOException e) {
//      throw new RuntimeException("Could not initialize folder for upload!");
//    }
//  }
//
//  @Override
//  public void save(MultipartFile file) {
//    try {
//      Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
//    } catch (Exception e) {
//      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
//    }
//  }
//
//  @Override
//  public Resource load(String filename) {
//    try {
//      Path file = root.resolve(filename);
//      Resource resource = new UrlResource(file.toUri());
//
//      if (resource.exists() || resource.isReadable()) {
//        return resource;
//      } else {
//        throw new RuntimeException("Could not read the file!");
//      }
//    } catch (MalformedURLException e) {
//      throw new RuntimeException("Error: " + e.getMessage());
//    }
//  }
//
//  @Override
//  public void deleteAll() {
//    FileSystemUtils.deleteRecursively(root.toFile());
//  }
//
//  @Override
//  public Stream<Path> loadAll() {
//    try {
//      return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
//    } catch (IOException e) {
//      throw new RuntimeException("Could not load the files!");
//    }
//  }

	public String storeFile(MultipartFile file) throws Exception {
		Image image = new Image();
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new Exception("Sorry! Filename contains invalid path sequence " + fileName);
			}

			// Copy file to the target location (Replacing existing file with the same name)
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			
			image.setTitle(fileName);
			image.setImageDir(targetLocation.toString());
			image.setStatus(1);
			image.setPosition(1);
			
			imageRepository.save(image);
			
			return fileName;
		} catch (IOException ex) {
			throw new Exception("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

	public Resource loadFileAsResource(String fileName) {
		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new MyFileNotFoundException("File not found " + fileName);
			}
		} catch (MalformedURLException ex) {
			throw new MyFileNotFoundException("File not found " + fileName, ex);
		}
	}

}