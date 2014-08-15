package com.hashedin.artcollective.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.hashedin.artcollective.entity.Artist;

public interface ArtistRepository extends CrudRepository<Artist, Long> {

	List<Artist> findByLastName(String lastName); 
}
