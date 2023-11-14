package tn.esprit.rh.achat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.repositories.DetailFournisseurRepository;
import tn.esprit.rh.achat.repositories.FournisseurRepository;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.repositories.SecteurActiviteRepository;
import tn.esprit.rh.achat.services.FournisseurServiceImpl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ContextConfiguration(classes = {FournisseurServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class FournisseurTest {
    @MockBean
    private FournisseurRepository fournisseurRepository;
    @MockBean
    private DetailFournisseurRepository detailFournisseurRepository;
    @MockBean
    private ProduitRepository produitRepository;
    @MockBean
    private SecteurActiviteRepository secteurActiviteRepository;
    @Autowired
    private FournisseurServiceImpl fournisseurServiceImpl;

    @Test
     void shouldRetrieveAllFournisseurs() {
        ArrayList<Fournisseur> fournisseurList = new ArrayList<>();
        when(fournisseurRepository.findAll()).thenReturn(fournisseurList);
        List<Fournisseur> actualRetrieveAllFournisseursResult = fournisseurServiceImpl.retrieveAllFournisseurs();
        assertSame(fournisseurList, actualRetrieveAllFournisseursResult);
        assertTrue(actualRetrieveAllFournisseursResult.isEmpty());
        verify(fournisseurRepository).findAll();
    }
    @Test
     void shouldDeleteFournisseur() {
        doNothing().when(fournisseurRepository).deleteById((Long) any());
        fournisseurServiceImpl.deleteFournisseur(123L);
        verify(fournisseurRepository).deleteById((Long) any());
    }
    @Test
    void shouldAddFournisseur() {
        Fournisseur fournisseur = new Fournisseur();
        when(fournisseurRepository.save(any())).thenReturn(fournisseur);
        Fournisseur result = fournisseurServiceImpl.addFournisseur(fournisseur);
        verify(fournisseurRepository).save(any());
        assertNotNull(result);
        assertNotNull(result.getDetailFournisseur());
        assertNotNull(result.getDetailFournisseur().getDateDebutCollaboration());
    }
    @Test
    void shouldUpdateFournisseur() {
        Fournisseur fournisseur = new Fournisseur();
        when(fournisseurRepository.save(any())).thenReturn(fournisseur);
        Fournisseur result = fournisseurServiceImpl.updateFournisseur(fournisseur);
        verify(fournisseurRepository).save(any());
        assertNotNull(result);
        assertEquals(result, fournisseur);
    }
    @Test
    void shouldRetrieveFournisseur() {
        Long fournisseurId = 456L;
        Fournisseur expectedFournisseur = new Fournisseur();
        when(fournisseurRepository.findById(fournisseurId)).thenReturn(Optional.of(expectedFournisseur));
        Fournisseur result = fournisseurServiceImpl.retrieveFournisseur(fournisseurId);
        verify(fournisseurRepository).findById(fournisseurId);
        assertNotNull(result);
        assertSame(expectedFournisseur, result);
    }

    @Test
    void shouldAssignSecteurActiviteToFournisseur() {
        Long idSecteurActivite = 789L;
        Long idFournisseur = 101L;
        Fournisseur fournisseur = new Fournisseur();
        SecteurActivite secteurActivite = new SecteurActivite();
        when(fournisseurRepository.findById(idFournisseur)).thenReturn(Optional.of(fournisseur));
        when(secteurActiviteRepository.findById(idSecteurActivite)).thenReturn(Optional.of(secteurActivite));
        fournisseurServiceImpl.assignSecteurActiviteToFournisseur(idSecteurActivite, idFournisseur);
        verify(fournisseurRepository).save(fournisseur);
        assertTrue(fournisseur.getSecteurActivites().contains(secteurActivite));
    }
}
