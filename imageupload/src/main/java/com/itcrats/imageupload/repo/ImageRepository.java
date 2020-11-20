package com.itcrats.imageupload.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itcrats.imageupload.entity.Image;

public interface ImageRepository extends JpaRepository<Image,Integer> {


}
