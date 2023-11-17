package tn.esprit.rh.achat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.entities.DetailFacture;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.repositories.DetailFactureRepository;
import tn.esprit.rh.achat.repositories.FactureRepository;
import tn.esprit.rh.achat.repositories.FournisseurRepository;
import tn.esprit.rh.achat.repositories.OperateurRepository;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.services.FactureServiceImpl;
import tn.esprit.rh.achat.services.ReglementServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {FactureServiceImpl.class})
@ExtendWith(SpringExtension.class)
class FactureTest {

    @MockBean
    private FactureRepository factureRepository;
	@MockBean
	private OperateurRepository operateurRepository;
	@MockBean
	private DetailFactureRepository detailFactureRepository;
	@MockBean
	private FournisseurRepository fournisseurRepository;
	@MockBean
	private ProduitRepository produitRepository;
    @MockBean
    private ReglementServiceImpl reglementService;

    @Autowired
    private FactureServiceImpl factureServiceImpl;

    @Test
    void testRetrieveAllFactures() {
        ArrayList<Facture> factureList = new ArrayList<>();
        when(factureRepository.findAll()).thenReturn(factureList);

        // Act
        List<Facture> actualRetrieveAllFacturesResult = factureServiceImpl.retrieveAllFactures();

        // Assert
        assertSame(factureList, actualRetrieveAllFacturesResult);
        assertTrue(actualRetrieveAllFacturesResult.isEmpty());
        verify(factureRepository).findAll();
    }

    @Test
    void testAddFacture() {
        // Arrange
        Facture factureToAdd = new Facture(100.00f, 230.25f);
        when(factureRepository.save(factureToAdd)).thenReturn(factureToAdd);

        // Act
        Facture addedFacture = factureServiceImpl.addFacture(factureToAdd);

        // Assert
        assertNotNull(addedFacture);
        assertEquals(factureToAdd, addedFacture);
        verify(factureRepository).save(factureToAdd);
    }

    @Test
    public void testCancelFacture() {
        Long factureId = 1L; // ID fictif d'une facture

        // Appel de la méthode à tester
        factureServiceImpl.cancelFacture(factureId);

        // Vérifications
        verify(factureRepository, times(1)).updateFacture(factureId);
    }
   
}