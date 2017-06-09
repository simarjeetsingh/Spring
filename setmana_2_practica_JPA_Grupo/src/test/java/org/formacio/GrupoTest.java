package org.formacio;

import static org.junit.Assert.*;

import org.formacio.repositori.GrupoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GrupoTest {
	
	@Autowired
	private GrupoService grupoService;

	@Test
	public void testInit() {
		assertEquals(3L, grupoService.size());
	}

}
