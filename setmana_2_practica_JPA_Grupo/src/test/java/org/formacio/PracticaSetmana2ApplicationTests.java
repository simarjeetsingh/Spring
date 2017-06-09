package org.formacio;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.formacio.repositori.AgendaService;
import org.formacio.repositori.GrupoService;
import org.junit.After;
import org.junit.Before;
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
	
	@Autowired
	private GrupoService grupos;
	
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
	
	@Before
	public void setup_base_datos() throws Exception{
		agenda.init();
	}
	
	
	@After
	public void release_base_datos() throws Exception{
		agenda.deleteAll();
		grupos.deleteAll();
	}
	

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
	public void test_nou_contacte_grupo_existente() throws Exception {
		mockMvc.perform(post("/afegir")
				           .param("id", "jos")
				           .param("nom", "Josep")
				           .param("telefon", "971-555326")
				           .param("grupo", "uno")
				     )
				.andExpect(status().isOk());
		
		// comprova que s'ha inserit
		mockMvc.perform(get("/contacte/jos").accept(MediaType.APPLICATION_XML))
						.andExpect(status().isOk());
		
		// aprovecho un el controlador que he creado
		// /name?id=jos que devuelve el nombre
		// del contacto
		mockMvc.perform(get("/name?id=jos"))
				.andExpect(status().isOk())
				.andExpect(content().string("Josep"));
		
		mockMvc.perform(get("/nombre"))
				.andExpect(status().isOk())
				.andExpect(content().string("4"));
	}
	
	@Test
	public void test_nou_contacte_grupo_nuevo() throws Exception {
		mockMvc.perform(post("/afegir")
				           .param("id", "bas")
				           .param("nom", "Barrabas")
				           .param("telefon", "971-112233")
				           .param("grupo", "dos")
				     )
				.andExpect(status().isOk());
		
		// comprova que s'ha inserit
		mockMvc.perform(get("/contacte/bas").accept(MediaType.APPLICATION_XML))
						.andExpect(status().isOk());
		
		// aprovecho un el controlador que he creado
		// /name?id=jos que devuelve el nombre
		// del contacto
		mockMvc.perform(get("/name?id=bas"))
				.andExpect(status().isOk())
				.andExpect(content().string("Barrabas"));
		
		mockMvc.perform(get("/grupo").param("grupo", "dos"))
				.andExpect(status().isOk())
				.andExpect(content().string("Barrabas"));		
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
				           .param("grupo", "uno")
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
		           .param("id", "ant")
		           .param("nom", "Toni")
		           .param("telefon", "971-555123")
		           .param("grupo", "uno")
		     )
		.andExpect(status().isOk());

		// comprova que s'ha inserit
		mockMvc.perform(get("/contacte/ant"))
		.andExpect(status().isOk());
		
		// Creo un nuevo controlador /name?id=xx
		// que devuelva el contacto que busco
		
		mockMvc.perform(get("/name?id=ant"))
				.andExpect(status().isOk())
				.andExpect(content().string("Toni"));
		
		mockMvc.perform(get("/nombre"))
				.andExpect(status().isOk())
				.andExpect(content().string("3"));
	}
	
	/**
	 * Crea un nuevo controlador /listado
	 * que devuelva un String  con los nombres
	 * de todos los contactos de la base de datos.
	 *  
	 */
	
	@Test
	public void test_listar_personas() throws Exception{
		mockMvc.perform(get("/listado"))
				.andExpect(status().isOk())
				.andExpect(content().string("Antoni Joana Lina"));
	}
	
	/**
	 * Crea un controlador /grupos
	 * que devuelva un listado con los nombres
	 * de los grupos 
	 * 
	 */
	
	@Test
	public void test_listado_grupos() throws Exception {
		mockMvc.perform(get("/grupos"))
				.andExpect(status().isOk())
				.andExpect(content().string("uno"));
		
		/* 
		 * Añado un nuevo contacto en un nuevo grupo: dos
		 */
		mockMvc.perform(post("/afegir")
		           .param("id", "bas")
		           .param("nom", "Barrabas")
		           .param("telefon", "971-112233")
		           .param("grupo", "dos")
		     )
		.andExpect(status().isOk());

		// comprova que s'ha inserit
		// Este contacto se elimina de la tabla Personas al finalizar
		// el caso test.
		mockMvc.perform(get("/contacte/bas").accept(MediaType.APPLICATION_XML))
						.andExpect(status().isOk());
		
		// aprovecho un el controlador que he creado
		// /name?id=jos que devuelve el nombre
		// del contacto
		mockMvc.perform(get("/name?id=bas"))
				.andExpect(status().isOk())
				.andExpect(content().string("Barrabas"));
		
		mockMvc.perform(get("/grupo").param("grupo", "dos"))
				.andExpect(status().isOk())
				.andExpect(content().string("Barrabas"));
		
		/* 
		 * Compruebo que se ha creado el grupo dos
		 */
		mockMvc.perform(get("/grupos"))
			.andExpect(status().isOk())
			.andExpect(content().string("uno dos"));			
	}
	
	/*
	 * Crea un controlador /grupo
	 * que devuelva un String con las personas del grupo "uno"
	 * 
	 */
	
	@Test
	public void test_listado_personas_grupo() throws Exception {
		mockMvc.perform(get("/grupo").param("grupo", "uno"))
				.andExpect(status().isOk())
				.andExpect(content().string("Antoni Joana Lina"));
		/*
		 * Barrabas no está en la tabla Persona porque al
		 * finalizar el caso test se elimina.
		 */
		mockMvc.perform(get("/grupo").param("grupo", "dos"))
				.andExpect(status().isOk())
				.andExpect(content().string(""));
	}
	
	/*
	 * Cambiar a una persona de grupo (actualizarlo)	 * 
	 */	
	
	@Test
	public void test_actualizar_contacto_grupo() throws Exception {
				
		mockMvc.perform(post("/actualizar")
		           .param("id", "ant")
		           .param("nom", "Toni")
		           .param("telefon", "971-555123")
		           .param("grupo", "dos")
		     )
		.andExpect(status().isOk());

		// comprova que s'ha inserit
		mockMvc.perform(get("/contacte/ant"))
				.andExpect(status().isOk());
		
		// Creo un nuevo controlador /name?id=xx
		// que devuelva el contacto que busco
		
		mockMvc.perform(get("/name?id=ant"))
				.andExpect(status().isOk())
				.andExpect(content().string("Toni"));
		
		mockMvc.perform(get("/nombre"))
				.andExpect(status().isOk())
				.andExpect(content().string("3"));
		
		/*
		mockMvc.perform(get("/grupo").param("grupo", "uno"))
				.andExpect(status().isOk())
				.andExpect(content().string("Joana Lina"));*/
		
		mockMvc.perform(get("/grupo").param("grupo", "dos"))
				.andExpect(status().isOk())
				.andExpect(content().string("Toni"));		
	}

	
}