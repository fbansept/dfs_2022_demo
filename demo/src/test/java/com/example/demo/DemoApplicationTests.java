package com.example.demo;

import com.example.demo.model.Utilisateur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@PropertySource("application-test.properties")
class DemoApplicationTests {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@BeforeEach
	private void initialisation() {

		mvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity())
				.build();
	}

	@Test
	void utilisateurListeRole_initialise() {

		Utilisateur utilisateur = new Utilisateur();

		assertDoesNotThrow(() -> utilisateur.getListeRole().size());

	}

	@Test
	@WithMockUser(username= "doe",roles = {"USER"})
	void simpleUserGetListeMateriel_reponse200ok() throws Exception {
		mvc.perform(get("/liste-materiel")).andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username= "doe",roles = {"USER"})
	void simpleUserGetListeMarque_reponse403Forbidden() throws Exception {
		mvc.perform(get("/admin/liste-marque")).andExpect(status().isForbidden());
	}

	@Test
	@WithMockUser(username= "toto",roles = {"ADMIN"})
	void adminGetListeMarque_reponse200ok() throws Exception {
		mvc.perform(get("/admin/liste-marque")).andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username= "doe",roles = {"USER"})
	void simpleUserGetMaterielId1_materielComporteECRANDELL001() throws Exception {
		mvc.perform(
				get("/materiel/1"))
				.andExpect(
						jsonPath("$.code").value("ECRANDELL001")
				);
	}

	@Test
	@WithMockUser(username= "doe",roles = {"USER"})
	void simpleUserGetListeReservation_utilisateurSansMotDePasse() throws Exception {
		mvc.perform(
				get("/liste-reservation"))
				.andExpect(
						jsonPath("$[0].emprunteur.motDePasse").doesNotExist()
				);
	}
}
