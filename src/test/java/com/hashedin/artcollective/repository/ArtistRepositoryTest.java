package com.hashedin.artcollective.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hashedin.artcollective.Main;
import com.hashedin.artcollective.entity.Artist;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class) 
public class ArtistRepositoryTest {

	@Autowired
	private ArtistRepository repository;
	
	@Test
	public void testFindByLastName() {
		List<Artist> artists = repository.findByLastName("Jain");
		assertEquals(artists.size(), 2);
		assertEquals(artists.get(0).getFirstName(), "Shilpa");
		assertEquals(artists.get(1).getFirstName(), "Anurag");
		
	}

}
