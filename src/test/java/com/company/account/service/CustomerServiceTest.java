package com.company.account.service;

import com.company.account.dto.CustomerDto;
import com.company.account.dto.converter.CustomerDtoConverter;
import com.company.account.exception.CustomerNotFoundException;
import com.company.account.model.Customer;
import com.company.account.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CustomerServiceTest {
    private CustomerService service;
    private CustomerRepository customerRepository;
    private CustomerDtoConverter converter;

    @BeforeEach
    public void setUp(){
     customerRepository = Mockito.mock(CustomerRepository.class);
     converter = Mockito.mock(CustomerDtoConverter.class);
     service = new CustomerService(customerRepository,converter);
    }

    @Test
    public void testFindByCustomerId_whenCustomerIdExists_shouldReturnCustomer(){

        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class,
                ()-> service.findCustomerById("id"));


    }

    @Test
    public void testFindByCustomerId_whenCustomerIdExists_shouldReturnCustomerNotFoundException(){
        Customer customer =new Customer("id","name","surname", Set.of());
        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.of(customer));
        Customer result = service.findCustomerById("id");
        assertEquals(result,customer);
    }
    @Test
    public void testGetByCustomerId_whenCustomerIdExists_shouldReturnCustomer(){
        Customer customer = new Customer("id","name","surname",Set.of());

        CustomerDto customerDto = new CustomerDto("id","name","surname",Set.of());

        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.of(customer));
        Mockito.when(converter.convertToCustomerDto(customer)).thenReturn(customerDto);
        CustomerDto result =service.getCustomerById("id");

        assertEquals(result,customerDto);
    }
    @Test
    public void testGetCustomerById_whenCustomerIdExists_shouldReturnCustomerNotFoundException(){

        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class,
                ()->service.getCustomerById("id"));
        Mockito.verifyNoInteractions(converter);
    }


}
