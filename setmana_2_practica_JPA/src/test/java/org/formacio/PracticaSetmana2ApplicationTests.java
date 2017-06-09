package org.formacio;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.formacio.repositori.AgendaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PracticaSetmana2ApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private AgendaService agenda;
	
	/*
	 * Actualiza el codigo de AgendaServiceController 
	 * y de AgendaService para que hagan uso de
	 * los metodos de la clase CrudRepository de JPA Spring,
	 * de manera que se sigan pasando estos casos test.
	 * 
	 * Has de indicar que la clase Persona es una entidad
	 * de la base de datos e indicar que su primary key 
	 * es la propiedad clau.
	 * 
	 */

	/**
	 * Creau un controlador al package org.formacio.mvc que respongui 
	 * a les peticions que s'especifiquen
	 * als seguents tests. El controlador ha de fer us de AgendaService 
	 */
	
	/**
	 * a la peticio /nombre ha de respondre amb el nombre de contactes 
	 * (obtingut de AgendaService) 
	 */
	@Test
	public void test_obte_nombre_contactes() throws Exception {
		mockMvc.perform(get("/nombre"))
				.andExpect(status().isOk())
				.andExpect(content().string("3"));
	}

	/**
	 * A la peticio /telefon?id=xx
	 * ha de respondre amb el telefon del contacte amb id passat com a paràmetre (xx)
	 */
	@Test
	public void test_obte_telefon() throws Exception {
		mockMvc.perform(get("/telefon?id=lin"))
				.andExpect(status().isOk())
				.andExpect(content().string("971-555888"));
	}
	
	/**
	 * A la peticio /contacte/id
	 * ha de respondre amb l'objecte Persona corresponent al identificador id 
	 * (forma part de la url)
	 *  
	 *  Per aquest test basta que s'accepti JSON
	 */
	
	
	@Test
	public void test_contacte() throws Exception {
		mockMvc.perform(get("/contacte/lin").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
	}

	/**
	 * Customitzau la conversio de Persona a json per a que 
	 * la propietat nom de Persona surti com a contacte a json
	 */
	@Test
	public void test_contacte_customitzat() throws Exception {
		mockMvc.perform(get("/contacte/lin").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(content().json("{clau : 'lin', contacte: 'Lina', telefon='971-555888'}"));
	}

	/**
	 * Modifiqueu el que sigui necessari per a que accepti XML
	 */
	@Test
	public void test_contacte_xml() throws Exception {
		mockMvc.perform(get("/contacte/lin").accept(MediaType.APPLICATION_XML))
				.andExpect(status().isOk())
				.andExpect(content().xml("<persona><clau>lin</clau><nom>Lina</nom><telefon>971-555888</telefon></persona>"));
	}

	@Test
	public void test_key_no_existe(){
		assertNull(agenda.recupera("dom"));
	}
	
	/**
	 * Feis, sense espatllar res del que s'ha fet anteriorment, 
	 * que la si l'id no existeix, retorni un 404
	 */
	@Test
	public void test_error_404() throws Exception {
		mockMvc.perform(get("/contacte/dom").accept(MediaType.APPLICATION_XML))
				.andExpect(status().is(404));
	}

	/**
	 * A la peticio POST /afegir
	 * amb parametres id, nom i telefon
	 * se ha de crear una nova entrada al repositori 
	 * 
	 */
	@Test
	public void test_nou_contacte() throws Exception {
		// localhots:8080
		mockMvc.perform(post("/afegir")
				           .param("id", "jos")
				           .param("nom", "Josep")
				           .param("telefon", "971-555326")
				     )
				.andExpect(status().isOk());
		
		// comprova que s'ha inserit
		mockMvc.perform(get("/contacte/jos").accept(MediaType.APPLICATION_XML))
		.andExpect(status().isOk());
	}


	/**
	 * Modifiqueu el codi per a que /afegir nomes es pugui cridar per POST
	 * 
	 */
	@Test
	public void test_nou_contacte_get_falla() throws Exception {
		mockMvc.perform(get("/afegir")
				           .param("id", "jos")
				           .param("nom", "Josep")
				           .param("telefon", "971-555326")
				     )
				.andExpect(status().is4xxClientError());
	}
	
	/* 
	 * Crea un nuevo controlador llamado /actualizar
	 * que actualice el nombre de un contacto 
	 * existente en la base de datos. 
	 * 
	 */

	@Test
	public void test_actualizar_contacto() throws Exception {
		mockMvc.perform(post("/actualizar")
		           .param("id", "jos")
		           .param("nom", "Josefa")
		           .param("telefon", "971-112233")
		     )
		.andExpect(status().isOk());

		// comprova que s'ha inserit
		mockMvc.perform(get("/contacte/jos"))
		.andExpect(status().isOk());
		
		mockMvc.perform(get("/name?id=jos"))
		.andExpect(status().isOk())
		.andExpect(content().string("Josefa"));
	}
	
	/*
	 * Crea un nuevo controlador /listado
	 * que devuelva un String  con los nombres
	 * de todos los contactos de la base de datos.
	 *  
	 */
	
	@Test
	public void test_listar_personas() throws Exception{
		mockMvc.perform(get("/listado"))
		.andExpect(status().isOk())
		.andExpect(content().string("Antoni Joana Lina Josefa"));
	}
}