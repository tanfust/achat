package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.OperateurRepository;
import tn.esprit.rh.achat.services.OperateurServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OperateurTestStatique {
    @InjectMocks
    private OperateurServiceImpl operateurService;

    @Mock
    private OperateurRepository operateurRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRetrieveAllOperateurs() {
        // Mocking data
        Operateur operateur1 = new Operateur();
        Operateur operateur2 = new Operateur(/* your constructor parameters here */);
        List<Operateur> mockOperateurs = Arrays.asList(operateur1, operateur2);

        // Mocking repository behavior
        when(operateurRepository.findAll()).thenReturn(mockOperateurs);

        // Test the service method
        List<Operateur> result = operateurService.retrieveAllOperateurs();

        // Assertions
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testAddOperateur() {
        // Mocking data
        Operateur operateur = new Operateur();

        // Test the service method
        Operateur result = operateurService.addOperateur(operateur);

        // Assertions
        assertNotNull(result);
        // Add additional assertions as needed

        // Verify that save method was called with the correct argument
        verify(operateurRepository).save(operateur);
    }

    @Test
    void testDeleteOperateur() {
        // Test data
        Long operateurId = 1L;

        // Test the service method
        operateurService.deleteOperateur(operateurId);

        // Verify that the deleteById method was called with the correct argument
        verify(operateurRepository).deleteById(operateurId);
    }

    @Test
    void testUpdateOperateur() {
        // Mocking data
        Operateur operateur = new Operateur(/* your constructor parameters here */);

        // Test the service method
        Operateur result = operateurService.updateOperateur(operateur);

        // Assertions
        assertNotNull(result);
        // Add additional assertions as needed

        // Verify that save method was called with the correct argument
        verify(operateurRepository).save(operateur);
    }

    @Test
    void testRetrieveOperateur() {
        // Mocking data
        Long operateurId = 1L;
        Operateur mockOperateur = new Operateur(/* your constructor parameters here */);

        // Mocking repository behavior
        when(operateurRepository.findById(anyLong())).thenReturn(Optional.of(mockOperateur));

        // Test the service method
        Operateur result = operateurService.retrieveOperateur(operateurId);

        // Assertions
        assertNotNull(result);
    }
}