package tn.esprit.rh.achat;


import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.repositories.FournisseurRepository;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.services.FournisseurServiceImpl;
import tn.esprit.rh.achat.services.ProduitServiceImpl;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.List;

@ContextConfiguration(classes = {FournisseurServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class FournisseurTest {

    @MockBean
    private FournisseurRepository fournisseurRepository;

    @Autowired
    private FournisseurServiceImpl fournisseurServiceImpl;

    @Test
    void testRetrieveAllFournisseurs() {
        ArrayList<Fournisseur> fournisseurList = new ArrayList<>();
        when(fournisseurRepository.findAll()).thenReturn(fournisseurList);
        List<Fournisseur> actualRetrieveAllFournisseursResult = fournisseurServiceImpl.retrieveAllFournisseurs();
        assertSame(fournisseurList, actualRetrieveAllFournisseursResult);
        assertTrue(actualRetrieveAllFournisseursResult.isEmpty());
        verify(fournisseurRepository).findAll();
    }
}
